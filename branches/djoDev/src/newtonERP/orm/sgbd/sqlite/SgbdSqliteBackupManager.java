package newtonERP.orm.sgbd.sqlite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import newtonERP.orm.BackupManager;

/**
 * Gestionnaire de backup pour SQLite
 * @author Guillaume Lacasse
 */
public class SgbdSqliteBackupManager
{
    private static long latestBackupTime;

    private static String backupDirectoryPath = "./backup/";

    /**
     * @param originalFileName fichier de DB original
     * @param maxBackupCount nombre maximum de backup persistants
     */
    public void doBackup(String originalFileName, int maxBackupCount)
    {
	if (countBackupFile(originalFileName) > maxBackupCount)
	    deleteFile(getEarliestBackupFile(originalFileName));

	long currentTime = BackupManager.getCurrentTime();

	copyFile("./" + originalFileName, backupDirectoryPath
		+ originalFileName + "." + currentTime);

	latestBackupTime = currentTime;
    }

    private void deleteFile(String fileName)
    {
	File file = new File(backupDirectoryPath + fileName);
	file.delete();
    }

    private String getEarliestBackupFile(String originalFileName)
    {
	File folder = new File(backupDirectoryPath);
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (!listOfFiles[i].isDirectory()
		    && listOfFiles[i].getName().startsWith(
			    originalFileName + "."))
	    {
		return listOfFiles[i].getName();
	    }
	}
	return null;
    }

    private int countBackupFile(String originalFileName)
    {
	int count = 0;
	File folder = new File(backupDirectoryPath);
	File[] listOfFiles = folder.listFiles();

	for (int i = 0; i < listOfFiles.length; i++)
	{
	    if (!listOfFiles[i].isDirectory()
		    && listOfFiles[i].getName().startsWith(
			    originalFileName + "."))
	    {
		count++;
	    }
	}
	return count;
    }

    private void copyFile(String sourceFile, String destinationFile)
    {
	try
	{
	    File f1 = new File(sourceFile);
	    File f2 = new File(destinationFile);
	    InputStream in = new FileInputStream(f1);

	    // For Append the file.
	    // OutputStream out = new FileOutputStream(f2,true);

	    // For Overwrite the file.
	    OutputStream out = new FileOutputStream(f2);

	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = in.read(buf)) > 0)
	    {
		out.write(buf, 0, len);
	    }
	    in.close();
	    out.close();
	    System.out.println("File copied.");
	} catch (FileNotFoundException ex)
	{
	    System.out
		    .println(ex.getMessage() + " in the specified directory.");
	    System.exit(0);
	} catch (IOException e)
	{
	    System.out.println(e.getMessage());
	}
    }

    /**
     * @return moment où le dernier backup à été fait
     */
    public long getLatestBackupTime()
    {
	return latestBackupTime;
    }
}