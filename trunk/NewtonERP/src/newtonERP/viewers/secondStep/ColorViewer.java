package newtonERP.viewers.secondStep;

import java.util.Hashtable;

public class ColorViewer
{
    private static Hashtable<String, String> colorMap;

    public static String getColor(String text)
    {
	text = text.replaceAll("\\<.*?\\>", "");
	text = text.toLowerCase();

	text = text.replaceAll("[^a-z0-9]", "");

	if (getColorMap().get(text) == null)
	    getColorMap().put(text, buildColor(text));

	return getColorMap().get(text);
    }

    private static Hashtable<String, String> getColorMap()
    {
	if (colorMap == null)
	    colorMap = new Hashtable<String, String>();
	return colorMap;
    }

    private static String buildColor(String text)
    {
	if (text.equals(""))
	    return "#FFF";

	String hex = Integer.toHexString(text.hashCode()).toUpperCase();

	while (hex.length() < 6)
	    hex += "0";

	hex = normalizeColors(hex);

	return "#" + hex.substring(0, 6);
    }

    private static String normalizeColors(String color)
    {
	return normalizeColor(color.substring(0, 2))
		+ normalizeColor(color.substring(2, 4))
		+ normalizeColor(color.substring(4, 6));
    }

    private static String normalizeColor(String color)
    {
	char firstNumber = color.charAt(0);

	if (firstNumber == '0')
	    firstNumber = '8';
	else if (firstNumber == '1')
	    firstNumber = '9';
	else if (firstNumber == '2')
	    firstNumber = 'A';
	else if (firstNumber == '3')
	    firstNumber = 'B';
	else if (firstNumber == '4')
	    firstNumber = 'C';
	else if (firstNumber == '5')
	    firstNumber = 'D';
	else if (firstNumber == '6')
	    firstNumber = 'E';
	else if (firstNumber == '7')
	    firstNumber = 'F';

	return firstNumber + color.substring(1);
    }
}
