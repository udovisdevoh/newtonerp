package newtonERP.orm;

import java.util.GregorianCalendar;

/**
 * Sert à gérer les backup automatiques
 * @author Guillaume Lacasse
 */
public class BackupManager
{
    private static GregorianCalendar gregorianCalendar = new GregorianCalendar();

    private static long desiredBackupTimeInterval = 1800000; // Chaque demie
							     // heure

    private static int maximumBackupInstanceCount = 100;

    /**
     * @return temps actuel en ms
     */
    public static long getCurrentTime()
    {
	gregorianCalendar = new GregorianCalendar();
	return gregorianCalendar.getTime().getTime();
    }

    /**
     * @return interval désirée pour backup en ms
     */
    public static long getDesiredBackupIntervalTime()
    {
	return desiredBackupTimeInterval;
    }

    /**
     * @return nombre maximum d'instance de backup
     */
    public static int getMaximumBackupInstanceCount()
    {
	return maximumBackupInstanceCount;
    }
}
