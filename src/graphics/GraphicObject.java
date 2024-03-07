
package graphics;

import javafx.scene.canvas.GraphicsContext;
import states.PlayState;

/**
 * @author sophiacederkvisthansen 
 * Abstrakt superklass för alla grafiska object i spelet
 * Kommentar efter komplettering: GraphicObject har nu ingen image, subklasserna hanterar utritningen själva
 * Kommentar efter komplettering: GraphicObject har 2 nya underklasser, en som tar hand om "stilla" objekt, och
 * en som tar hand om rörliga objekt.
 */
public abstract class GraphicObject {
	 double x;
	 double y;
	 protected PlayState myState;

	public GraphicObject(double x, double y, PlayState playState) {
		this.x = x;
		this.y = y;
		myState = playState;
	}
	
	public abstract void update();
	

	public abstract void paintYourself(GraphicsContext g, double blockSize, double blockSize2);

}
