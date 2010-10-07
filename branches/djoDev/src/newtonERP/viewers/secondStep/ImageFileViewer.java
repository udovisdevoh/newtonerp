package newtonERP.viewers.secondStep;

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
	public static String getHtmlCode(
			newtonERP.module.generalEntity.ImageFile imageFile)
	{
		return "<img src=\"" + imageFile.getUrl() + "\" alt=\""
				+ imageFile.getAlt() + "\" />";
	}

}
