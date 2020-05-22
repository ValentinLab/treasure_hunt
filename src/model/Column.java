package model;

import java.util.ArrayList;
import java.util.Collections;

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
		Collections.addAll(col, rowOfCells);
	}

	// ----- Getter -----

	public Cell getCell(int row) {
		return col.get(row);
	}
}