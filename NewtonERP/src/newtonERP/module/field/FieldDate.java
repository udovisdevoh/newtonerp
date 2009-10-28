package newtonERP.module.field;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import newtonERP.module.exception.FieldNotCompatibleException;
import newtonERP.module.exception.InvalidOperatorException;

/**
 * Date field for entities
 * 
 * @author r3hallejo
 */
public class FieldDate extends Field
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    GregorianCalendar data;
    String operator;

    /**
     * @param name the viewable name
     * @param shortName the internal name
     * @param data the date
     */
    public FieldDate(String name, String shortName, GregorianCalendar data)
    {
	super(name, shortName);
	this.data = data;
	operator = "=";
    }

    @Override
    public Object getData()
    {
	return data;
    }

    @Override
    public String getDataString()
    {
	return sdf.format(data);
    }

    @Override
    public String getOperator()
    {
	return operator;
    }

    @Override
    public void setData(String date) throws ParseException
    {
	Date tempDate = sdf.parse(date);
	data.setTime(tempDate);
    }

    @Override
    public void setData(Object date) throws FieldNotCompatibleException
    {
	if (date instanceof GregorianCalendar)
	    data = (GregorianCalendar) date;
	else
	    throw new FieldNotCompatibleException(
		    "Field has to be a gregorian calendar");
    }

    @Override
    public void setOperator(String operator) throws InvalidOperatorException
    {
	operator.trim();

	if (operator.equals("="))
	{
	    this.operator = operator;
	}
	else
	    throw new InvalidOperatorException("Opérateur invalide pour "
		    + getClass().getSimpleName());
    }

    public void setDefaultValue() throws FieldNotCompatibleException
    {
	setData(new GregorianCalendar());
    }
}