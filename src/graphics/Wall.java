package graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import states.PlayState;
import static constants.Constants.objectSize;
import static constants.Constants.blockSize;

//Klass som hanterar grafiken för väggarna i spelet.
//konverterar bilden och ritar ut sig själv
//Kommentar efter komplettering: Wall håller koll på om ghost eller pacman intersectar med dem

public class Wall extends StillObject {

	public Wall(double x, double y, PlayState playState) {
		super(x, y, playState);
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.setFill(Color.DARKBLUE);
		gc.fillRect(x, y, w, h);
	}

	@Override
	public void update() {
		if (this.intersectObject(myState.getMyPacman()) == true) {
			myState.getMyPacman().changeDirection();
		}
		for (Ghost ghost : myState.getGhosts()) {
			if (this.intersectObject(ghost) == true) {
				ghost.changeDirection();
			}
		}
	}

	@Override
	public boolean intersectObject(MovableObject object) {
		MovableObject myObject = object;
		Rectangle2D objectRect = new Rectangle2D(myObject.getX(), myObject.getY(), objectSize, objectSize);
		Rectangle2D wallRect = new Rectangle2D(this.x, this.y, blockSize, blockSize);
		if (objectRect.intersects(wallRect)) {
			return true;
		}
		return false;
	}

	@Override
	public StillObject getRemove() {
		return null;
	}

	@Override
	public void setRemove() {
	}

}
