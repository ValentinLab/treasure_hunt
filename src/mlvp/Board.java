package mlvp;

import java.util.ArrayList;
import java.util.Collections;

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

	public Cell getDestCell(Hunter h) {
		int x = h.getDir().dirToX();
		int y = h.getDir().dirToY();
		Position destPos = new Position(h.getPos().getPosX() + x + 1, h.getPos().getPosY() + y + 1);

		return getCell(destPos);
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
		Position treasureP = new Position(0, 0);
		int x = 0, y = 0;
		int selectedGroundLength = selectedGround.length();
		for(int i = 0; i < selectedGroundLength; ++i) {
			switch(selectedGround.charAt(i)) {
				case '+':
					allCells[x][y] = new Side(x-1, y-1);
					x += 1;
					break;
				case '.':
					allCells[x][y] = new Free(x-1, y-1);
					x += 1;
					break;
				case '#':
					allCells[x][y] = new Stone(x-1, y-1);
					x += 1;
					break;
				case 'T':
					treasureP = new Position(x, y);
					allCells[x][y] = new Treasure(x-1, y-1);
					x += 1;
					break;
				case '\n':
					x = 0;
					y += 1;
					break;
				default:
					Hunter h = new Hunter(selectedGround.charAt(i), new Position(x, y));
					players.add(h);
					allCells[x][y] = new Free(x-1, y-1, h);
					x += 1;
					break;
			}

			// Ajouter la position du trésor aux joueurs
			for(Hunter h : players) {
				h.setTreasurePos(treasureP);
			}

			// Trier les joueurs par nom
			Collections.sort(players);
		}

		// Ajout des colonnes
		for(int i = 0; i < selectedGroundSize; ++i) {
			cells.add(new Column(allCells[i]));
		}
	}

	public void playGame() {
		boolean isPlaying = true;

		// Boucle de jeu
		do {
			// Tours de jeu
			playRound();

			// Vérifier si un joueur a gagné
			for(Hunter h : players) {
				if(h.getPos() == h.getTreasurePos()) {
					isPlaying = false;
					break;
				}
			}
		} while(isPlaying);
	}

	public void playRound() {
		// Affichage du board
		System.out.println(this.toString());

		// Actions des trois joueurs
		for(Hunter h : players) {
			// Case cible
			Cell target = getDestCell(h);

			// Affichage des informations
			System.out.println("Personnage " + h);
			System.out.println("Hunter " + h.getPos() + " dir " + h.getDir());
			System.out.println("Case cible : " + target.posToStr());

			// Action sur la case
			target.process(h);

			// Nouvel affichage du joueur
			System.out.println(" -> Hunter " + h.getPos() + " dir " + h.getDir() + "\n");
		}
	}
	
	public String toString() {
		String board="";

		int playersNuber = players.size();
		int boardSize = cells.size();
		for(int y = 0; y < boardSize; ++y) {
			for(int x = 0; x < boardSize; ++x) {
				board += getCell(x, y);
			}
			board += "\n";
		}
		
		return board;
	}
}
