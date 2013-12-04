package org.sipml5.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;

public class IceServer extends JavaScriptObject {
	protected IceServer() {
	}

	public native final void setUrl(String url) /*-{
		this.url = url;
	}-*/;

	public native final String getUrl() /*-{
		return this.url;
	}-*/;

	public native final void setCredential(String credential) /*-{
		this.credential = credential;
	}-*/;

	public native final String getCredential() /*-{
		return this.credential;
	}-*/;

}
