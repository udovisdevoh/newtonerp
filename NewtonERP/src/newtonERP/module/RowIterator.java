package newtonERP.module;

public class RowIterator
{
    private String foreignLabel;
    private String localKeyName;
    private String foreignKeyName;
    private String targetForeignKeyName;
    private String farKeyName;
    private String farForeignKeyName;
    private String farForeignVisibleKeyName;
    private AbstractOrmEntity intermediateEntityDefinition;
    private AbstractOrmEntity farEntityDefinition;

    public RowIterator(String foreignLabel, String localKeyName,
	    String foreignKeyName, String targetForeignKeyName,
	    String farKeyName, String farForeignKeyName,
	    String farForeignVisibleKeyName,
	    AbstractOrmEntity intermediateEntityDefinition,
	    AbstractOrmEntity farEntityDefinition)
    {
	this.foreignLabel = foreignLabel;
	this.localKeyName = localKeyName;
	this.foreignKeyName = foreignKeyName;
	this.targetForeignKeyName = targetForeignKeyName;
	this.farKeyName = farKeyName;
	this.farForeignKeyName = farForeignKeyName;
	this.farForeignVisibleKeyName = farForeignVisibleKeyName;
	this.intermediateEntityDefinition = intermediateEntityDefinition;
	this.farEntityDefinition = farEntityDefinition;
    }
}
