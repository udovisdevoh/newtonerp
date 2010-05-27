package newtonERP.module.generalEntity;

import java.util.Vector;

import newtonERP.common.ActionLink;
import newtonERP.module.AbstractEntity;

/**
 * Représente un Splash Screen en HTML
 * @author Guillaume Lacasse
 */
public class SplashScreen extends AbstractEntity
{
    private Vector<ImageFile> imageFileList = new Vector<ImageFile>();

    private Vector<ActionLink> actionLinkList = new Vector<ActionLink>();

    /**
     * @throws Exception si ça fail
     */
    public SplashScreen() throws Exception
    {
	super();
    }

    /**
     * @param image image à ajouter
     */
    public void addImage(ImageFile image)
    {
	imageFileList.add(image);
    }

    /**
     * @param actionLink actionLink à ajouter
     */
    public void addActionLink(ActionLink actionLink)
    {
	actionLinkList.add(actionLink);
    }

    /**
     * @return image file list
     */
    public Vector<ImageFile> getImageFileList()
    {
	return imageFileList;
    }

    /**
     * @return liste des liens
     */
    public Vector<ActionLink> getActionLinkList()
    {
	return actionLinkList;
    }
}