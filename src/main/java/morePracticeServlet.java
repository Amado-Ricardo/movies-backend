import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "moviePractice", urlPatterns = "/moviePractice")
public class morePracticeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            Movie movie = new Movie(2,"The lion king","1994"
                    ,"Steven Seagal", "Simba","4561234"
                    ,"there's one out there","cheap"
                    , "kid becomes king");

            String movieString = new Gson().toJson(movie);
            out.println(movieString);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request) throws IOException {

        BufferedReader bufferedReader = request.getReader();
        try {

            Movie movie = new Movie(2,"The lion king","1994"
                    ,"Steven Seagal", "Simba","4561234"
                    ,"there's one out there","cheap"
                    , "kid becomes king");

            String movieString = new Gson().toJson(movie);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}

