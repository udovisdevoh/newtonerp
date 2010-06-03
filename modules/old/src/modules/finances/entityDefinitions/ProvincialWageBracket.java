package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldCurrency;
import newtonERP.orm.field.type.FieldDouble;
import newtonERP.orm.field.type.FieldInt;

/**
 * Entité ProvincialWageBracket du module finances: représente les tranche
 * salariales et les taux impôsables à ceux-ci.
 * 
 * Données utilisées par l'action calculateProvincialTax
 * 
 * Sert également de donnée pour générer talon de paye...à venir
 * 
 * @author Pascal Lemay
 */
public class ProvincialWageBracket extends AbstractOrmEntity
{
	/**
	 * @throws Exception if creation fails
	 */
	public ProvincialWageBracket() throws Exception
	{
		super();
		setVisibleName("Tanches Salariales/Impôt Provincial");
	}

	@Override
	public Fields initFields() throws Exception
	{
		Vector<Field<?>> fieldsInit = new Vector<Field<?>>();
		FieldInt primaryKey = new FieldInt("Numéro", getPrimaryKeyName());
		primaryKey.setNaturalKey(true);
		fieldsInit.add(primaryKey);

		fieldsInit.add(new FieldCurrency("Minimum de tranche", "minBracket"));
		fieldsInit.add(new FieldCurrency("Maximum de tranche", "maxBracket"));
		fieldsInit.add(new FieldCurrency("Montant d'ajustement", "ajustment"));
		fieldsInit.add(new FieldDouble("Impôt %", "tax"));

		return new Fields(fieldsInit);
	}

}