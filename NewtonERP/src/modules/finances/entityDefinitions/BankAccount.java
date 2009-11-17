package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCurrency;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;

/**
 * Entité BankAccount du module finances: représente les comptes bancaires
 * (capitaux et montants crédités).
 * 
 * @author Pascal Lemay
 */
public class BankAccount extends AbstractOrmEntity
{
    /**
     * @throws Exception if creation fails
     */
    public BankAccount() throws Exception
    {
	super();
	addNaturalKey("folio");
	AccessorManager.addAccessor(this, new Bank());
	setVisibleName("Comptes Bancaires");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Folio", "folio"));
	fieldsInit.add(new FieldCurrency("Solde", "balance"));
	fieldsInit.add(new FieldCurrency("Marge", "margin"));
	fieldsInit.add(new FieldInt("Numéro de Banque", new Bank()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }

}
