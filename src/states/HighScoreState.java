/**
 * 
 */
package states;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameModel;
import static constants.Constants.SCREEN_WIDTH;

/**
 * @author oliviabrofors Hanterar highscore state, sparar ner och plockar
 *         tillbaka highscorelista till/från hårddisk.
 */

public class HighScoreState extends GameState {
	ArrayList<Integer> highScores = new ArrayList<Integer>();
	private Integer newScore;
	private String printScore;

	public HighScoreState(GameModel model, int newHighScore) {

		super(model);

		try {
			readHighScore();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		newScore = Integer.valueOf(newHighScore);
		highScores.add(newScore);
		saveHighScore();

	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g) {
		model.clear(g);
		drawBg(g, Color.LIGHTGREEN);
		g.setFill(Color.LIGHTCORAL);
		g.fillRect(0, 0, SCREEN_WIDTH, 150);
		g.setFill(Color.BLACK);
		g.setFont(new Font(70));
		g.fillText("HIGHSCORES", 300, 100);
		sortDescending(highScores);
		printHighScores(g);
	}

	public void printHighScores(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.setFont(new Font(30));
		int xCord = 300;
		int yCord = 200;
		for (int i = 0; i < 10; i++) {
			if (highScores.get(i) == 0) {
				if (i == 0) {
					g.fillText("The highscore list is empty", xCord, yCord);
				}
				break;
			}
			printScore = Integer.toString(highScores.get(i));
			g.fillText(("#" + Integer.toString(i + 1)), xCord - 150, yCord);
			g.fillText(printScore, xCord, yCord);
			g.fillText("POINTS.", xCord + 150, yCord);
			yCord += 40;
		}
	}

	public static void sortDescending(ArrayList<Integer> highScores) {
		Collections.sort(highScores, Collections.reverseOrder());
	}

	public void saveHighScore() {
		ObjectOutputStream saveHighScores;
		try {
			saveHighScores = new ObjectOutputStream(new FileOutputStream(new File("highScoreList.txt")));
			saveHighScores.writeObject(highScores);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void readHighScore() throws ClassNotFoundException, IOException {
		ObjectInputStream readHighScores;
		try {
			readHighScores = new ObjectInputStream(new FileInputStream(new File("highScoreList.txt")));
			this.highScores = (ArrayList<Integer>) readHighScores.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("the highscore list is empty");
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
		}
	}

	@Override
	public void screenPressed(MouseEvent event) {
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
	}

}