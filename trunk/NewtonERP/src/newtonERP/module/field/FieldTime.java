package newtonERP.module.field;

import java.text.SimpleDateFormat;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldTime extends FieldDateTime
{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
	    "HH:mm");

    /**
     * @param name the viewable name
     * @param shortName the internal name
     * @param data the date
     */
    public FieldTime(String name, String shortName)
    {
	super(name, shortName);
    }

    @Override
    public String getDataString(Boolean forOrm)
    {
	// TODO : Remove this code if no bug is reported with this class
	// if (data == null)
	// data = new GregorianCalendar();
	if (forOrm)
	    return super.getDataString(forOrm);
	return dateFormatter.format(data.getTime());
    }
}
