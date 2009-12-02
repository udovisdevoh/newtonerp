package newtonERP.orm.field;

/**
 * champ calcule
 * @author CloutierJo
 * @param <T> type de field a valider
 */
public abstract class FieldValidator<T>
{
    private String errorMessage = "";

    protected abstract boolean valide(T value, Fields entityFields);

    /**
     * valide la donne entre, si la donnee n'Est pas valide un peu recupere la
     * raison de non validite par getErrorMessage
     * @param value valeur a valide
     * @param entityFields fields actuelle servant normalement au calcul
     * @return true si la value est valide false sinon
     */
    public boolean validate(T value, Fields entityFields)
    {
	try
	{
	    return valide(value, entityFields);
	} catch (NullPointerException e)
	{
	    setErrorMessage("Une valeur obligatoire n'a pas été remplie");
	    return false;
	}
    }

    /**
     * valide la donne entre, si la donnee n'Est pas valide un peu recupere la
     * raison de non validite par getErrorMessage
     * @param value valeur a valide
     * @param entityFields fields actuelle servant normalement au calcul
     * @return true si la value est valide false sinon
     */
    public final boolean isValide(T value, Fields entityFields)
    {
	boolean ret = validate(value, entityFields);
	if (!ret && errorMessage.length() == 0)
	    errorMessage = "Une erreur inconnue s'est produite";
	return ret;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage)
    {
	this.errorMessage = errorMessage;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage()
    {
	return errorMessage;
    }

}
