package org.sipml5.gwt.sipml5.session;

import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public class Subscribe extends ASession<org.sipml5.gwt.sipml5.session.Subscribe.EventType> {
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
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.subscribe(to);
	}-*/;

	public final native int subscribe(String to, Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.subscribe(to, config);
	}-*/;

	public final native int unsubscribe(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unsubscribe(config);
	}-*/;

	public final native int unsubscribe() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unsubscribe();
	}-*/;
	
	@Override
	protected final native void addEventListener(String type, IEventHandler<AEvent<org.sipml5.gwt.sipml5.session.Subscribe.EventType>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @org.sipml5.gwt.sipml5.event.SubscribeEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@org.sipml5.gwt.sipml5.handler.IEventHandler::onEvent(Lorg/sipml5/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name().toLowerCase();
	}
}
