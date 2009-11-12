package modules.finances.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.associations.AccessorManager;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldDouble;
import newtonERP.orm.field.FieldInt;
import newtonERP.orm.field.FieldString;
import newtonERP.orm.field.Fields;
import newtonERP.viewers.viewables.PromptViewable;

/**
 * Entité BankAccount du module finances: représente les comptes bancaires
 * (capitaux et montants crédités).
 * 
 * @author Pascal Lemay
 */
public class BankAccount extends AbstractOrmEntity implements PromptViewable
{
    /**
     * @throws Exception if creation fails
     */
    public BankAccount() throws Exception
    {
	super();
	addNaturalKey("folio");
	addCurrencyFormat("balance");
	addCurrencyFormat("margin");
	AccessorManager.addAccessor(this, new Bank());
	setVisibleName("Comptes Bancaires");
    }

    @Override
    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsInit = new Vector<Field>();
	fieldsInit.add(new FieldInt("Numéro", getPrimaryKeyName()));
	fieldsInit.add(new FieldString("Folio", "folio"));
	fieldsInit.add(new FieldDouble("Solde", "balance"));
	fieldsInit.add(new FieldDouble("Marge", "margin"));
	fieldsInit.add(new FieldInt("Numéro de Banque", new Bank()
		.getForeignKeyName()));

	return new Fields(fieldsInit);
    }

}
