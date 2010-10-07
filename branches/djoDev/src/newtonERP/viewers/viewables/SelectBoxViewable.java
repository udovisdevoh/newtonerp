package newtonERP.viewers.viewables;

/**
 * The select box view interface
 * @author Guillaume Lacasse
 */
public interface SelectBoxViewable
{
	/**
	 * @return the elements of the select box
	 */
	newtonERP.common.NaturalMap getElements();

	/**
	 * @return the label name
	 */
	String getLabelName();

}
