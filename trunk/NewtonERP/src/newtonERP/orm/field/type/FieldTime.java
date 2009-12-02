package newtonERP.orm.field.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import newtonERP.module.exception.InvalidOperatorException;

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
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldTime(String name, String shortName, GregorianCalendar data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldTime(String name, String shortName)
	    throws InvalidOperatorException
    {
	super(name, shortName);
    }

    @Override
    public void setData(String date) throws ParseException
    {
	try
	{
	    GregorianCalendar tempDate = new GregorianCalendar();
	    tempDate.setTime(dateFormatter.parse(date));
	    data = tempDate;
	} catch (Exception e)
	{
	    setErrorMessage("Le format de donnée entrée ne correspond pas avec le type de champ (Time): "
		    + data);
	    System.err.println(e.getMessage());
	}
    }

    @Override
    public String getDataString(Boolean forOrm)
    {
	if (forOrm)
	    return super.getDataString(forOrm);
	if (data == null)
	    return null;
	return dateFormatter.format(super.getData().getTime());
    }
}
