package modules.home;

import modules.testModule.actions.TestAction;
import modules.testModule.entityDefinitions.TestEntity;
import newtonERP.module.Module;

/**
 * @author cloutierJo module servant a généré les information de la page
 *         d'aceuil
 */
public class Home extends Module
{
    /**
     * constructeur
     */
    public Home()
    {
	super();
	// TODO: le home module est a faire en entié, si quelqu'un se porte
	// volontaire (au moin pour analiser se qu'il doit contenir, je n'en sui
	// pas certain présentement), est-ce qu'elle contien les méthode de
	// login ou pas?
	addDefinitinEntity(new TestEntity());

	addAction(new TestAction(), true);

    }
}