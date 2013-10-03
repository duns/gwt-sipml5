package it.netgrid.gwt.sipml5.response;

import com.google.gwt.core.client.JavaScriptObject;

public class InitResponse extends JavaScriptObject {

	public native final void setMessage(String message) /*-{
		this.message = message;
	}-*/;

	public native final String getMessage() /*-{
		return this.message;
	}-*/;

}
