/**
 * 
 */
package modules.humanResources.actions;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import modules.humanResources.entityDefinitions.Employee;
import modules.humanResources.entityDefinitions.Schedule;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;

/**
 * @author CloutierJo
 * 
 */
public class GetManyTimeTable extends AbstractAction
{
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
	    "yyyy-MM-dd");
    private static SimpleDateFormat timeFormatter = new SimpleDateFormat(
	    "HH:00");
    private static SimpleDateFormat weekDayFormatter = new SimpleDateFormat(
	    "EEEE");

    /**
     * constructeur vide
     */
    public GetManyTimeTable()
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
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	Vector<AbstractOrmEntity> vSchedul = new Schedule().get("1=1");
	GregorianCalendar date;
	GregorianCalendar StartDate;
	GridCaseData[] leftHeader = new GridCaseData[24];
	int[] nbColumns = new int[10];
	int nbColumn = 0;
	Arrays.fill(nbColumns, 1);
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
	for (int i = 0; i < 10; i++) // passe chacun des jour
	{
	    for (int j = 0; j < leftHeader.length; j++)
	    {
		date.set(Calendar.HOUR_OF_DAY, j);
		int nbColumnInDay = 0;
		for (AbstractOrmEntity schedul : vSchedul)
		{
		    GregorianCalendar timeStart = (GregorianCalendar) schedul
			    .getData("timeStart");
		    GregorianCalendar timeStop = (GregorianCalendar) schedul
			    .getData("timeStop");

		    if (timeStart.compareTo(date) <= 0
			    && timeStop.compareTo(date) > 0)
		    {
			nbColumnInDay++;
		    }
		    if (nbColumns[i] < nbColumnInDay)
			nbColumns[i] = nbColumnInDay;
		}

	    }
	    nbColumn += nbColumns[i];
	    date.add(Calendar.DAY_OF_YEAR, 1);
	}

	date = (GregorianCalendar) StartDate.clone();
	GridCaseData[] header = new GridCaseData[nbColumn];
	GridCaseData[][] cases = new GridCaseData[24][nbColumn];

	// remplie les header de gauche
	for (int i = 0; i < leftHeader.length; i++)
	{
	    date.set(Calendar.HOUR_OF_DAY, i);
	    leftHeader[i] = new GridCaseData(timeFormatter.format(date
		    .getTime()));
	}
	nbColumn = 0;
	// remplie les data et le header du haut
	for (int i = 0; i < 10; i++) // passe chacun des jour
	{
	    for (int j = nbColumn; j < nbColumn + nbColumns[i]; j++)
		header[j] = new GridCaseData(weekDayFormatter.format(date
			.getTime())
			+ " " + dateFormatter.format(date.getTime()));

	    for (int j = 0; j < leftHeader.length; j++)
	    {
		date.set(Calendar.HOUR_OF_DAY, j);
		for (Iterator<AbstractOrmEntity> iSchedul = vSchedul.iterator(); iSchedul
			.hasNext();)
		{
		    AbstractOrmEntity schedul = iSchedul.next();

		    GregorianCalendar timeStart = (GregorianCalendar) schedul
			    .getData("timeStart");
		    GregorianCalendar timeStop = (GregorianCalendar) schedul
			    .getData("timeStop");
		    if (timeStart.compareTo(date) <= 0
			    && timeStop.compareTo(date) > 0)
		    {
			String strData = schedul.getSingleAccessor(
				new Employee().getForeignKeyName())
				.getNaturalKeyDescription();

			Hashtable<String, String> param = new Hashtable<String, String>();
			param.put(schedul.getPrimaryKeyName(), schedul
				.getPrimaryKeyValue().toString());

			for (int k = nbColumn; k < nbColumn + nbColumns[i]; k++)
			    if (cases[j][k] == null)
			    {
				for (int l = j; l < leftHeader.length; l++)
				{
				    if (timeStart.compareTo(date) <= 0
					    && timeStop.compareTo(date) > 0)
				    {
					date.set(Calendar.HOUR_OF_DAY, l);
					cases[l][k] = new GridCaseData(strData,
						new BaseAction("Edit",
							new Schedule()), param);
				    }
				}
				iSchedul.remove();
				break;
			    }
		    }
		}
	    }
	    date.add(Calendar.DAY_OF_YEAR, 1);
	    nbColumn += nbColumns[i];
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
	tt.addGlobalActions(new ActionLink("&lt; Précédent", this, parameters));
	// lien suivant
	date = (GregorianCalendar) StartDate.clone();
	date.add(Calendar.DAY_OF_YEAR, 7);
	parameters.put("startDate", dateFormatter.format(date.getTime()));
	tt.addGlobalActions(new ActionLink("Suivant &gt;", this, parameters));

	tt.setColor(true);
	tt.setSpanSimilar(true);

	return tt;
    }
}
