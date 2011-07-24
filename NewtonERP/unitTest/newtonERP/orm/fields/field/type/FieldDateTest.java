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
public class FieldDateTest {

	/** The field bool. */
	private FieldDate fieldDateTime;

	/** The gregorian calendar. */
	private GregorianCalendar gregorianCalendar;

	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		fieldDateTime = new FieldDate();

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
		fieldDateTime.setDataString("2001-01-02");
		assertEquals("2001-01-02", fieldDateTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#getDataString()}.
	 */
	@Test
	public final void testGetDataStringEmpty() {
		fieldDateTime.setDataString("2001-01-02");
		assertEquals("2001-01-02", fieldDateTime.getDataString());
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#setDefaultValue()}.
	 */
	@Test
	public final void testSetDefaultValue() {
		fieldDateTime.setDefaultValue();
		assertTrue(new GregorianCalendar().compareTo(fieldDateTime.getData()) < 1000);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool()}.
	 */
	@Test
	public final void testFieldDateTime() {
		FieldDate testFieldDateTime = new FieldDate();
		assertNotNull(testFieldDateTime);
	}

	/**
	 * Test method for {@link newtonERP.orm.fields.field.type.FieldBool#FieldBool(java.lang.Boolean)}.
	 */
	@Test
	public final void testFieldDateTimeDate() {
		FieldDate testFieldDateTime = new FieldDate(gregorianCalendar);
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
