package main;

import javafx.scene.layout.HBox;

// initerar gamepanel och ser till så att den canvasen visas på skrämen

public class GameFrame extends HBox {
	private GamePanel gamePanel;

	public GameFrame(GameModel model, int width, int height) {

		gamePanel = new GamePanel(model, width, height);
		this.getChildren().add(gamePanel);
	}

	public void repaint() {
		gamePanel.repaint();
	}

}
