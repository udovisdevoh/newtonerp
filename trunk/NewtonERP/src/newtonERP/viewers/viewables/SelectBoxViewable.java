package newtonERP.viewers.viewables; 
 // TODO: clean up that file

import newtonERP.common.NaturalMap;

/**
 * The select box view interface
 * 
 * @author Guillaume Lacasse
 */
public interface SelectBoxViewable {
	/**
	 * @return the elements of the select box
	 */
	public NaturalMap<String, String> getElements();

	/**
	 * @return the label name
	 */
	public String getLabelName();
}
