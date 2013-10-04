package it.netgrid.gwt.sipml5.event;

import it.netgrid.gwt.sipml5.Stack.EventType;
import it.netgrid.gwt.sipml5.session.ASession;

import com.google.gwt.core.client.JavaScriptObject;

public class StackEvent extends AEvent<EventType> {

	public StackEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession<?> getNewSession() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.newSession;
	}-*/;

	@Override
	protected EventType getTypeByName(String name) {
		return name == AEvent.AllWildcard ? EventType.ALL : EventType.valueOf(name);
	}
}
