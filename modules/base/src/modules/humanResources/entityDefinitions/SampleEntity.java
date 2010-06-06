package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractOrmEntity;
import newtonERP.orm.field.Field;
import newtonERP.orm.field.FieldCalcule;
import newtonERP.orm.field.FieldValidator;
import newtonERP.orm.field.Fields;
import newtonERP.orm.field.type.FieldInt;
import newtonERP.orm.field.type.FieldString;

/**
 * Représente un entité d'employé
 * 
 * @author Guillaume, CloutierJo
 */
public class SampleEntity extends AbstractOrmEntity{
	
	/**
	 * @throws Exception lorsque la création d'un employé fail
	 */
	public SampleEntity() throws Exception{
		super();
		
		setVisibleName("Employé");
	}
	
	@Override
	public Fields initFields() throws Exception{
		Vector<Field<?>> fieldsData = new Vector<Field<?>>();
		fieldsData.add(new FieldInt("Numéro", getPrimaryKeyName()));
		fieldsData.add(new FieldString("nom", "Name"));
		// ******* NAS *******
		FieldInt fieldNAS = new FieldInt("Numéro d'assurance social", "NAS");
		fieldNAS.setValidator(new FieldValidator<Integer>(){
			public boolean valide(Integer value, Fields entityFields){
				if (value.toString().length() == 9)
					return true;
				setErrorMessage("les NAS doit avoir 9 numéro");
				return false;
			}
		});
		fieldsData.add(fieldNAS);
		// ******* *******
		fieldsData.add(new FieldInt("Nombre de jour de vacance possible",
				"nbVacancyDays"));
		fieldsData.add(new FieldInt("Nombre de jour de maladie possible",
				"nbSicknessDays"));
		
		FieldInt fieldCalc = new FieldInt("calcule", "calcul");
		fieldCalc.setCalcul(new FieldCalcule<Integer>(){
			public Integer calcul(Fields entityFields){
				return (Integer) entityFields.getField("nbVacancyDays")
						.getData()
						- (Integer) entityFields.getField("nbSicknessDays")
								.getData();
			}
		});
		fieldsData.add(fieldCalc);
		
		return new Fields(fieldsData);
	}
}
