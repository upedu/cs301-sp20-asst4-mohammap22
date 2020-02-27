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

//general object for all shapes
public abstract class rpsObj {

    //instance variables for rock, paper, and scissors
    protected float xPos;
    protected float yPos;
    protected float xSize;
    protected float ySize;
    protected float xSpeed;
    protected float ySpeed;
    protected boolean dead;
    protected float gravityX;
    protected float gravityY;

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
        gravityX = 0;
        gravityY = 0;
    }


    //variable for object got deleted
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
        xPos += xSpeed; //this method moved the shaped around based on its given speed
        yPos += ySpeed;
        ySpeed += 0.8; //this is for gravity
        xSpeed += gravityX*10;
        ySpeed += gravityY*10;
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
        xSpeed =-(float)(0.9*xSpeed); //reduces the speed by 10% every time hits the floor
    }

    public void bounceY(){
        ySpeed =-(float)(0.9*ySpeed); //reduces the speed every time hits a side by 10%
    }

    public float getSizeX() {
        return xSize;
    }
    public float getSizeY(){
        return ySize;
    }
    public float getySpeed(){
        return ySpeed;
    }
    public float getxSpeed(){
        return xSpeed;
    }
    public void setGravX(float newXGrav){
        gravityX += newXGrav;
    }

    public void setGravY(float newYGrav){
        gravityY += newYGrav;
    }
    public void rGrav(){
        gravityX = 0;
        gravityY = 0;
    }
}


