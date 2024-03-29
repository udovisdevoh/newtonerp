package newtonERP.viewers.viewerData; 
 // TODO: clean up that file

import java.util.Hashtable;

import newtonERP.module.AbstractEntity;
import newtonERP.viewers.viewables.BandDiagramViewable;

/**
 * Représente le modèle de donnée d'un diagramme à bandes
 * 
 * @author Guillaume Lacasse
 */
public class BandDiagramViewerData extends AbstractEntity implements BandDiagramViewable {
	private Hashtable<String, Double> diagramInfo;

	/**
	 * @param diagramInfo information interne du diagramme
	 */
	public BandDiagramViewerData(Hashtable<String, Double> diagramInfo)

	{
		super();
		this.diagramInfo = diagramInfo;
	}

	/**
	 * @return modèle de donnée d'un diagramme à bandes
	 */
	@Override
	public Hashtable<String, Double> getDiagramInfo() {
		if(diagramInfo == null){
			diagramInfo = new Hashtable<String, Double>();
		}
		return diagramInfo;
	}

	@Override
	public double getMaximumValue() {
		double max = 0;
		for(double value : getDiagramInfo().values()){
			if(value > max){
				max = value;
			}
		}
		return max;
	}
}
