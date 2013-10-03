package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AEventTarget {

	protected final JavaScriptObject instance;

	protected AEventTarget(JavaScriptObject instance) {
		this.instance = instance;
	}

	public final native void addEventListener(String type,
			IEventHandler callback) /*-{

		var callbackFunc = function(e) {
			var ev = @it.netgrid.gwt.sipml5.Event::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@it.netgrid.gwt.sipml5.handler.IEventHandler::onEvent(Lit/netgrid/gwt/sipml5/Event;)(ev);
		};

		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;

	public final native void removeEventListener(String type) /*-{
		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.removeEventListener(type);
	}-*/;
}
