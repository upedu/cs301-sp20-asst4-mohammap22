package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 *
 * Abstact Class that defines necessary parameters and methods for Paper, Rock, and Scissors
 */


public abstract class rpsObj {

    //instance variables
    protected float xPos;
    protected float yPos;
    protected float xSize;
    protected float ySize;
    protected float xSpeed;
    protected float ySpeed;
    protected boolean dead;

    //contructor for subclasses
    public rpsObj() {
    }

    public rpsObj(float xP, float yP, float yS, float xS, float xSpd, float ySpd) {
        //Initialize all the variables based on the contructor
        xPos = xP;
        yPos = yP;
        xSize = xS;
        ySize = yS;
        xSpeed = xSpd;
        ySpeed = ySpd;
        dead = false;
    }

    //whenever the animator detects that the object hits the left or right wall
    //it reverses direction and slows down by 10%

    //sets the destroy variable to true to show it's destroyed
    public void dead() {
        dead = true;
    }

    //checks to see if the object is destroyed
    public boolean isDead() {
        return dead;
    }

    //generic draw method will be implemented further in subclasses
    public void draw(Paint objColor, Canvas c) {
    }
    public void ticked(){
        xPos += xSpeed;
        yPos += ySpeed;

        ySpeed += 0.8;

    }

    public float getPosX() {
        return xPos;
    }

    public float getPosY() {
        return yPos;
    }

    public void setxPos(float newX) {
        xPos = newX;
    }

    public void setyPos(float newY) {
        yPos = newY;
    }

    public void bounceX(){
        xSpeed = -(float)(0.9*xSpeed);
    }

    public void bounceY(){
        ySpeed = -(float) (0.9*ySpeed);
    }


    public float getSizeX(){
        return xSize;
    }
    public float getSizeY(){
        return ySize;
    }
}


