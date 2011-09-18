package newtonERP.orm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({OrmTest.class, newtonERP.orm.fields.AllTests.class})
public class AllTests {
	// Junit test suit, there is nothing to do...
}
