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
    	final double a = getA();
    	final double b = getB();
    	final double c = getC();
    	final double d = getD();
    	final double f = ( ( ( 3 * c ) / a ) - ( ( ( b * b ) / ( a * a ) ) ) ) / 3.0f;
		final double g = ( ( ( 2 * ( ( Math.pow( b, 3.0 ) ) / ( Math.pow( a, 3.0 ) ) ) - ( 9.0 * b * c / ( a * a ) ) + ( ( 27.0 * ( d / a ) ) ) ) ) / 27.0); // Math.pow(b,3)
		final double h = ( ( ( g * g ) / 4.0 ) + ( ( f * f * f ) / 27.0f ) );
		
		if( h > 0 )
		{
	        final float m = (float) ( -( g / 2.0 ) + ( Math.sqrt( h ) ) );
	        double k = 1.0;
	        if( m < 0 )
        	{
	        	k = -1.0;
        	}
	        else
	        {
	        	k = 1.0;
	        }
	        double m2 = ( Math.pow( ( m * k ), ( 1.0 / 3.0 ) ) );
	        m2 = m2 * k;
	        k = 1.0f;
	        final double n = -( g / 2.0 ) - ( Math.sqrt( h ) );
	        if( n < 0 )
	        {
	        	k = -1.0f;
	        }
	        else
	        {
	        	k = 1.0f;
	        }
	        double n2 = ( Math.pow( ( n * k ), ( 1.0 / 3.0 ) ) );
	        n2 = n2 * k;
	        k = 1.0;
	        mSolutions.add( new Complex( ( ( m2 + n2 ) - ( b / ( 3.0 * a ) ) ), 0.0 ) );
	        mSolutions.add( new Complex( -1.0 * ( m2 + n2 ) / 2.0 - ( b / ( 3 * a ) ), ( ( m2 - n2 ) / 2.0 ) * Math.pow( 3.0, 0.5 ) ) );
	        mSolutions.add( new Complex( -1.0 * ( m2 + n2 ) / 2.0 - ( b / ( 3 * a ) ), ( -1.0 ) * ( ( m2 - n2 ) / 2 ) * Math.pow( 3, 0.5 ) ) );
	    }
		else if( h <= 0 )
		{
	        final double r = ( ( Math.sqrt( ( g * g / 4.0 ) - h ) ) );
	        double k = 1;
	        if( r < 0 )
	        {
	        	k = -1;
	        }
	        double rc = Math.pow( Math.abs( r * k ), 1.0 / 3.0 ) * k;
	        rc *= Math.abs( r * k ) / ( r * k );
	        k = 1.0;
	        final double theta = Math.acos( ( -1.0 ) * g / ( 2.0 * r ) );
	        final double x1 = ( 2.0 * ( rc * Math.cos( theta / 3.0 ) ) - ( b / ( 3.0 * a ) ) );
	        final double x2a = rc * -1.0;//
	        final double x2b = Math.cos( theta / 3.0 );//
	        final double x2c = Math.sqrt( 3.0 ) * ( Math.sin( theta / 3.0 ) );//
	        final double x2d = ( b / 3.0 * a ) * -1;
	        final double x2 = ( x2a * ( x2b + x2c ) ) - ( b / ( 3.0 * a ) );
	        final double x3 = ( x2a * ( x2b - x2c ) ) - ( b / ( 3.0 * a ) );

	        mSolutions.add( new Complex( x1, 0.0 ) );
	        mSolutions.add( new Complex( x2, 0.0 ) );
	        mSolutions.add( new Complex( x3, 0.0 ) );
	    }
		
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
