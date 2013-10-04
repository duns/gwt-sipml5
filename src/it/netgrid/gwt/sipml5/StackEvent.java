package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.session.ASession;

import com.google.gwt.core.client.JavaScriptObject;

public class StackEvent extends Event {

	public StackEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession getNewSession() /*-{
		return this.@it.netgrid.gwt.sipml5.StackEvent::instance.newSession;
	}-*/;

}
