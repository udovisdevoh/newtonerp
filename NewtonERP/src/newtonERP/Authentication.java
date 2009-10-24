package newtonERP;

/**
 * @author Guillaume Lacass Cette classe sert à authentifier un user de manière
 *         hardcodée OU par session HTTP Le but de cette classe est d'être gérée
 *         par le code qui manipulera les sessions HTTP
 */
public class Authentication
{
    private static String currentUserName = "admin";

    /**
     * @return Utilisateur présentement loggé par session HTTP ou par valeur
     *         hard-codée (si code de session HTTP pas fait)
     */
    public static String getCurrentUserName()
    {
	return currentUserName;
    }
}
