package newtonERP.viewers.firstStep;

import newtonERP.viewers.viewerData.ImgViewerData;

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
    public static String getHtmlCode(ImgViewerData entity)
    {
	return "<img src='" + entity.getFilePath() + "' />";
    }
}
