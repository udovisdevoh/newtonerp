package newtonERP.orm.fields.field.type;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.InnerField;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Date field for entities.
 * 
 * @author r3hallejo, Jonatan Cloutier
 */
public class FieldDateTime extends InnerField<GregorianCalendar> {

	/** The date formatter. */
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Instantiates a new field date time.
	 */
	public FieldDateTime() {
		super();
	}

	/**
	 * Instantiates a new field date time.
	 * 
	 * @param data the data
	 */
	public FieldDateTime(GregorianCalendar data) {
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDataString() {
		if(data == null){
			return null;
		}
		return dateFormatter.format(data.getTime());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataString(String date) {
		try{
			GregorianCalendar tempDate = new GregorianCalendar();
			tempDate.setTime(dateFormatter.parse(date));
			data = tempDate;
		}catch(Exception e){
			root.addProperty(new ErrorProperty(
			        "Le format de donnée entrée ne correspond pas avec le type de champ (dateTime): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		setData(new GregorianCalendar());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<GregorianCalendar> getTypeArgument() {
		return GregorianCalendar.class;
	}

	/**
	 * Convertie une date de string vers GregorianCalendar.
	 * 
	 * @param dateInString date en string
	 * @return date un gregorian calendar
	 */
	public static GregorianCalendar getFormatedDate(String dateInString)

	{
		return getFormatedDate(dateInString, dateFormatter);
	}

	/**
	 * Convertie une date de string vers GregorianCalendar.
	 * 
	 * @param dateInString date en string
	 * @param sdf format de dateTime a utiliser
	 * @return date un gregorian calendar
	 */
	public static GregorianCalendar getFormatedDate(String dateInString, SimpleDateFormat sdf) {
		try{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			Date tempDate = sdf.parse(dateInString);
			gregorianCalendar.setTime(tempDate);
			return gregorianCalendar;
		}catch(Exception e){
			Logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Checks if is in same day.
	 * 
	 * @param first premier date
	 * @param second deuxieme date
	 * @return true si les 2 date son dans le meme intervale
	 */
	public static boolean isInSameDay(GregorianCalendar first, GregorianCalendar second) {
		boolean ret;
		ret = first.get(Calendar.DAY_OF_YEAR) == second.get(Calendar.DAY_OF_YEAR);
		ret &= first.get(Calendar.YEAR) == second.get(Calendar.YEAR);
		return ret;
	}
}
