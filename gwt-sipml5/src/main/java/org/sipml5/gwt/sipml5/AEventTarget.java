package org.sipml5.gwt.sipml5;

import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AEventTarget<T> {

	protected final JavaScriptObject instance;

	protected AEventTarget(JavaScriptObject instance) {
		this.instance = instance;
	}

	public void addEventListener(T type, IEventHandler<AEvent<T>> callback) {
		this.addEventListener(this.getTypeName(type), callback);
	}

	public void removeEventListener(T type) {
		this.removeEventListener(this.getTypeName(type));
	}

//	private final native void addEventListener(String type, IEventHandler<AEvent<T>> callback) /*-{
//
//		var callbackFunc = function(e) {
//			var ev = @org.sipml5.gwt.sipml5.event.AEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
//			callback.@org.sipml5.gwt.sipml5.handler.IEventHandler::onEvent(Lorg/sipml5/gwt/sipml5/event/AEvent;)(ev);
//		};
//
//		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
//	}-*/;

	private final native void removeEventListener(String type) /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.removeEventListener(type);
	}-*/;
	
	protected abstract void addEventListener(String type, IEventHandler<AEvent<T>> callback);

	protected abstract String getTypeName(T type);
}
