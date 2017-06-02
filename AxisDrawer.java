package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by vostanin on 4/18/17.
 */

public class AxisDrawer implements IDrawShapeRenderer
{
    @Override
    public void draw( ShapeRenderer renderer )
    {
        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Axis );
        renderer.begin(ShapeRenderer.ShapeType.Line);
        {
            renderer.setColor( Color.RED );
            final float x_start = - Constants.AxesMaxWidth;
            final float x_end = Constants.AxesMaxWidth;
            renderer.line( x_start, 0.0f, 0.0f, x_end, 0.0f, 0.0f);
        }
        {
            renderer.setColor( Color.GREEN );
            final float y_start = - Constants.AxesMaxWidth;
            final float y_end = Constants.AxesMaxWidth;
            renderer.line(0.0f, y_start, 0.0f, 0.0f, y_end, 0.0f);

        }
        {
            renderer.setColor( Color.BLUE );
            final float z_start = - Constants.AxesMaxWidth;
            final float z_end = Constants.AxesMaxWidth;
            renderer.line( 0.0f, 0.0f, z_start, 0.0f, 0.0f, z_end );
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
            final float x_end = Constants.AxesMaxWidth;
            renderer.line( x_end - arrowLength, 0.0f + arrowWing, 0.0f, x_end, 0.0f, 0.0f );
            renderer.line( x_end - arrowLength, 0.0f - arrowWing, 0.0f, x_end, 0.0f, 0.0f );

            renderer.line( x_end - arrowLength, 0.0f, 0.0f + arrowWing, x_end, 0.0f, 0.0f );
            renderer.line( x_end - arrowLength, 0.0f, 0.0f - arrowWing, x_end, 0.0f, 0.0f );
        }
        {
            renderer.setColor( Color.GREEN );
            final float y_end = Constants.AxesMaxWidth;
            renderer.line( 0.0f + arrowWing, y_end - arrowLength, 0.0f, 0.0f, y_end, 0.0f );
            renderer.line( 0.0f - arrowWing, y_end - arrowLength, 0.0f, 0.0f ,y_end, 0.0f );

            renderer.line( 0.0f, y_end - arrowLength, 0.0f + arrowWing, 0.0f, y_end, 0.0f );
            renderer.line( 0.0f, y_end - arrowLength, 0.0f - arrowWing, 0.0f ,y_end, 0.0f );

        }
        {
            renderer.setColor( Color.BLUE );
            final float z_end = Constants.AxesMaxWidth;
            renderer.line( 0.0f + arrowWing, 0.0f, z_end - arrowLength, 0.0f, 0.0f, z_end );
            renderer.line( 0.0f - arrowWing, 0.0f, z_end - arrowLength, 0.0f, 0.0f, z_end );

            renderer.line( 0.0f, 0.0f + arrowWing, z_end - arrowLength, 0.0f, 0.0f, z_end );
            renderer.line( 0.0f, 0.0f - arrowWing, z_end - arrowLength, 0.0f, 0.0f, z_end );
        }
    }
}
