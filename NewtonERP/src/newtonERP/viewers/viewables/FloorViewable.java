package newtonERP.viewers.viewables;

import java.util.Vector;

import newtonERP.common.ActionLink;

/**
 * Ce qui peut être vu en tant que plan 2D comportant des salles et des
 * corridors
 * @author Guillaume Lacasse
 */
public interface FloorViewable
{
    /**
     * @return nombre de colonnes
     */
    int getColumnCount();

    /**
     * @return nombre de rangées
     */
    int getRowCount();

    /**
     * @param x position x
     * @param y position y
     * @return s'il y a un corridor à la position x
     */
    boolean isCorridorAt(int x, int y);

    /**
     * @param x position x
     * @param y position y
     * @return nom de la zone à la position x, y
     * @throws Exception si ça fail
     */
    String tryGetZoneNameAt(int x, int y) throws Exception;

    /**
     * @param x position x
     * @param y position y
     * @return liste des actionLink pour la position spécifiée
     * @throws Exception
     */
    Vector<ActionLink> getActionLinkListAt(int x, int y) throws Exception;

    /**
     * @param string nom de type de mur
     * @param x position x de zone
     * @param y position y de zone
     * @return vrai s'il y a un mur à la position indiquée
     */
    boolean isWallAt(String string, int x, int y);
}
