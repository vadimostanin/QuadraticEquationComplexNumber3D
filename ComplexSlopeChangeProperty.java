package com.vadimostanin.quadratic_complex;

/**
 * Created by vadim on 22.04.17.
 */

public class ComplexSlopeChangeProperty implements IProperty
{
    private float mSlope;

    public ComplexSlopeChangeProperty()
    {
        this( 0.0f );
    }

    public ComplexSlopeChangeProperty( float slope )
    {
        mSlope = slope;
    }

    public void set( float slope )
    {
        mSlope = slope;
    }

    public float slope()
    {
        return mSlope;
    }
}
