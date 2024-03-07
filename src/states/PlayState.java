package states;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameModel;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;
import static constants.Constants.blockSize;
import java.util.ArrayList;

import graphics.Banana;
import graphics.Ghost;
import graphics.GraphicObject;
import graphics.Heart;
import graphics.Pacman;
import graphics.Poison;
import graphics.Smiley;
import graphics.Star;
import graphics.StillObject;
import graphics.Wall;

//playstate håller koll på vilket level spelaren är på och ritar ut rätt bana. Den håller koll på krockar mellan pacman och förmål och ändrar till rätt state vid gameover elleer vinst. 
//Kommentar efter komplettering: Vi har tagit bort att PlayState har koll på när objekten 
//intersectar och lagt ansvaret på andra klasser
// + tagit bort onödig protected på variabler

public class PlayState extends GameState {
	private Color bgColor;
	private static Pacman myPacman;
	private AnimationTimer timer;
	private static ArrayList<Ghost> ghosts;
	private static ArrayList<StillObject> objects;
	private static ArrayList<Banana> bananas;
	private static ArrayList<Heart> hearts;
	private static ArrayList<Wall> wallRects;
	final double targetFps = 50.0;
	final double nanoPerUpdate = 1000000000000.0 / targetFps;
	private boolean visitedOne;
	private boolean visitedTwo;
	private LevelOne firstLevel;
	private LevelTwo secondLevel;
	private Level currentLevel;
	private GraphicObject myObject;
	private boolean remove;
	private int savedScore = 0, finalScore;
	private String stringScore;
	private Banana myBanana;

