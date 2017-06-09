package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 6/9/17.
 */

public class QubicGraphInputData
{
    private float mA = 0.0f;
    private float mB = 0.0f;
    private float mC = 0.0f;
    private float mD = 0.0f;

    private Complex mRoot = new Complex();

    public QubicGraphInputData( float a, float b, float c, float d, Complex root )
    {
        mA = a;
        mB = b;
        mC = c;
        mD = d;
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
    
    public float getD()
    {
        return mD;
    }

    public Complex getRoot()
    {
        return mRoot;
    }
}
