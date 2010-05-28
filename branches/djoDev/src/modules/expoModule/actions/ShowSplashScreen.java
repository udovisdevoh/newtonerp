package modules.expoModule.actions;

import java.util.Hashtable;

import newtonERP.common.ActionLink;
import newtonERP.common.ListModule;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.generalEntity.ImageFile;
import newtonERP.module.generalEntity.SplashScreen;

/**
 * SplashScreen
 * @author NewtonERP code generator - Guillaume Lacasse
 */
public class ShowSplashScreen extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public ShowSplashScreen()
    {
	setDetailedDescription("Retourner à l'accueil");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity sourceEntity,
	    Hashtable<String, String> parameters) throws Exception
    {
	SplashScreen splashScreen = new SplashScreen();

	splashScreen.addImage(new ImageFile("Expo 67",
		"/file/images/expo67SplashScreen.png"));

	splashScreen.addActionLink(new ActionLink("Inscrivez-vous", ListModule
		.getModule("ExpoModule").getAction("Subscribe")));

	splashScreen.addActionLink(new ActionLink("Loguez-vous", ListModule
		.getModule("UserRightModule").getAction("Login")));

	return splashScreen;
    }
}