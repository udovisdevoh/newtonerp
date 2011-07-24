package newtonERP.common; 
 // TODO: clean up that file

import modules.userRightModule.entityDefinitions.User;

/**
 * Cette classe sert à authentifier un user de manière hardcodée OU par session HTTP Le but de cette classe est d'être
 * gérée par le code qui manipulera les sessions HTTP
 * 
 * @author Guillaume Lacasse
 */
public class Authentication {
	private static String currentUserName;

	/**
	 * @return Utilisateur présentement loggé par session HTTP
	 */
	public static String getCurrentUserName() {
		return currentUserName;
	}

	/**
	 * @return Entité représentant l'utilisateur courant
	 */
	public static User getCurrentUser() {
		User user = new User();
		user.setData("name", getCurrentUserName());
		user = (User) user.get().get(0);
		return user;
	}

	/**
	 * @param currentUserName Utilisateur présentement loggé par session HTTP
	 */
	public static void setCurrentUserName(String currentUserName) {
		if(currentUserName == null){
			Authentication.currentUserName = "unLogedUser";
		}else{
			Authentication.currentUserName = currentUserName;
		}
	}
}
