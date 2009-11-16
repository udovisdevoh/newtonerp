package newtonERP.viewers.secondStep;

import java.text.NumberFormat;

/**
 * Sert à formatter de l'argent
 * @author Guillaume
 * 
 */
public class MoneyViewer
{
    private static NumberFormat numberFormat = NumberFormat
	    .getCurrencyInstance();

    /**
     * @param inputValue valeur à formatter
     * @return valeur formattée
     */
    public static String getHtmlCode(String inputValue)
    {
	inputValue = inputValue.replace("$", "");
	inputValue = inputValue.replace(',', '.');
	inputValue = inputValue.trim();

	if (inputValue.equals(""))
	    inputValue = "0.0";

	double doubleNumber = Double.parseDouble(inputValue);
	return getHtmlCode(doubleNumber);
    }

    /**
     * @param inputValue valeur à formatter
     * @return valeur formattée
     */
    public static String getHtmlCode(double inputValue)
    {
	return numberFormat.format(inputValue).replace(" $", "$");
    }
}