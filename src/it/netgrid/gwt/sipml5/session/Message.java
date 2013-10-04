package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public class Message extends ASession {

	protected Message(JavaScriptObject instance) {
		super(instance);
	}

	public final native int send(String to, String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content, contentType, config);
	}-*/;

	public final native int send(String to, String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content, contentType);
	}-*/;

	public final native int send(String to, String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to, content);
	}-*/;

	public final native int send(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Message::instance.send(to);
	}-*/;
}
