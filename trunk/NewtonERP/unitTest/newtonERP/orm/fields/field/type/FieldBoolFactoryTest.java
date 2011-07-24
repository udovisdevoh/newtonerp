/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldBoolFactoryTest {

	/** The field bool. */
	private Field fieldBool;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldBool = FieldFactory.newField(FieldType.BOOL, "test Field");
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
	public final void testSetDataStringTrue() {
		fieldBool.setDataString("true");
		assertEquals(true, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringOn() {
		fieldBool.setDataString("on");
		assertEquals(true, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringYes() {
		fieldBool.setDataString("yes");
		assertEquals(true, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringFalse() {
		fieldBool.setDataString("false");
		assertEquals(false, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDataString(java.lang.String)}.
	 */
	@Test
	public final void testSetDataStringDsafe() {
		fieldBool.setDataString("Dsafe");
		assertEquals(false, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringTrue() {
		fieldBool.setData(true);
		assertEquals("True", fieldBool.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringFalse() {
		fieldBool.setData(false);
		assertEquals("False", fieldBool.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldBool.setDefaultValue();
		assertEquals("False", fieldBool.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldBool() {
		Field testFieldBool = FieldFactory.newField(FieldType.BOOL, "test Field");
		assertNotNull(testFieldBool);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldBoolBooleanTrue() {
		Field testFieldBool = FieldFactory.newField(FieldType.BOOL, "test Field", true);
		assertNotNull(testFieldBool);
		assertEquals(true, testFieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldBoolBooleanFalse() {
		Field testFieldBool = FieldFactory.newField(FieldType.BOOL, "test Field", false);
		assertNotNull(testFieldBool);
		assertEquals(false, testFieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetDataTrue() {
		fieldBool.setData(true);
		assertEquals(true, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetDataFalse() {
		fieldBool.setData(false);
		assertEquals(false, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetDataTrue() {
		fieldBool.setData(true);
		assertEquals(true, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetDataFalse() {
		fieldBool.setData(false);
		assertEquals(false, fieldBool.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldBool.setData(true);
		fieldBool.reset();
		assertNull(fieldBool.getData());
	}

}
