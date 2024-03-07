package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.GameModel;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

// Superklass för alla states

public abstract class GameState {

	protected GameModel model;

	public GameState(GameModel model) {
		this.model = model;
	}

	public abstract void update();

	public abstract void draw(GraphicsContext g);

	public abstract void keyPressed(KeyEvent key);

	public void drawBg(GraphicsContext g, Color color) {
		g.setFill(color);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	public abstract void screenPressed(MouseEvent event);

	public void drawHighScoreButton(GraphicsContext g) {
		g.setFill(Color.BLACK);
		g.fillRect(385, 335, 100, 50);
		g.setFill(Color.WHITE);
		g.setFont(new Font(10));
		g.fillText("See highscores", 400, 360);
	}

	public abstract void activate();

	public abstract void deactivate();
}
