package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by vostanin on 4/18/17.
 */

public class ResourceManager
{
    private static final ResourceManager ourInstance = new ResourceManager();

    public static ResourceManager getInstance() {
        return ourInstance;
    }

    private ResourceManager()
    {
    }

    public FileHandle get( String s )
    {
        if( Gdx.app.getType() == Application.ApplicationType.Android )
        {
            return Gdx.files.internal( s );
        }

        return Gdx.files.local( "assets/" + s );
    }
}
