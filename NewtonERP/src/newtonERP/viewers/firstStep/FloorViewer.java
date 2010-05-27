package newtonERP.viewers.firstStep;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.viewers.secondStep.LinkViewer;
import newtonERP.viewers.secondStep.colorViewer.ColorViewer;
import newtonERP.viewers.viewables.FloorViewable;

/**
 * Représente un viewer de plancher
 * @author Guillaume Lacasse
 */
public class FloorViewer
{
    private static final String noWallColor = "#433";

    private static final String wallColor = "#DEE";

    private static final int internalZoneSize = 72;

    private static final int wallWidth = 4;

    /**
     * @param entity entité de plancher
     * @return code HTML
     * @throws Exception si ça fail
     */
    public static String getHtmlCode(FloorViewable entity) throws Exception
    {
	String html = "";

	html += "<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\">";

	int columnCount = entity.getColumnCount();
	for (int y = 0; y < entity.getRowCount(); y++)
	{
	    html += "<tr>";
	    for (int x = 0; x < columnCount; x++)
	    {
		String currentText = "";
		String currentColor = "#FFF";

		boolean isCorridor = entity.isCorridorAt(x, y);

		if (isCorridor)
		{
		    currentColor = "#000";
		}
		else
		{
		    String zoneName = entity.tryGetZoneNameAt(x, y);

		    if (zoneName != null)
		    {
			currentText = zoneName;
			currentColor = ColorViewer.getColor(currentText);
		    }
		}

		html += "<td style=\"width:"
			+ (internalZoneSize + wallWidth * 2) + "px;height:"
			+ (internalZoneSize + wallWidth * 2) + "px\">";

		html += getRoomHtml(currentText, currentColor, entity, x, y,
			isCorridor);

		html += "</td>";
	    }
	    html += "</tr>";
	}
	html += "</table>";

	return html;
    }

    private static String getRoomHtml(String currentText,
	    String backgroundColor, FloorViewable entity, int x, int y,
	    boolean isCorridor) throws Exception
    {
	String html = "";

	String northWestColor = noWallColor;
	String northEastColor = noWallColor;
	String southWestColor = noWallColor;
	String southEastColor = noWallColor;
	String eastNorthColor = noWallColor;
	String eastSouthColor = noWallColor;
	String westNorthColor = noWallColor;
	String westSouthColor = noWallColor;

	if (entity.isWallAt("nord-ouest", x, y))
	    northWestColor = wallColor;
	if (entity.isWallAt("nord-est", x, y))
	    northEastColor = wallColor;
	if (entity.isWallAt("sud-ouest", x, y))
	    southWestColor = wallColor;
	if (entity.isWallAt("sud-est", x, y))
	    southEastColor = wallColor;
	if (entity.isWallAt("ouest-nord", x, y))
	    westNorthColor = wallColor;
	if (entity.isWallAt("est-nord", x, y))
	    eastNorthColor = wallColor;
	if (entity.isWallAt("ouest-sud", x, y))
	    westSouthColor = wallColor;
	if (entity.isWallAt("est-sud", x, y))
	    eastSouthColor = wallColor;

	html += "<table style=\"width:100%;height:100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";

	html += "<tr>";

	html += "<td style=\"height:" + wallWidth + "px;width:" + wallWidth
		+ "px;background-color:" + noWallColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;background-color:"
		+ northWestColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;background-color:"
		+ northEastColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;width:" + wallWidth
		+ "px;background-color:" + noWallColor + "\"><div></div></td>";

	html += "</tr>";

	html += "<tr>";

	html += "<td style=\"height:32px;width:" + wallWidth
		+ "px;background-color:" + westNorthColor
		+ "\"><div></div></td>";
	html += "<td colspan=\"2\" rowspan=\"2\" ><div style=\"width:"
		+ internalZoneSize + "px;height:" + internalZoneSize
		+ "px;overflow:auto;background-color:" + backgroundColor
		+ "\">";
	html += currentText;

	Vector<ActionLink> actionLinkList = entity.getActionLinkListAt(x, y);

	if (!isCorridor)
	    for (ActionLink link : actionLinkList)
	    {
		String currentLinkCode = LinkViewer.getHtmlCode(link);
		if (currentLinkCode.length() > 0)
		    html += currentLinkCode;
	    }

	html += "</div></td>";
	html += "<td style=\"height:32px;width:" + wallWidth
		+ "px;background-color:" + eastNorthColor
		+ "\"><div></div></td>";

	html += "</tr>";

	html += "<tr>";

	html += "<td style=\"height:32px;width:" + wallWidth
		+ "px;background-color:" + westSouthColor
		+ "\"><div style=\"height:32px;width:" + wallWidth
		+ "px\"></div></td>";
	html += "<td style=\"height:32px;width:" + wallWidth
		+ "px;background-color:" + eastSouthColor
		+ "\"><div style=\"height:32px;width:" + wallWidth
		+ "px\"></div></td>";

	html += "</tr>";

	html += "<tr>";

	html += "<td style=\"height:" + wallWidth + "px;width:" + wallWidth
		+ "px;background-color:" + noWallColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;background-color:"
		+ southWestColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;background-color:"
		+ southEastColor + "\"><div></div></td>";
	html += "<td style=\"height:" + wallWidth + "px;width:" + wallWidth
		+ "px;background-color:" + noWallColor + "\"><div></div></td>";

	html += "</tr>";

	html += "</table>";

	return html;
    }
}
