package org.sipml5.gwt.sipml5.handler;

import org.sipml5.gwt.sipml5.event.AEvent;


public interface IEventHandler<E extends AEvent<?>> {
	public void onEvent(E event);
}
