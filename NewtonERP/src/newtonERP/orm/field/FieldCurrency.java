package newtonERP.orm.field;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Double field in the entities
 * 
 * @author djo
 */
public class FieldCurrency extends FieldDouble
{
    private DecimalFormat df = (DecimalFormat) NumberFormat
	    .getCurrencyInstance();

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldCurrency(String name, String shortName, Double data)
    {
	super(name, shortName, data);
	df.setGroupingUsed(false);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldCurrency(String name, String shortName)
    {
	this(name, shortName, null);
    }

    public String getDataString(Boolean forOrm)
    {
	if (forOrm)
	    return super.getDataString(forOrm);
	return df.format(data);
    }

    public void setData(String data) throws Exception
    {
	data = data.replaceAll("\\s", "");
	data = data.replaceAll("\\$", "");
	data += " $";
	this.data = df.parse(data).doubleValue();
    }
}
