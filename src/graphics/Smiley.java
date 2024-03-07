/**
 * 
 */
package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import states.PlayState;

/**
 * @author sophiacederkvisthansen
 *Klass som hanterar grafiken för smiley, en powerup.
 *konverterar bilden och ritar ut sig själv
 */
public class Smiley extends StillObject{

	private Image smiley;
	private Smiley mySmiley = null;
	//private static Boolean canEatGhost = false;
	

	public Smiley(double x, double y, PlayState playState) {
		super(x, y, playState);
		try {
			smiley = new Image(new FileInputStream("smiley.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(smiley, this.x, this.y, w, h);
	}

	@Override
	public void update() {
		if (intersectObject(myState.getMyPacman()) == true) {
			myState.setBgColor(Color.PINK);
			System.out.println("You now have smiley-power and you can kill a ghost!");
			myState.getMyPacman().setCanEatGhost(true);
		}
	}

	@Override
	public boolean intersectObject(MovableObject object) {
		Rectangle2D nextRect = new Rectangle2D(object.getX(), object.getY(), 50, 50);
		Rectangle2D thisRect = new Rectangle2D(this.x, this.y, 50, 50);
		if (nextRect.intersects(thisRect)) {
			setRemove();
			return true;	
		} return false;
	}

	@Override
	public void setRemove() {
		mySmiley = this;
	}
	
	@Override
	public Smiley getRemove() {
		return mySmiley;
	}
	
//	public static Boolean getCanEatGhost() {
//		return canEatGhost;
//	}

}
