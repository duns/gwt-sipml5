package org.sipml5.gwt.sipml5.event;

import org.sipml5.gwt.sipml5.session.Message.EventType;

import com.google.gwt.core.client.JavaScriptObject;

public class MessageEvent extends ASessionEvent<EventType> {

	public MessageEvent(JavaScriptObject instance) {
		super(instance);
	}

	@Override
	protected EventType getTypeByName(String name) {
		return name == AEvent.AllWildcard ? EventType.ALL : EventType.valueOf(name.toUpperCase());
	}

}
