package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public class Subscribe extends ASession {

	protected Subscribe(JavaScriptObject instance) {
		super(instance);
	}

	public final native int subscribe(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Subscribe::instance.subscribe(to);
	}-*/;

	public final native int subscribe(String to, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Subscribe::instance.subscribe(to, config);
	}-*/;

	public final native int unsubscribe(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Subscribe::instance.unsubscribe(config);
	}-*/;

	public final native int unsubscribe() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Subscribe::instance.unsubscribe();
	}-*/;
}
