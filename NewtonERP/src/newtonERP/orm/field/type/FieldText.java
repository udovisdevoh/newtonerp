package newtonERP.orm.field.type;

import newtonERP.module.exception.InvalidOperatorException;

/**
 * text field in the entities (text is a long text where String is a short text)
 * 
 * @author CloutierJo
 */
public class FieldText extends FieldString
{
    private boolean isVeryLong = false;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     * @throws InvalidOperatorException remonte
     */
    public FieldText(String name, String shortName, String data)
	    throws InvalidOperatorException
    {
	super(name, shortName, data);
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param isVeryLong if it is a very long text field (default: false)
     * @throws InvalidOperatorException remonte
     */
    public FieldText(String name, String shortName, boolean isVeryLong)
	    throws InvalidOperatorException
    {
	super(name, shortName);
	this.isVeryLong = isVeryLong;
    }

    /**
     * isVeryLong sera à false par default
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @throws InvalidOperatorException remonte
     */
    public FieldText(String name, String shortName)
	    throws InvalidOperatorException
    {
	this(name, shortName, false);
    }

    /**
     * @param isVeryLong if it is a very long text area
     */
    public void setVeryLong(boolean isVeryLong)
    {
	this.isVeryLong = isVeryLong;
    }

    /**
     * @return isVeryLong
     */
    public boolean isVeryLong()
    {
	return isVeryLong;
    }

}
