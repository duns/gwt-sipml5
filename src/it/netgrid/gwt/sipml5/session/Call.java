package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.config.Configuration;
import it.netgrid.gwt.sipml5.event.AEvent;

import com.google.gwt.core.client.JavaScriptObject;

public class Call extends ASession<it.netgrid.gwt.sipml5.session.Call.EventType> {

	public enum Type {
		AUDIO, AUDIOVIDEO, VIDEO, SCREENSHARE
	}

	public enum EventType {
		ALL,
		CONNECTING,
		CONNECTED,
		TERMINATING,
		TERMINATED,
		I_AO_REQUEST,
		MEDIA_ADDED,
		MEDIA_REMOVED,
		I_REQUEST,
		O_REQUEST,
		CANCELLED_REQUEST,
		SENT_REQUEST,
		TRANSPORT_ERROR,
		GLOBAL_ERROR,
		MESSAGE_ERROR,
		WEBRTC_ERROR,
		M_EARLY_MEDIA,
		M_LOCAL_HOLD_OK,
		M_LOCAL_HOLD_NOK,
		M_LOCAL_RESUME_OK,
		M_LOCAL_RESUME_NOK,
		M_REMOTE_HOLD,
		M_REMOTE_RESUME,
		M_STREAM_VIDEO_LOCAL_ADDED,
		M_STREAM_VIDEO_LOCAL_REMOVED,
		M_STREAM_VIDEO_REMOTE_ADDED,
		M_STREAM_VIDEO_REMOTE_REMOVED,
		M_STREAM_AUDIO_LOCAL_ADDED,
		M_STREAM_AUDIO_LOCAL_REMOVED,
		M_STREAM_AUDIO_REMOTE_ADDED,
		M_STREAM_AUDIO_REMOTE_REMOVED,
		I_ECT_NEW_CALL,
		O_ECT_TRYING,
		O_ECT_ACCEPTED,
		O_ECT_COMPLETED,
		I_ECT_COMPLETED,
		O_ECT_FAILED,
		I_ECT_FAILED,
		O_ECT_NOTIFY,
		I_ECT_NOTIFY,
		I_ECT_REQUESTED;
	}

	protected Call(JavaScriptObject instance) {
		super(instance);
	}

	public final native int acceptTransfer(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.acceptTransfer(config);
	}-*/;

	public final native int dtmf(char digit) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.dtmf(digit);
	}-*/;

	public final native int dtmf(char digit, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.dtmf(digit, config);
	}-*/;

	public final native int info() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info();
	}-*/;

	public final native int info(String content) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content);
	}-*/;

	public final native int info(String content, String contentType) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content, contentType);
	}-*/;

	public final native int info(String content, String contentType, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.info(content, contentType, config);
	}-*/;

	public final native int hangup(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.hangup(config);
	}-*/;

	public final native int resume() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.resume();
	}-*/;

	public final native int resume(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.resume(config);
	}-*/;

	public final native int rejectTransfer() /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.rejectTransfer();
	}-*/;

	public final native int rejectTransfer(Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.rejectTransfer(config);
	}-*/;

	public final native int transfer(String to, Configuration config) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.transfer(to, config);
	}-*/;

	public final native int transfer(String to) /*-{
		return this.@it.netgrid.gwt.sipml5.session.Call::instance.transfer(to);
	}-*/;

	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name();
	}
}
