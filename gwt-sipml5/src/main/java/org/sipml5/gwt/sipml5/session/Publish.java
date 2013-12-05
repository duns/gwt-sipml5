package org.sipml5.gwt.sipml5.session;

import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public class Publish extends ASession<org.sipml5.gwt.sipml5.session.Publish.EventType> {
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
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.publish(content, contentType, config);
	}-*/;

	public final native int publish(String content) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.publish(content);
	}-*/;

	public final native int publish(String content, String contentType) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.publish(content, contentType);
	}-*/;

	public final native int publish() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.publish();
	}-*/;

	public final native void unpublish() /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unpublish();
	}-*/;

	public final native void unpublish(Configuration config) /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unpublish(config);
	}-*/;
	
	@Override
	protected final native void addEventListener(String type, IEventHandler<AEvent<org.sipml5.gwt.sipml5.session.Publish.EventType>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @org.sipml5.gwt.sipml5.event.PublishEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@org.sipml5.gwt.sipml5.handler.IEventHandler::onEvent(Lorg/sipml5/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;


	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name().toLowerCase();
	}
}
