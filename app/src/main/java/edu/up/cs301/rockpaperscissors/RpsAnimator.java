package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.animation.Animator;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;


/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @version August 2016
 */

//FOR ENHANCEMENTS I DID ADD THE "BOUNCE" MECHANIC, BUT IT SOMETIMES BUGS OUT WHEN OBJECTS ARE CREATED WHEN OVERLAPPED
public class RpsAnimator implements Animator {

	// THIS CLAS IS PRESENTLY DUMMIED-UP


	// instance variables

	ArrayList<rpsObj> RpsList = new ArrayList<>(); //this arrayList is for keeping track of the all the objects on screen
	private rpsObj tempOne; //object 1
	private rpsObj tempTwo; //object 2

	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 30;
	}

	/**
	 * The background color: a light blue.
	 *
	 * @return the background color onto which we will draw the image.
	 */
	public int backgroundColor() {
		// create/return the background color
		return Color.rgb(140, 180, 255); // light blue
	}

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void add(int num) { //this method randomly assigns objects to add to the room
		for (int i = 0; i < num; i++) { //will add all the same type of object each time
			int type = (int) (Math.random() * 3); //used to choose which object is going to be added
			float ranX = (float) (2 + (230 * Math.random())); //random size for x
			float rVelX = (float) (30 * Math.random() - 14); //random vel
			float rVelY = (float) (30 * Math.random() - 14); //random vel
			float randX = (float) (100 + 1500 * Math.random());//and random positions on canvis
			float randY = (float) (100 + 1000 * Math.random());
			rpsObj tempAdd;
			if (type == 0) { //adds a rock
				tempAdd = new rock(randX, randY, ranX, ranX, rVelX, rVelY);
				RpsList.add(tempAdd); //adds objects to the arrayList
			}
			if (type == 1) {//adds a paper
				tempAdd = new paper(randX, randY, ranX, ranX, rVelX, rVelY);
				RpsList.add(tempAdd);
			} else {//else add scissors
				tempAdd = new scissors(randX, randY, ranX, ranX, rVelX, rVelY);
				RpsList.add(tempAdd);
			}
		}
	}

	public void onTouch(MotionEvent event) { //if you press down on the screen, objects get closer to it
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == ACTION_DOWN || event.getAction() == ACTION_MOVE) {
			gravitate(x, y); //gravitates towards position
		} else if (event.getAction() == ACTION_UP) {
			gravitate(-1, -1); //once finger goes up, the gravity is set back to normal
		}
	}
	public void gravitate(float xdirection, float ydirection) { //my friend helped me set up this method
		float a, b, c, grav;
		for (int k = 0; k < RpsList.size(); k++) {//first checks to make sure the gravity should / shouldn't be reset
			if (xdirection == -1 && ydirection == -1) {
				RpsList.get(k).rGrav();
			}
			a = xdirection - RpsList.get(k).getPosX(); //checking direction of velocity
			b = ydirection - RpsList.get(k).getPosY();
			c = (float) Math.sqrt((a * a + b * b)); //gets the direct line from the object to the point clicked
			grav = (float) (1 / (c + 0.1)); //the farther away, less powerful it is
			if (a > 0) {
				RpsList.get(k).setGravX(grav); //if facing toward and going in x direction
			}
			if (a < 0) {
				RpsList.get(k).setGravX(-grav); //going opposite direction in x
			}
			if (b > 0) {
				RpsList.get(k).setGravY(grav); //going same direction in y
			}
			if (b < 0) {
				RpsList.get(k).setGravY(-grav); //opposite direction in y
			}
		}
	}
