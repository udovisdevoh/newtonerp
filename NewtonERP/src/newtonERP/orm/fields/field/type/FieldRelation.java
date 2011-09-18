package newtonERP.orm.fields.field.type;

import newtonERP.orm.fields.field.InnerField;
import newtonERP.orm.relation.AbstractRelation;

/**
 * text field in the entities (text is a long text where String is a short text).
 * 
 * @author Jonatan Cloutier
 */
public class FieldRelation extends InnerField<AbstractRelation> {

	/**
	 * Instantiates a new field int.
	 */
	public FieldRelation() {
		throw new UnsupportedOperationException("relation field must be initialized with a relation");
	}

	/**
	 * Instantiates a new relation field.
	 * 
	 * @param data the data
	 */
	public FieldRelation(AbstractRelation data) {
		super(data);
	}

	/**
	 * Sets the data string.
	 * 
	 * @param data the data to set
	 */
	@Override
	public void setDataString(String data) {
		throw new UnsupportedOperationException("cannot set data string on a relation field");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultValue() {
		throw new UnsupportedOperationException("cannot set default data on a relation field");
	}

	@Override
	public void reset() {
		throw new UnsupportedOperationException("cannot reset data on a relation field");
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<AbstractRelation> getTypeArgument() {
		return (Class<AbstractRelation>) getData().getClass();
	}
}
