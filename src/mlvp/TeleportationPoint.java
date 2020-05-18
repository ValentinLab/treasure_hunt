package mlvp;

import java.util.ArrayList;

/**
 * Liste de toutes les cases de téléportation du plateau
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class TeleportationPoint {
    // ----- Attribut -----

    ArrayList<Teleportation> teleportationPoints;

    // ----- Constructeur -----

    public TeleportationPoint() {
        teleportationPoints = new ArrayList<Teleportation>();
    }

    // ----- Fonctions -----

    /**
     * Choisir une case de téléportation au hasard
     * Permet d'obtenir une case de téléportation différente de la case courante
     * Si toutes les cases sont occupées par un joueur, alors aucune case n'est retournée
     *
     * @param h Joueur à téléporter
     * @return Une case de téléportation ou null si aucune n'est disponible
     */
    public Teleportation chooseTeleCell(Hunter h) {
        // Obtenir une nouvelle case
        int teleCellsNumber  = teleportationPoints.size();
        int randomIndex = 0, attempt = 0;
        do{
            randomIndex = (int)(Math.random() * teleCellsNumber);
            ++attempt;
        } while(
            attempt < teleCellsNumber
                && h.getPos().equals(teleportationPoints.get(randomIndex).getPos())
                && !teleportationPoints.get(randomIndex).isFree()
        );

        if(attempt == teleCellsNumber && !teleportationPoints.get(randomIndex).isFree()) {
            return null;
        }

        return teleportationPoints.get(randomIndex);
    }

    /**
     * Ajouter une nouvelle case de téléportation à la liste
     *
     * @param tCell Case de téléportation à ajouter
     */
    public void addTeleportationCell(Teleportation tCell) {
        teleportationPoints.add(tCell);
    }
}
