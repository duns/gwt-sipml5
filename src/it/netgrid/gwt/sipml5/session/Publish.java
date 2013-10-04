package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.event.AEvent;

import com.google.gwt.core.client.JavaScriptObject;

public class Publish extends ASession<it.netgrid.gwt.sipml5.session.Publish.EventType> {
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
	protected Publish(JavaScriptObject instance) {
		super(instance);
	}

	public final native int publish(String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content, contentType, config);
	}-*/;

	public final native int publish(String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content);
	}-*/;

	public final native int publish(String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content, contentType);
	}-*/;

	public final native int publish() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish();
	}-*/;

	public final native void unpublish() /*-{
		this.@it.netgrid.gwt.sipml5.session.Publish::instance.unpublish();
	}-*/;

	public final native void unpublish(Configuration config) /*-{
		this.@it.netgrid.gwt.sipml5.session.Publish::instance.unpublish(config);
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name();
	}
}
