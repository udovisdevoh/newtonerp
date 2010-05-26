package newtonERP.module.generalEntity;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.StaticTextViewable;

/**
 * @author Guillaume Lacasse
 * 
 *         Represents a static text entity
 */
public class StaticTextEntity extends AbstractEntity implements
	StaticTextViewable
{
    private String text;

    /**
     * @param text the text of the entity
     * @throws Exception si ça fail d'entrer le texte
     */
    public StaticTextEntity(String text) throws Exception
    {
	this.text = text;
    }

    public String getText()
    {
	return text.replace("<", "&lt;").replace(">", "&gt;");
    }
}
