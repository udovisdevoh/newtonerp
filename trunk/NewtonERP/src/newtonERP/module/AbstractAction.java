package newtonERP.module; 
 // TODO: clean up that file

import java.util.Hashtable;

/**
 * @author r3lacasgu cloutierJo Class representing an abstract action
 */
public abstract class AbstractAction {
	private AbstractEntity entityUsable;
	private String detailedDescription = null;

	protected AbstractAction() {
		entityUsable = null;
	}

	/**
	 * Default constructor for the action
	 * 
	 * @param entityUsable le type d'entité qui sera utiliser par la methode
	 */
	protected AbstractAction(AbstractEntity entityUsable) {
		this.entityUsable = entityUsable;
	}

	/**
	 * Abstract method that will be used to perform the actions
	 * 
	 * @param parameters list de parametre utilisable par l'action, une partie d'entre eux sera transforme en entite
	 * @return l,entite resultante de l'action
	 */
	public final AbstractEntity perform(Hashtable<String, String> parameters) {

		AbstractEntity entity = null;
		try{
			try{
				entity = entityUsable.getClass().newInstance();
			}catch(InstantiationException e){
				throw new RuntimeException(e);
			}catch(IllegalAccessException e){
				throw new RuntimeException(e);
			}
			entity.getFields().setFromHashTable(parameters);
		}catch(NullPointerException e){
			// ne rien faire si cette exception est lancé, laissé le bloc
			// présent
		}
		return doAction(entity, parameters);
	}

	/**
	 * Abstract method that will be reused by all the actions
	 * 
	 * @param entity entite a utiliser a l'intérieur du doAction
	 * @param parameters list de parametre autre que l'entite
	 * @return l'entite resultante
	 */
	public abstract AbstractEntity doAction(AbstractEntity entity, Hashtable<String, String> parameters);

	/**
	 * @return the entityUsable
	 */
	public AbstractEntity getEntityUsable() {
		return entityUsable;
	}

	/**
	 * @param ownedByModul the ownedByModul to set
	 */
	public void setOwnedByModul(Module ownedByModul) {
		ActionModule.addActionModule(this, ownedByModul);
	}

	/**
	 * @return the ownedByModul
	 */
	public Module getOwnedByModule() {
		return ActionModule.getModule(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detailedDescription == null) ? 0 : detailedDescription.hashCode());
		result = prime * result + ((entityUsable == null) ? 0 : entityUsable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(getClass() != obj.getClass()){
			return false;
		}
		AbstractAction other = (AbstractAction) obj;
		if(detailedDescription == null){
			if(other.detailedDescription != null){
				return false;
			}
		}else if(!detailedDescription.equals(other.detailedDescription)){
			return false;
		}
		if(entityUsable == null){
			if(other.entityUsable != null){
				return false;
			}
		}else if(!entityUsable.equals(other.entityUsable)){
			return false;
		}
		return true;
	}

	/**
	 * @return nom de l'action dans le système (normalement nom de la classe mais peut être overridé si c'est une action
	 *         dynamique)
	 */
	public String getSystemName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return description détaillée facultative
	 */
	public String getDetailedDescription() {
		return detailedDescription;
	}

	/**
	 * @param detailedDescription description détaillée facultative
	 */
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
}
