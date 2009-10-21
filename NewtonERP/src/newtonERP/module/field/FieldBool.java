package newtonERP.module.field;

/**
 * @author djo
 * 
 */
public class FieldBool extends Field
{

    boolean data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldBool(String name, String shortName, boolean data)
    {
	super(name, shortName);
	this.data = data;
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldBool(String name, String shortName)
    {
	this(name, shortName, false);
    }

    private void setDataB(boolean data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(boolean data)
    {
	setDataB(data);
    }

    /**
     * @return the data
     */
    public String getDataString()
    {
	return ((Boolean) data).toString();
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	this.data = Boolean.parseBoolean(data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (!(obj instanceof FieldBool))
	    return false;
	FieldBool other = (FieldBool) obj;
	if (data != other.data)
	    return false;
	return true;
    }

    public Boolean getData()
    {
	// TODO Auto-generated method stub
	return data;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Boolean)
	    setDataB((Boolean) data);
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    public String toString()
    {
	return getDataString();
    }

}
