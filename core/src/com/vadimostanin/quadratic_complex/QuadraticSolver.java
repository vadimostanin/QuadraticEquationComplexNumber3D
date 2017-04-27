package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.utils.Array;

/**
 * Created by vostanin on 4/12/17.
 */

public class QuadraticSolver
{
    private float mA = 0.0f;
    private float mB = 0.0f;
    private float mC = 0.0f;

    private Array<Complex> mSolutions = new Array<Complex>();
    private Complex mTempComplex = new Complex();
    private Complex mYComplex = new Complex();
    private Complex mXValue = new Complex();

    public QuadraticSolver( float a, float b, float c )
    {
        mA = a;
        mB = b;
        mC = c;
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

    public Array<Complex> solve()
    {
        if( mSolutions.size > 0 )
        {
            return mSolutions;
        }
        final float DiscremenantSquare = mB * mB - 4 * mA * mC;

        if( DiscremenantSquare < 0.0f )
        {
            final float Im = ( float )( Math.sqrt( Math.abs( DiscremenantSquare ) ) / ( 2.0f * mA ) );
            final float Re = ( -1 ) * mB / ( 2.0f * mA );

            mSolutions.add( new Complex( Re, Im ));
            mSolutions.add( new Complex( Re, ( -1 ) * Im ));
        }
        else
        {
            float Re = ( -1 ) * mB / ( 2.0f * mA ) + ( float )( Math.sqrt( Math.abs( DiscremenantSquare ) ) / ( 2.0f * mA ) );
            mSolutions.add( new Complex( Re, 0.0f ));
                  Re = ( -1 ) * mB / ( 2.0f * mA ) - ( float )( Math.sqrt( Math.abs( DiscremenantSquare ) ) / ( 2.0f * mA ) );
            mSolutions.add( new Complex( Re, 0.0f ));
        }

        return mSolutions;
    }

    public Complex getY( Complex xValue )
    {
        xValue.mult(xValue, mYComplex).mult( mA, mYComplex);
        mYComplex.add(xValue.mult( mB, mTempComplex), mYComplex);
        mYComplex.add( mC, 0.0f, mYComplex);
//        mYComplex.set( 0.0f, 0.0f );
        return mYComplex;
    }

    public Complex getY( float re, float im )
    {
        mXValue.set( re, im );
        return getY( mXValue );
    }
}
