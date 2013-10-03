package it.netgrid.gwt.sipml5.config;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class Configuration extends JavaScriptObject {
	protected Configuration() {
	}

	public native final void setExpires(int expires) /*-{
		this.expires = expires;
	}-*/;

	public native final int getExpires() /*-{
		return this.expires;
	}-*/;

	public native final void setBandwidth(Bandwidth bandwidth) /*-{
		this.bandwidth = bandwidth;
	}-*/;

	public native final Bandwidth getBandwidth() /*-{
		return this.bandwidth;
	}-*/;

	public native final void setVideoSize(VideoSize videoSize) /*-{
		this.video_size = videoSize;
	}-*/;

	public native final VideoSize getVideoSize() /*-{
		return this.video_size;
	}-*/;

	public native final void setFrom(String from) /*-{
		this.from = from;
	}-*/;

	public native final String getFrom() /*-{
		return this.from;
	}-*/;

	public native final void setAudioRemote(String audioRemote) /*-{
		this.audio_remote = document.getElementById(audioRemote);
	}-*/;

	public native final void setVideoRemote(String videoRemote) /*-{
		this.video_remote = document.getElementById(videoRemote);
	}-*/;

	public native final void setVideoLocal(String videoLocal) /*-{
		this.video_local = document.getElementById(videoLocal);
	}-*/;

	public native final void addSipCap(SipCap sipCap) /*-{
		this.sip_caps.push(sipCap);
	}-*/;

	public native final void addSipHeader(SipHeader sipHeader) /*-{
		this.sip_headers.push(sipHeader);
	}-*/;

	public native final JsArray<SipHeader> getSipHeaders() /*-{
		return this.sip_headers;
	}-*/;

	public native final JsArray<SipCap> getSipCaps() /*-{
		return this.sip_caps;
	}-*/;
}
