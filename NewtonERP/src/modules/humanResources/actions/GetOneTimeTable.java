/**
 * 
 */
package modules.humanResources.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.humanResources.entityDefinitions.CaseTable;
import modules.humanResources.entityDefinitions.PeriodeType;
import modules.humanResources.entityDefinitions.Schedule;
import modules.humanResources.entityDefinitions.TimeTable;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.field.FieldDateTime;

/**
 * @author djo
 * 
 */
public class GetOneTimeTable extends AbstractAction
{
    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
	    "yyyy-MM-dd HH:00:00");
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
	    "yyyy-MM-dd");
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat(
	    "HH:00");
    private static SimpleDateFormat weekDayFormatter = new SimpleDateFormat(
	    "EEEE");

    /**
     * constructeur vide
     */
    public GetOneTimeTable()
    {
	super(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * newtonERP.module.AbstractAction#doAction(newtonERP.module.AbstractEntity,
     * java.util.Hashtable)
     */
    protected AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<AbstractOrmEntity> vSchedul = new Schedule().get("1=1");
	// todo comment on fais un select sans where :S...
	GregorianCalendar date;
	GregorianCalendar StartDate;
	CaseTable[] header = new CaseTable[10];
	CaseTable[] leftHeader = new CaseTable[24];
	CaseTable[][] cases = new CaseTable[24][10];

	if (parameters.containsKey("startDate"))
	    date = FieldDateTime.getFormatedDate(parameters.get("startDate"),
		    dateFormatter);
	else
	{
	    date = new GregorianCalendar();
	    date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek());
	}
	StartDate = (GregorianCalendar) date.clone();

	// remplie les header de gauche
	for (int i = 0; i < leftHeader.length; i++)
	{
	    date.set(Calendar.HOUR_OF_DAY, i);
	    leftHeader[i] = new CaseTable(timeFormatter.format(date.getTime()));
	}

	// remplie les data et leheader du haut
	for (int i = 0; i < 10; i++)
	{
	    header[i] = new CaseTable(weekDayFormatter.format(date.getTime())
		    + " " + dateFormatter.format(date.getTime()));

	    for (int j = 0; j < leftHeader.length; j++)
	    {
		String strData = "...";
		date.set(Calendar.HOUR_OF_DAY, j);
		String strParam = "timeStart="
			+ dateTimeFormatter.format(date.getTime());

		cases[j][i] = new CaseTable(strData, new BaseAction("New",
			new Schedule()), strParam);

	    }
	    for (AbstractOrmEntity schedul : vSchedul)
	    {
		GregorianCalendar gSchedul = (GregorianCalendar) schedul
			.getData("timeStart");
		if (FieldDateTime.isInSameDay(gSchedul, date))
		{
		    String strData = schedul.getSingleAccessor(
			    new PeriodeType().getForeignKeyName())
			    .getNaturalKeyDescription();

		    String strParam = schedul.getPrimaryKeyName() + "="
			    + schedul.getPrimaryKeyValue();

		    cases[gSchedul.get(Calendar.HOUR_OF_DAY)][i] = new CaseTable(
			    strData, new BaseAction("Edit", new Schedule()),
			    strParam);
		}

	    }
	    date.add(Calendar.DAY_OF_YEAR, 1);
	}
	TimeTable tt = new TimeTable();
	tt.setCases(cases);
	tt.setHeader(header);
	tt.setLeftHeader(leftHeader);
	tt.setTitle("Horaire");
	// lien precedent
	date = (GregorianCalendar) StartDate.clone();
	date.add(Calendar.DAY_OF_YEAR, -7);
	parameters.put("startDate", dateFormatter.format(date.getTime()));
	tt.addGlobalActions(new ActionLink("Précédent", this, parameters));
	// lien suivant
	date = (GregorianCalendar) StartDate.clone();
	date.add(Calendar.DAY_OF_YEAR, 7);
	parameters.put("startDate", dateFormatter.format(date.getTime()));
	tt.addGlobalActions(new ActionLink("suivant", this, parameters));

	return tt;
    }
}
