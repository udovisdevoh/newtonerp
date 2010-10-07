package newtonERP.module.generalEntity;

/**
 * @author Guillaume Lacasse
 * 
 *         Represents a static text entity
 */
public class StaticTextEntity extends newtonERP.module.AbstractEntity implements
		newtonERP.viewers.viewables.StaticTextViewable
{
	private String text;

	/**
	 * @param text the text of the entity d'entrer le texte
	 */
	public StaticTextEntity(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text.replace("<", "&lt;").replace(">", "&gt;");
	}

}
