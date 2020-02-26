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
public class RpsAnimator implements Animator {

	// THIS CLAS IS PRESENTLY DUMMIED-UP


	// instance variables

	ArrayList<rpsObj> RpsList = new ArrayList<>();
	private rpsObj tempOne;
	private rpsObj tempTwo;

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
		return Color.rgb(140,180,255); // light blue
	}

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void add(int num) {
		int type =(int)(Math.random()*3);
		for (int i = 0; i < num; i++) {
			float randSizeX = (float) (2 + (230 * Math.random()));
			float randSizeY = (float) (2 + (230 * Math.random()));
			float randVelX = (float) (20 * Math.random() - 14);
			float randVelY = (float) (20 * Math.random() - 14);
			float randX = (float) (100 + 1500 * Math.random());
			float randY = (float) (100 + 1000 * Math.random());
			rpsObj tempAdd;
			if (type == 0) {
				tempAdd = new rock(randX, randY, randSizeX, randSizeY, randVelX, randVelY);
				RpsList.add(tempAdd);
			}
			if (type == 1) {
				tempAdd = new paper(randX, randY, randSizeX, randSizeY, randVelX, randVelY);
				RpsList.add(tempAdd);
			}
			else {
				tempAdd = new scissors(randX, randY, randSizeX, randSizeY, randVelX, randVelY);
				RpsList.add(tempAdd);
			}
		}
	}
	public void onTouch(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == ACTION_DOWN || event.getAction() == ACTION_MOVE) {
			//gravitate(x, y);
		} else if (event.getAction() == ACTION_UP) {
			//gravitate(-1, -1);
		}

	}
	public void tick(Canvas g) {
		int height = g.getHeight();
		int width = g.getWidth();
		if(RpsList.size() == 0) {
			for (int i = 0; i < 20; i++) {
				add(1);
			}
		}
		Paint p = new Paint();
		if (RpsList.size() > 0) {
			for (int i = 0; i < RpsList.size(); i++) {
				tempOne = RpsList.get(i);
				if (tempOne.getPosX() < 0) {
					tempOne.setxPos(1);
					tempOne.bounceX();
				}
				if (tempOne.getPosX() + tempOne.getSizeX() > width) {
					tempOne.setxPos(width - tempOne.getSizeX() - 1);
					tempOne.bounceX();
				}
				if ((tempOne.getPosY() + tempOne.getSizeY() > height)) {
					tempOne.setyPos(height - tempOne.getSizeY() - 1);
					tempOne.bounceY();
				}
				for (int j = 0; j < RpsList.size(); j++) {
					tempTwo = RpsList.get(j);
					if (!tempOne.isDead() && !tempTwo.isDead()) {
						if(tempOne == tempTwo && overlaps(tempOne, tempTwo)){
							tempOne.bounceY();
							tempTwo.bounceY();
						}
						if ((tempOne != tempTwo) && overlaps(tempOne, tempTwo)) {
							if (tempOne instanceof paper && tempTwo instanceof rock) { //Lab taught me this
								tempTwo =RpsList.get(j);
								tempTwo.dead();
								return;
							}
							if (tempOne instanceof paper && tempTwo instanceof scissors) {
								tempOne = RpsList.get(i);
								tempOne.dead();
							}
							if (tempOne instanceof scissors && tempTwo instanceof rock) {
								tempOne = RpsList.get(i);
								tempOne.dead();
							}
							if (tempOne instanceof scissors && tempTwo instanceof paper) {
								tempTwo = RpsList.get(j);
								tempTwo.dead();
							}
							if (tempOne instanceof rock && tempTwo instanceof paper) {
								tempOne = RpsList.get(i);
								tempOne.dead();
							}
							if (tempOne instanceof rock && tempTwo instanceof scissors) {
								tempTwo = RpsList.get(j);
								tempTwo.dead();
							}
						}
					}

				}
				if (!tempOne.isDead()) {
					tempOne.ticked();
					tempOne.draw(p, g);
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

	public boolean overlaps(rpsObj t1, rpsObj t2) {
		if ((t1.getPosX() + t1.getSizeX()) > t2.getPosX() && (t1.getPosX() + t1.getSizeX()) < (t2.getPosX() + t2.getSizeX())) {
			if (t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY() + t2.getSizeY())) {
				return true;
			}
			if ((t1.getPosY() + t1.getSizeY()) > t2.getPosY() && (t1.getPosY() + t1.getSizeY()) < (t2.getPosY() + t2.getSizeY())) {
				return true;
			}
		}
		if (t1.getPosX() > t2.getPosX() && t1.getPosX() < (t2.getPosX() + t2.getSizeX())) {
			if (t1.getPosY() > t2.getPosY() && t1.getPosY() < (t2.getPosY() + t2.getSizeY())) {
				return true;
			}
			if ((t1.getPosY() + t1.getSizeY()) > t2.getPosY()
					&& (t1.getPosY() + t1.getSizeY()) < (t2.getPosY() + t2.getSizeY())) {
				return true;
			}
		}
		return false;
	}
}//class RpsAnimator
