package newtonERP.orm.field;

/**
 * champ calcule
 * @author CloutierJo
 * @param <T> type de field a valider
 */
public abstract class FieldValidator<T>
{
    private String errorMessage = "";

    /**
     * valide la donne entre, si la donnee n'Est pas valide un peu recupere la
     * raison de non validite par getErrorMessage
     * @param value valeur a valide
     * @return true si la value est valide false sinon
     */
    public abstract boolean validate(T value);

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
