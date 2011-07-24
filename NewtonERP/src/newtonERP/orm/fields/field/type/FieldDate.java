package newtonERP.orm.fields.field.type;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Date field for entities
 * 
 * @author r3hallejo, Jonatan Cloutier
 */
public class FieldDate extends FieldDateTime {

	/** The date formatter. */
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Instantiates a new field date.
	 */
	public FieldDate() {
		super();
	}

	/**
	 * Instantiates a new field date.
	 * 
	 * @param data the data
	 */
	public FieldDate(GregorianCalendar data) {
		super(data);
	}

	@Override
	public void setDataString(String date) {
		try{
			GregorianCalendar tempDate = new GregorianCalendar();
			tempDate.setTime(dateFormatter.parse(date));
			data = tempDate;
		}catch(Exception e){
			root.addProperty(new ErrorProperty(
			        "Le format de donnée entrée ne correspond pas avec le type de champ (date): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
	}

	@Override
	public String getDataString() {
		if(data == null){
			return null;
		}
		return dateFormatter.format(data.getTime());
	}
}
