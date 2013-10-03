package it.netgrid.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;

public class SipHeader extends JavaScriptObject {
	protected SipHeader() {
	}

	public native final void setName(String name) /*-{
		this.name = name;
	}-*/;

	public native final String getName() /*-{
		return this.name;
	}-*/;

	public native final void setValue(String value) /*-{
		this.value = value;
	}-*/;

	public native final String getValue() /*-{
		return this.value;
	}-*/;

	public native final void setSession(boolean session) /*-{
		this.session = session;
	}-*/;

	public native final boolean getSession() /*-{
		return this.session;
	}-*/;

}
