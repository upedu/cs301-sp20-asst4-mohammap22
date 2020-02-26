package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * rock-paper-scissors animation
 *
 * @author Steve Vegdahl
 * @author Samuel DeWhitt
 * @version November 2016
 */

public class paper extends rpsObj {

    public paper(float xP, float yP, float xS, float yS, float xSpd, float ySpd) {
        super(xP, yP, xS, yS, xSpd, ySpd);
    }
    // sets a paint, creates a path, and then draws the path and paint
    @Override
    public void draw(Paint objColor, Canvas c) {
        if(dead){
            return;
        }
        Paint paper = objColor;
        paper.setColor(Color.WHITE);
        c.drawRect(xPos, yPos, xPos+xSize, yPos+ySize, paper);

    }
}