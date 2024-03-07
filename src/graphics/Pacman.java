/**
 * 
 */
package graphics;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

// Klass för pacman som även är spelets "player". Pacman håller koll på poäng, liv 
//och de olika power/svagheterna den får vid krock med annat föremål. 
//Kommentar efter komplettering: Pacman håller inte koll på om den  intersectar med ngt, 

public class Pacman extends MovableObject{

	private Image pacman;
	private double dirX, dirY;
	private int bananaPoints = 0;
	private int life = 3;
	private int addPoints = 100;
	private double startX, startY;
	private boolean maxPoints;
	private boolean canEatGhost = false;
	private Ghost myGhost = null;

	public Pacman(double x, double y, double dirX, double dirY, PlayState playState) {
		super(x, y, dirX, dirY, playState);
		startX = x;
		startY = y;

		try {
			pacman = new Image(new FileInputStream("pacman.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void paintYourself(GraphicsContext gc, double w, double h) {
		gc.drawImage(pacman, getX(), getY(), w, h);
	}

	@Override
	public void update() {
		dirX = getDirection().getDirX();
		dirY = getDirection().getDirY();
		setPosition(getX()+dirX, getY()+dirY);
	}

	public void setPoints(int points) {
		addPoints = points;
	}

	public void removeLife() {
		life -= 1;
		this.setPosition(startX, startY);
		System.out.println(life);
	}

	public int getLife() {
		return life;
	}

	public void addBananaPoints() {
		bananaPoints += addPoints;
		if (myState.getBananas().isEmpty()){
			setBananaPoints(0);
			maxPoints = true;			
		}
	}

	public int getBananaPoints() {
		return bananaPoints;
	}

	public void setBananaPoints(int i) {
		bananaPoints = i;
	}

	public void setMax() {
		maxPoints = false;
	}

	public boolean getMax() {
		return maxPoints;
	}

	public void setGhost(Ghost ghost) {
		myGhost = ghost;
	}
	
	public Ghost getGhost() {
		return myGhost;
	}

	public void changeDirection() {
	this.getDirection().setDirection(dirX*-1, dirY*-1);
	}

	public Boolean canEatGhost() {
		return canEatGhost;
	}

	public void setCanEatGhost(boolean value) {
		canEatGhost = value;
		
	}
	

}


