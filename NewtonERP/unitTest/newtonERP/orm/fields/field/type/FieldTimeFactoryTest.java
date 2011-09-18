/**
 * 
 */
package newtonERP.orm.fields.field.type;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.FieldFactory;
import newtonERP.orm.fields.field.FieldType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldTimeFactoryTest {

	/** The field bool. */
	private Field fieldDateTime;

	/** The gregorian calendar. */
	private GregorianCalendar gregorianCalendar;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		fieldDateTime = FieldFactory.newField(FieldType.TIME, "time test");

		gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(Calendar.DAY_OF_YEAR, -10);
		gregorianCalendar.add(Calendar.HOUR, -3);
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
		fieldDateTime.setDataString("03:04");
		assertEquals("03:04", fieldDateTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringEmpty() {
		fieldDateTime.setDataString("03:04");
		assertEquals("03:04", fieldDateTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldDateTime.setDefaultValue();
		assertTrue(new GregorianCalendar().compareTo((Calendar) fieldDateTime.getData()) < 1000);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldSDateTime() {
		Field testFieldDateTime = FieldFactory.newField(FieldType.TIME, "time test");
		assertNotNull(testFieldDateTime);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldDateTimeDate() {
		Field testFieldDateTime = FieldFactory.newField(FieldType.TIME, "time test", gregorianCalendar);
		assertNotNull(testFieldDateTime);
		assertEquals(gregorianCalendar, testFieldDateTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldDateTime.setData(gregorianCalendar);
		assertEquals(gregorianCalendar, fieldDateTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldDateTime.setData(gregorianCalendar);
		assertEquals(gregorianCalendar, fieldDateTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldDateTime.setData(gregorianCalendar);
		fieldDateTime.reset();
		assertNull(fieldDateTime.getData());
	}

}
