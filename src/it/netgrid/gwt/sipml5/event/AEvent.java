package it.netgrid.gwt.sipml5.event;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AEvent<T> {

	public final static String AllWildcard = "*";

	protected final JavaScriptObject instance;

	public AEvent(JavaScriptObject instance) {
		this.instance = instance;
	}

	public final T getType() {
		return this.getTypeByName(this.getTypeString());
	}

	public native final void setDescription(String description) /*-{
		this.@it.netgrid.gwt.sipml5.event.AEvent::instance.description = description;
	}-*/;

	public native final String getDescription() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.description;
	}-*/;

	protected native final void setTypeString(String type) /*-{
		this.@it.netgrid.gwt.sipml5.event.AEvent::instance.type = type;
	}-*/;

	public native final String getTypeString() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.type;
	}-*/;

	public final native JavaScriptObject getContent() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.getContent();
	}-*/;

	public final native JavaScriptObject getContentType() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.getContentType();
	}-*/;

	public final native int getSipResponseCode() /*-{
		return this.@it.netgrid.gwt.sipml5.event.AEvent::instance.getSipResponseCode();
	}-*/;

	protected abstract T getTypeByName(String name);
}
