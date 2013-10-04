package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.event.AEvent;

import com.google.gwt.core.client.JavaScriptObject;

public class Message extends ASession<it.netgrid.gwt.sipml5.session.Message.EventType> {

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
		WEBRTC_ERROR;
	}

	protected Message(JavaScriptObject instance) {
		super(instance);
	}

	public final native int send(String to, String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content, contentType, config);
	}-*/;

	public final native int send(String to, String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content, contentType);
	}-*/;

	public final native int send(String to, String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content);
	}-*/;

	public final native int send(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to);
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name();
	}
}
