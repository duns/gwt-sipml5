package it.netgrid.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;

public class VideoSize extends JavaScriptObject {
	protected VideoSize() {
	}

	public native final void setMinWidth(int minWidth) /*-{
		this.minWidth = minWidth;
	}-*/;

	public native final int getMinWidth() /*-{
		return this.minWidth;
	}-*/;

	public native final void setMaxWidth(int maxWidth) /*-{
		this.maxWidth = maxWidth;
	}-*/;

	public native final int getMaxWidth() /*-{
		return this.maxWidth;
	}-*/;

	public native final void setMinHeight(int minHeight) /*-{
		this.minHeight = minHeight;
	}-*/;

	public native final int getMinHeight() /*-{
		return this.minHeight;
	}-*/;

	public native final void setMaxHeight(int maxHeight) /*-{
		this.maxHeight = maxHeight;
	}-*/;

	public native final int getMaxHeight() /*-{
		return this.maxHeight;
	}-*/;

}
