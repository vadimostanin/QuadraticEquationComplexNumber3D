package com.vadimostanin.quadratic_complex;

public interface IPropertyPublisher
{
	void addListeners(IPropertyChangeListener listener);
	void publish(IProperty property);
}
