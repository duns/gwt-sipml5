package it.netgrid.gwt.sipml5;

import it.netgrid.gwt.sipml5.handler.IInitHandler;

public class SipMl {

	public final native static String getNavigatorFriedlyName() /*-{
		return SIPml.getNavigatorFriendlyName();
	}-*/;

	public native final static String getNavigatorVersion() /*-{
		return SIPml.getNavigatorVersion();
	}-*/;

	public native final static String getWebRtc4AllVersion() /*-{
		return SIPml.getWebRtc4AllVersion();
	}-*/;

	public native final static boolean haveMediaStream() /*-{
		return SIPml.haveMediaStream();
	}-*/;

	public native final static void init(IInitHandler callback) /*-{
		SIPml.init(
			callback.@it.netgrid.gwt.sipml5.handler.IInitHandler::onSuccess(Lit/netgrid/gwt/sipml5/response/InitResponse;),
			callback.@it.netgrid.gwt.sipml5.handler.IInitHandler::onError(Lit/netgrid/gwt/sipml5/response/InitResponse;));
	}-*/;

	public native final static boolean isInitialized() /*-{
		return SIPml.isInitialized();
	}-*/;

	public native final static boolean isNavigatorOutdated() /*-{
		return SIPml.isNavigatorOutdated();
	}-*/;

	public native final static boolean isReady() /*-{
		return SIPml.isReady();
	}-*/;

	public native final static boolean isScreenShareSupported() /*-{
		return SIPml.isScreenShareSupported();
	}-*/;

	public native final static boolean isWebRtc4AllPluginOutdated() /*-{
		return SIPml.isWebRtc4AllPluginOutdated();
	}-*/;

	public native final static boolean isWebRtcSupported() /*-{
		return SIPml.isWebRtcSupported();
	}-*/;

	public native final static boolean isWebSocketSupported() /*-{
		return SIPml.isWebSocketSupported();
	}-*/;

	public native final static void setDebugLevel(String level) /*-{
		SIPml.setDebugLevel(level);
	}-*/;
}