//class RpsAnimator

	public void tick(Canvas g) { //method used for moving objects around
		int height = g.getHeight();
		int width = g.getWidth();
		if (RpsList.size() == 0) { //adds 20 random objects to the room
			add(20);
		}
		Paint p = new Paint();
		if (RpsList.size() > 0) { //if there are objects in the list
			for (int i = 0; i < RpsList.size(); i++) {
				tempOne = RpsList.get(i); //assign temp one to the first object
				if (tempOne.getPosX() < 0) { //if the object hits a wall, reverse its momentum
					tempOne.setxPos(1); //making sure the object doesn't get stuck in the wall
					tempOne.bounceX(); //reverse its speed and slightly slow it down
				}
				if (tempOne.getPosX() + tempOne.getSizeX() > width) { //if hits right side of screen
					tempOne.setxPos(width - tempOne.getSizeX() - 1);
					tempOne.bounceX(); //reverse its direction
				}
				if ((tempOne.getPosY() + tempOne.getSizeY() > height)) { //if it hits the bottom of the screen
					tempOne.setyPos(height - tempOne.getSizeY() - 1);
					tempOne.bounceY(); //reverse its direction in the y
				}
				for (int j = 0; j < RpsList.size(); j++) { //this for loop is for overlaps
					tempTwo = RpsList.get(j);
					if (!tempOne.isDead() && !tempTwo.isDead()) { //if objects exist
						if (tempOne instanceof paper && tempTwo instanceof paper && overlaps(tempOne, tempTwo)) { //if they are both paper and overlap
							if (tempTwo.getxSpeed() > 0 && tempOne.getxSpeed() < 0) {
								tempOne.bounceY(); //bounce off one another
								tempOne.bounceX();
								tempTwo.bounceX();
								tempTwo.bounceY();
							}
						}
						if (tempOne instanceof rock && tempTwo instanceof rock && overlaps(tempOne, tempTwo)) { //Same thing for rocks
							if (tempTwo.getxSpeed() > 0 && tempOne.getxSpeed() < 0) {
								tempOne.bounceY();
								tempOne.bounceX();
								tempTwo.bounceX();
								tempTwo.bounceY();
							}
						}
						if (tempOne instanceof scissors && tempTwo instanceof scissors && overlaps(tempOne, tempTwo)) {//same for scissors
							if (tempTwo.getxSpeed() > 0 && tempOne.getxSpeed() < 0) {
								tempOne.bounceY();
								tempOne.bounceX();
								tempTwo.bounceX();
								tempTwo.bounceY();
							}
						}
						if ((tempOne != tempTwo) && overlaps(tempOne, tempTwo)) { //now for different kinds of objects
							if (tempOne instanceof paper && tempTwo instanceof rock) {
								tempTwo = RpsList.get(j); //if a paper and rock collide, delete the rock
								tempTwo.dead();
								return;
							}
							if (tempOne instanceof paper && tempTwo instanceof scissors) {
								tempOne = RpsList.get(i); //if a paper and scissors collide, delete the paper
								tempOne.dead();
							}
							if (tempOne instanceof scissors && tempTwo instanceof rock) {
								tempOne = RpsList.get(i); //if a scissors and rock collide, delete the scissors
								tempOne.dead();
							}
						}
					}

				}
				if (!tempOne.isDead()) { //if the object isn't destroyed move all the objects and continue
					tempOne.ticked();
					tempOne.draw(p, g); //draw object with new position
				}
			}

		}
	}


	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
	public boolean doPause() {
		return false;
	}

	/**
	 * Tells that we never stop the animation.
	 *
	 * @return indication of whether to quit.
	 */
	public boolean doQuit() {
		return false;
	}

	/**
	 * reverse the ball's direction when the screen is tapped
	 */

	public boolean overlaps(rpsObj objectOne, rpsObj objectTwo) { //this is a method to determine if an object overlaps over another one
		if ((objectOne.getPosX() + objectOne.getSizeX()) > objectTwo.getPosX() && (objectOne.getPosX() + objectOne.getSizeX()) < (objectTwo.getPosX() + objectTwo.getSizeX())) {
			if (objectOne.getPosY() > objectTwo.getPosY() && objectOne.getPosY() < (objectTwo.getPosY() + objectTwo.getSizeY())) {
				return true; //return true if the right side of the rectangle is overlapping the left side of the first object or vise versa
			}
			if ((objectOne.getPosY() + objectOne.getSizeY()) > objectTwo.getPosY() && (objectOne.getPosY() + objectOne.getSizeY()) < (objectTwo.getPosY() + objectTwo.getSizeY())) { //same for y
				return true;
			}
		}
		if (objectOne.getPosX() > objectTwo.getPosX() && objectOne.getPosX() < (objectTwo.getPosX() + objectTwo.getSizeX())) { //checks if the left side of the objects are overlapping
			if (objectOne.getPosY() > objectTwo.getPosY() && objectOne.getPosY() < (objectTwo.getPosY() + objectTwo.getSizeY())) { //same for y
				return true;
			}
			if ((objectOne.getPosY() + objectOne.getSizeY()) > objectTwo.getPosY() //checks corners
					&& (objectOne.getPosY() + objectOne.getSizeY()) < (objectTwo.getPosY() + objectTwo.getSizeY())) {
				return true;
			}
		}
		return false; //return false if all else fails
	}

}
