package newtonERP.module;

public class ModuleNotFoundException extends Exception
{
    /**
     * @param message nom du module
     */
    public ModuleNotFoundException(String name)
    {
	super(name + " module doesn't exist");
    }
}
