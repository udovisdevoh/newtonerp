package newtonERP.viewers.firstStep;

/**
 * viewer servant a afiche UNE image seulement
 * @author CloutierJo
 */
public class ImgViewer
{
	/**
	 * @param entity l'ImgViewerData
	 * @return html d'une image
	 */
	public static String getHtmlCode(
			newtonERP.viewers.viewerData.ImgViewerData entity)
	{
		return "<img src='" + entity.getFilePath() + "' />";
	}

}
