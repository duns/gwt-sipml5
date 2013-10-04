package it.netgrid.gwt.sipml5.event;

import it.netgrid.gwt.sipml5.session.ASession;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ASessionEvent<T> extends AEvent<T> {

	public ASessionEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession<T> getSession() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.session;
	}-*/;

	public native final String getTransferDestinationFriendlyName() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.getTransferDestinationFriendlyName();
	}-*/;
}
