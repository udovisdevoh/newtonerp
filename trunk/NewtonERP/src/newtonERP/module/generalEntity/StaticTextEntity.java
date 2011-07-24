package newtonERP.module.generalEntity; 
 // TODO: clean up that file

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.StaticTextViewable;

/**
 * @author Guillaume Lacasse Represents a static text entity
 */
public class StaticTextEntity extends AbstractEntity implements StaticTextViewable {
	private String text;

	/**
	 * @param text the text of the entity d'entrer le texte
	 */
	public StaticTextEntity(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text.replace("<", "&lt;").replace(">", "&gt;");
	}
}
