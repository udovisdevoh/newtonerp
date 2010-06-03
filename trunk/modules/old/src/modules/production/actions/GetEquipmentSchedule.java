package modules.production.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import modules.production.entityDefinitions.EquipmentPeriodType;
import modules.production.entityDefinitions.EquipmentSchedule;
import newtonERP.common.ActionLink;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.orm.field.type.FieldDateTime;
import newtonERP.viewers.viewerData.GridCaseData;
import newtonERP.viewers.viewerData.GridViewerData;

/**
 * The equipment schedule
 * 
 * @author r3hallejo
 */
public class GetEquipmentSchedule extends AbstractAction
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
	 * Default constructor
	 */
	public GetEquipmentSchedule()
	{
		super(null);
	}

	@Override
	public AbstractEntity doAction(AbstractEntity entity,
			Hashtable<String, String> parameters) throws Exception
	{
		Vector<AbstractOrmEntity> scheduleList = new EquipmentSchedule()
				.get("1=1");

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
			leftHeader[i] = new GridCaseData(timeFormatter.format(date
					.getTime()));
		}

		// remplie les data et le header du haut
		for (int i = 0; i < 10; i++) // passe chacun des jour
		{
			header[i] = new GridCaseData(weekDayFormatter
					.format(date.getTime())
					+ " " + dateFormatter.format(date.getTime()));

			for (int j = 0; j < leftHeader.length; j++)
			{
				String strData = "...";
				date.set(Calendar.HOUR_OF_DAY, j);
				Hashtable<String, String> param = new Hashtable<String, String>();
				param
						.put("timeStart", dateTimeFormatter.format(date
								.getTime()));
				date.add(Calendar.HOUR, 1);
				param.put("timeStop", dateTimeFormatter.format(date.getTime()));
				date.add(Calendar.HOUR, -1);
				cases[j][i] = new GridCaseData(strData, new BaseAction("New",
						new EquipmentSchedule()), param);

			}
			for (AbstractOrmEntity schedule : scheduleList)
			{
				for (int j = 0; j < leftHeader.length; j++)
				{
					date.set(Calendar.HOUR_OF_DAY, j);
					GregorianCalendar timeStart = (GregorianCalendar) schedule
							.getData("timeStart");
					GregorianCalendar timeStop = (GregorianCalendar) schedule
							.getData("timeStop");
					if (timeStart.compareTo(date) <= 0
							&& timeStop.compareTo(date) > 0)
					{
						String strData = schedule.getSingleAccessor(
								new EquipmentPeriodType().getForeignKeyName())
								.getNaturalKeyDescription();

						Hashtable<String, String> param = new Hashtable<String, String>();
						param.put(schedule.getPrimaryKeyName(), schedule
								.getPrimaryKeyValue().toString());

						cases[date.get(Calendar.HOUR_OF_DAY)][i] = new GridCaseData(
								strData, new BaseAction("Edit",
										new EquipmentSchedule()), param);
					}
				}
			}
			date.add(Calendar.DAY_OF_YEAR, 1);
		}
		// creation du GridViewerData entity
		GridViewerData scheduleView = new GridViewerData();
		scheduleView.setCases(cases);
		scheduleView.setHeader(header);
		scheduleView.setLeftHeader(leftHeader);
		scheduleView.setTitle("Horaire");

		// lien precedent
		date = (GregorianCalendar) StartDate.clone();
		date.add(Calendar.DAY_OF_YEAR, -7);
		parameters.put("startDate", dateFormatter.format(date.getTime()));
		scheduleView.addGlobalActions(new ActionLink("&lt; Précédent", this,
				parameters));

		// lien suivant
		date = (GregorianCalendar) StartDate.clone();
		date.add(Calendar.DAY_OF_YEAR, 7);
		parameters.put("startDate", dateFormatter.format(date.getTime()));
		scheduleView.addGlobalActions(new ActionLink("Suivant &gt;", this,
				parameters));

		scheduleView.setColored(true);
		scheduleView.setSpanSimilar(true);

		return scheduleView;
	}

}
