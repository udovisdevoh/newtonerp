package newtonERP.viewers.secondStep;

import newtonERP.viewers.viewerData.SearchBar;

/**
 * Ser à voir les barres de recherche
 * @author Guillaume Lacasse
 */
public class SearchBarViewer
{
    /**
     * @param searchBar barre de recherche
     * @return code html
     */
    public static String getHtmlCode(SearchBar searchBar)
    {
	String html = "";

	html += "<div class='searchBar'>";

	String searchEntry = searchBar.getCurrentSearchEntry();
	if (searchEntry == null)
	    searchEntry = "";

	html += "<form method='GET' action='" + searchBar.getTargetUrl() + "'>";
	html += "Rercherche: <input type='text' class='textField' maxlength='128' size='48' name='searchEntry' value='"
		+ searchEntry + "' />";

	html += " | ordre: <select name='orderBy'>";

	if (searchBar.getCurrentOrder() != null
		&& searchBar.getCurrentOrder().length() > 0)
	    html += "<option value='" + searchBar.getCurrentOrder() + "'>"
		    + searchBar.getCurrentOrder() + "</option>";

	for (String possibleOrderName : searchBar.getPossibleOrderNameList())
	    if (!possibleOrderName.equals(searchBar.getCurrentOrder()))
		html += "<option value='" + possibleOrderName + "'>"
			+ possibleOrderName + "</option>";
	html += "</select> ";

	if (searchBar.getOffset() != 0)
	    html += "<input type='hidden' name='offset' value='"
		    + searchBar.getOffset() + "' />";

	if (searchBar.getLimit() != 0)
	    html += "<input type='hidden' name='limit' value='"
		    + searchBar.getLimit() + "' />";

	html += "<input type='submit' value='Go' />";

	html += "</form>";

	html += "</div>";

	return html;
    }
}
