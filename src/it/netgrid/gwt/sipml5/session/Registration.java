package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public class Registration extends ASession {

	protected Registration(JavaScriptObject instance) {
		super(instance);
	}


	public final native void register() /*-{
		this.@it.netgrid.gwt.sipml5.session.Registration::instance.register();
	}-*/;

	public final native void register(Configuration config) /*-{
		this.@it.netgrid.gwt.sipml5.session.Registration::instance.register(config);
	}-*/;


	public final native void unregister() /*-{
		this.@it.netgrid.gwt.sipml5.session.Registration::instance.unregister();
	}-*/;

	public final native void unregister(Configuration config) /*-{
		this.@it.netgrid.gwt.sipml5.session.Registration::instance.unregister(config);
	}-*/;
}
