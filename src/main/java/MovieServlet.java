import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name="MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            Movie movie = new Movie(2, "King Kong", "1942", "Harry Carey", "Fay Ray", "8675309", "there aint one", "cheap", "gorilla on skyscrapper");

            String movieString = new Gson().toJson(movie);

            out.println(movieString);

        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

}
