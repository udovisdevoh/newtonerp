package modules.userRightModule.actions;

import newtonERP.common.Authentication;
import newtonERP.module.generalEntity.StaticTextEntity;

/**
 * action permettant de ce delogguer
 * @author GLacasse
 */
public class Logout extends newtonERP.module.AbstractAction
{
	/**
	 * Constructeur
	 */
	public Logout()
	{
		setDetailedDescription("fermer votre session de manière sécuritaire");
	}

	@Override
	public newtonERP.module.AbstractEntity doAction(
			newtonERP.module.AbstractEntity entity,
			Hashtable<String, String> parameters)
	{
		Authentication.setCurrentUserName(null);

		return new StaticTextEntity("Au revoir!");
	}

}
