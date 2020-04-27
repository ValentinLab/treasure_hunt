package mlvp;

public class Wall {
    // ----- Attributs -----

    private boolean isHrizontal;
    private Position from;
    private Position to;

    // ----- Constructeur -----

    public Wall(boolean iH, Position f, Position t) {
        isHrizontal = iH;
        from = f;
        to = t;
    }

    // ----- Fonctions -----

    public boolean isInside(Stone s) {
        int sPosX = s.getPos().getPosX();
        int sPosY = s.getPos().getPosY();

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
