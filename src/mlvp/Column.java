package mlvp;

import java.util.ArrayList;

public class Column {

	// ----- Attributs -----
	
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

	// ----- Getters -----

	public int getSize() {
		return col.size();
	}

	public Cell getCell(int row) {
		return col.get(row);
	}
}