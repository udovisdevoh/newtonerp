package newtonERP.orm.fields.field.property;

import newtonERP.orm.fields.field.AbstractFieldDecorator;
import newtonERP.orm.fields.field.AbstractProperty;

// TODO: Auto-generated Javadoc
/**
 * The property ReadOnly block every write method on the field, so the field must be set before setting this property.
 * 
 * @author Jonatan Cloutier
 */
public class ReadOnly extends AbstractProperty {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractFieldDecorator getDecorator() {
		return new ReadOnlyDecorator();
	}

	/**
	 * The Class ReadOnlyDecorator.
	 */
	private class ReadOnlyDecorator extends AbstractFieldDecorator {

		/**
		 * Instantiates a new read only decorator.
		 */
		public ReadOnlyDecorator() {
			// needed to be able to instantiate it.
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setData(Object data) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setDataString(String data) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void setDefaultValue() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void reset() {
			throw new UnsupportedOperationException();
		}
	}
}
