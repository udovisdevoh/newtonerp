package newtonERP.viewers.viewerData;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * donnee pour faire affiche UNE image
 * @author CloutierJo
 */
public class ImgViewerData extends BaseViewerData
{
	String filePath;

	public ImgViewerData()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param bi image sous forme de BufferedImage
	 * @param fileName nom du fichier
	 */
	public ImgViewerData(BufferedImage bi, String fileName)
	{
		filePath = "file/" + fileName + ".png";
		File outputfile = new File(filePath);
		try
		{
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e)
		{
			// on log le probleme et affiche l'Exception, sa ne fera qu'une
			// image introuvable pour l'utilisateur et nous avons une trace du
			// probleme
			Logger.error("the image " + filePath + " cannot be created");
			e.printStackTrace();
		}
		filePath = "../" + filePath;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath()
	{
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

}
