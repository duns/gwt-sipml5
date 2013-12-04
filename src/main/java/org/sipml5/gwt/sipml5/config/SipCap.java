package org.sipml5.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;

public class SipCap extends JavaScriptObject {
	protected SipCap() {
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
}
