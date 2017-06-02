package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by vostanin on 4/18/17.
 */

public class GridDrawer implements IDrawShapeRenderer
{
    @Override
    public void draw( ShapeRenderer renderer )
    {
        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Grid );
        renderer.begin( ShapeRenderer.ShapeType.Line );
        {
            renderer.setColor( Color.RED );
            final float x_start = -10.0f;
            final float x_end = 10.0f;
            final float x_grid_delta = 1.0f;

            for( float x_axis_pos = x_start ; x_axis_pos <= x_end ; x_axis_pos += x_grid_delta )
            {
                if( x_axis_pos != 0.0f )
                {
                    renderer.line( x_axis_pos, -10.0f, 0.0f, x_axis_pos, 10.0f, 0.0f );
                    renderer.line( x_axis_pos, 0.0f, -10.0f, x_axis_pos, 0.0f, 10.0f );
                }
            }
        }
        {
            renderer.setColor( Color.GREEN );
            final float y_start = -10.0f;
            final float y_end = 10.0f;
            final float y_grid_delta = 1.0f;
            for( float y_axis_pos = y_start ; y_axis_pos <= y_end ; y_axis_pos += y_grid_delta )
            {
                if( y_axis_pos != 0.0f )
                {
                    renderer.line( -10.0f, y_axis_pos, 0.0f, 10.0f, y_axis_pos, 0.0f );
                }
            }
        }
        {

            {
                renderer.setColor( Color.BLUE );
                final float z_start = -10.0f;
                final float z_end = 10.0f;
                final float z_grid_delta = 1.0f;
                for( float z_axis_pos = z_start ; z_axis_pos <= z_end ; z_axis_pos += z_grid_delta )
                {
                    if( z_axis_pos != 0.0f )
                    {
                        renderer.line( -10.0f, 0.0f, z_axis_pos, 10.0f, 0.0f, z_axis_pos );
                    }
                }
            }
        }
        renderer.end();

        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Unused );
    }
}
