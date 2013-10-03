package it.netgrid.gwt.sipml5.config;

import it.netgrid.gwt.sipml5.handler.IStackEventHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class StackConfig extends JavaScriptObject {
	protected StackConfig() {
	}

	public native final void setRealm(String realm) /*-{
		this.realm = realm;
	}-*/;

	public native final String getRealm() /*-{
		return this.realm;
	}-*/;

	public native final void setImpi(String impi) /*-{
		this.impi = impi;
	}-*/;

	public native final String getImpi() /*-{
		return this.impi;
	}-*/;

	public native final void setImpu(String impu) /*-{
		this.impu = impu;
	}-*/;

	public native final String getImpu() /*-{
		return this.impu;
	}-*/;

	public native final void setPassword(String password) /*-{
		this.password = password;
	}-*/;

	public native final String getPassword() /*-{
		return this.password;
	}-*/;

	public native final void setDisplayName(String displayName) /*-{
		this.display_name = displayName;
	}-*/;

	public native final String getDisplayName() /*-{
		return this.display_name;
	}-*/;

	public native final void setWebsocketProxyUrl(String websocketProxyUrl) /*-{
		this.websocket_proxy_url = websocket_proxy_url;
	}-*/;

	public native final String getWebsocketProxyUrl() /*-{
		return this.websocket_proxy_url;
	}-*/;

	public native final void setOutboundProxyUrl(String outboundProxyUrl) /*-{
		this.outbound_proxy_url = outboundProxyUrl;
	}-*/;

	public native final String getOutboundProxyUrl() /*-{
		return this.outbound_proxy_url;
	}-*/;

	public native final void addIceServer(IceServer iceServer) /*-{
		this.ice_servers.push(iceServer);
	}-*/;

	public native final JsArray<IceServer> getIceServers() /*-{
		return this.ice_servers;
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

	public native final void setEnableRtcwebBreaker(boolean enableRtcwebBreaker) /*-{
		this.enable_rtcweb_breaker = enableRtcwebBreaker;
	}-*/;

	public native final boolean isEnableRtcwebBreaker() /*-{
		return this.enable_rtcweb_breaker;
	}-*/;

	public native final void setEnableClick2Call(boolean enableClick2call) /*-{
		this.enable_click2call = enableClick2call;
	}-*/;

	public native final boolean isEnableClick2Call() /*-{
		return this.enable_click2call;
	}-*/;

	public native final void addSipHeader(SipHeader sipHeader) /*-{
		this.sip_headers.push(sipHeader);
	}-*/;

	public native final JsArray<SipHeader> getSipHeaders() /*-{
		return this.sip_headers;
	}-*/;

	public native final void setEventsHandler(String mask,
			IStackEventHandler callback) /*-{
		var callbackFunc = function(e) {
			var ev = @it.netgrid.gwt.sipml5.StackEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@it.netgrid.gwt.sipml5.handler.IStackEventHandler::onEvent(Lit/netgrid/gwt/sipml5/StackEvent;)(ev);
		};

		this.events_listener = {
			"events" : mask,
			"listener" : callbackFunc
		};
	}-*/;

	/*
	 * { realm: 'example.org', impi: 'bob', impu: 'sip:bob@example.org',
	 * password: 'mysecret', // optional display_name: 'I Am Legend', //
	 * optional websocket_proxy_url: 'ws://192.168.0.10:5060', // optional
	 * outbound_proxy_url: 'udp://192.168.0.12:5060', // optional ice_servers:
	 * [{ url: 'stun:stun.l.google.com:19302'}, {
	 * url:'turn:user@numb.viagenie.ca', credential:'myPassword'}], // optional
	 * bandwidth: { audio:64, video:512 }, // optional video_size: {
	 * minWidth:640, minHeight:480, maxWidth:1920, maxHeight:1080 }, // optional
	 * enable_rtcweb_breaker: true, // optional enable_click2call: false, //
	 * optional events_listener: { events: '*', listener: listenerFunc },
	 * //optional sip_headers: [ //optional {name: 'User-Agent', value:
	 * 'IM-client/OMA1.0 sipML5-v1.0.89.0'}, {name: 'Organization', value:
	 * 'Doubango Telecom'} ] }
	 */
}
