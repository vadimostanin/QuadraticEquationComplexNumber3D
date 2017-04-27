package com.vadimostanin.quadratic_complex.android;

import android.widget.Toast;

import com.vadimostanin.quadratic_complex.IMessageSender;

/**
 * Created by vostanin on 4/14/17.
 */

public class MessageSenderAndroid implements IMessageSender
{
    private AndroidLauncher mActivity;
    public MessageSenderAndroid( AndroidLauncher activity )
    {
        mActivity = activity;
    }
    @Override
    public void send( final String s )
    {
        mActivity.getHandler().post( new Runnable()
        {

            @Override
            public void run() {
                //System.out.println("toatsing in launcher run");
                Toast.makeText( mActivity.getApplicationContext(), s, Toast.LENGTH_SHORT ).show();

            }

        });

    }
}
