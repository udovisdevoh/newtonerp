package newtonERP.orm.field;

/**
 * champ calcule
 * @author CloutierJo
 * @param <T> type de field a calculer
 */
public abstract class FieldCalcule<T>
{
    /**
     * calcule a executer
     * @param entityFields fields actuelle servant normalement au calcul
     * @return valeur calculer
     */
    public abstract T calculate(Fields entityFields);
}
