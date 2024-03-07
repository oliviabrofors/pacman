package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameModel;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

// håller koll på menyn och initierar playstate

public class MenuState extends GameState {

	private String informationText;
	private Color bgColor;
	private Color fontColor;
	private PlayState playState;

	public MenuState(GameModel model) {
		super(model);
		playState = new PlayState(model);
		informationText = "Welcome to PACMAN\nPress Enter To Play\nEscape to exit\n\n\n\n\nStar = double points\nPoison = half the points\nSmiley = you can eat ghosts ";
		bgColor = Color.LIGHTBLUE;
		fontColor = Color.BLACK;
	}

	@Override
	public void draw(GraphicsContext g) {
		model.clear(g);
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		drawBg(g, bgColor);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
		drawHighScoreButton(g);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(playState);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void update() {
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
				model.switchState(new HighScoreState(model, 0));	
		}

	}

}
