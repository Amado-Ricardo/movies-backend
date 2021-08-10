import com.google.gson.Gson;
import data.DaoFactory;
import data.Movie;
import data.MoviesDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {
            //get object which can write to the response
            PrintWriter out = response.getWriter();
            //eventually get movies from the database
//            Movie movie = new Movie(2, "King kong", "1942"
//                    , "Harry Carrey", "Elsa banks"
//                    , "8675309", "there aint one"
//                    , "cheap", "Gorilla on skyscraper");

            MoviesDao moviesDao = DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL);


            //turn into a json string
            String moviesString = new Gson().toJson(moviesDao.all());

            //inject into response
            out.println(moviesString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // get the stream of characters from the request (eventually becomes our movie)
            BufferedReader reader = request.getReader();

            // turn that stream into an array of movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);


            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).insert(movies[0]);

            // sout out properties of each movie, so we know the objects made it into our code
            for (Movie movie : movies) {
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getDirector());
                System.out.println(movie.getActors());
                System.out.println(movie.getRating());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("************************");
            }

        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }

        // write a meaningful response body and set the status code to 200
        out.println(new Gson().toJson("{message: \"Movies POST was successful\"}"));
        response.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);
//            DaoFactory.getMoviesDao(DaoFactory.ImplType.IN_MEMORY).update(movie);

            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);

            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).insertAll(movies);

            System.out.println(movie.getId());
            System.out.println(movie.getTitle());
            System.out.println(movie.getDirector());
            System.out.println(movie.getActors());
            System.out.println(movie.getRating());
            System.out.println(movie.getPlot());
            System.out.println(movie.getPoster());
            System.out.println("************************");

        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movies POST was successful\"}"));
        response.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");

        PrintWriter out = null;


        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            int id = new Gson().fromJson(reader, int.class);

            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).destroy(id);

            System.out.println("The movie id to delete: " + id);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        out.println(new Gson().toJson("{message: \"Movie DELETE was successful\"}"));
    }
}
