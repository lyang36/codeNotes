package com.yang.drawpad;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * <p>Use quadratic Bezier line to smooth a hand drew path. 
 * Note that this has constant second derivatives but the first
 * derivatives match at the glue points.</p>
 * 
 * <p>Denote the list of points as p_0, p_1, ... p_n, 
 * let c_i = (pi+p_{i_1})/2 as the middle point of pi and p_{i-1}.</p>
 * <p>We can model the quadratic Bezier line as following:</p>
 * <p>G_i(t)=(1-t)^2c_i +2(1-t)tp_i + t^2 c_{i+1}</p>
 * <p>Consider the derivative of t:</p> 
 * <p>G_i'(t) = -2(1-t)c_i + 2(1-t-t)p_i + 2t c_{i+1}, and </p>
 * <p>G_i''(t) = 2c_i -4p_i + 2c_{i+1} = 2p_{i-1} + 2 p_{i+1}</p>
 * <p>Thus the second derivatives are constants. To show that the 
 *  first derivatives match at each c_i, we can plug in t=0, and t=1.</p>
 * <p>G_i'(0) = 2p_i-2c_i = p_i - p_{i-1}</p>
 * <p>G_{i-1}'(1) = -2p_{i-1}+2c_i = p_i - p_{i-1} = G_i'(0)</p>
 * 
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
    public Path constructPath() {
        return path;
    }

}
