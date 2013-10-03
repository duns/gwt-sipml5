package it.netgrid.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;

public class Bandwidth extends JavaScriptObject {
	protected Bandwidth() {
	}

	public native final void setAudio(int audio) /*-{
		this.audio = audio;
	}-*/;

	public native final int getAudio() /*-{
		return this.audio;
	}-*/;

	public native final void setVideo(int video) /*-{
		this.video = video;
	}-*/;

	public native final int getVideo() /*-{
		return this.video;
	}-*/;

}
