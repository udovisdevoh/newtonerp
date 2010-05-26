package newtonERP.viewers.secondStep.colorViewer;

import java.util.Hashtable;

/**
 * Sert à retourner des couleurs spéciales pré-définies
 * @author Guillaume Lacasse
 */
public class ColorViewerSpecialWordList
{
    private static Hashtable<String, String> specialWordColorList;

    /**
     * @param key string key
     * @return color
     */
    public static String getSpecialWordColor(String key)
    {
	key = key.toLowerCase().trim();

	return getSpecialWordColorList().get(key);
    }

    private static Hashtable<String, String> getSpecialWordColorList()
    {
	if (specialWordColorList == null)
	    specialWordColorList = buildSpecialWordColorList();

	return specialWordColorList;
    }

    private static Hashtable<String, String> buildSpecialWordColorList()
    {
	Hashtable<String, String> newSpecialWordColorList = new Hashtable<String, String>();
	newSpecialWordColorList.put("rush", "#F88");
	newSpecialWordColorList.put("cash", "#F88");
	return newSpecialWordColorList;
    }
}
