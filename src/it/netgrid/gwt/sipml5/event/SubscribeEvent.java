package it.netgrid.gwt.sipml5.event;

import it.netgrid.gwt.sipml5.session.Subscribe.EventType;

import com.google.gwt.core.client.JavaScriptObject;

public class SubscribeEvent extends ASessionEvent<EventType> {

	public SubscribeEvent(JavaScriptObject instance) {
		super(instance);
	}


	@Override
	protected EventType getTypeByName(String name) {
		return name == AEvent.AllWildcard ? EventType.ALL : EventType.valueOf(name.toUpperCase());
	}

}
