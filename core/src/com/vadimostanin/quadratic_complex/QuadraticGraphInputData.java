package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.utils.Array;

/**
 * Created by vostanin on 4/13/17.
 */

public class QuadraticGraphInputData
{
    private float mA = 0.0f;
    private float mB = 0.0f;
    private float mC = 0.0f;

    private Complex mRoot = new Complex();

    public QuadraticGraphInputData( float a, float b, float c, Complex root )
    {
        mA = a;
        mB = b;
        mC = c;
        mRoot.set( root );
    }

    public float getA()
    {
        return mA;
    }

    public float getB()
    {
        return mB;
    }

    public float getC()
    {
        return mC;
    }

    public Complex getRoot()
    {
        return mRoot;
    }
}
