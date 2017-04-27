package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by vostanin on 4/18/17.
 */

public class GraphDrawer implements IDrawShapeRenderer
{
    private Iterable<GraphInfo> mGraphInfoIterable;
    public GraphDrawer()
    {

    }

    public void setGraphInfoIterable( Iterable<GraphInfo> graphInfoIterable )
    {
        mGraphInfoIterable = graphInfoIterable;
    }

    @Override
    public void draw( ShapeRenderer renderer )
    {
        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Graph );
        renderer.begin( ShapeRenderer.ShapeType.Line );
        for( final GraphInfo graphInfo : mGraphInfoIterable )
        {
            drawGraph( graphInfo, renderer );
        }
        renderer.end();
        Gdx.gl20.glLineWidth( Constants.GL_LineWidth_Unused );
    }

    private void drawGraph( GraphInfo graphInfo, ShapeRenderer renderer )
    {
        final Array<Vector3> points = graphInfo.Points();
        final Color color = graphInfo.Color();

        for( int point_index = 1 ; point_index < points.size ; point_index++ )
        {
            final Vector3 point_prev = points.get(point_index - 1);
            final Vector3 point_curr = points.get(point_index);

            renderer.line(point_prev.x, point_prev.y, point_prev.z, point_curr.x, point_curr.y, point_curr.z, color, color);
        }
    }
}
