package org.sipml5.gwt.sipml5.session;

import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public class Registration extends ASession<org.sipml5.gwt.sipml5.session.Registration.EventType> {
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
	protected Registration(JavaScriptObject instance) {
		super(instance);
	}

	public final native void register() /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.register();
	}-*/;

	public final native void register(Configuration config) /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.register(config);
	}-*/;

	public final native void unregister() /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unregister();
	}-*/;

	public final native void unregister(Configuration config) /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.unregister(config);
	}-*/;
	
	@Override
	protected final native void addEventListener(String type, IEventHandler<AEvent<org.sipml5.gwt.sipml5.session.Registration.EventType>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @org.sipml5.gwt.sipml5.event.RegistrationEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@org.sipml5.gwt.sipml5.handler.IEventHandler::onEvent(Lorg/sipml5/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name().toLowerCase();
	}
}
