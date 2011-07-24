package newtonERP.viewers.viewables; 
 // TODO: clean up that file

import java.util.Hashtable;

/**
 * Diagramme à bande
 * 
 * @author Guillaume Lacasse
 */
public interface BandDiagramViewable {
	/**
	 * @return modèle de donnée du diagramme
	 */
	Hashtable<String, Double> getDiagramInfo();

	/**
	 * @return valeur maximum du diagramme
	 */
	double getMaximumValue();
}
