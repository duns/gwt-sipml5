package org.sipml5.gwt.sipml5.session;

import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;

import com.google.gwt.core.client.JavaScriptObject;

public class Call extends ASession<org.sipml5.gwt.sipml5.session.Call.EventType> {

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

	public Call(JavaScriptObject instance) {
		super(instance);
	}

	public final native int acceptTransfer() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.acceptTransfer();
	}-*/;

	public final native int acceptTransfer(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.acceptTransfer(config);
	}-*/;

	public final native int call(String to) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.call(to);
	}-*/;

	public final native int dtmf(char digit) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.dtmf(digit);
	}-*/;

	public final native int dtmf(char digit, Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.dtmf(digit, config);
	}-*/;

	public final native int info() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.info();
	}-*/;

	public final native int info(String content) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.info(content);
	}-*/;

	public final native int info(String content, String contentType) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.info(content, contentType);
	}-*/;

	public final native int info(String content, String contentType, Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.info(content, contentType, config);
	}-*/;

	public final native int hangup() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.hangup();
	}-*/;

	public final native int hangup(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.hangup(config);
	}-*/;

	public final native int hold() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.hold();
	}-*/;

	public final native int hold(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.hold(config);
	}-*/;

	public final native int resume() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.resume();
	}-*/;

	public final native int resume(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.resume(config);
	}-*/;

	public final native int rejectTransfer() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.rejectTransfer();
	}-*/;

	public final native int rejectTransfer(Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.rejectTransfer(config);
	}-*/;

	public final native int transfer(String to, Configuration config) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.transfer(to, config);
	}-*/;

	public final native int transfer(String to) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.transfer(to);
	}-*/;

	@Override
	protected final native void addEventListener(String type, IEventHandler<AEvent<org.sipml5.gwt.sipml5.session.Call.EventType>> callback) /*-{

		var callbackFunc = function(e) {
			var ev = @org.sipml5.gwt.sipml5.event.CallEvent::new(Lcom/google/gwt/core/client/JavaScriptObject;)(e);
			callback.@org.sipml5.gwt.sipml5.handler.IEventHandler::onEvent(Lorg/sipml5/gwt/sipml5/event/AEvent;)(ev);
		};

		this.@org.sipml5.gwt.sipml5.AEventTarget::instance.addEventListener(type, callbackFunc);
	}-*/;


	@Override
	protected String getTypeName(EventType type) {
		return type == EventType.ALL ? AEvent.AllWildcard : type.name().toLowerCase();
	}
}
