package newtonERP.serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.Module;
import newtonERP.viewers.Viewer;
import newtonERP.viewers.firstStep.ErrorViewer;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.ServletHandler;

/**
 * la gestion du serveur, request manager
 * @author JoCloutier
 */
public class Servlet extends ServletHandler
{
    private Pattern urlPattern = Pattern
	    .compile("(/(\\w*)(/(\\w*)(/(\\w*))?)?)?");
    private CommandRouteur cmdRouter = new CommandRouteur();
    ResourceHandler resourceHandler = new ResourceHandler();

    /**
     * constructeur
     */
    public Servlet()
    {
	resourceHandler.setResourceBase(".");
    }

    public void handle(String target, HttpServletRequest request,
	    HttpServletResponse response, int dispatch) throws IOException,
	    ServletException
    {
	System.err.println(target);
	request.setCharacterEncoding(Viewer.getEncoding());
	if (target.matches("/file/.*")) // si on veut un fichier
	{
	    resourceHandler.handle(target, request, response, dispatch);
	}// ou une page normale de l'aplication
	else
	{
	    HttpSession session = request.getSession(true);

	    Authentication.setCurrentUserName((String) session
		    .getAttribute("SESSION_UserName"));
	    String pageContent = "";
	    try
	    {

		AbstractEntity viewEntity = urlToAction(target, request);

		pageContent += Viewer.getHtmlCode(viewEntity,
			buildModuleName(target), buildActionName(target));

	    } catch (Exception e)
	    {
		e.printStackTrace();

		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		pageContent = ErrorViewer.getErrorPage(stacktrace);

	    }

	    // on formate la reponse
	    response.setContentType("text/html");
	    response.setStatus(HttpServletResponse.SC_OK);

	    response.getWriter().println(pageContent);
	    ((Request) request).setHandled(true);

	    session.setAttribute("SESSION_UserName", Authentication
		    .getCurrentUserName());
	}
    }

    /**
     * @param target url d'apelle
     * @param request la request
     * @return le Viewable
     * @throws Exception remonte
     */
    @SuppressWarnings("unchecked")
    public AbstractEntity urlToAction(String target, HttpServletRequest request)
	    throws Exception
    {
	String moduleName;
	String actionName;
	String entityName;
	Hashtable<String, String> parameter = new Hashtable<String, String>();

	actionName = buildActionName(target);
	if (actionName == null || actionName.trim().length() == 0)
	    actionName = "default";

	moduleName = buildModuleName(target);
	if (moduleName == null || moduleName.trim().length() == 0)
	{
	    moduleName = "UserRightModule";
	    actionName = "Login";// override default car login peut être ou ne
	    // pas être default
	}

	entityName = buildEntityName(target);
	// on trouve les parametres pour les mettre dans le hashtable

	Enumeration e = request.getParameterNames();
	while (e.hasMoreElements())
	{
	    String paramName = (String) e.nextElement();
	    parameter.put(paramName, request.getParameter(paramName));
	}

	return cmdRouter.routeCommand(moduleName, actionName, entityName,
		parameter);
    }

    private String buildEntityName(String target)
    {
	// Légèrement moins optimisé ici, mais comme le pattern regex est déjà
	// compilé, ça change à peu près rien. Améliore la clarté et permet de
	// partager l'information sur les noms de module, action et entité
	// présentement utilisés
	Matcher urlMatch = urlPattern.matcher(target);
	urlMatch.matches();
	return urlMatch.group(6);
    }

    private String buildActionName(String target)
    {
	// Légèrement moins optimisé ici, mais comme le pattern regex est déjà
	// compilé, ça change à peu près rien. Améliore la clarté et permet de
	// partager l'information sur les noms de module, action et entité
	// présentement utilisés
	Matcher urlMatch = urlPattern.matcher(target);
	urlMatch.matches();
	return urlMatch.group(4);
    }

    private String buildModuleName(String target)
    {
	// Légèrement moins optimisé ici, mais comme le pattern regex est déjà
	// compilé, ça change à peu près rien. Améliore la clarté et permet de
	// partager l'information sur les noms de module, action et entité
	// présentement utilisés
	Matcher urlMatch = urlPattern.matcher(target);
	urlMatch.matches();
	return urlMatch.group(2);
    }

    /**
     * @param module module a lier
     * @return le lien relatif vers les ressource demander
     */
    static public String makeLink(Module module)
    {
	return "/" + module.getSystemName();
    }

    /**
     * @param module module a lier
     * @param action action a lier
     * @return le lien relatif vers les ressource demander
     */
    static public String makeLink(Module module, AbstractAction action)
    {
	String link = "/" + module.getSystemName();
	if (action instanceof BaseAction)
	{
	    String actionName = ((BaseAction) action).getSystemName();
	    String entityName = ((BaseAction) action).getEntity()
		    .getSystemName();
	    link += "/" + actionName + "/" + entityName;
	}
	else
	{
	    link += "/" + action.getSystemName();
	}

	return link;
    }

    /**
     * @param module module a lier
     * @param action action a lier
     * @return le lien relatif vers les ressource demander
     * @throws Exception remonte
     */
    static public String makeLink(AbstractAction action) throws Exception
    {
	if (action instanceof BaseAction)
	{
	    return makeLink(((BaseAction) action).getEntity()
		    .getCurrentModule(), action);
	}
	return makeLink(action.getOwnedByModule(), action);
    }
}
