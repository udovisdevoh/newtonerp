package test.servletTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author r3hallejo
 * 
 *         All right this is not working yet... Stupid question but how to show
 *         this in a browser? Taken from :
 *         http://www.commentcamarche.net/contents/servlets/servcarac.php3
 */
public class PremiereServlet extends HttpServlet
{
    public void init()
    {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException
    {

	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<HTML>");
	out.println("<HEAD><TITLE> Titre </TITLE></HEAD>");
	out.println("<BODY>");
	out.println("Ma première servlet");
	out.println("</BODY>");
	out.println("</HTML>");
	out.close();
    }
}
