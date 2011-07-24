package newtonERP.orm.fields.field.type;

/**
 * text field in the entities (text is a long text where String is a short text).
 * 
 * @author Jonatan Cloutier
 */
public class FieldText extends FieldString {

	/**
	 * Instantiates a new field text.
	 */
	public FieldText() {
		super();
	}

	/**
	 * Instantiates a new field text.
	 * 
	 * @param data the data
	 */
	public FieldText(String data) {
		super(data);
	}

}
