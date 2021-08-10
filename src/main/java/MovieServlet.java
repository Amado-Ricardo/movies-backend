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

@WebServlet(name="MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");

        PrintWriter out = null;

        try {

            out = response.getWriter();

//            Movie movie = new Movie(2, "King Kong", "1942", "Harry Carey", "Fay Ray", "8675309", "there aint one", "cheap", "gorilla on skyscrapper");

            MoviesDao moviesDao = DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL);

            String moviesString = new Gson().toJson(moviesDao.all());

            out.println(moviesString);

        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        PrintWriter out = null;

        try{

            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).insertAll(movies);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \"Movies POST was successful\""));
        response.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        PrintWriter out = null;

        try{

            out = response.getWriter();

            BufferedReader reader = request.getReader();

//            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).update(movie);


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

        out.println(new Gson().toJson("{message: \"Movie UPDATE was successful\"}"));
        response.setStatus(200);
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        PrintWriter out = null;

        try{

            out = response.getWriter();

            BufferedReader reader = request.getReader();

            int id = new Gson().fromJson(reader, int.class);

            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).destroy(id);

            System.out.println("The movie id to delete: " + id);


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        out.println(new Gson().toJson("{message: \"Movies DELETE was successful\""));
        response.setStatus(200);

    }


}
