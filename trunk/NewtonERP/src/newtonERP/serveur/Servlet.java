package newtonERP.serveur;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.PromptViewer;
import newtonERP.viewers.viewables.PromptViewable;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 * @author JoCloutier
 * 
 *         la gestion du serveur, request manager
 * 
 */
public class Servlet extends AbstractHandler
{

    CommandRouteur cmdRouter = new CommandRouteur();

    public void handle(String target, HttpServletRequest request,
	    HttpServletResponse response, int dispatch) throws IOException,
	    ServletException
    {
	AbstractEntity viewEntity = urlToAction(target, request);

	// on formatte la reponse
	response.setContentType("text/html");
	response.setStatus(HttpServletResponse.SC_OK);

	String pageContent = "";
	try
	{
	    // TODO: remove shortcut... devrai apellé le main viewer plutot
	    pageContent += PromptViewer
		    .getHtmlCode((PromptViewable) viewEntity);
	} catch (Exception e) // todo le main viewer ne devrai pas renvoyer
	// d'Exception stp
	{
	    System.out.println("Viewer exception");
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	response.getWriter().println(pageContent);
	((Request) request).setHandled(true);
    }

    /**
     * @param target url d'apelle
     * @param request la request
     * @return le Viewable
     */
    @SuppressWarnings("unchecked")
    public AbstractEntity urlToAction(String target, HttpServletRequest request)
    {
	String moduleName;
	String action;
	Hashtable<String, String> parameter = new Hashtable<String, String>();

	// on trouve le moduleName et l'actionName dans l'url
	Matcher urlMatch = Pattern.compile("(/(\\w*)(/(\\w*))?)?").matcher(
		target);
	urlMatch.matches();

	moduleName = urlMatch.group(2);
	if (moduleName == null)
	    moduleName = "Home";

	action = urlMatch.group(4);
	if (action == null)
	    action = "default";

	System.out.println(moduleName);
	System.out.println(action);
	try
	{
	    // on trouve les parametres pour les mettre dans le hashtable

	    Enumeration e = request.getParameterNames();
	    while (e.hasMoreElements())
	    {
		String paramName = (String) e.nextElement();
		parameter.put(paramName, request.getParameter(paramName));
	    }
	} catch (NullPointerException e)
	{
	    parameter = null;
	}

	return cmdRouter.routeCommand(moduleName, action, parameter);
    }
}
