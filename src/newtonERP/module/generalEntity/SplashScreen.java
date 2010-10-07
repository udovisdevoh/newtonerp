package newtonERP.module.generalEntity;

import java.util.Vector;

import newtonERP.common.ActionLink;

/**
 * Représente un Splash Screen en HTML
 * @author Guillaume Lacasse
 */
public class SplashScreen extends newtonERP.module.AbstractEntity
{
	private ImageFile imageFileList = new Vector<ImageFile>();

	private newtonERP.common.ActionLink actionLinkList = new Vector<ActionLink>();

	public SplashScreen()
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
	public void addActionLink(newtonERP.common.ActionLink actionLink)
	{
		actionLinkList.add(actionLink);
	}

	/**
	 * @return image file list
	 */
	public ImageFile getImageFileList()
	{
		return imageFileList;
	}

	/**
	 * @return liste des liens
	 */
	public newtonERP.common.ActionLink getActionLinkList()
	{
		return actionLinkList;
	}

}
