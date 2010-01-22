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
	html += "<input type='text' class='textField' maxlength='128' size='48' name='searchEntry' value='"
		+ searchEntry + "' /> ";
	html += "<input type='submit' value='Rechercher' />";
	html += "</form>";

	html += "</div>";

	return html;
    }
}
