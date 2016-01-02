package com.yang.drawpad;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * Use quadratic Bezier line to smooth a hand drew path. 
 * Note that this has zero second derivatives but the first
 * derivatives match at the glue points.
 * Created by lyang on 12/31/15.
 */
public class BezierCurveConstructor {
    Path path;

    PointF previousPoint;
    int pointCounter = 0;

    public BezierCurveConstructor() {
        reset();
    }


    /**
     * reset the path
     */
    public void reset() {
        path = new Path();
        pointCounter = 0;
    }


    public void addPoint(float x, float y) {

        pointCounter ++;
        if (pointCounter == 1) {
            path.moveTo(x, y);
            previousPoint = new PointF(x, y);
            return;
        }


        PointF mid = new PointF((x + previousPoint.x) / 2.0f, (y + previousPoint.y) / 2.0f);

        if (pointCounter < 3) {
            path.lineTo(mid.x, mid.y);
        } else {
            path.quadTo(previousPoint.x, previousPoint.y, mid.x, mid.y);
        }
        previousPoint = new PointF(x, y);
    }

    /**
     * construct path by points
     *
     * @return
     */
    Path constructPath() {
        return path;
    }

}
