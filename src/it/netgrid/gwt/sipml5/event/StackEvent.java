package it.netgrid.gwt.sipml5.event;

import it.netgrid.gwt.sipml5.Stack.EventType;
import it.netgrid.gwt.sipml5.session.ASession;

import com.google.gwt.core.client.JavaScriptObject;

public class StackEvent extends AEvent<EventType> {

	public StackEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession<?> getNewSession() /*-{
		var s = this.@it.netgrid.gwt.sipml5.event.AEvent::instance.newSession;
		return @it.netgrid.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	@Override
	protected EventType getTypeByName(String name) {
		return name == AEvent.AllWildcard ? EventType.ALL : EventType.valueOf(name.toUpperCase());
	}
}
