package model;

/**
 * Mur du plateau : ensemble de pierres
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Wall {
    // ----- Attributs -----

    private boolean isHrizontal;
    private Position from;
    private Position to;

    // ----- Constructeur -----

    public Wall(Position f, Position t) {
        isHrizontal = f.getY() == t.getY();
        from = f;
        to = t;
    }

    // ----- Getters -----

    public boolean getIsHorizontal() {
        return isHrizontal;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    // ----- Fonction -----

    /**
     * Vérifier si une case du plateau appartient au mur (à partir de sa position)
     *
     * @param p Position de la case à vérifier
     * @return Vrai si la case est dans un mur
     */
    public boolean isInside(Position p) {
        int sPosX = p.getX();
        int sPosY = p.getY();

        boolean isGoodY, isGoodX;
        if(isHrizontal) {
            isGoodY = sPosY == from.getY();
            isGoodX = sPosX >= from.getX() && sPosX <= to.getX();
        } else {
            isGoodY = sPosY >= from.getY() && sPosY <= to.getY();
            isGoodX = sPosX == from.getX();
        }

        return isGoodY && isGoodX;
    }
}
