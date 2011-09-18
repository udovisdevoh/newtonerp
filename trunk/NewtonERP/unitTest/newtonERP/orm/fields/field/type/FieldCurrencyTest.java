/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldCurrencyTest {

	/** The field bool. */
	private FieldCurrency fieldCurrency;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		fieldCurrency = new FieldCurrency();
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
		fieldCurrency.setDataString("1.2 $");
		assertTrue(1.2 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataString5432() {
		fieldCurrency.setDataString("5432,97");
		assertTrue(5432.97 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringMinus184() {
		fieldCurrency.setDataString("-184,46 $");
		assertTrue(-184.46 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataString5() {
		fieldCurrency.setData(5.85);
		assertEquals("5,85 $", fieldCurrency.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringMinus23() {
		fieldCurrency.setData(-23.2);
		assertEquals("-23,20 $", fieldCurrency.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldCurrency.setDefaultValue();
		assertTrue(0 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldCurrency() {
		FieldCurrency testFieldCurrency = new FieldCurrency();
		assertNotNull(testFieldCurrency);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldCurrencyDouble25() {
		FieldCurrency testFieldCurrency = new FieldCurrency(25.75);
		assertNotNull(testFieldCurrency);
		assertTrue(25.75 == testFieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldCurrency.setData(10.24);
		assertTrue(10.24 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldCurrency.setData(-32.57);
		assertTrue(-32.57 == fieldCurrency.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldCurrency.setData(8.3);
		fieldCurrency.reset();
		assertNull(fieldCurrency.getData());
	}

}
