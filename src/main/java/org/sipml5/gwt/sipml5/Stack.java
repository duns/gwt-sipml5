package org.sipml5.gwt.sipml5;

import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.config.StackConfig;
import org.sipml5.gwt.sipml5.session.Call;
import org.sipml5.gwt.sipml5.session.Message;
import org.sipml5.gwt.sipml5.session.Publish;
import org.sipml5.gwt.sipml5.session.Registration;
import org.sipml5.gwt.sipml5.session.Subscribe;

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
		this.@org.sipml5.gwt.sipml5.Stack::instance.setConfiguration(config);
	}-*/;

	public final native void start() /*-{
		this.@org.sipml5.gwt.sipml5.Stack::instance.start();
	}-*/;

	public final native void stop(int timeout) /*-{
		this.@org.sipml5.gwt.sipml5.Stack::instance.stop(timeout);
	}-*/;

	public final Call newCall(Call.Type type, Configuration config) {
		return this.newCall(type.name().toLowerCase(), config);
	}

	public final Call newCall(Call.Type type) {
		return this.newCall(type.name().toLowerCase());
	}

	private final native Call newCall(String type) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('call-'+type);
		return @org.sipml5.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	private final native Call newCall(String type, Configuration config) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('call-'+type, config);
		return @org.sipml5.gwt.sipml5.session.Call::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Registration newRegister() /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('register');
		return @org.sipml5.gwt.sipml5.session.Registration::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Message newMessage() /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('message');
		return @org.sipml5.gwt.sipml5.session.Message::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Subscribe newSubscribe() /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('subscribe');
		return @org.sipml5.gwt.sipml5.session.Subscribe::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Publish newPublish() /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('publish');
		return @org.sipml5.gwt.sipml5.session.Publish::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Registration newRegister(Configuration config) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('register', config);
		return @org.sipml5.gwt.sipml5.session.Registration::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Message newMessage(Configuration config) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('message', config);
		return @org.sipml5.gwt.sipml5.session.Message::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Subscribe newSubscribe(Configuration config) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('subscribe', config);
		return @org.sipml5.gwt.sipml5.session.Subscribe::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;

	public final native Publish newPublish(Configuration config) /*-{
		var s = this.@org.sipml5.gwt.sipml5.Stack::instance.newSession('publish', config);
		return @org.sipml5.gwt.sipml5.session.Publish::new(Lcom/google/gwt/core/client/JavaScriptObject;)(s);
	}-*/;
}
