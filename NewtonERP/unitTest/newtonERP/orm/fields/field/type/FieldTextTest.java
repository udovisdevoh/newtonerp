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
public class FieldTextTest {

	/** The field bool. */
	private FieldText fieldText;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldText = new FieldText();
	}

	/**
	 * Tear down.
	 * 
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		// nothing to do
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString1() {
		fieldText.setDataString("134");
		assertEquals("134", fieldText.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringEmpty() {
		fieldText.setDataString("");
		assertEquals("", fieldText.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringMinusDiDi() {
		fieldText.setDataString("didi pop");
		assertEquals("didi pop", fieldText.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringEmpty() {
		fieldText.setData("");
		assertEquals("", fieldText.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringMinusZaza() {
		fieldText.setData("zaza");
		assertEquals("zaza", fieldText.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldText.setDefaultValue();
		assertEquals("", fieldText.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldString() {
		FieldText testFieldString = new FieldText();
		assertNotNull(testFieldString);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldStringString() {
		FieldText testFieldString = new FieldText("hey");
		assertNotNull(testFieldString);
		assertEquals("hey", testFieldString.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldText.setData("youhou");
		assertEquals("youhou", fieldText.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldText.setData("zapata");
		assertEquals("zapata", fieldText.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldText.setData("dodeli pop");
		fieldText.reset();
		assertNull(fieldText.getData());
	}

}
