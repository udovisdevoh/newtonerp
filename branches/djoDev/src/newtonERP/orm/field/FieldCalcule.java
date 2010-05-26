package newtonERP.orm.field;

/**
 * champ calcule
 * @author CloutierJo
 * @param <T> type de field a calculer
 */
public abstract class FieldCalcule<T>
{

    protected abstract T calcul(Fields entityFields);

    /**
     * calcule a executer
     * @param entityFields fields actuelle servant normalement au calcul
     * @return valeur calculer
     */
    public T calculate(Fields entityFields)
    {
	try
	{
	    return calcul(entityFields);
	} catch (NullPointerException e)
	{
	    return null;
	}
    }
}
