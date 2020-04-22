package mlvp;

import java.util.ArrayList;

public class Board {

    // ----- Attributs -----
	private ArrayList<Hunter> players;
	private ArrayList<Column> cells;
	
    // ----- Constructeur -----
	Board(ArrayList<Hunter> p,ArrayList<Column> c){
		players =p;
		cells=c;
	}
	
	// ----- Fonctions -----
	public Cell getCell(Hunter h) {
		
	}
	public void playRound() {
		
		for(int i=0;i<players.size();i++) {
			System.out.println(this.toString());
			//faire jouer les joueurs
			System.out.println(this.toString());
		}
	}
	public String toString() {
		String board="";
		for(int i =0;i<cells.size();i++) {
			for(int j=0;i<cells.size();i++) {
				board +=cells[i][j].getSymbol();
			}
			board+="\n";
			
		}		
		return board;
	}
	
}
