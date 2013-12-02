package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.event.AEvent;

import com.google.gwt.core.client.JavaScriptObject;

public class Subscribe extends ASession<it.netgrid.gwt.sipml5.session.Subscribe.EventType> {
	public enum EventType {
		ALL,
		CONNECTING,
		CONNECTED,
		TERMINATING,
		TERMINATED,
		I_AO_REQUEST,
		MEDIA_ADDED,
		MEDIA_REMOVED,
		I_REQUEST,
		O_REQUEST,
		CANCELLED_REQUEST,
		SENT_REQUEST,
		TRANSPORT_ERROR,
		GLOBAL_ERROR,
		MESSAGE_ERROR,
		WEBRTC_ERROR,
		I_NOTIFY;
	}
	protected Subscribe(JavaScriptObject instance) {
		super(instance);
	}

	public final native int subscribe(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance.subscribe(to);
	}-*/;

	public final native int subscribe(String to, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance.subscribe(to, config);
	}-*/;

	public final native int unsubscribe(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance.unsubscribe(config);
	}-*/;

	public final native int unsubscribe() /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance.unsubscribe();
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name().toLowerCase();
	}
}
