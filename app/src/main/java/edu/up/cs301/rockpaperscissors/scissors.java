package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 */

public class scissors extends rpsObj {

    public scissors(float xP, float yP, float xS, float yS, float xSpd, float ySpd){
        super(xP, yP, xS, yS, xSpd, ySpd);
    }


    // sets a paint, creates a path, and then draws the path and paint
    //creates a very ugly pair of scissors
    @Override
    public void draw(Paint objColor, Canvas c) {
        if(dead){
            return;//return nothing if dead
        }
        Paint SPaint = objColor;
        SPaint.setColor(Color.rgb(140,18,255));
        c.drawRect(xPos, yPos, xPos+xSize, yPos+ySize, SPaint); //draws purple square for scissors

    }
}