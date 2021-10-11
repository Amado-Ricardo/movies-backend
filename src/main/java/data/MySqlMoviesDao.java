package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;
    private HashMap<Integer, Movie> moviesMap = getMoviesMap();

    private HashMap<Integer, Movie> getMoviesMap() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("movies.json"));
            Type type = TypeToken.getParameterized(ArrayList.class, Movie.class).getType();
            return getMovieMap(new Gson().fromJson(reader, type));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private HashMap<Integer, Movie> getMovieMap(List<Movie> movies) {
        HashMap<Integer, Movie> movieHashMap = new HashMap<>();
        for (Movie movie : movies) {
            movieHashMap.put(movie.getId(), movie);
        }
        return movieHashMap;
     }



    public MySqlMoviesDao(Config config) {

        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Movie> all() throws SQLException {

        //
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM movies");

        List<Movie> movies = new ArrayList<>();

        while (rs.next()) {
            movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("year"),
                    rs.getString("director"),
                    rs.getString("actors"),
                    rs.getString("rating"),
                    rs.getString("poster"),
                    rs.getString("genre"),
                    rs.getString("plot")
            ));
        }

        return movies;

//        return moviesMap != null ? Arrays.asList(moviesMap.values()) : null;
    }

    @Override
    public Movie findOne(int id) {
        return moviesMap != null ? moviesMap.get(id) : null;
    }

    @Override
    public void insert(Movie movie) throws SQLException {

        int newId = moviesMap.keySet().size() + 1;
        movie.setId(newId);
        moviesMap.put(newId, movie);

        // Build sql template
        String sql = "INSERT INTO movies " +
                "SET title = ?, rating = ?"
//                " genre = ?, actors = ?, director = ?, " +
//                "plot = ?, year = ?, poster = ? "
                + "WHERE id = ? ";

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getRating());
        statement.setInt(3, movie.getId());
//        statement.setString(4, movie.getGenre());
//        statement.setString(5, movie.getActors());
//        statement.setString(6, movie.getDirector());
//        statement.setString(7, movie.getPlot());
//        statement.setInt(8, movie.getYear());
//        statement.setString(9, movie.getPoster());


        statement.executeUpdate();
    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

        moviesMap = getMovieMap(Arrays.asList(movies));


        // Build sql template
        StringBuilder sql = new StringBuilder("INSERT INTO movies (" +
                "title, rating, id, genre, actors, director, plot, year, poster) " +
                "VALUES ");


        // Add a interpolation template for each element in movies list
        sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?), ".repeat(movies.length));

        // Create a new String and take off the last comma and whitespace
        sql = new StringBuilder(sql.substring(0, sql.length() - 2));

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql.toString());

        // Add each movie to the prepared statement using the index of each sql param: '?'
        // This is done by using a counter
        // You could use a for loop to do this as well and use its incrementor
        int counter = 0;
        for (Movie movie : movies) {
            statement.setString((counter * 9) + 1, movie.getTitle());
            statement.setString((counter * 9) + 2, movie.getRating());
            statement.setInt((counter * 9) + 3, movie.getId());
            statement.setString((counter * 9) + 4, movie.getGenre());
            statement.setString((counter * 9) + 5, movie.getActors());
            statement.setString((counter * 9) + 6, movie.getDirector());
            statement.setString((counter * 9) + 7, movie.getPlot());
            statement.setInt((counter * 9) + 8, movie.getYear());
            statement.setString((counter * 9) + 9, movie.getPoster());
            counter++;
        }
        statement.executeUpdate();
    }

    @Override
    public void update(Movie movie) throws SQLException {

        if (moviesMap != null){
            moviesMap.replace(movie.getId(), movie);
        }

        // Build sql template
        String sql = "UPDATE movies " +
                "SET title = ?, rating = ?, genre = ?, actors = ?, director = ?, " +
                "plot = ?, year = ?, poster = ? " +
                "WHERE id = ? ";

        // Use the sql string to create a prepared statement
        PreparedStatement statement = connection.prepareStatement(sql);


        statement.setString(1, movie.getTitle());
        statement.setString(2, movie.getRating());
        statement.setString(3, movie.getGenre());
        statement.setString(4, movie.getActors());
        statement.setString(5, movie.getDirector());
        statement.setString(6, movie.getPlot());
        statement.setInt(7, movie.getYear());
        statement.setString(8, movie.getPoster());
        statement.setInt(9, movie.getId());

        statement.executeUpdate();

    }

    @Override
    public void destroy(int id) throws SQLException {

        if (moviesMap != null){
            moviesMap.remove(id);
        }

        String sql = "DELETE FROM movies WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.execute();

    }

}