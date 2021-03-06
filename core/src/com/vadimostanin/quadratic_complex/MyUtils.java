package com.vadimostanin.quadratic_complex;

/**
 * Created by vostanin on 4/24/17.
 */

public class MyUtils
{
    static float recalcSlopeAngle( double Re, double Im )
    {
        final float hypot = ( float ) Math.sqrt( Re * Re + Im * Im );
        double cosValue = Re / hypot;
        double sinValue = Im / hypot;
        if( cosValue > 1.0 )
        {
            cosValue = 1.0;
        }
        if( sinValue > 1.0 )
        {
            sinValue = 1.0;
        }
        final double acos  = Math.acos( cosValue );
        final float cosAngle = ( float ) Math.toDegrees( acos );
        if( sinValue <= 0.0f )
        {
            return 360.0f - cosAngle;
        }
        else
        {
            return cosAngle;
        }
    }

    static float getXAxisCirculum( float slopeDegrees, float maxKatet )
    {
        final float cosValue = getCos( slopeDegrees );
        return cosValue * maxKatet;
    }

    static float getIAxisCirculum( float slopeDegrees, float maxKatet )
    {
        final float sinValue = getSin( slopeDegrees );
        return sinValue * maxKatet;
    }

    static float getIAxisDisplacement( float slopeDegrees, float maxKatet )
    {
        final float sinValue = getSin( slopeDegrees );
        final float cosValue = getCos( slopeDegrees );
        return maxKatet * sinValue  / cosValue ;
    }

    static float getCos( float slopeDegrees )
    {
        final float minValue = 0.000001f;
        final float cosTmpValue = ( float ) Math.cos( Math.toRadians( slopeDegrees ) );
        return ( Math.abs( cosTmpValue ) > minValue ) ? cosTmpValue : minValue;
    }

    static float getSin( float slopeDegrees )
    {
        final float minValue = 0.000001f;
        final float sinTmpValue = ( float ) Math.sin( Math.toRadians( slopeDegrees ) );
        return ( Math.abs( sinTmpValue ) > minValue ) ? sinTmpValue : minValue;
    }
}
