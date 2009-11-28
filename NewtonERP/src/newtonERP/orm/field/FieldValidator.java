package newtonERP.orm.field;

/**
 * champ calcule
 * @author CloutierJo
 * @param <T> type de field a valider
 */
public abstract class FieldValidator<T>
{
    private String errorMessage = "une erreur inconnue s'est produite";

    protected abstract boolean valide(T value);

    /**
     * valide la donne entre, si la donnee n'Est pas valide un peu recupere la
     * raison de non validite par getErrorMessage
     * @param value valeur a valide
     * @return true si la value est valide false sinon
     */
    public boolean validate(T value)
    {
	try
	{
	    return valide(value);
	} catch (NullPointerException e)
	{
	    setErrorMessage("une valeur obligatoir n'a pas été emplis");
	    return false;
	}
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    protected void setErrorMessage(String errorMessage)
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
