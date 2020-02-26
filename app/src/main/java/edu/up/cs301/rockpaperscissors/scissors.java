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
 * @version November 2016/4
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
            return;
        }
        Paint SPaint = objColor;

        Path p = new Path();
        p.moveTo(xPos + 0*xSize, yPos + 0*ySize);
        p.lineTo(xPos + 9/4*xSize, yPos + (float)9/4*ySize);
        p.lineTo(xPos + 12/4*xSize, yPos + 16/4*ySize);
        p.lineTo(xPos + 14/4*xSize, yPos + 23/4*ySize);
        p.lineTo(xPos + 24/4*xSize, yPos + 23/4*ySize);
        p.lineTo(xPos + 16/4*xSize, yPos + 16/4*ySize);
        p.lineTo(xPos + 25/4*xSize, yPos + 17/4*ySize);
        p.lineTo(xPos + 25/4*xSize, yPos + 12/4*ySize);
        p.lineTo(xPos + 17/4*xSize, yPos + 11/4*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 7/4*ySize);
        p.lineTo(xPos + 0*xSize, yPos + 0*ySize);

        Path p2 = new Path();
        p2.moveTo(xPos + 9/4*xSize, yPos + 9/4*ySize);
        p2.lineTo(xPos + 0*xSize, yPos + 7/4*ySize);
        p2.lineTo(xPos + 0*xSize, yPos + 3/4*ySize);
        p2.lineTo(xPos + 3/4*xSize, yPos + 16/4*ySize);
        p2.lineTo(xPos + 9/4*xSize, yPos + 9/4*ySize);

        Path h1 = new Path();
        h1.moveTo(xPos + 13/4*xSize, yPos + 17/4*ySize);
        h1.lineTo(xPos + 15/4*xSize, yPos + 17/4*ySize);
        h1.lineTo(xPos + 18/4*xSize, yPos + 5/4*ySize);
        h1.lineTo(xPos + 16/4*xSize, yPos + 5/4*ySize);
        h1.lineTo(xPos + 13/4*xSize, yPos + 17/4*ySize);

        Path h2 = new Path();
        h2.moveTo(xPos + 17/4*xSize, yPos + 13/4*ySize);
        h2.lineTo(xPos + 22/4*xSize, yPos + 14/4*ySize);
        h2.lineTo(xPos + 22/4*xSize, yPos + 16/4*ySize);
        h2.lineTo(xPos + 18/4*xSize, yPos + 14/4*ySize);
        h2.lineTo(xPos + 17/4*xSize, yPos + 13/4*ySize);

        SPaint.setColor(Color.GRAY);
        c.drawPath(p, SPaint);
        c.drawPath(p2, SPaint);

        SPaint.setColor(Color.rgb(140,18,255));
        c.drawPath(h1, SPaint);
        c.drawPath(h2, SPaint);


    }
}