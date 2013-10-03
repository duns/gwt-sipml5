package it.netgrid.gwt.sipml5;

import com.google.gwt.core.client.JavaScriptObject;

public class Event {

	protected final JavaScriptObject instance;

	public Event(JavaScriptObject instance) {
		this.instance = instance;
	}

	public native final void setDescription(String description) /*-{
		this.@it.netgrid.gwt.sipml5.Event::instance.description = description;
	}-*/;

	public native final String getDescription() /*-{
		return this.@it.netgrid.gwt.sipml5.Event::instance.description;
	}-*/;

	public native final void setType(String description) /*-{
		this.@it.netgrid.gwt.sipml5.Event::instance.type = type;
	}-*/;

	public native final String getType() /*-{
		return this.@it.netgrid.gwt.sipml5.Event::instance.type;
	}-*/;

	public final native JavaScriptObject getContent() /*-{
		return this.@it.netgrid.gwt.sipml5.Event::instance.getContent();
	}-*/;

	public final native JavaScriptObject getContentType() /*-{
		return this.@it.netgrid.gwt.sipml5.Event::instance.getContentType();
	}-*/;

	public final native int getSipResponseCode() /*-{
		return this.@it.netgrid.gwt.sipml5.Event::instance.getSipResponseCode();
	}-*/;

}
