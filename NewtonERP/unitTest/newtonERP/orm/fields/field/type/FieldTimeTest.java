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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonatan Cloutier
 */
public class FieldTimeTest {

	/** The field bool. */
	private FieldTime fieldTime;

	/** The gregorian calendar. */
	private GregorianCalendar gregorianCalendar;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldTime = new FieldTime();

		gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(Calendar.DAY_OF_YEAR, -10);
		gregorianCalendar.add(Calendar.HOUR, -3);
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
		fieldTime.setDataString("03:04");
		assertEquals("03:04", fieldTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringEmpty() {
		fieldTime.setDataString("03:04");
		assertEquals("03:04", fieldTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldTime.setDefaultValue();
		assertTrue(new GregorianCalendar().compareTo(fieldTime.getData()) < 1000);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldSDateTime() {
		FieldDateTime testFieldDateTime = new FieldTime();
		assertNotNull(testFieldDateTime);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldDateTimeDate() {
		FieldDateTime testFieldDateTime = new FieldTime(gregorianCalendar);
		assertNotNull(testFieldDateTime);
		assertEquals(gregorianCalendar, testFieldDateTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#setData(java.lang.Object)}.
	 */
	@Test
	public final void testSetData() {
		fieldTime.setData(gregorianCalendar);
		assertEquals(gregorianCalendar, fieldTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#getData()}.
	 */
	@Test
	public final void testGetData() {
		fieldTime.setData(gregorianCalendar);
		assertEquals(gregorianCalendar, fieldTime.getData());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.InnerField#reset()}.
	 */
	@Test
	public final void testReset() {
		fieldTime.setData(gregorianCalendar);
		fieldTime.reset();
		assertNull(fieldTime.getData());
	}

}
