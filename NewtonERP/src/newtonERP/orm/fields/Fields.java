package newtonERP.orm.fields;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import newtonERP.module.exception.FieldNotFoundException;
import newtonERP.orm.fields.field.Field;

/**
 * All the fields of an entity.
 * 
 * @author Jonatan Cloutier
 */
public class Fields implements Iterable<Field> {

	/** The fields data map. */
	private Map<String, Field> fieldsDataMap; // todo remove the protected

	/**
	 * constructeur vide, permet de construire un Fields sans aucun champ.
	 */
	public Fields() {
		fieldsDataMap = new LinkedHashMap<String, Field>();
	}

	/**
	 * Instantiates a new fields.
	 * 
	 * @param fields une liste de champ a inclure dans le Fields
	 */
	public Fields(Vector<Field> fields) {
		fieldsDataMap = new LinkedHashMap<String, Field>();
		for(Field field : fields){
			fieldsDataMap.put(field.getSystemName(), field);
		}
	}

	/**
	 * Gets the fields.
	 * 
	 * @return the fields
	 */
	public Collection<Field> getFields() {
		return fieldsDataMap.values();
	}

	/**
	 * Gets the field.
	 * 
	 * @param shortName le nom du champ voulu
	 * @return the named field
	 */
	public Field getField(String shortName) {
		return fieldsDataMap.get(shortName);
	}

	/**
	 * methode pour changer la valeur d'un champ.
	 * 
	 * @param shortName nom du champ
	 * @param data valeur modifie
	 */
	public void setData(String shortName, Object data) {
		try{
			fieldsDataMap.get(shortName).setData(data);
		}catch(NullPointerException e){
			throw new FieldNotFoundException(shortName);
		}
	}

	/**
	 * Sets the values from a hash table.
	 * 
	 * @param parameters Hashtable de parametre
	 */
	public void setFromHashTable(Hashtable<String, ?> parameters)

	{
		for(Entry<String, ?> parameter : parameters.entrySet()){
			try{
				Object value = parameter.getValue();
				if(value instanceof String){
					value = (((String) (value)).replace("&rsquo;", "'"));
				}

				setData(parameter.getKey(), value);
			}catch(FieldNotFoundException e){
				// nothing to do
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldsDataMap == null) ? 0 : fieldsDataMap.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Fields)){
			return false;
		}
		Fields other = (Fields) obj;
		if(fieldsDataMap == null){
			if(other.fieldsDataMap != null){
				return false;
			}
		}else if(!fieldsDataMap.equals(other.fieldsDataMap)){
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return fieldsDataMap.toString();
	}

	/**
	 * {@inheritDoc} iterate over the fields (not the keys).
	 * 
	 * @return the iterator
	 */
	@Override
	public Iterator<Field> iterator() {
		return fieldsDataMap.values().iterator();
	}

	/**
	 * Gets the key.
	 * 
	 * @return Liste des clefs des fields
	 */
	public Collection<String> getKey() {
		return fieldsDataMap.keySet();
	}

	/**
	 * met les valeur par defaut dans les field pour ne pas avoir de valeur null <br>
	 * <b>*ATTENTION* NE PAS UTILISE UNE ENTITY COMME SEARCHCRITERIA APRES AVOIR UTILISER CETTE FONCTION</b>.
	 * 
	 * @param allField true pour mettre tous les champ a leur valeur par defaut false pour ne mettre les valeur par
	 *        defaut qu'au field n'étant pas settez
	 */
	public void setDefaultValue(boolean allField) {
		for(Field field : getFields()){
			if(field.getData() == null || allField){
				field.setDefaultValue();
			}
		}
	}

	/**
	 * identique a setDefaultValue(true);.
	 */
	public void setDefaultValue() {
		setDefaultValue(true);
	}

	/**
	 * Contains field name.
	 * 
	 * @param fieldName nom du champ
	 * @return true si nom du champ existe dans les fields
	 */
	public boolean containsFieldName(String fieldName) {
		return fieldsDataMap.containsKey(fieldName);
	}

	/**
	 * Contains values.
	 * 
	 * @return true si au moins un field n'a pas une valeur null, sinon false
	 */
	public boolean containsValues() {
		for(Field field : this){
			if(field.getData() != null){
				return true;
			}
		}
		return false;
	}

	/**
	 * remet les valeur a null.
	 */
	public void reset() {
		for(Field field : getFields()){
			field.reset();
		}
	}
}
