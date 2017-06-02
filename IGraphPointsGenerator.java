package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by vostanin on 4/18/17.
 */

public abstract class IGraphPointsGenerator implements Iterable<GraphInfo>
{
    void generateOne(){}

    void generateRange(){}

    void getPoint( float re, float im, Vector3 result ){}

    void setSlope( float slope ){}
}
