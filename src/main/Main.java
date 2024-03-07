package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

// Main håller koll på stage och scene och öppnar grafikrutan

public class Main extends Application {

	final double nanoPerUpdate = 1000.0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage gameStage) throws Exception {
		gameStage.setTitle("PACMAN");
		gameStage.setWidth(1150); // 23 x 50
		gameStage.setHeight(678); // 13 x 50
		GameModel model = new GameModel();
		GameFrame frame = new GameFrame(model, 1200, 800);
		Scene gameScene = new Scene(frame);

		gameStage.setScene(gameScene);

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				model.keyPressed(event);
			}
		});

		gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				model.screenPressed(event);
				frame.repaint();
			}
		});

		new AnimationTimer() {
			long lastUpdate = 0;

			public void handle(long now) {

				if ((now - lastUpdate) > nanoPerUpdate) {					
					frame.repaint();
					model.update();
				}
			}
		}.start();

		gameStage.show();

	}
}