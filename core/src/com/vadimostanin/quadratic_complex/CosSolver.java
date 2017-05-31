package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.utils.Array;

public class CosSolver
{
	private Complex mYComplex = new Complex();
	private Complex mXValue = new Complex();

	public CosSolver()
	{
		;
	}

	public Array<Complex> solve()
    {
        

        return null;
    }
	
	

    public Complex getY( Complex xValue )
    {
    	final double x = xValue.re();
    	final double y = xValue.im();

    	final double ch = ( Math.exp( x ) + Math.exp( -x ) ) / 2.0;
        final float cosCh = ( float ) Math.cos( xValue.re() * ch );
        
        final double sh = ( Math.exp( x ) - Math.exp( -x ) ) / 2.0;
        final float sinSh = ( float ) Math.sin( xValue.re() * sh );
        
        mYComplex.set( cosCh, ( -1 ) * sinSh );
        return mYComplex;
    }

    public Complex getY( float re, float im )
    {
    	mXValue.set( re, im );
        return getY( mXValue );
    }
}
