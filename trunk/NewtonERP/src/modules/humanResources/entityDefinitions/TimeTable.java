package modules.humanResources.entityDefinitions;

import java.util.Vector;

import newtonERP.module.AbstractEntity;
import newtonERP.module.field.Field;
import newtonERP.module.field.FieldDate;
import newtonERP.module.field.Fields;

/**
 * Représente un département dans une compagnie
 * @author Guillaume
 */
public class TimeTable extends AbstractEntity
{
    CaseTable[] header;
    CaseTable[] leftHeader;
    CaseTable[][] cases;

    /**
     * @throws Exception si création fails
     */
    public TimeTable() throws Exception
    {
	super();
    }

    public Fields initFields() throws Exception
    {
	Vector<Field> fieldsData = new Vector<Field>();

	fieldsData.add(new FieldDate("date de début", "dateStart"));
	fieldsData.add(new FieldDate("date de fin", "dateStop"));

	return new Fields(fieldsData);
    }

    /**
     * @return the header
     */
    public CaseTable[] getHeader()
    {
	return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(CaseTable[] header)
    {
	this.header = header;
    }

    /**
     * @return the leftHeader
     */
    public CaseTable[] getLeftHeader()
    {
	return leftHeader;
    }

    /**
     * @param leftHeader the leftHeader to set
     */
    public void setLeftHeader(CaseTable[] leftHeader)
    {
	this.leftHeader = leftHeader;
    }

    /**
     * @return the cases
     */
    public CaseTable[][] getCases()
    {
	return cases;
    }

    /**
     * @param cases the cases to set
     */
    public void setCases(CaseTable[][] cases)
    {
	this.cases = cases;
    }

    /**
     * @return le titre de la page
     */
    public String getTitle()
    {
	return "Horaire";
    }

}