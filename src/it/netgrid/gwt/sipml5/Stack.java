package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.config.StackConfig;

import com.google.gwt.core.client.JavaScriptObject;

public class Stack {
	private final JavaScriptObject instance;

	public Stack(StackConfig config) {
		this.instance = Stack.getInstance(config);
	}

	private static native final JavaScriptObject getInstance(StackConfig config) /*-{
		return new SIPml.Stack(config);
	}-*/;

	public final native void setConfiguration(StackConfig config) /*-{
		this.@it.netgrid.gwt.sipml5.Stack::instance.setConfiguration(config);
	}-*/;

	public final native void start() /*-{
		this.@it.netgrid.gwt.sipml5.Stack::instance.start();
	}-*/;

	public final native void stop(int timeout) /*-{
		this.@it.netgrid.gwt.sipml5.Stack::instance.stop(timeout);
	}-*/;

	public final native ASession newSession(String type, Configuration config) /*-{
		this.@it.netgrid.gwt.sipml5.Stack::instance.newSession(type, config);
	}-*/;
}
