package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.AEventTarget;
import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.event.AEvent;
import it.netgrid.gwt.sipml5.event.ASessionEvent;
import it.netgrid.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ASession<T> extends AEventTarget<T> {

	protected ASession(JavaScriptObject instance) {
		super(instance);
	}

	public final native int getId() /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance.getId();
	}-*/;

	public final native String getRemoteFriendlyName() /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance
				.getRemoteFriendlyName();
	}-*/;

	public final native String getRemoteUri() /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance
				.getRemoteUri();
	}-*/;

	public final native void setConfiguration(Configuration configuration) /*-{
		this.@it.netgrid.gwt.sipml5.AEventTarget::instance
				.setConfiguration(configuration);
	}-*/;

	public final native int accept(Configuration configuration) /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance
				.accept(configuration);
	}-*/;

	public final native int reject(Configuration configuration) /*-{
		return this.@it.netgrid.gwt.sipml5.AEventTarget::instance
				.reject(configuration);
	}-*/;

	// FIXME rename later
	public void addSessionEventListener(T type, IEventHandler<ASessionEvent<T>> callback) {
		this.addEventListener(this.getTypeName(type), callback);
	}

	// FIXME rename later
	public void removeSessionEventListener(T type) {
		this.removeEventListener(this.getTypeName(type));
	}

	private final native void addEventListener(String type, IEventHandler<ASessionEvent<T>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @it.netgrid.gwt.sipml5.event.ASessionEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@it.netgrid.gwt.sipml5.handler.IEventHandler::onEvent(Lit/netgrid/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;

	private final native void removeEventListener(String type) /*-{
		this.@it.netgrid.gwt.sipml5.AEventTarget::instance.removeEventListener(type);
	}-*/;

}
