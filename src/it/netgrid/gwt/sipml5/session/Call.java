package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public class Call extends ASession {

	protected Call(JavaScriptObject instance) {
		super(instance);
	}

	public final native int acceptTransfer(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.acceptTransfer(config);
	}-*/;

	public final native int dtmf(char digit) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.dtmf(digit);
	}-*/;

	public final native int dtmf(char digit, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.dtmf(digit, config);
	}-*/;

	public final native int info() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info();
	}-*/;

	public final native int info(String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content);
	}-*/;

	public final native int info(String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content, contentType);
	}-*/;

	public final native int info(String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content, contentType, config);
	}-*/;

	public final native int hangup(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.hangup(config);
	}-*/;

	public final native int resume() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.resume();
	}-*/;

	public final native int resume(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.resume(config);
	}-*/;

	public final native int rejectTransfer() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.rejectTransfer();
	}-*/;

	public final native int rejectTransfer(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.rejectTransfer(config);
	}-*/;

	public final native int transfer(String to, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.transfer(to, config);
	}-*/;

	public final native int transfer(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.transfer(to);
	}-*/;
}
