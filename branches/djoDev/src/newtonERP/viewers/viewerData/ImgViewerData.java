/**
 * 
 */
package newtonERP.viewers.viewerData;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * donnee pour faire affiche UNE image
 * @author CloutierJo
 */
public class ImgViewerData extends BaseViewerData
{

    String filePath;

    /**
     * @throws Exception remonte
     */
    public ImgViewerData() throws Exception
    {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @param bi image sous forme de BufferedImage
     * @param fileName nom du fichier
     * @throws Exception remonte
     */
    public ImgViewerData(BufferedImage bi, String fileName) throws Exception
    {
	filePath = "file/" + fileName + ".png";
	File outputfile = new File(filePath);
	ImageIO.write(bi, "png", outputfile);
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
