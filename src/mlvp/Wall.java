package mlvp;

public class Wall {
    // ----- Attributs -----

    private boolean isHrizontal;
    private Position from;
    private Position to;

    // ----- Constructeur -----

    public Wall(Position f, Position t) {
        isHrizontal = f.getPosY() == t.getPosY();
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

    // ----- Fonctions -----

    public boolean isInside(Position p) {
        int sPosX = p.getPosX();
        int sPosY = p.getPosY();

        boolean isGoodY, isGoodX;
        if(isHrizontal) {
            isGoodY = sPosY == from.getPosY();
            isGoodX = sPosX >= from.getPosX() && sPosX <= to.getPosX();
        } else {
            isGoodY = sPosY >= from.getPosY() && sPosY <= to.getPosY();
            isGoodX = sPosX == from.getPosX();
        }

        return isGoodY && isGoodX;
    }
}
