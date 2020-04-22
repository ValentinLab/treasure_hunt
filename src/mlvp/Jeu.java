package mlvp;

public class Jeu {

	// ----- Attributs -----
	private Board jeu;
	
	// ----- Constructeur -----
	Jeu(Board b){
		jeu=b;
	}
	
	// ----- Fonctions -----
	public static void main(String args[]) {
		Board b=new Board(players,column);
		Jeu game=new Jeu(b);
		While(){
			b.playRound();
		}
		System.out.println("Le gagnant a gagné");
		
	}
}
