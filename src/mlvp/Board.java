package mlvp;

import java.util.ArrayList;

public class Board {

	// ----- Attributs -----
	
	private ArrayList<Hunter> players;
	private ArrayList<Column> cells;

	// ----- Constructeur -----
	
	Board(int playersNumber){
		// Initialisation des éléments
		players = new ArrayList<Hunter>();
		cells = new ArrayList<Column>();

		// Initialisation du tableau de jeu
		initBoard();
	}
	
	// ----- Getters -----
	
	public Cell getCell(Position p) {
		return cells.get(p.getPosX() - 1).getCell(p.getPosY() - 1);
	}

	public Cell getCell(int col, int row) {
		return getCell(new Position(col + 1, row + 1));
	}
	
	public Cell getCell(Hunter h) {
		return getCell(h.getPos());
	}
	
	// ----- Fonctions -----
	
	private void initBoard() {
		// Tableau de terrains
		String grounds[] = {
			"++++++++++++++\n" +
			"+............+\n" +
			"+............+\n" +
			"+...#........+\n" +
			"+.T.#.....A..+\n" +
			"+...#........+\n" +
			"+...#........+\n" +
			"+...#..###...+\n" +
			"+...#...C....+\n" +
			"+...#....B...+\n" +
			"+............+\n" +
			"+............+\n" +
			"+............+\n" +
			"++++++++++++++"
		};

		// Choisir un terrain
		String selectedGround = grounds[(int)(Math.random() * grounds.length)];
		// Taille du terrain
		int selectedGroundSize = 0;
		while(selectedGround.charAt(selectedGroundSize) != '\n') {
			++selectedGroundSize;
		}

		// Parsez le terrain
		Cell[][] allCells = new Cell[selectedGroundSize][selectedGroundSize];
		int x = 0, y = 0;
		int selectedGroundLength = selectedGround.length();
		for(int i = 0; i < selectedGroundLength; ++i) {
			switch(selectedGround.charAt(i)) {
				case '+':
					allCells[y][x] = new Side(x, y);
					x += 1;
					break;
				case '.':
					allCells[y][x] = new Free(x, y);
					x += 1;
					break;
				case '#':
					allCells[y][x] = new Stone(x, y);
					x += 1;
					break;
				case 'T':
					allCells[y][x] = new Treasure(x, y);
					x += 1;
					break;
				case '\n':
					x = 0;
					y += 1;
					break;
				default:
					Hunter h = new Hunter(selectedGround.charAt(i), new Position(x + 1, y + 1));
					players.add(h);
					allCells[y][x] = new Free(x, y);
					allCells[y][x].process(h);
					x += 1;
					break;
			}
		}

		// Ajout des colonnes
		for(int i = 0; i < selectedGroundSize; ++i) {
			cells.add(new Column(allCells[i]));
		}
	}
	
	public void playRound() {
		int playersNb = players.size();
		for(int i = 0; i < playersNb; ++i) {

		}
	}
	
	public String toString() {
		String board="";

		int playersNuber = players.size();
		int boardSize = cells.size();
		for(int x = 0; x < boardSize; ++x) {
			for(int y = 0; y < boardSize; ++y) {
				board += getCell(x, y);
			}
			board += "\n";
		}
		
		return board;
	}
	
}
