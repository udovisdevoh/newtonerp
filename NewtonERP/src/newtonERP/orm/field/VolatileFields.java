package newtonERP.orm.field;

/**
 * Version non-persistante de fields. Marche exactement comme fields sauf qu'on
 * peut modifier les field apres instanciation et on ne peut les utiliser avec
 * l'Orm
 * @author Guillaume Lacasse
 */
public class VolatileFields extends Fields
{
    /**
     * @param field field à ajouter ou remplacer s'il existe
     */
    public void add(Field field)
    {
	fieldsDataMap.put(field.getShortName(), field);
	fieldsDataVector.add(field);
    }
}
