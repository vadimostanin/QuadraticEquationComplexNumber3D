package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by vostanin on 4/13/17.
 */

public class GraphScreenUICreator
{
    private Group mContainer;

    public GraphScreenUICreator( Group container )
    {
        mContainer = container;
    }

    public void createUiSettingIcon( final Table container )
    {
        final ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        final Skin settingsButtonSkin = new Skin();
        final TextureAtlas atlas = new TextureAtlas( ResourceManager.getInstance().get( "settings_icon.atlas" ) );
        settingsButtonSkin.addRegions( atlas );
        style.imageUp = settingsButtonSkin.getDrawable( "settings_up" );
        style.imageDown = settingsButtonSkin.getDrawable( "settings_down" );
        final ImageButton imageButton = new ImageButton( style );
        imageButton.addListener( new ClickListener()
                                 {
                                     public void clicked(InputEvent event, float x, float y )
                                     {
                                         ScreensStack.getInstance().push( new SettingsScreen() );
                                     }
                                 }
        );
        container.add( imageButton ).align( Align.bottomLeft );
    }
}
