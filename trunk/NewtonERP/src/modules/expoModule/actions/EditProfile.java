package modules.expoModule.actions;

import java.util.Hashtable;

import modules.expoModule.entityDefinitions.KioskCustomer;
import modules.userRightModule.entityDefinitions.User;
import newtonERP.common.ActionLink;
import newtonERP.common.Authentication;
import newtonERP.module.AbstractAction;
import newtonERP.module.AbstractEntity;
import newtonERP.orm.associations.PluralAccessor;
import newtonERP.viewers.viewerData.BaseViewerData;
import newtonERP.viewers.viewerData.PromptViewerData;

/**
 * Sert à modifier son propre profil en tant qu'exposant
 * @author Guillaume Lacasse
 */
public class EditProfile extends AbstractAction
{
    /**
     * Créer instance d'action
     */
    public EditProfile()
    {
	setDetailedDescription("Modifier les informations de son profil");
    }

    @Override
    public AbstractEntity doAction(AbstractEntity entity,
	    Hashtable<String, String> parameters) throws Exception
    {
	User currentUser = Authentication.getCurrentUser();

	PluralAccessor customerList = currentUser
		.getPluralAccessor("KioskCustomer");

	KioskCustomer kioskCustomer = (KioskCustomer) customerList.get(0);

	if (parameters.containsKey(kioskCustomer.getPrimaryKeyName()))
	{
	    for (String key : kioskCustomer.getFields().getKeyList())
		if (parameters.get(key) != null)
		    kioskCustomer.setData(key, parameters.get(key));
	}

	parameters.put(kioskCustomer.getPrimaryKeyName(), kioskCustomer
		.getPrimaryKeyValue().toString());

	BaseViewerData baseEdit = kioskCustomer.editUI(parameters);
	baseEdit.setCurrentAction(this);

	PromptViewerData promptEdit = (PromptViewerData) baseEdit;

	promptEdit.setButtonAction(new ActionLink("Modifier son profil", this));

	promptEdit.getData().getFields().getField("internetConnectionTypeID")
		.setReadOnly(true);

	return promptEdit;
    }
}
