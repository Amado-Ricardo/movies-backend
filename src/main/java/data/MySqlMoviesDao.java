package data;

import com.mysql.cj.jdbc.Driver;

import java.sql.*; // this is importing everything on the SQL imports
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao {

    private Connection connection = null;

    public MySqlMoviesDao(config.Config config) {

        try {
            DriverManager.registerDriver(new Driver());

            this.connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

    }

    @Override
    public List<Movie> all() throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM movies");

        List<Movie> movies = new ArrayList<>();

        while (rs.next()) {
            movies.add(new Movie(
                    rs.getInt("id"),
                    rs.getString("rating"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("actor"),
                    rs.getString("director"),
                    rs.getString("plot"),
                    rs.getString("year"),
                    rs.getString("poster")
                    // *** if it doesn't work then look into the order of which you have it ***
            ));
        }
        return movies;
    }

    @Override
    public Movie findOne(int id) {
        // TODO: Get one movie by id
        return null;
    }

    @Override
    public void insert(Movie movie) throws SQLException {
        // TODO: Insert one movie

        String sql = "INSERT movies" + "SET title = ?, rating = ?, Id = ?, genre = ?, actor = ?, director = ?, plot = ?, year = ?, poster = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString( + 1, movie.getTitle());
        statement.setString( + 2, movie.getRating());
        statement.setInt( + 3, movie.getId());
        statement.setString( + 4, movie.getGenre());
        statement.setString( + 5, movie.getActors());
        statement.setString( + 6, movie.getDirector());
        statement.setString( + 7, movie.getPlot());
        statement.setString( + 8, movie.getYear());
        statement.setString( + 9, movie.getPoster());


        statement.executeUpdate();

    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {

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
            statement.setString((counter * 9) + 8, movie.getYear());
            statement.setString((counter * 9) + 9, movie.getPoster());
            counter++;
        }
        statement.executeUpdate();
    }


    @Override
    public void update(Movie movie) throws SQLException {
        //TODO: Update a movie here
        String sql = "UPDATE movies" + "SET title = ?, rating = ?, Id = ?, genre = ?, actor = ?, director = ?, plot = ?, year = ?, poster = ?";

                PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString( + 1, movie.getTitle());
        statement.setString( + 2, movie.getRating());
        statement.setInt( + 3, movie.getId());
        statement.setString( + 4, movie.getGenre());
        statement.setString( + 5, movie.getActors());
        statement.setString( + 6, movie.getDirector());
        statement.setString( + 7, movie.getPlot());
        statement.setString( + 8, movie.getYear());
        statement.setString( + 9, movie.getPoster());


        statement.executeUpdate();


    }

    @Override
    public void destroy(int id) throws SQLException {

        String sql = "DELETE FROM movies " + "WHERE ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.execute();

    }
}
