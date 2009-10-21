package unitTest;

import junit.framework.Test;
import junit.framework.TestSuite;
import unitTest.abstractModule.EntityTest;
import unitTest.module.UserRightModuleTest;
import unitTest.serveur.ServletTest;

/**
 * @author JoCloutier
 * 
 */
public class AllTests
{

    /**
     * @return .
     */
    public static Test suite()
    {
	TestSuite suite = new TestSuite("Test for unitTest");
	// $JUnit-BEGIN$
	// racine
	suite.addTestSuite(ListModuleTest.class);

	// abstractModule
	suite.addTestSuite(EntityTest.class);

	// module
	suite.addTestSuite(UserRightModuleTest.class);

	// orm
	// suite.addTestSuite(OrmTest.class);

	// serveur
	suite.addTestSuite(ServletTest.class);
	// $JUnit-END$
	return suite;
    }

}
