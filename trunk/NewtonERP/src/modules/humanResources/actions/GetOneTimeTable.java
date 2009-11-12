/**
 * 
 */
package modules.humanResources.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.humanResources.entityDefinitions.PeriodeType;
import modules.humanResources.entityDefinitions.Schedule;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.field.FieldDateTime;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;

/**
 * @author CloutierJo
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
	GridCaseData[] header = new GridCaseData[10];
	GridCaseData[] leftHeader = new GridCaseData[24];
	GridCaseData[][] cases = new GridCaseData[24][10];

	if (parameters.containsKey("startDate"))
	    date = FieldDateTime.getFormatedDate(parameters.get("startDate"),
		    dateFormatter);
	else
	{
	    date = new GregorianCalendar();
	    date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek());
	}
	date.set(Calendar.MINUTE, 0);
	date.set(Calendar.SECOND, 0);

	StartDate = (GregorianCalendar) date.clone();

	// remplie les header de gauche
	for (int i = 0; i < leftHeader.length; i++)
	{
	    date.set(Calendar.HOUR_OF_DAY, i);
	    leftHeader[i] = new GridCaseData(timeFormatter.format(date.getTime()));
	}

	// remplie les data et le header du haut
	for (int i = 0; i < 10; i++) // passe chacun des jour
	{
	    header[i] = new GridCaseData(weekDayFormatter.format(date.getTime())
		    + " " + dateFormatter.format(date.getTime()));

	    for (int j = 0; j < leftHeader.length; j++)
	    {
		String strData = "...";
		date.set(Calendar.HOUR_OF_DAY, j);
		String strParam = "timeStart="
			+ dateTimeFormatter.format(date.getTime());

		cases[j][i] = new GridCaseData(strData, new BaseAction("New",
			new Schedule()), strParam);

	    }
	    for (AbstractOrmEntity schedul : vSchedul)
	    {
		for (int j = 0; j < leftHeader.length; j++)
		{
		    date.set(Calendar.HOUR_OF_DAY, j);
		    GregorianCalendar timeStart = (GregorianCalendar) schedul
			    .getData("timeStart");
		    GregorianCalendar timeStop = (GregorianCalendar) schedul
			    .getData("timeStop");
		    if (timeStart.compareTo(date) <= 0
			    && timeStop.compareTo(date) >= 0)
		    {
			String strData = schedul.getSingleAccessor(
				new PeriodeType().getForeignKeyName())
				.getNaturalKeyDescription();

			String strParam = schedul.getPrimaryKeyName() + "="
				+ schedul.getPrimaryKeyValue();

			cases[date.get(Calendar.HOUR_OF_DAY)][i] = new GridCaseData(
				strData,
				new BaseAction("Edit", new Schedule()),
				strParam);
		    }
		}
	    }
	    date.add(Calendar.DAY_OF_YEAR, 1);
	}
	// creation du GridViewerData entity
	GridViewerData tt = new GridViewerData();
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
