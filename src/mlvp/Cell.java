package mlvp;

public abstract class Cell implements Questionnable {
	// ----- Attributs -----
	
	protected Position pos;
	
	//  ----- Fonctions -----

	public abstract void process(Hunter h);
	
	public abstract String getSymbol();
	
}
