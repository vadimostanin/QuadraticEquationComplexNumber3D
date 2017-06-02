package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by vostanin on 4/18/17.
 */

public class QuazyXAxisDrawer implements IDrawShapeRenderer, IPropertyChangeListener
{
    private float mSlope;
    private float mRe;
    private float mIm;

    private float mArrowWingPlusX;
    private float mArrowWingPlusY;
    private float mArrowWingMinusX;
    private float mArrowWingMinusY;

    @Override
    public void draw( ShapeRenderer renderer )
    {
        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Axis );
        renderer.begin(ShapeRenderer.ShapeType.Line);
        {
            renderer.setColor( Color.RED );
            renderer.line( - mRe, 0.0f, - mIm, mRe, 0.0f, mIm );
        }
        drawArrows( renderer );
        renderer.end();

        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Unused );
    }

    private void drawArrows( ShapeRenderer renderer )
    {
        final float arrowLength = 1.0f;
        final float arrowWing = 0.5f;
        {
            renderer.setColor( Color.RED );
            renderer.line( mArrowWingPlusX, 0.0f, mArrowWingPlusY, mRe, 0.0f, mIm );
            renderer.line( mArrowWingMinusX, 0.0f, mArrowWingMinusY, mRe, 0.0f, mIm );
        }
    }

    @Override
    public void onChanged( IProperty prop )
    {
        if( prop instanceof ComplexSlopeChangeProperty )
        {
            ComplexSlopeChangeProperty slopeProp = ( ComplexSlopeChangeProperty ) prop;
            mSlope = slopeProp.slope();

            mRe = MyUtils.getXAxisCirculum( mSlope, Constants.AxesMaxWidth );
            mIm = MyUtils.getIAxisCirculum( mSlope, Constants.AxesMaxWidth );

            final float arrowWingAngle = 5.0f;
            final float arrowWingLength = 1.0f;
            mArrowWingPlusX = MyUtils.getXAxisCirculum( mSlope + arrowWingAngle, Constants.AxesMaxWidth - arrowWingLength );
            mArrowWingPlusY = MyUtils.getIAxisCirculum( mSlope + arrowWingAngle, Constants.AxesMaxWidth - arrowWingLength );

            mArrowWingMinusX = MyUtils.getXAxisCirculum( mSlope - arrowWingAngle, Constants.AxesMaxWidth - arrowWingLength );
            mArrowWingMinusY = MyUtils.getIAxisCirculum( mSlope - arrowWingAngle, Constants.AxesMaxWidth - arrowWingLength );
        }
    }
}
