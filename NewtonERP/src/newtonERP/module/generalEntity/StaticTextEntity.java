package newtonERP.module.generalEntity;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.StaticTextViewable;

public class StaticTextEntity extends AbstractEntity implements
	StaticTextViewable
{
    private String text;

    public StaticTextEntity(String text)
    {
	this.text = text;
    }

    public String getText()
    {
	return text.replace('<', ' ').replace('>', ' ');
    }
}
