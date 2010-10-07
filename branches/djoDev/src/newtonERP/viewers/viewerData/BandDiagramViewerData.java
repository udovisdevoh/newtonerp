
package newtonERP.viewers.viewerData;

/**
 * Représente le modèle de donnée d'un diagramme à bandes
 * @author Guillaume Lacasse
 */
public class BandDiagramViewerData extends newtonERP.module.AbstractEntity implements newtonERP.viewers.viewables.BandDiagramViewable {
  private String, Double diagramInfo;

  /**
   * @param diagramInfo information interne du diagramme
   */
  public  BandDiagramViewerData(Hashtable<String, Double> diagramInfo) {
		super();
		this.diagramInfo = diagramInfo;
  }

  /**
   * @return modèle de donnée d'un diagramme à bandes
   */
  public Hashtable<String, Double> getDiagramInfo() {
		if (diagramInfo == null)
			diagramInfo = new Hashtable<String, Double>();
		return diagramInfo;
  }

  @Override
  public double getMaximumValue() {
		double max = 0;
		for (double value : getDiagramInfo().values())
			if (value > max)
				max = value;
		return max;
  }

}
