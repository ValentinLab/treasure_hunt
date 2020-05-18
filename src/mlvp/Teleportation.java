package mlvp;

/**
 * Case du plateau de type téléportation
 * Lorsqu'un joueur arrive sur cette case et qu'elle n'est occupée pas aucun autre joueur, alors le
 * joueur est téléporté vers une autre case de téléportation libre
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Teleportation extends Cell {
    // ----- Attributs -----

    TeleportationPoint telePoint;
    Hunter player;

    // ----- Constructeur -----

    public Teleportation(int x, int y, TeleportationPoint tp) {
        pos = new Position(x+1, y+1);
        telePoint = tp;
        player = null;
    }

    // ----- Fonctions -----

    /**
     * Supprimer le joueur de la case si sa position est différente
     * (Lorsque le joueur s'est déplacé)
     */
    private void removePlayer() {
        if(player != null) {
            if(!player.getPos().equals(pos)) {
                player = null;
            }
        }
    }

    /**
     * Vérifier si la case est libre
     *
     * @return Vrai si aucun joueur n'occupe la case
     */
    public boolean isFree() {
        return player == null;
    }

    /**
     * Interaction entre le joueur et la case
     *
     * @param h Le joueur qui arrive sur la case
     */
    public String process(Hunter h) {
        // Supprimer le joueur actuellement sur la case s'il s'est déplace
        removePlayer();

        // case libre
        if(player == null) {
            // Obtenir la case de destination
            Teleportation dest = telePoint.chooseTeleCell(h);

            h.getDir().setNear(h);
            // aucune case n'est disponible
            if(dest == null) {
                h.setPos(pos);
                return "Téléportation impossible";
            }
            // une case est disponible
            dest.player = h;
            h.setPos(dest.getPos());
            return "Joueur téléporté";
        }

        // case occupée
        h.getDir().setRandom();
        return "Conflit de personnages";
    }

    /**
     * Transformer la case en chaîne de caractères
     *
     * @return La case sous forme de chaîne
     */
    public String toString() {
        removePlayer();

        if(player == null) {
            return "?";
        }
        return player.toString();
    }
}
