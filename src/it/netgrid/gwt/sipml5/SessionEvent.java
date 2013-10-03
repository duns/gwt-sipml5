package it.netgrid.gwt.sipml5;

import com.google.gwt.core.client.JavaScriptObject;

public class SessionEvent extends Event {

	public SessionEvent(JavaScriptObject instance) {
		super(instance);
	}

	public native final ASession getSession() /*-{
		return this.@it.netgrid.gwt.sipml5.SessionEvent::instance.session;
	}-*/;

	public native final String getTransferDestinationFriendlyName() /*-{
		return this.@it.netgrid.gwt.sipml5.SessionEvent::instance.getTransferDestinationFriendlyName();
	}-*/;
}
