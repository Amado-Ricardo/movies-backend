import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;

@WebServlet(name="PracticeServlet", urlPatterns = "/practice-Servlet")
public class PracticeServlet extends HttpServlet {

    protected void doGet(ServletRequest request, ServletResponse response){
        response.setContentType("text/html");

        try{
            PrintWriter out = response.getWriter();
            System.out.println("This is a test");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
