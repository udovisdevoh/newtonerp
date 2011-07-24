package newtonERP.orm.fields.field.property;

import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.AbstractFieldDecorator;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.AbstractProperty;

// TODO: Auto-generated Javadoc
/**
 * The property CalculateProperty set a calculated field every read will get the value generated from the calcul method.
 * This property also set the field as readOnly since a write operation is not possible.
 * 
 * @author Jonatan Cloutier
 */
public abstract class CalculateProperty extends AbstractProperty {

	/** The fields. */
	private Fields fields;

	/**
	 * Instantiates a new field calcule.
	 * 
	 * @param fields the fields
	 */
	public CalculateProperty(Fields fields) {
		this.fields = fields;
	}

	/**
	 * Calcul.
	 * 
	 * @param entityFields the entity fields
	 * @return the object
	 */
	protected abstract Object calcul(Fields entityFields);

	/**
	 * execution du calcul.
	 * 
	 * @return valeur calculer
	 */
	public final Object calculate() {
		try{
			return calcul(fields);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractFieldDecorator getDecorator() {
		return new CalculateDecorator(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preSetup(Field field) {
		if(field.getProperty(ReadOnly.class) != null){
			throw new UnsupportedOperationException("cannot add a CalculateProperty on a readOnly Field");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void postSetup(Field field) {
		super.postSetup(field);

		field.addProperty(new ReadOnly());
	}

	/**
	 * The Class CalculateDecorator.
	 */
	private final class CalculateDecorator extends AbstractFieldDecorator {

		/** The calcule ref. */
		private CalculateProperty calculeRef;

		/**
		 * Instantiates a new calculate decorator.
		 * 
		 * @param calculeRef the calcule Field ref
		 */
		public CalculateDecorator(CalculateProperty calculeRef) {
			this.calculeRef = calculeRef;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public <T> T getData() {
			setData(calculeRef.calculate());
			return super.getData();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getDataString() {
			setData(calculeRef.calculate());
			return super.getDataString();
		}
	}

}
