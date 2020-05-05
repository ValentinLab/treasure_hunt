package mlvp;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Plateau de jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class Board {
	// ----- Attributs -----

	private ArrayList<Hunter> players;
	private ArrayList<Column> cells;

	// ----- Constructeur -----
	
	Board() {
		// Initialisation des éléments
		players = new ArrayList<Hunter>();
		cells = new ArrayList<Column>();

		// Initialisation du tableau de jeu
		initBoard();
	}

	// ----- Getters -----

	public int getSize() {
		return cells.size();
	}

	public int getPlayersNumber() {
		return players.size();
	}

	public Hunter getPlayer(int i) {
		return players.get(i);
	}

	/**
	 * Obtenir une case du jeu
	 *
	 * @param col Colonne de la case
	 * @param row Ligne de la case
	 * @return Case du jeu
	 */
	public Cell getCell(int col, int row) {
		return cells.get(col).getCell(row);
	}

	// ----- Fonctions -----

	/**
	 * Initialiser le plateau
	 * Permet de positionner les joueurs, les murs et le trésor
	 */
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
			"++++++++++++++",

			"++++++++++++\n" +
			"+..........+\n" +
			"+....T.....+\n" +
			"+...####...+\n" +
			"+..........+\n" +
			"+....####..+\n" +
			"+.....B....+\n" +
			"+..####....+\n" +
			"+....A.....+\n" +
			"+..........+\n" +
			"+..........+\n" +
			"++++++++++++"
		};
		// Tableau de murs pour chaque terrain
		Wall[][] groundsWalls = {
			{
				new Wall(new Position(4, 3), new Position(4, 9)),
				new Wall(new Position(7, 7), new Position(9, 7))
			},
			{
				new Wall(new Position(4,3), new Position(7, 3)),
				new Wall(new Position(5, 5), new Position(8, 5)),
				new Wall(new Position(3, 7), new Position(6, 7))
			}
		};

		// Choisir un terrain
		int indexGround = (int)(Math.random() * grounds.length);
		String selectedGround = grounds[indexGround];
		// Taille du terrain
		int selectedGroundSize = 0;
		while(selectedGround.charAt(selectedGroundSize) != '\n') {
			++selectedGroundSize;
		}
		// Nombre de murs
		int wallsNumber = groundsWalls[indexGround].length;

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
					Position stonePosition = new Position(x, y);
					Wall currentW = null;
					for(int k = 0; k < wallsNumber; ++k) {
						if(groundsWalls[indexGround][k].isInside(stonePosition)) {
							currentW = groundsWalls[indexGround][k];
						}
					}
					allCells[x][y] = new Stone(stonePosition, currentW);
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

	/**
	 * Obtenir la case cible d'un joueur
	 *
	 * @param h Un joueur
	 * @return La case cible du joueur
	 */
	private Cell getDestCell(Hunter h) {
		int x = h.getDir().dirToX();
		int y = h.getDir().dirToY();

		return getCell(h.getPos().getX() + x, h.getPos().getY() + y);
	}

	/**
	 * Jouer un tour de jeu
	 * Chaque joueur peut jouer un tour de jeu et interagir avec sa case cible
	 */
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

	/**
	 * Jouer une partie de jeu entière
	 */
	public void playGame() {
		boolean isPlaying = true;

		// Boucle de jeu
		do {
			// Tours de jeu
			playRound();

			// Vérifier si un joueur a gagné
			for(Hunter h : players) {
				if(h.getPos().equals(h.getTreasurePos())) {
					System.out.println("Le jour " + h + " a gagné la partie !");

					isPlaying = false;
					break;
				}
			}
		} while(isPlaying);
	}

	/**
	 * Obtenir le plateau sous forme de chaîne de caractères
	 *
	 * @return Le plateau
	 */
	public String toString() {
		String board="";

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
