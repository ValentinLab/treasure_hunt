package model;

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

	/**
	 * Création d'un plateau de jeu
	 * (le plateau de jeu est transmis par les paramètres,
	 * si la chaîne est vide, le plateau est sélectionné au hasard)
	 *
	 * @param builtBoard Chaîne représentant le plateau de jeu
	 * @param builtWalls Murs du plateau
	 */
	public Board(String builtBoard, Wall[] builtWalls) {
		// Initialisation des éléments
		players = new ArrayList<Hunter>();
		cells = new ArrayList<Column>();

		// Initialisation du tableau de jeu
		initBoard(builtBoard, builtWalls);
	}

	/**
	 * Création d'un plateau de jeu
	 * (un plateau au hasard sera sélectionné pour la partie)
	 */
	public Board() {
		this("", null);
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

	public Cell getCell(int col, int row) {
		return cells.get(col).getCell(row);
	}

	// ----- Fonctions -----

	/**
	 * Initialiser le plateau
	 * Permet de positionner les joueurs, les murs et le trésor
	 *
	 * @param builtBoard Chaîne représentant le plateau de jeu
	 * @param builtWalls Murs du plateau
	 */
	public void initBoard(String builtBoard, Wall[] builtWalls) {
		// Tableau de terrains
		String grounds[] = {
			"++++++++++++++\n" +
			"+.....?......+\n" +
			"+............+\n" +
			"+...#........+\n" +
			"+.T.#.....A..+\n" +
			"+...#?.......+\n" +
			"+...#........+\n" +
			"+.?.#..###...+\n" +
			"+...#...C....+\n" +
			"+...#.?..B...+\n" +
			"+............+\n" +
			"+..?.........+\n" +
			"+............+\n" +
			"++++++++++++++",

			"++++++++++++\n" +
			"+.......?..+\n" +
			"+.?..T.....+\n" +
			"+.######...+\n" +
			"+..........+\n" +
			"+....####..+\n" +
			"+..?..B....+\n" +
			"+..######..+\n" +
			"+....A.....+\n" +
			"+..........+\n" +
			"+......?...+\n" +
			"++++++++++++",

			"++++++++++++++++\n" +
			"+........C.....+\n" +
			"+..............+\n" +
			"+...########...+\n" +
			"+..?.........?.+\n" +
			"+..#####.......+\n" +
			"+....T....#....+\n" +
			"+.....?...#....+\n" +
			"+.####....#....+\n" +
			"+.........#.?..+\n" +
			"+..#####.......+\n" +
			"+.?..A.........+\n" +
			"+.........B....+\n" +
			"+...?..........+\n" +
			"+..............+\n" +
			"++++++++++++++++",

			"+++++++++++++\n" +
			"+.T.........+\n" +
			"+.....###...+\n" +
			"+...........+\n" +
			"+....#......+\n" +
			"+.?..#...?..+\n" +
			"+....#......+\n" +
			"+....#......+\n" +
			"+.#.......A.+\n" +
			"+.#..?......+\n" +
			"+.#......B..+\n" +
			"+....C......+\n" +
			"+++++++++++++",

			"+++++++++++++\n" +
			"+?....T.....+\n" +
			"+...........+\n" +
			"+...#####?..+\n" +
			"+.....?.....+\n" +
			"+...........+\n" +
			"+.####......+\n" +
			"+...........+\n" +
			"+......####.+\n" +
			"+...........+\n" +
			"+A.......B..+\n" +
			"+?...C.....?+\n" +
			"+++++++++++++",

			"+++++++++++++++++\n" +
			"+..A.........B..+\n" +
			"+........C......+\n" +
			"+...............+\n" +
			"+...#########...+\n" +
			"+.......?.......+\n" +
			"+...............+\n" +
			"+...#.......#...+\n" +
			"+...#..?.?..#...+\n" +
			"+...#.......#...+\n" +
			"+...#.......#...+\n" +
			"+.......?.......+\n" +
			"+...#########...+\n" +
			"+...............+\n" +
			"+.###.......###.+\n" +
			"+.......T....?..+\n" +
			"+++++++++++++++++"
		};
		// Tableau de murs pour chaque terrain
		Wall[][] groundsWalls = {
			{
				new Wall(new Position(4, 3), new Position(4, 9)),
				new Wall(new Position(7, 7), new Position(9, 7))
			},
			{
				new Wall(new Position(2,3), new Position(7, 3)),
				new Wall(new Position(5, 5), new Position(8, 5)),
				new Wall(new Position(3, 7), new Position(8, 7))
			},
			{
				new Wall(new Position(4,3), new Position(11,3)),
				new Wall(new Position(3,5),new Position(7,5)),
				new Wall(new Position(2, 8), new Position(5, 8)),
				new Wall(new Position(3, 10), new Position(7, 10)),
				new Wall(new Position(10, 6), new Position(10, 9))
			},
			{
				new Wall(new Position(6,2), new Position(8,2)),
				new Wall(new Position(5,4),new Position(5,7)),
				new Wall(new Position(2, 8), new Position(2, 10))
			},
			{
				new Wall(new Position(4,3), new Position(8,3)),
				new Wall(new Position(2,6),new Position(5,6)),
				new Wall(new Position(7, 8), new Position(10, 8))
			},
			{
				new Wall(new Position(4,4), new Position(12,4)),
				new Wall(new Position(4,7),new Position(4,10)),
				new Wall(new Position(12,7),new Position(12,10)),
				new Wall(new Position(4, 12), new Position(12, 12)),
				new Wall(new Position(2, 14), new Position(4, 14)),
				new Wall(new Position(12, 14), new Position(14, 14))
			}


		};

		// Choix du terrain
		String selectedGround;
		Wall[] selectedWalls;
		if(!builtBoard.equals("") && builtWalls != null) {
			// Terrain transmis en paramètres
			selectedGround = builtBoard;
			selectedWalls = builtWalls;
		} else {
			// Choisir un terrain au hasard
			int indexGround = (int)(Math.random() * grounds.length);
			selectedGround = grounds[indexGround];
			// Murs liés au terrain
			selectedWalls = groundsWalls[indexGround];
		}

		// Taille du terrain
		int selectedGroundSize = 0;
		while(selectedGround.charAt(selectedGroundSize) != '\n') {
			++selectedGroundSize;
		}
		// Nombre de murs
		int wallsNumber = selectedWalls.length;

		// Parsez le terrain
		Cell[][] allCells = new Cell[selectedGroundSize][selectedGroundSize];
		TeleportationPoint telePoint = new TeleportationPoint();
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
						if(selectedWalls[k].isInside(stonePosition)) {
							currentW = selectedWalls[k];
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
				case '?':
					Teleportation teleCell = new Teleportation(x-1, y-1, telePoint);
					telePoint.addTeleportationCell(teleCell);
					allCells[x][y] = teleCell;
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
	 *
	 * @param movements Ensemble des mouvements réalisés par les joueurs
	 */
	public void playRound(String[] movements) {
		// Actions des trois joueurs
		int index = 0;
		for(Hunter h : players) {
			// Case cible
			Cell target = getDestCell(h);

			// Action sur la case
			movements[index] = "<html><strong>Personnage " + h + "</strong> : ";
			movements[index] += target.process(h);
			movements[index] += " <em>(" + h.getPos() + " dir " + h.getDir()  + ")</em></html>";
			++index;
		}
	}

	/**
	 * Vérifier si un joueur a gagné
	 *
	 * @return Le joueur qui gagne, null autrement
	 */
	public Hunter checkVictory() {
		Hunter winner = null;
		for(Hunter h : players) {
			if(h.getPos().equals(h.getTreasurePos())) {
				winner = h;
				break;
			}
		}

		return winner;
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
