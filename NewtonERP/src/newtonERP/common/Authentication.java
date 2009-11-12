package newtonERP.common;

/**
 * @author Guillaume Lacasse Cette classe sert à authentifier un user de manière
 *         hardcodée OU par session HTTP Le but de cette classe est d'être gérée
 *         par le code qui manipulera les sessions HTTP
 */
public class Authentication
{
    private static String currentUserName;

    /**
     * @return Utilisateur présentement loggé par session HTTP
     */
    public static String getCurrentUserName()
    {
	return currentUserName;
    }

    /**
     * @param currentUserName Utilisateur présentement loggé par session HTTP
     */
    public static void setCurrentUserName(String currentUserName)
    {
	if (currentUserName == null)
	{
	    Authentication.currentUserName = "unLogedUser";
	}
	else
	{
	    Authentication.currentUserName = currentUserName;
	}
    }
}
