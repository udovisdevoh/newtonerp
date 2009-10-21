package newtonERP.module.field;

/**
 * @author djo
 * 
 */
public class FieldInt extends Field
{

    int data;

    /**
     * constructeur minimum
     * 
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     * @param data donne du champ
     */
    public FieldInt(String name, String shortName, int data)
    {
	super(name, shortName);
	this.data = data;
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldInt(String name, String shortName)
    {
	this(name, shortName, 0);
    }

    /**
     * @return the data
     */
    public Integer getData()
    {
	return data;
    }

    private void setDataI(int data)
    {
	this.data = data;
    }

    /**
     * @param data the data to set
     */
    public void setData(int data)
    {
	setDataI(data);
    }

    /**
     * @return the data
     */
    public String getDataString()
    {
	return data + "";
    }

    /**
     * @param data the data to set
     */
    public void setData(String data)
    {
	this.data = Integer.parseInt(data);
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
	if (!(obj instanceof FieldInt))
	    return false;
	FieldInt other = (FieldInt) obj;
	if (data != other.data)
	    return false;
	return true;
    }

    public void setData(Object data) throws FieldNotCompatibleException
    {
	if (data instanceof Integer)
	    setDataI((Integer) data);
	else
	    throw new FieldNotCompatibleException(getShortName(), data);
    }

    public String toString()
    {
	return getDataString();
    }
}
