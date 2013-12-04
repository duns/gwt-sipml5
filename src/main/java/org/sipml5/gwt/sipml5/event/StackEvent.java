package org.sipml5.gwt.sipml5.event;

import org.sipml5.gwt.sipml5.Stack.EventType;
import org.sipml5.gwt.sipml5.session.ASession;

import com.google.gwt.core.client.JavaScriptObject;

public class StackEvent extends AEvent<EventType> {

	public StackEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession<?> getNewSession() /*-{
		var s = this.@org.sipml5.gwt.sipml5.event.AEvent::instance.newSession;
		return @org.sipml5.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	@Override
	protected EventType getTypeByName(String name) {
		return name == AEvent.AllWildcard ? EventType.ALL : EventType.valueOf(name.toUpperCase());
	}
}