	public PlayState(GameModel model) {
		super(model);
		bgColor = Color.LIGHTBLUE;
		firstLevel = new LevelOne();
		secondLevel = new LevelTwo();
		setCurrentLevel(firstLevel);

		objects = new ArrayList<StillObject>();
		ghosts = new ArrayList<Ghost>();
		hearts = new ArrayList<Heart>();
		bananas = new ArrayList<Banana>();
		wallRects = new ArrayList<Wall>();

		timer = new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {
				if ((now - lastUpdate) > nanoPerUpdate) {
					update();
					lastUpdate = now;
				}
			}
		};
	}

	public void setCurrentLevel(Level level) {
		currentLevel = level;
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	@Override
	public void draw(GraphicsContext g) {

		if ((currentLevel == firstLevel) && (!visitedOne)) {
			setItems(g);
			visitedOne = true;
		} else if ((currentLevel == secondLevel) && (!visitedTwo)) {
			setItems(g);
			visitedTwo = true;
		}

		drawBg(g, bgColor);
		fillWalls(g);
		printHighScore(g);

		for (Banana banana : bananas) {
			banana.paintYourself(g, blockSize, blockSize);
		}
		for (GraphicObject object : objects) {
			object.paintYourself(g, blockSize, blockSize);
		}
		for (Ghost ghost : ghosts) {
			ghost.paintYourself(g, blockSize, blockSize);
		}
		for (Heart heart : hearts) {
			heart.paintYourself(g, blockSize, blockSize);
		}
		printHighScore(g);

		myPacman.paintYourself(g, blockSize, blockSize);

	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		switch (key.getCode()) {

		case ESCAPE:
			remove = true;
			break;

		case LEFT:
			myPacman.getDirection().setDirection(-3, 0);
			break;

		case RIGHT:
			myPacman.getDirection().setDirection(3, 0);
			break;

		case UP:
			myPacman.getDirection().setDirection(0, -3);
			break;

		case DOWN:
			myPacman.getDirection().setDirection(0, 3);
			break;

		default:
			break;
		}
	}

	public void fillWalls(GraphicsContext g) {
		int[] map;
		int i = 0;
		map = currentLevel.getMap();

		for (int y = 0; y < SCREEN_HEIGHT; y += blockSize) {
			for (int x = 0; x < SCREEN_WIDTH; x += blockSize) {
				if ((map[i] == 1) || (map[i] == 8) || map[i] == 9) {
					Wall wall = new Wall(x, y, this);
					wall.paintYourself(g, blockSize, blockSize);
					wallRects.add(wall);
				}
				i++;
			}
		}
	}

	public void setItems(GraphicsContext g) {
		int[] map;
		int i = 0;
		map = currentLevel.getMap();

		for (int y = 0; y < SCREEN_HEIGHT; y += blockSize) {
			for (int x = 0; x < SCREEN_WIDTH; x += blockSize) {
				if (map[i] == 0) {
					Pacman pacman = new Pacman(x, y, 0, 0, this);
					myPacman = pacman;
				} else if (map[i] == 2) {
					Star star = new Star(x, y, this);
					objects.add(star);
				} else if (map[i] == 3) {
					Smiley smiley = new Smiley(x, y, this);
					objects.add(smiley);
				} else if (map[i] == 4) {
					Poison poison = new Poison(x, y, this);
					objects.add(poison);
				} else if (map[i] == 5) {
					Banana banan = new Banana(x, y, this);
					bananas.add(banan);
					Ghost ghost = new Ghost(x, y, -4, 0, this);
					ghosts.add(ghost);
				} else if (map[i] == 6) {
					Banana banan = new Banana(x, y, this);
					bananas.add(banan);
				} else if (map[i] == 7) {
					Banana banan = new Banana(x, y, this);
					bananas.add(banan);
					Ghost ghost = new Ghost(x, y, 0, 4, this);
					ghosts.add(ghost);
				} else if (map[i] == 8) {
					Heart heart = new Heart(x, y, this);
					hearts.add(heart);
				}
				i++;
			}
		}
	}

	public void printHighScore(GraphicsContext g) {
		stringScore = String.valueOf(myPacman.getBananaPoints());
		g.setFill(Color.WHITE);
		g.setFont(new Font(16));
		g.fillText(stringScore, blockSize, 30);
	}

	@Override
	public void update() {
		for (Ghost ghost : ghosts) {
			ghost.update();
		}
		if (myPacman.getGhost() != null) {
			ghosts.remove(myPacman.getGhost());
		}

		for (StillObject object : objects) {
			object.update();
			myObject = object.getRemove();
			if (myObject != null) {
				objects.remove(myObject);
				break;
			}
		}
		for (Banana banana : bananas) {
			banana.update();
			myBanana = banana.getRemove();
			if (myBanana != null) {
				bananas.remove(myBanana);
				break;
			}
		}

		for (Wall wall : wallRects) {
			wall.update();
		}

		myPacman.update();

		if (myPacman.getLife() == 0) {
			setHighScore(myPacman.getBananaPoints(), savedScore);
			gameOver();
			timer.stop();
		} else if ((currentLevel instanceof LevelOne) && (bananas.isEmpty())) {
			saveScore(myPacman.getBananaPoints());
			myPacman.setMax();
			changeLevel();
			timer.stop();
		} else if ((currentLevel instanceof LevelTwo) && (bananas.isEmpty())) {
			setHighScore(myPacman.getBananaPoints(), savedScore);
			finish(finalScore);
			timer.stop();
		} else if (remove == true) {
			menu();
			timer.stop();
		}
	}

	public void saveScore(int score) {
		savedScore = score;
	}

	public void setHighScore(int newScore, int savedScore) {
		finalScore = newScore + savedScore;
	}

	public int getHighScore() {
		return finalScore;
	}

	public ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public ArrayList<Banana> getBananas() {
		return bananas;
	}

	@Override
	public void activate() {
		timer.start();
	}

	@Override
	public void deactivate() {
	}

	public void setBgColor(Color color) {
		bgColor = color;
	}

	public void changeLevel() {
		wallRects.clear();
		objects.clear();
		ghosts.clear();
		hearts.clear();
		setBgColor(Color.LIGHTBLUE);
		secondLevel = new LevelTwo();
		setCurrentLevel(secondLevel);
	}

	public ArrayList<StillObject> getObjects() {
		return objects;
	}

	public void gameOver() {
		model.switchState(new GameOverState(model, finalScore));
	}

	public void finish(int highScore) {
		model.switchState(new FinishState(model, finalScore));
	}

	public void menu() {
		model.switchState(new MenuState(model));
	}

	@Override
	public void screenPressed(MouseEvent event) {
	}

	public ArrayList<Heart> getHearts() {
		return hearts;
	}

	public Pacman getMyPacman() {
		return myPacman;
	}
}
