package main;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import states.GameState;
import states.MenuState;

// byter mellan de olika states som finns i spelet. Aktiverar ny state om nödvändigt. Callar på update och draw funktion i currentState.

public class GameModel {

	private GameState currentState;

	public GameModel() {
		// We start out in the MenuState.
		this.currentState = new MenuState(this);
	}

	public void switchState(GameState nextState) {
		currentState.activate();
		currentState = nextState;
	}

	public void keyPressed(KeyEvent key) {
		currentState.keyPressed(key);
	}

	public void update() {
		currentState.update();
	}

	public void clear(GraphicsContext g) {
		g.clearRect(0,0, SCREEN_WIDTH,SCREEN_HEIGHT);
	}

	public void draw(GraphicsContext g) {
		currentState.draw(g);
	}

	public void screenPressed(MouseEvent event) {
		currentState.screenPressed(event);
	}

}
