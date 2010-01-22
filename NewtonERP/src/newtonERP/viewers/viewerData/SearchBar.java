package newtonERP.viewers.viewerData;

import newtonERP.module.AbstractEntity;

/**
 * Modele d'une barre de recherche
 * @author Guillaume Lacasse
 */
public class SearchBar extends AbstractEntity
{
    private String targetUrl;

    private String currentSearchEntry;

    /**
     * @param targetUrl url de destination de la recherche
     * @param currentSearchEntry contenu textuel courant de la rercherche
     * @throws Exception si ça fail
     */
    public SearchBar(String targetUrl, String currentSearchEntry)
	    throws Exception
    {
	super();
	this.targetUrl = targetUrl;
	this.currentSearchEntry = currentSearchEntry;
    }

    /**
     * @return Url de destination de la recherche
     */
    public String getTargetUrl()
    {
	return targetUrl;
    }

    /**
     * @return contenu textuel courant de la rercherche
     */
    public String getCurrentSearchEntry()
    {
	return currentSearchEntry;
    }

}
