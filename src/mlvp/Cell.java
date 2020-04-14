package mlvp;

public abstract class Cell implements Questionnable {
	// ----- Attributs -----
	
	private Position pos;
	
	//  ----- Fonctions -----

	public abstract void process(Hunter h);
	
	public abstract String getSymbol();
	
}
