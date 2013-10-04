package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.config.StackConfig;
import it.netgrid.gwt.sipml5.session.ASession;
import it.netgrid.gwt.sipml5.session.Call;
import it.netgrid.gwt.sipml5.session.Message;
import it.netgrid.gwt.sipml5.session.Publish;
import it.netgrid.gwt.sipml5.session.Registration;
import it.netgrid.gwt.sipml5.session.Subscribe;

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

	public final native Call newCall(String type) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('call-'+type);
	}-*/;

	public final native Registration newRegister() /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('register');
	}-*/;

	public final native Message newMessage() /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('message');
	}-*/;

	public final native Subscribe newSubscribe() /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('subscribe');
	}-*/;

	public final native Publish newPublish() /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('publish');
	}-*/;

	public final native ASession newSession(String type) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession(type);
	}-*/;

	public final native Call newCall(String type, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('call-'+type, config);
	}-*/;

	public final native Registration newRegister(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('register', config);
	}-*/;

	public final native Message newMessage(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('message', config);
	}-*/;

	public final native Subscribe newSubscribe(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('subscribe', config);
	}-*/;

	public final native Publish newPublish(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('publish', config);
	}-*/;

	public final native ASession newSession(String type, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.Stack::instance.newSession(type, config);
	}-*/;
}
