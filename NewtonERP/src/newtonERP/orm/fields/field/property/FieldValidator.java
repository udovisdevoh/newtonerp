package newtonERP.orm.fields.field.property;

import newtonERP.orm.fields.Fields;
import newtonERP.orm.fields.field.AbstractFieldDecorator;
import newtonERP.orm.fields.field.Field;
import newtonERP.orm.fields.field.AbstractProperty;

/**
 * The property FieldValidator add a validator to the field that will check on every write of the field if the new value
 * is valide if yes the value is save else an error is thrown,
 * 
 * @author Jonatan Cloutier
 */
public abstract class FieldValidator extends AbstractProperty {

	/** The fields ref. */
	private final Fields fieldsRef;

	/** The current field. */
	private Field currentField;

	/**
	 * Instantiates a new field validator.
	 * 
	 * @param fieldsRef the fields ref
	 */
	public FieldValidator(Fields fieldsRef) {
		this.fieldsRef = fieldsRef;
	}

	/**
	 * Validate the value.
	 * 
	 * @param value the value
	 * @param entityFields the entity fields
	 * @return true, if successful
	 */
	protected abstract boolean valide(Object value, Fields entityFields);

	/**
	 * valide la donne entre, si la donnee n'Est pas valide un peu recupere la raison de non validite par
	 * getErrorMessage.
	 * 
	 * @param value valeur a valide
	 * @return true si la value est valide false sinon
	 */
	public boolean validate(Object value) {
		try{
			return valide(value, fieldsRef);
		}catch(NullPointerException e){
			setErrorMessage("Une valeur obligatoire n'a pas été remplie");
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preSetup(Field field) {
		currentField = field;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractFieldDecorator getDecorator() {
		return new CalculateDecorator(this);
	}

	/**
	 * Sets the error message.
	 * 
	 * @param errorMessage the new error message
	 */
	protected final void setErrorMessage(String errorMessage) {
		currentField.addProperty(new ErrorProperty(errorMessage));
	}

	/**
	 * The Class CalculateDecorator.
	 */
	private final class CalculateDecorator extends AbstractFieldDecorator {

		/** The calcule ref. */
		private FieldValidator validatorRef;

		/**
		 * Instantiates a new calculate decorator.
		 * 
		 * @param validatorRef the validator ref
		 */
		public CalculateDecorator(FieldValidator validatorRef) {
			this.validatorRef = validatorRef;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setData(Object value) {
			setData(validatorRef.validate(value));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setDataString(String value) {
			Object data = getData();
			super.setDataString(value);
			if(!validatorRef.validate(getData())){
				// put back the original object as the new one didn't pass validation
				super.setData(data);
			}
		}
	}

}
