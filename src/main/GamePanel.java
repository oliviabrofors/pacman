package main;

import javafx.scene.canvas.Canvas;

//Canvas så att spelet kan ritas ut

public class GamePanel extends Canvas {

	private GameModel model;
	
    public GamePanel(final GameModel model, int width, int height) {
        this.model = model;
        this.setWidth(width);
        this.setHeight(height);
    }

    public void repaint() {
    	model.draw(getGraphicsContext2D());
    }
}
