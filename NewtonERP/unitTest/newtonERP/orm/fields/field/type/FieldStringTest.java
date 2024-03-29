/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldStringTest {

	/** The field bool. */
	private FieldString fieldString;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		fieldString = new FieldString();
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		// nothing to do
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString1() {
		fieldString.setDataString("134");
		assertEquals("134", fieldString.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringEmpty() {
		fieldString.setDataString("");
		assertEquals("", fieldString.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringMinusDiDi() {
		fieldString.setDataString("didi pop");
		assertEquals("didi pop", fieldString.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringEmpty() {
		fieldString.setData("");
		assertEquals("", fieldString.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringMinusZaza() {
		fieldString.setData("zaza");
		assertEquals("zaza", fieldString.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldString.setDefaultValue();
		assertEquals("", fieldString.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldString() {
		FieldString testFieldString = new FieldString();
		assertNotNull(testFieldString);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldStringString() {
		FieldString testFieldString = new FieldString("hey");
		assertNotNull(testFieldString);
		assertEquals("hey", testFieldString.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldString.setData("youhou");
		assertEquals("youhou", fieldString.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldString.setData("zapata");
		assertEquals("zapata", fieldString.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldString.setData("dodeli pop");
		fieldString.reset();
		assertNull(fieldString.getData());
	}

}
