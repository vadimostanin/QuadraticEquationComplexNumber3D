package com.vadimostanin.quadratic_complex;

import java.util.ArrayList;

public class PropertyChangeListeners extends ArrayList<IPropertyChangeListener> implements IPropertyChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PropertyChangeListeners()
	{
		;
	}
	@Override
	public void onChanged( IProperty prop )
	{
		for( IPropertyChangeListener listener : this )
		{
			listener.onChanged( prop );
		}
	}
}
