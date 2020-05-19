package model;

import java.util.ArrayList;

/**
 * Colonne de cases du plateau de jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Column {

	// ----- Attribut -----
	
	private ArrayList<Cell> col;
	
	// ----- Constructeur -----
	
	Column(Cell[] rowOfCells){
		// Création de la colonne
		col = new ArrayList<Cell>();

		// Ajout des cellules
		int columnSize = rowOfCells.length;
		for(int i = 0; i < columnSize; ++i) {
			col.add(rowOfCells[i]);
		}
	}

	// ----- Getter -----

	public Cell getCell(int row) {
		return col.get(row);
	}
}