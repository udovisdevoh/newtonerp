package unitTest;

import junit.framework.Test;
import junit.framework.TestSuite;
import unitTest.module.EntityTest;
import unitTest.orm.OrmTest;
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

	// module
	suite.addTestSuite(EntityTest.class);

	// orm
	suite.addTestSuite(OrmTest.class);

	// serveur
	suite.addTestSuite(ServletTest.class);
	// $JUnit-END$
	return suite;
    }

}
