package unitTest;

import junit.framework.Test;
import junit.framework.TestSuite;
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

	// orm

	// serveur
	suite.addTestSuite(ServletTest.class);
	// $JUnit-END$
	return suite;
    }

}
