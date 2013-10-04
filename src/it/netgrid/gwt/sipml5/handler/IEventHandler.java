package it.netgrid.gwt.sipml5.handler;

import it.netgrid.gwt.sipml5.event.AEvent;


public interface IEventHandler<E extends AEvent<?>> {
	public void onEvent(E event);
}
