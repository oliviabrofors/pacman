
package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import states.PlayState;

//Klass som hanterar grafiken för star, en powerup.
//konverterar bilden och ritar ut sig själv
//Kommentar efter komplettering: Wall håller koll på om ghost eller pacman intersectar med dem
public class Star extends StillObject {

	private Image star;
	private Star myStar = null;

	public Star(double x, double y, PlayState playState) {
		super(x, y, playState);

		try {
			star = new Image(new FileInputStream("star.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(star, this.x, this.y, w, h);
	}

	@Override
	public void update() {
		if (intersectObject(myState.getMyPacman()) == true) {
//			myState.getMyPacman().intersectStar();
			myState.setBgColor(Color.LIGHTGOLDENRODYELLOW);
			myState.getMyPacman().setPoints(200);
		}
	}

	@Override
	public boolean intersectObject(MovableObject object) {
		Rectangle2D nextRect = new Rectangle2D(object.getX(), object.getY(), 50, 50);
		Rectangle2D thisRect = new Rectangle2D(this.x, this.y, 50, 50);
		if (nextRect.intersects(thisRect)) {
			setRemove();
			return true;
		}
		return false;
	}

	@Override
	public void setRemove() {
		myStar = this;
	}

	@Override
	public Star getRemove() {
		return myStar;
	}

}
