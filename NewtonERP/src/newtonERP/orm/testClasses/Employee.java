package newtonERP.orm.testClasses;

import java.util.Hashtable;

import newtonERP.orm.Ormizable;
import newtonERP.orm.exceptions.OrmFieldNotFoundException;

/**
 * 
 * @author r3hallejo
 * 
 * 	Test entity only used by me or Guillaume for the time the Orm is in testing
 */
public class Employee implements Ormizable
{
    /*
     * Data will normally be affected to the entity somewhere else but for now we test them.
     */
    private int index = 0;
    private String firstName = "Jonathan";
    private String lastName = "Hallee";
    private String age = "19";
    private String dateEmbauche = "2009-10-20";
    private String noAssSociale = "345-678-908";
    private String etatCivil = "Celibataire";
    
    @Override
    public Hashtable<String, String> getOrmizableData() throws OrmFieldNotFoundException
    {
	Hashtable<String, String> ormizableData = new Hashtable<String, String>();
	
	ormizableData.put("firstName", firstName);
	ormizableData.put("lastName", lastName);
	ormizableData.put("age", age);
	ormizableData.put("dateEmbauche", dateEmbauche);
	ormizableData.put("noAssSociale", noAssSociale);
	ormizableData.put("etatCivil", etatCivil);
	// TODO Auto-generated method stub
	return ormizableData;
    }

    /*
     * Question pour Guillaume : Comment tu vois la PrimaryKeyValue? J'imagine qu'elle sera affectee directement a 
     * partir de la vue lors exemple de la creation d'un nouvel employe?
     * 
     * (non-Javadoc)
     * @see newtonERP.orm.Ormizable#getPrimaryKeyValue()
     */
    @Override
    public int getPrimaryKeyValue()
    {
	// TODO Auto-generated method stub
	return index;
    }

    public String getSearchCriteria()
    {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * Le table name j'imagine qu'il ne sera pas hardcoder alors on va le chercher comment? Via un if exists Nom_de_la_classe dans la db?
     * 
     * (non-Javadoc)
     * @see newtonERP.orm.Ormizable#getTableName()
     */
    @Override
    public String getTableName()
    {
	// TODO Auto-generated method stub
	return "Employee";
    }
}
