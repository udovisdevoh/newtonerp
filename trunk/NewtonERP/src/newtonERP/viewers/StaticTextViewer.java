package newtonERP.viewers;

import newtonERP.viewers.viewables.StaticTextViewable;

public class StaticTextViewer
{
    public static String getHtmlCode(StaticTextViewable entity)
    {
	return entity.getText().replace('<', ' ').replace('>', ' ');
    }
}
