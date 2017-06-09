package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.utils.Array;

/**
 * Created by vostanin on 4/12/17.
 */

public class QubicEquationSolver
{
    private float mA = 0.0f;
    private float mB = 0.0f;
    private float mC = 0.0f;
    private float mD = 0.0f;

    private Array<Complex> mSolutions = new Array<Complex>();
    private Complex mTempComplex = new Complex();
    private Complex mYComplex = new Complex();
    private Complex mXValue = new Complex();

    public QubicEquationSolver( float a, float b, float c, float d )
    {
        mA = a;
        mB = b;
        mC = c;
        mD = d;
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

    public Array<Complex> solve()
    {
        return mSolutions;
    }

    public Complex getY( Complex xValue )
    {
        xValue.mult( xValue, mYComplex ).mult( xValue, mYComplex );//.mult( mA, mYComplex);
        mYComplex.add( xValue.mult( mB, mTempComplex ).mult( xValue, mTempComplex ), mYComplex );
        mYComplex.add( xValue.mult( mC, mTempComplex ), mYComplex );
        mYComplex.add( mD, 0.0f, mYComplex );
//        mYComplex.set( 0.0f, 0.0f );
        return mYComplex;
    }

    public Complex getY( float re, float im )
    {
        mXValue.set( re, im );
        return getY( mXValue );
    }
}
