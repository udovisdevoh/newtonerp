package newtonERP.viewers.firstStep;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.BaseAction;
import newtonERP.module.generalEntity.FlagPool;
import newtonERP.module.generalEntity.ListOfValue;
import newtonERP.module.generalEntity.ScrollList;
import newtonERP.orm.associations.GateWay;
import newtonERP.orm.associations.GateWayManager;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.orm.field.Field;
import newtonERP.serveur.Servlet;
import newtonERP.viewers.secondStep.CheckListViewer;
import newtonERP.viewers.secondStep.FieldViewer;
import newtonERP.viewers.secondStep.ScrollListViewer;
import newtonERP.viewers.secondStep.SelectBoxViewer;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Represents the prompt viewer
 * @author Guillaume Lacasse, Pascal Lemay
 */
public class PromptViewer
{
    /**
     * Return the html code for the web page
     * @param promptData the entity to view
     * @return html
     * @throws Exception general exception
     */
    public static String getHtmlCode(PromptViewerData promptData)
	    throws Exception
    {
	AbstractOrmEntity ormEntity = null;
	System.out.println("getHtmlCode() (prompt viewer)");
	String html = "";
	AbstractEntity data = promptData.getData();

	if (data instanceof AbstractOrmEntity)
	    ormEntity = (AbstractOrmEntity) (data);

	html += "<form method=\"post\" action=\""
		+ promptData.getButtonAction().getUrlParam() + "\">";

	html += "<table>";

	for (Field<?> field : promptData.getData().getFields())
	{

	    ListOfValue listOfValue = promptData.getData().tryMatchListOfValue(
		    field.getShortName());

	    if (listOfValue == null)
	    {
		html += "\n<tr><td>" + field.getName() + ": </td><td>"
			+ FieldViewer.getHtmlCode(field) + "</td></tr>";
	    }
	    else
	    {
		html += "<tr><td>"
			+ SelectBoxViewer.getHtmlCode(listOfValue, field
				.getShortName(), field.getDataString(), field
				.isReadOnly()) + "</td></tr>";
	    }

	}

	if (data instanceof AbstractOrmEntity)
	{
	    if (ormEntity != null)
	    {
		for (GateWay gateWay : ormEntity.getGateWayList())
		{
		    try
		    {
			AbstractOrmEntity externalEntity = GateWayManager
				.getExternalEntity(gateWay, ormEntity);

			html += "\n<tr><td>"
				+ externalEntity.getNaturalKeyName()
				+ ": </td><td>"
				+ externalEntity.getNaturalKeyDescription()
				+ "</td></tr>";
		    } catch (Exception e)
		    {
			// Catch vide lorsqu'on essaie de passer par une entité
			// qui n'existe pas encore pour accéder à un accesseur
			// secondaire
		    }
		}

		for (FlagPool flagPool : ormEntity.getPositiveFlagPoolList()
			.values())
		{
		    flagPool.query(ormEntity.getPrimaryKeyName(), ormEntity
			    .getPrimaryKeyValue());
		    html += "<tr><td colspan=\"2\">"
			    + CheckListViewer.getHtmlCode(flagPool)
			    + "</td></tr>";
		}
	    }

	    html += getSingleAccessorLinkList((AbstractOrmEntity) data);
	    html += getMultipleAccessorLinkList((AbstractOrmEntity) data);
	}

	html += "<tr><td colspan=\"2\" align=\"center\" class=\"submitButton\">"
		+ "<input class=\"submitButton\" type=\"submit\" name=\"submit\" value=\""
		+ promptData.getButtonAction().getName() + "\" />";

	html += "</td></tr>";
	html += "</table>";

	html += "</form>";

	return html;
    }

    private static String getMultipleAccessorLinkList(AbstractOrmEntity entity)
	    throws Exception
    {
	String html = "";

	PluralAccessor pluralAccessor;

	ScrollList scrollList;

	for (String accessorName : entity.getPluralAccessorList().keySet())
	{
	    pluralAccessor = entity.getPluralAccessorList().get(accessorName);

	    if (pluralAccessor == null)
		continue;

	    scrollList = new ScrollList(pluralAccessor
		    .getInternalEntityDefinition().getVisibleName()
		    + "(s)");

	    scrollList.setTitleUrl(Servlet.makeLink(pluralAccessor
		    .getInternalEntityDefinition().getCurrentModule(),
		    new BaseAction("GetList", pluralAccessor
			    .getInternalEntityDefinition())));

	    for (AbstractOrmEntity currentForeignEntity : pluralAccessor)
	    {

		scrollList.addLink(currentForeignEntity
			.getNaturalKeyDescription(), Servlet.makeLink(
			currentForeignEntity.getCurrentModule(),
			new BaseAction("Edit", currentForeignEntity))
			+ "?"
			+ currentForeignEntity.getPrimaryKeyName()
			+ "="
			+ currentForeignEntity.getPrimaryKeyValue());
	    }

	    if (pluralAccessor.getInternalEntityDefinition().getFields()
		    .containsFieldName(entity.getForeignKeyName())
		    && entity.getPrimaryKeyValue() != 0)
	    {
		ActionLink newItemActionLink = new ActionLink(
			"<img src='/file/images/plusIcon.gif' alt='Nouveau' style='display:block;margin-left:3px;margin-top:2px' />",
			new BaseAction("New", pluralAccessor
				.getInternalEntityDefinition()));
		newItemActionLink.addParameters(entity.getForeignKeyName(),
			entity.getPrimaryKeyValue().toString());
		scrollList.addActionLink(newItemActionLink);
	    }

	    html += "<tr><td style=\"column-span: all;\" colspan=\"100%\">"
		    + ScrollListViewer.getHtmlContent(scrollList)
		    + "</td></tr>";
	}

	return html;
    }

    private static String getSingleAccessorLinkList(AbstractOrmEntity entity)
	    throws Exception
    {
	String html = "";

	ScrollList scrollList = new ScrollList("Est associé à");

	AbstractOrmEntity foreignEntity;
	for (String accessorName : entity.getSingleAccessorList().keySet())
	{
	    foreignEntity = entity.getSingleAccessorList().get(accessorName);

	    String listLinkName = foreignEntity.getVisibleName();

	    String entityLinkName = foreignEntity.getNaturalKeyDescription();

	    String entityUrl = Servlet.makeLink(foreignEntity
		    .getCurrentModule(), new BaseAction("Edit", foreignEntity))
		    + "?"
		    + foreignEntity.getPrimaryKeyName()
		    + "="
		    + foreignEntity.getPrimaryKeyValue();

	    String listUrl = Servlet.makeLink(foreignEntity.getCurrentModule(),
		    new BaseAction("GetList", foreignEntity));

	    scrollList.addLinkGroup(listLinkName, listUrl, entityLinkName,
		    entityUrl);
	}

	if (entity.getSingleAccessorList().size() > 0)
	    html += "<tr><td style=\"column-span: all;\" colspan=\"100%\">"
		    + ScrollListViewer.getHtmlContent(scrollList)
		    + "</td></tr>";

	return html;
    }
}
