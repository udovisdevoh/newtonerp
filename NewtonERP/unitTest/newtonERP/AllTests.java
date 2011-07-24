package newtonERP;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({newtonERP.orm.AllTests.class, newtonERP.serveur.AllTests.class})
public class AllTests {

}
