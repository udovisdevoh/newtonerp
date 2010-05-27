package newtonERP.viewers.secondStep;

import newtonERP.module.generalEntity.ImageFile;

/**
 * Pour voir fichiers images en HTML
 * @author Guillaume
 */
public class ImageFileViewer
{
    /**
     * @param imageFile image en HTML
     * @return html avec image dedans
     */
    public static String getHtmlCode(ImageFile imageFile)
    {
	return "<img src=\"" + imageFile.getUrl() + "\" alt=\""
		+ imageFile.getAlt() + "\" />";
    }

}