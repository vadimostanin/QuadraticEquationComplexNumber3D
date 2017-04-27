package com.vadimostanin.quadratic_complex.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.vadimostanin.quadratic_complex.*;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final AndroidScreenOrientation orientation = new AndroidScreenOrientation( this );

		ScreenOrientation.getInstance().setOrientationManager( orientation );
		final MessageSenderAndroid messageSender = new MessageSenderAndroid( this );
		MessageSenderStrategy.getInstance().setSender( messageSender );

		final AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}
}
