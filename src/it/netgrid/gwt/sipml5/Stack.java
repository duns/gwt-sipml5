package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.config.StackConfig;
import it.netgrid.gwt.sipml5.session.Call;
import it.netgrid.gwt.sipml5.session.Message;
import it.netgrid.gwt.sipml5.session.Publish;
import it.netgrid.gwt.sipml5.session.Registration;
import it.netgrid.gwt.sipml5.session.Subscribe;

import com.google.gwt.core.client.JavaScriptObject;

public class Stack {
	private final JavaScriptObject instance;

	public static boolean initialized;

	public enum EventType {
		ALL,
		STARTING,
		STARTED,
		STOPPING,
		STOPPED,
		FAILED_TO_START,
		FAILED_TO_STOP,
		I_NEW_CALL,
		I_NEW_MESSAGE,
		M_PERMISSION_REQUESTED,
		M_PERMISSION_ACCEPTED,
		M_PERMISSION_REFUSED;
	}

	public Stack(StackConfig config) {
		this.instance = Stack.getInstance(config);
	}

	private static native final JavaScriptObject getInstance(StackConfig config) /*-{
		return new $wnd.SIPml.Stack(config);
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

	public final Call newCall(Call.Type type, Configuration config) {
		return this.newCall(type.name().toLowerCase(), config);
	}

	public final Call newCall(Call.Type type) {
		return this.newCall(type.name().toLowerCase());
	}

	private final native Call newCall(String type) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('call-'+type);
		return @it.netgrid.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	private final native Call newCall(String type, Configuration config) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('call-'+type, config);
		return @it.netgrid.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Registration newRegister() /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('register');
		return @it.netgrid.gwt.sipml5.session.Registration::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Message newMessage() /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('message');
		return @it.netgrid.gwt.sipml5.session.Message::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Subscribe newSubscribe() /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('subscribe');
		return @it.netgrid.gwt.sipml5.session.Subscribe::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Publish newPublish() /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('publish');
		return @it.netgrid.gwt.sipml5.session.Publish::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Registration newRegister(Configuration config) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('register', config);
		return @it.netgrid.gwt.sipml5.session.Registration::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Message newMessage(Configuration config) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('message', config);
		return @it.netgrid.gwt.sipml5.session.Message::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Subscribe newSubscribe(Configuration config) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('subscribe', config);
		return @it.netgrid.gwt.sipml5.session.Subscribe::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Publish newPublish(Configuration config) /*-{
		var s = this.@it.netgrid.gwt.sipml5.Stack::instance.newSession('publish', config);
		return @it.netgrid.gwt.sipml5.session.Publish::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;
}
