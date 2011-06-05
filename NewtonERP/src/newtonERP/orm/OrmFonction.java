package newtonERP.orm;

import java.util.Collection;

import newtonERP.common.ListModule;
import newtonERP.logging.Logger;
import newtonERP.module.AbstractOrmEntity;
import newtonERP.module.Module;
import newtonERP.module.exception.ModuleException;
import newtonERP.orm.exceptions.OrmException;
import newtonERP.orm.field.Field;
import newtonERP.orm.sgbd.AbstractSgbd;
import newtonERP.orm.sgbd.sqlite.SgbdSqlite;
import newtonERP.serveur.ConfigManager;

/**
 * @author CloutierJo
 * 
 */
public class OrmFonction {
    private static AbstractSgbd sgbd = null;

    /**
     * @return abstractSgdb that is in the config file
     */
    public static AbstractSgbd getSgbd() {
        if (sgbd == null)
            sgbd = buildSgbd();
        return sgbd;
    }

    private static AbstractSgbd buildSgbd() {
        if (ConfigManager.loadStringProperty("dmbs-name").equals("sqlite"))
            return new SgbdSqlite();// On cré la référence
        throw new OrmException("Invalid DBMS type");
    }

    /**
     * Creates the non-existent table from the modules in the database
     */
    public static void createNonExistentTables() {
        for (String key : ListModule.getAllModules()) {
            try {
                Module module = ListModule.getModule(key);
                createNonExistentTables(module);
            } catch (ModuleException e) {
                // PrintStackTrace nécéssaire pour afficher l'information de
                // l'exception précédente. Il faudrait mettre l'ancien
                // stackTrace dans le nouveau
                e.printStackTrace();
                throw new ModuleException(
                        "Erreur à la construction de la requête pour créer les tables : "
                                + e.getMessage());
            }
        }
    }

    private static void createNonExistentTables(Module module) {
        Collection<AbstractOrmEntity> moduleEntities = module
                .getEntityDefinitionList().values();

        // For each entity in the list of module entities
        for (AbstractOrmEntity entity : moduleEntities) {
            createTableForEntity(entity);
            addMissingColumnsForEntity(entity);
            createIndexesForEntity(entity);
        }
    }

    private static void createTableForEntity(AbstractOrmEntity entity) {
        getSgbd().createTableForEntity(entity);
    }

    private static void addMissingColumnsForEntity(AbstractOrmEntity entity) {
        for (Field<?> field : entity.getFields()) {
            try {
                getSgbd().addColumnToTable(entity, field);
            } catch (OrmException e) {
                Logger.warning("[ORM] Champ déjà dans entité");
            }
        }
    }

    private static void createIndexesForEntity(AbstractOrmEntity entity) {
        // On cré des index pour chaque clef étrangère
        for (String fieldName : entity.getFields().getKeyList())
            if ((fieldName.endsWith("ID") && !fieldName.startsWith("PK"))
                    || fieldName.startsWith("system"))
                getSgbd().createIndex(entity.getSystemName(), fieldName);
    }

    /**
     * Fait un backup de la DB si l'intervale de temps est assez grande
     */
    @Deprecated
    public static void doBackupIfTimeIntervalAllows() {
        long currentTime = BackupManager.getCurrentTime();
        long latestBackupTime = getSgbd().getLatestBackupTime();
        long desiredBackupTimeInterval = BackupManager
                .getDesiredBackupIntervalTime();

        if (currentTime - latestBackupTime > desiredBackupTimeInterval)
            getSgbd().doBackup();
    }

}
