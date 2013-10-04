package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public class Publish extends ASession {

	protected Publish(JavaScriptObject instance) {
		super(instance);
	}

	public final native int publish(String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content, contentType, config);
	}-*/;

	public final native int publish(String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content);
	}-*/;

	public final native int publish(String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish(content, contentType);
	}-*/;

	public final native int publish() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Publish::instance.publish();
	}-*/;

	public final native void unpublish() /*-{
		this.@it.netgrid.gwt.sipml5.session.Publish::instance.unpublish();
	}-*/;

	public final native void unpublish(Configuration config) /*-{
		this.@it.netgrid.gwt.sipml5.session.Publish::instance.unpublish(config);
	}-*/;
}
