package com.vadimostanin.quadratic_complex;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher
{
	public static void main( String[] arg )
	{
		final DesktopScreenOrientation orientation = new DesktopScreenOrientation();
		
		ScreenOrientation.getInstance().setOrientationManager( orientation );

		final MessageSenderDesktop messageSender = new MessageSenderDesktop();
		MessageSenderStrategy.getInstance().setSender( messageSender );

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 576;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
