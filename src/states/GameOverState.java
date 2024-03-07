/**
 * 
 */
package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameModel;

// State för när spelaren har förlorat (tappart sina tre liv). 

public class GameOverState extends GameState{

	private String informationText;
	private Color bgColor;
	private Color fontColor, highScoreColor;
	private String stringScore;
	private int myHighScore;


	public GameOverState(GameModel model, int highScore) {
		super(model);
		myHighScore = highScore;
		informationText = "You lost\nPress Enter to go back to menu";
		bgColor = Color.LIGHTBLUE;
		fontColor = Color.BLUE;
		stringScore = String.valueOf(highScore);
		highScoreColor = Color.YELLOW;
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(GraphicsContext g) {
		model.clear(g);
		drawBg(g, bgColor);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
		g.setFill(highScoreColor);
		g.setFont(new Font(60));
		g.fillText("Score:", (SCREEN_WIDTH/3)-100, SCREEN_HEIGHT/5);
		g.fillText(stringScore, (SCREEN_WIDTH / 3 + 100), SCREEN_HEIGHT / 5);
		drawHighScoreButton(g);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i GameOverState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(new MenuState(model));
		}
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void screenPressed(MouseEvent event) {
		if ((event.getSceneX() > 385) && (event.getSceneY() > 335)) {
			if ((event.getSceneX() < 485) && (event.getSceneY() < 435))
				model.switchState(new HighScoreState(model, myHighScore));	
		}


	}
}
