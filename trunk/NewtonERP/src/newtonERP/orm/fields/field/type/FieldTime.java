package newtonERP.orm.fields.field.type;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import newtonERP.common.logging.Logger;
import newtonERP.orm.fields.field.property.ErrorProperty;

/**
 * Date field for entities.
 * 
 * @author r3hallejo, Jonatan Cloutier
 */
public class FieldTime extends FieldDateTime {

	/** The date formatter. */
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");

	/**
	 * Instantiates a new field time.
	 */
	public FieldTime() {
		super();
	}

	/**
	 * Instantiates a new field time.
	 * 
	 * @param data the data
	 */
	public FieldTime(GregorianCalendar data) {
		super(data);
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
			        "Le format de donnée entrée ne correspond pas avec le type de champ (Time): " + data));
			Logger.error(e.getMessage());// TODO: change to send error back to front end
		}
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
}
