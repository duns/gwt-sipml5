package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.handler.IInitHandler;

public class SipMl {

	public final native static String getNavigatorFriedlyName() /*-{
		return $wnd.SIPml.getNavigatorFriendlyName();
	}-*/;

	public native final static String getNavigatorVersion() /*-{
		return $wnd.SIPml.getNavigatorVersion();
	}-*/;

	public native final static String getWebRtc4AllVersion() /*-{
		return $wnd.SIPml.getWebRtc4AllVersion();
	}-*/;

	public native final static boolean haveMediaStream() /*-{
		return $wnd.SIPml.haveMediaStream();
	}-*/;

	public native final static void init(IInitHandler callback) /*-{

		var okCallback = function(e) {
			callback.@it.netgrid.gwt.sipml5.handler.IInitHandler::onSuccess(Lit/netgrid/gwt/sipml5/response/InitResponse;)(e);
		};

		var koCallback = function(e) {
			callback.@it.netgrid.gwt.sipml5.handler.IInitHandler::onError(Lit/netgrid/gwt/sipml5/response/InitResponse;)(e);
		};

		$wnd.SIPml.init(okCallback,koCallback);
	}-*/;

	public native final static boolean isInitialized() /*-{
		return $wnd.SIPml.isInitialized();
	}-*/;

	public native final static boolean isNavigatorOutdated() /*-{
		return $wnd.SIPml.isNavigatorOutdated();
	}-*/;

	public native final static boolean isReady() /*-{
		return $wnd.SIPml.isReady();
	}-*/;

	public native final static boolean isScreenShareSupported() /*-{
		return $wnd.SIPml.isScreenShareSupported();
	}-*/;

	public native final static boolean isWebRtc4AllPluginOutdated() /*-{
		return $wnd.SIPml.isWebRtc4AllPluginOutdated();
	}-*/;

	public native final static boolean isWebRtcSupported() /*-{
		return $wnd.SIPml.isWebRtcSupported();
	}-*/;

	public native final static boolean isWebSocketSupported() /*-{
		return $wnd.SIPml.isWebSocketSupported();
	}-*/;

	public native final static void setDebugLevel(String level) /*-{
		$wnd.SIPml.setDebugLevel(level);
	}-*/;
}
