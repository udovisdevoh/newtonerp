package newtonERP.viewers.viewerData;

import java.util.Hashtable;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;

/**
 * represent une cellule d'un tableau affichable
 * 
 * @author CloutierJo
 */
public class GridCaseData extends ActionLink {
	private boolean isColored;

	/**
	 * @param name valeur a affiche dans cette case
	 * @param action action a effectuer lorsque clique (null par defaut)
	 * @param parameters parametre du lien
	 */
	public GridCaseData(String name, AbstractAction action, Hashtable<String, String> parameters) {
		super(name, action, parameters);
	}

	/**
	 * @param name valeur a affiche dans cette case
	 * @param actionLink action a effectuer lorsque clique (null par defaut)
	 */
	public GridCaseData(String name, AbstractAction actionLink)

	{
		this(name, actionLink, null);
	}

	/**
	 * @param name valeur a affiche dans cette case
	 */
	public GridCaseData(String name) {
		this(name, null);
	}

	/**
	 * constructeur vide
	 */
	public GridCaseData() {
		this("");
	}

	/**
	 * methode de remplacement temporaire
	 * 
	 * @return l'action
	 */
	@Deprecated
	public AbstractAction getActionLink() {
		return getAction();
	}

	/**
	 * @return si la case doit être colorée
	 */
	public boolean isColored() {
		return isColored;
	}

	/**
	 * @param isColored si la case doit être colorée
	 */
	public void setColored(boolean isColored) {
		this.isColored = isColored;
	}
}
