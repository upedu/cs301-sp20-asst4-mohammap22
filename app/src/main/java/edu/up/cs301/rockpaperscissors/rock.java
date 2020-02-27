package edu.up.cs301.rockpaperscissors;

import android.graphics.*;
import android.util.Log;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 */
public class rock extends rpsObj {

    public rock(float xP, float yP, float xS, float yS, float xSpd, float ySpd) {
        super(xP, yP, xS, yS, xSpd, ySpd);
    }

    // sets a paint, creates a path, and then draws the path and paint
    @Override
    public void draw(Paint objColor, Canvas c) {
        if(dead){//draw nothing if dead
            return;
        }
        Paint paper = objColor;
        paper.setColor(Color.BLACK);
        c.drawRect(xPos, yPos, xPos+xSize, yPos+ySize, paper); //draws black square for rocks

    }
}

