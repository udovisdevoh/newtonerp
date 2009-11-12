package newtonERP.orm.field;

/**
 * Double field in the entities
 * 
 * @author djo
 */
public class FieldCurrency extends FieldDouble
{

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
    }

    /**
     * @param name nom du champ qui sera visible par l'utilisateur
     * @param shortName nom du champ qui sera utiliser a l'interne
     */
    public FieldCurrency(String name, String shortName)
    {
	super(name, shortName);
    }

}
