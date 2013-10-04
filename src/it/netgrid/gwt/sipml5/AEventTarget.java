package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.event.AEvent;
import it.netgrid.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AEventTarget<T> {

	protected final JavaScriptObject instance;

	protected AEventTarget(JavaScriptObject instance) {
		this.instance = instance;
	}

	public final void addEventListener(T type, IEventHandler<AEvent<T>> callback) {
		this.addEventListener(this.getTypeName(type), callback);
	}

	public final void removeEventListener(T type) {
		this.removeEventListener(this.getTypeName(type));
	}

	private final native void addEventListener(String type, IEventHandler<AEvent<T>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @it.netgrid.gwt.sipml5.event.AEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@it.netgrid.gwt.sipml5.handler.IEventHandler::onEvent(Lit/netgrid/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;

	private final native void removeEventListener(String type) /*-{
		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.removeEventListener(type);
	}-*/;

	protected abstract String getTypeName(T type);
}
