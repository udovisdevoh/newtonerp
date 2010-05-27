package newtonERP.common;

import modules.userRightModule.entityDefinitions.User;

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
     * @return Entité représentant l'utilisateur courant
     * @throws Exception si ça fail
     */
    public static User getCurrentUser() throws Exception
    {
	User user = new User();
	user.setData("name", getCurrentUserName());
	user = (User) user.get().get(0);
	return user;
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
