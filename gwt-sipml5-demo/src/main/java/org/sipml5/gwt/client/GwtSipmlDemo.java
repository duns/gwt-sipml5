package org.sipml5.gwt.client;

import org.sipml5.gwt.sipml5.SipMl;
import org.sipml5.gwt.sipml5.Stack;
import org.sipml5.gwt.sipml5.config.Bandwidth;
import org.sipml5.gwt.sipml5.config.Configuration;
import org.sipml5.gwt.sipml5.config.ConfigurationFactory;
import org.sipml5.gwt.sipml5.config.SipCap;
import org.sipml5.gwt.sipml5.config.StackConfig;
import org.sipml5.gwt.sipml5.config.VideoSize;
import org.sipml5.gwt.sipml5.event.AEvent;
import org.sipml5.gwt.sipml5.event.ASessionEvent;
import org.sipml5.gwt.sipml5.event.StackEvent;
import org.sipml5.gwt.sipml5.handler.IEventHandler;
import org.sipml5.gwt.sipml5.handler.IInitHandler;
import org.sipml5.gwt.sipml5.handler.IStackEventHandler;
import org.sipml5.gwt.sipml5.response.InitResponse;
import org.sipml5.gwt.sipml5.session.Call;
import org.sipml5.gwt.sipml5.session.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.Video;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtSipmlDemo implements EntryPoint, KeyUpHandler,
		IEventHandler<AEvent<Call.EventType>> {

	private Logger log = LoggerFactory.getLogger(getClass().getName());

	private String transferNumber;
	private Stack sipStack;
	private Registration sipSessionRegister;
	private Call sipSessionCall;
	private Call sipSessionTransferCall;
	private Video videoRemote, videoLocal;
	// private Element audioRemote;
	private boolean fullScreen = false;
	private Timer readyStateTimer;
	private boolean disableVideo = false;
	// private Video viewVideoLocal; // <video> (webrtc) or <div> (webrtc4all)
	// private Video viewVideoRemote;
	private Configuration configCall;

	private HTML txtRegStatus = new HTML();
	private TextBox txtDisplayName = new TextBox();
	private TextBox txtPrivateIdentity = new TextBox();
	private TextBox txtPublicIdentity = new TextBox();
	private TextBox txtPassword = new TextBox();
	private TextBox txtRealm = new TextBox();
	private TextBox txtWebsocketProxyUrl = new TextBox();
	private TextBox txtOutboundProxyUrl = new TextBox();
	private Button btnRegister = new Button("LogIn");
	private Button btnUnRegister = new Button("LogOut");

	private HTML txtCallStatus = new HTML();
	private TextBox txtPhoneNumber = new TextBox();
	private Button btnCall = new Button("Call");
	private Button btnHangUp = new Button("HangUp");
	// private Button btnHoldResume = new Button("Hold");
	// private Button btnTransfer = new Button("Transfer");

	private Audio ringtone = Audio.createIfSupported();
	private Audio ringbacktone = Audio.createIfSupported();
	private Audio dtmfTone = Audio.createIfSupported();

	// FIXME should be per session
	boolean held = false;
	boolean transfering = false;

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				log.info("Uncaught Exception", e);
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				boot();
			}
		});
	}

	public void boot() {
		RootPanel.get("txtRegStatus").add(txtRegStatus);
		txtRegStatus.setHorizontalAlignment(HTML.ALIGN_CENTER);
		txtRegStatus.setWidth("100%");

		RootPanel.get("txtDisplayName").add(txtDisplayName);
		txtDisplayName.setWidth("100%");
		txtDisplayName.setHeight("100%");
		txtDisplayName.getElement()
				.setAttribute("placeholder", "e.g. John Doe");

		RootPanel.get("txtPrivateIdentity").add(txtPrivateIdentity);
		txtPrivateIdentity.setWidth("100%");
		txtPrivateIdentity.setHeight("100%");
		txtPrivateIdentity.getElement().setAttribute("placeholder",
				"e.g. +33600000000");

		RootPanel.get("txtPublicIdentity").add(txtPublicIdentity);
		txtPublicIdentity.setWidth("100%");
		txtPublicIdentity.setHeight("100%");
		txtPublicIdentity.getElement().setAttribute("placeholder",
				"e.g. sip:+33600000000@doubango.org");

		RootPanel.get("txtPassword").add(txtPassword);
		txtPassword.setWidth("100%");
		txtPassword.setHeight("100%");
		txtPassword.getElement().setAttribute("type", "password");

		RootPanel.get("txtRealm").add(txtRealm);
		txtRealm.setWidth("100%");
		txtRealm.setHeight("100%");
		txtRealm.getElement().setAttribute("placeholder", "e.g. doubango.org");

		RootPanel.get("txtWebsocketProxyUrl").add(txtWebsocketProxyUrl);
		txtWebsocketProxyUrl.setWidth("100%");
		txtWebsocketProxyUrl.setHeight("100%");
		txtWebsocketProxyUrl.getElement().setAttribute("placeholder",
				"e.g. ws://sipml5.org:5062");

		RootPanel.get("txtOutboundProxyUrl").add(txtOutboundProxyUrl);
		txtOutboundProxyUrl.setWidth("100%");
		txtOutboundProxyUrl.setHeight("100%");
		txtOutboundProxyUrl.getElement().setAttribute("placeholder",
				"e.g. udp://sipml5.org:5060");

		RootPanel.get("btnRegister").add(btnRegister);
		btnRegister.setStyleName("btn btn-success");
		btnRegister.setEnabled(false);
		btnRegister.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sipRegister();
			}
		});

		RootPanel.get("btnUnRegister").add(btnUnRegister);
		btnUnRegister.setStyleName("btn btn-danger");
		btnUnRegister.setEnabled(false);
		btnUnRegister.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sipUnRegister();
			}
		});

		RootPanel.get("txtCallStatus").add(txtCallStatus);
		txtCallStatus.setHorizontalAlignment(HTML.ALIGN_CENTER);
		txtCallStatus.setWidth("100%");

		RootPanel.get("txtPhoneNumber").add(txtPhoneNumber);
		txtPhoneNumber.setWidth("100%");
		txtPhoneNumber.setHeight("100%");
		txtPhoneNumber.getElement().setAttribute("placeholder",
				"Enter phone number to call");

		RootPanel.get("btnCall").add(btnCall);
		btnCall.setStyleName("btn btn-primary");
		btnCall.setEnabled(false);
		btnCall.setText("Call Audio");
		btnCall.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				sipCall(Call.Type.AUDIO);
			}
		});

		RootPanel.get("btnHangUp").add(btnHangUp);
		btnHangUp.setStyleName("btn btn-primary");
		btnHangUp.setEnabled(false);
		// style="margin: 0; vertical-align:middle;"
		btnHangUp.setHeight("100%");
		btnHangUp.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				sipHangUp();
			}
		});

		RootPanel.get("ringtone").add(ringtone);
		ringtone.setSrc("sounds/ringtone.wav");
		ringtone.setLoop(true);

		RootPanel.get("ringbacktone").add(ringbacktone);
		ringbacktone.setSrc("sounds/ringbacktone.wav");
		ringbacktone.setLoop(true);

		RootPanel.get("dtmfTone").add(dtmfTone);
		dtmfTone.setSrc("sounds/dtmf.wav");

		Document document = Document.get();
		// videoLocal = document.getElementById("video_local");
		// videoRemote = document.getElementById("video_remote");
		// audioRemote = document.getElementById("audio_remote");

		// document.onkeyup = onKeyUp;
		RootPanel.get().addDomHandler(this, KeyUpEvent.getType());
		// divCallCtrl.onmousemove = onDivCallCtrlMouseMove;

		SipMl.setDebugLevel("info");

		loadCredentials();
		loadCallOptions();

		readyStateTimer = new Timer() {
			@Override
			public void run() {
				init();
			}
		};
		readyStateTimer.schedule(500);
	}

	private void init() {
		SipMl.init(new IInitHandler() {

			@Override
			public void onSuccess(InitResponse response) {
				postInit();
			}

			@Override
			public void onError(InitResponse response) {
				Window.alert("No support for WebRtc, Error "
						+ response.getMessage());
			}
		});
	}

	private void postInit() {
		log.info("getNavigatorFriendlyName: "
				+ SipMl.getNavigatorFriendlyName());

		log.info("getNavigatorVersion: " + SipMl.getNavigatorVersion());

		log.info("getWebRtc4AllVersion: " + SipMl.getWebRtc4AllVersion());

		log.info("isScreenShareSupported: " + SipMl.isScreenShareSupported());

		log.info("haveMediaStream: " + SipMl.haveMediaStream());

		log.info("isInitialized: " + SipMl.isInitialized());

		log.info("isNavigatorOutdated: " + SipMl.isNavigatorOutdated());

		log.info("isReady: " + SipMl.isReady());

		log.info("isWebRtc4AllPluginOutdated: "
				+ SipMl.isWebRtc4AllPluginOutdated());

		log.info("isWebRtcSupported: " + SipMl.isWebRtcSupported());

		log.info("isWebSocketSupported: " + SipMl.isWebSocketSupported());

		// FIXME: displays must be per session

		// attach video displays
		if (SipMl.isWebRtc4AllSupported()) {
			// viewVideoLocal = (Video)document.getElementById("divVideoLocal");
			// viewVideoRemote =
			// (Video)document.getElementById("divVideoRemote");
			// WebRtc4all_SetDisplays(viewVideoLocal, viewVideoRemote); //
			// FIXME: move to SIPml.* API
		} else {
			// viewVideoLocal = videoLocal;
			// viewVideoRemote = videoRemote;
		}

		if (!SipMl.isWebRtc4AllSupported() && !SipMl.isWebRtcSupported()) {
			if (Window
					.confirm("Your browser does not support WebRTC.\naudio/video calls will be disabled.\nDo you want to download a WebRTC-capable browser?")) {
				Window.Location
						.assign("https://www.google.com/intl/en/chrome/browser/");
			}
		}

		btnRegister.setEnabled(true);
		btnRegister.setFocus(true);
		// document.body.style.cursor = "default";

		configCall = ConfigurationFactory.get().buildConfig();
		configCall.setAudioRemote("audio_remote");
		configCall.setVideoLocal("video_local");
		configCall.setVideoRemote("video_remote");

		Bandwidth bandWidth = ConfigurationFactory.get().buildBandwidth();
		configCall.setBandwidth(bandWidth);

		VideoSize videoSize = ConfigurationFactory.get().buildVideoSize();
		configCall.setVideoSize(videoSize);

		SipCap sipCap1 = ConfigurationFactory.get().buildSipCap();
		sipCap1.setName("+g.oma.sip-im");
		configCall.addSipCap(sipCap1);

		SipCap sipCap2 = ConfigurationFactory.get().buildSipCap();
		sipCap1.setName("+sip.ice");
		configCall.addSipCap(sipCap2);

		SipCap sipCap3 = ConfigurationFactory.get().buildSipCap();
		sipCap1.setName("language");
		sipCap1.setValue("\"en,fr\"");
		configCall.addSipCap(sipCap3);
	}

	private void loadCallOptions() {
		if (Storage.isLocalStorageSupported()) {
			String value;
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.call.phone_number")) != null)
				txtPhoneNumber.setValue(value);
			disableVideo = (Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.expert.disable_video") == "true");

			txtCallStatus.setHTML("<i>Video "
					+ (disableVideo ? "disabled" : "enabled") + "</i>");
		}
	}

	private void saveCallOptions() {
		if (Storage.isLocalStorageSupported()) {
			Storage.getLocalStorageIfSupported()
					.setItem("org.doubango.call.phone_number",
							txtPhoneNumber.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.expert.disable_video",
					disableVideo ? "true" : "false");
		}
	}

	private void loadCredentials() {
		if (Storage.isLocalStorageSupported()) {
			// IE returns "null" if not defined
			String value;
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.identity.display_name")) != null)
				txtDisplayName.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.identity.impi")) != null)
				txtPrivateIdentity.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.identity.impu")) != null)
				txtPublicIdentity.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.identity.password")) != null)
				txtPassword.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.identity.realm")) != null)
				txtRealm.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.expert.websocket_server_url")) != null)
				txtWebsocketProxyUrl.setValue(value);
			if ((value = Storage.getLocalStorageIfSupported().getItem(
					"org.doubango.expert.sip_outboundproxy_url")) != null)
				txtOutboundProxyUrl.setValue(value);
		} else {
			/*
			 * txtDisplayName.value = "005"; txtPrivateIdentity.value = "005";
			 * txtPublicIdentity.value = "sip:005@192.168.0.28";
			 * txtPassword.value = "005"; txtRealm.value = "192.168.0.28";
			 * txtPhoneNumber.value = "005";
			 */
		}
	}

	private void saveCredentials() {
		if (Storage.isLocalStorageSupported()) {
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.identity.display_name",
					txtDisplayName.getValue());
			Storage.getLocalStorageIfSupported()
					.setItem("org.doubango.identity.impi",
							txtPrivateIdentity.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.identity.impu", txtPublicIdentity.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.identity.password", txtPassword.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.identity.realm", txtRealm.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.expert.websocket_server_url",
					txtWebsocketProxyUrl.getValue());
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.expert.sip_outboundproxy_url",
					txtOutboundProxyUrl.getValue());
		}
	};

	private void sipRegister() {
		btnRegister.setEnabled(false);
		if (txtRealm.getValue().isEmpty()
				|| txtPrivateIdentity.getValue().isEmpty()
				|| txtPublicIdentity.getValue().isEmpty()) {
			txtRegStatus.setHTML("<b>Please fill madatory fields (*)</b>");
			btnRegister.setEnabled(true);
			return;
		}
		// var o_impu = tsip_uri.prototype.Parse(txtPublicIdentity.value);
		// if (!o_impu || !o_impu.s_user_name || !o_impu.s_host) {
		// txtRegStatus.setHTML("<b>[" + txtPublicIdentity.value +
		// "] is not a valid Public identity</b>");
		// btnRegister.setEnabled(true);
		// return;
		// }

		// enable notifications if not already done
		// if (window.webkitNotifications &&
		// window.webkitNotifications.checkPermission() != 0) {
		// window.webkitNotifications.requestPermission();
		// }

		// save credentials
		saveCredentials();

		// update debug level to be sure new values will be used if the user
		// haven't updated the page
		SipMl.setDebugLevel("info");

		// create SIP stack
		// sipStack = new SIPml.Stack({
		// enable_early_ims: (window.localStorage ?
		// window.localStorage.getItem("org.doubango.expert.disable_early_ims")
		// != "true" : true), // Must be true unless you"re using a real IMS
		// network
		// enable_media_stream_cache: (window.localStorage ?
		// window.localStorage.getItem("org.doubango.expert.enable_media_caching")
		// == "true" : false),
		// }
		// );
		log.info("Configuring... ");
		StackConfig stackConfig = ConfigurationFactory.get().buildStackConfig();

		stackConfig.setRealm(txtRealm.getValue());
		stackConfig.setImpi(txtPrivateIdentity.getValue());
		stackConfig.setImpu(txtPublicIdentity.getValue());
		stackConfig.setPassword(txtPassword.getValue());
		stackConfig.setDisplayName(txtDisplayName.getValue());
		stackConfig.setWebsocketProxyUrl(txtWebsocketProxyUrl.getValue());
		stackConfig.setOutboundProxyUrl(txtOutboundProxyUrl.getValue());

		// FIXME: hardcoded
		stackConfig.setEnableRtcwebBreaker(false);
		stackConfig.setEnableClick2Call(false);

		// SipHeader sip1 = ConfigurationFactory.get().buildSipHeader();
		// sip1.setName("User-Agent");
		// sip1.setValue("IM-client/OMA1.0 sipML5-v1.2013.08.10B");
		// stackConfig.addSipHeader(sip1);
		//
		// SipHeader sip2 = ConfigurationFactory.get().buildSipHeader();
		// sip2.setName("Organization");
		// sip2.setValue("Mark Donszelmann");
		// stackConfig.addSipHeader(sip2);

		stackConfig.setEventsHandler("*", new IStackEventHandler() {
			// onSipEventStack
			@Override
			public void onEvent(StackEvent event) {
				log.info("StackEvent '" + event.getDescription() + "' "
						+ event.getType());

				switch (event.getType()) {
				case STARTED: {
					// LogIn (REGISTER) as soon as the stack finish starting
					log.info("Register");
					Configuration registerConfig = ConfigurationFactory.get()
							.buildConfig();

					registerConfig.setExpires(200);

					SipCap sipCap1 = ConfigurationFactory.get().buildSipCap();
					sipCap1.setName("+g.oma.sip-im");
					registerConfig.addSipCap(sipCap1);

					SipCap sipCap2 = ConfigurationFactory.get().buildSipCap();
					sipCap2.setName("+audio");
					registerConfig.addSipCap(sipCap2);

					SipCap sipCap3 = ConfigurationFactory.get().buildSipCap();
					sipCap3.setName("language");
					sipCap3.setValue("\"en,fr\"");
					registerConfig.addSipCap(sipCap3);

					sipSessionRegister = sipStack.newRegister(registerConfig);
					sipSessionRegister
							.addEventListener(
									Registration.EventType.ALL,
									new IEventHandler<AEvent<Registration.EventType>>() {

										// onSipEventSession
										@Override
										public void onEvent(
												AEvent<Registration.EventType> event) {
											log.info("Register Event "
													+ event.getDescription()
													+ " " + event.getType());

											boolean connected = (event
													.getType() == Registration.EventType.CONNECTED);

											switch (event.getType()) {

											case CONNECTING:
											case CONNECTED:
												log.info("Registered");
												uiOnConnectionEvent(connected,
														!connected);
												txtRegStatus.setHTML("<i>"
														+ event.getDescription()
														+ "</i>");
												break;

											case TERMINATING:
											case TERMINATED:
												log.info("Terminated");
												uiOnConnectionEvent(false,
														false);

												sipSessionCall = null;
												sipSessionRegister = null;

												txtRegStatus.setHTML("<i>"
														+ event.getDescription()
														+ "</i>");

												break;

											case ALL:
											case CANCELLED_REQUEST:
											case GLOBAL_ERROR:
											case I_AO_REQUEST:
											case I_REQUEST:
											case MEDIA_ADDED:
											case MEDIA_REMOVED:
											case MESSAGE_ERROR:
											case O_REQUEST:
											case SENT_REQUEST:
											case TRANSPORT_ERROR:
											case WEBRTC_ERROR:
												break;
											default:
												break;

											}

										}
									});
					sipSessionRegister.register();
					break;
				}

				case STOPPING:
				case STOPPED:
				case FAILED_TO_START:
				case FAILED_TO_STOP: {
					boolean failure = (event.getType() == Stack.EventType.FAILED_TO_START)
							|| (event.getType() == Stack.EventType.FAILED_TO_STOP);
					sipStack = null;
					sipSessionRegister = null;
					sipSessionCall = null;

					uiOnConnectionEvent(false, false);

					stopRingbackTone();
					stopRingTone();

					uiVideoDisplayShowHide(false);
					// divCallOptions.style.opacity = 0;

					txtCallStatus.setHTML("");
					txtRegStatus.setHTML(failure ? "<i>Disconnected: <b>"
							+ event.getDescription() + "</b></i>"
							: "<i>Disconnected</i>");
					break;
				}

				case I_NEW_CALL: {
					if (sipSessionCall != null) {
						// do not accept the incoming call if we're already 'in
						// call'
						((Call) event.getNewSession()).hangup(); // comment this
																	// line for
																	// multi-line
																	// support
					} else {
						sipSessionCall = (Call) event.getNewSession();
						// start listening for events
						sipSessionCall.setConfiguration(configCall);
						sipSessionCall.addEventListener(Call.EventType.ALL,
								GwtSipmlDemo.this);

						uiBtnCallSetText("Answer");
						btnHangUp.setText("Reject");
						btnCall.setEnabled(true);
						btnHangUp.setEnabled(true);

						startRingTone();

						String remoteNumber = sipSessionCall
								.getRemoteFriendlyName() != null ? sipSessionCall
								.getRemoteFriendlyName() : "unknown";
						txtCallStatus.setHTML("<i>Incoming call from [<b>"
								+ remoteNumber + "</b>]</i>");
						showNotifICall(remoteNumber);
					}
					break;
				}

				case M_PERMISSION_REQUESTED: {
					// divGlassPanel.style.visibility = 'visible';
					break;
				}

				case M_PERMISSION_ACCEPTED:
				case M_PERMISSION_REFUSED: {
					// divGlassPanel.style.visibility = 'hidden';
					if (event.getType() == Stack.EventType.M_PERMISSION_REFUSED) {
						uiCallTerminated("Media stream permission denied");
					}
					break;
				}

				case STARTING:
					break;
				default:
					log.info("Unknown stack event type: " + event.getType());
					break;
				}
			}
		});

		sipStack = new Stack(stackConfig);

		log.info("Starting... ");

		sipStack.start();

		// txtRegStatus.innerHTML = '<b>Failed to start the SIP stack</b>';
		btnRegister.setEnabled(true);
	}

	private void sipUnRegister() {
		if (sipStack != null) {
			sipStack.stop(0); // shutdown all sessions
		}
	}

	// makes a call (SIP INVITE)
	private void sipCall(Call.Type type) {
		if ((sipStack != null) && (sipSessionCall == null)
				&& !txtPhoneNumber.getValue().isEmpty()) {
			if (type == Call.Type.SCREENSHARE) {
				if (!SipMl.isScreenShareSupported()) {
					Window.alert("Screen sharing not supported. Are you using chrome 26+?");
					return;
				}
				if (!Window.Location.getProtocol().startsWith("https")) {
					if (Window
							.confirm("Screen sharing requires https://. Do you want to be redirected?")) {
						sipUnRegister();
						Window.Location
								.replace("https://ns313841.ovh.net/call.htm");
					}
					return;
				}
			}
			btnCall.setEnabled(false);
			btnHangUp.setEnabled(true);

			// if(window.localStorage) {
			// configCall.bandwidth =
			// tsk_string_to_object(window.localStorage.getItem('org.doubango.expert.bandwidth'));
			// // already defined at stack-level but redefined to use latest
			// values
			// configCall.video_size =
			// tsk_string_to_object(window.localStorage.getItem('org.doubango.expert.video_size'));
			// // already defined at stack-level but redefined to use latest
			// values
			// }

			// create call session
			sipSessionCall = sipStack.newCall(type, configCall);
			sipSessionCall.addEventListener(Call.EventType.ALL, this);

			// make call
			if (sipSessionCall.call(txtPhoneNumber.getValue()) != 0) {
				sipSessionCall = null;
				txtCallStatus.setText("Failed to make call");
				btnCall.setEnabled(true);
				btnHangUp.setEnabled(false);
				return;
			}
			saveCallOptions();
		} else if (sipSessionCall != null) {
			log.info("Answering");
			txtCallStatus.setHTML("<i>Connecting...</i>");
			sipSessionCall.accept(configCall);
		}
	}

	// transfers the call
	private void sipTransfer() {
		if (sipSessionCall != null) {
			String destination = Window.prompt("Enter destination number", "");
			if ((destination != null) && !destination.isEmpty()) {
				// btnTransfer.setEnabled(false);
				if (sipSessionCall.transfer(destination) != 0) {
					txtCallStatus.setHTML("<i>Call transfer failed</i>");
					// btnTransfer.setEnabled(true);
					return;
				}
				txtCallStatus.setHTML("<i>Transfering the call...</i>");
			}
		}
	}

	// holds or resumes the call
	private void sipToggleHoldResume() {
		// if (sipSessionCall != null) {
		// btnHoldResume.setEnabled(false);
		// txtCallStatus.setHTML(sipSessionCall.isHeld() ?
		// "<i>Resuming the call...</i>" : "<i>Holding the call...</i>");
		// int result = sipSessionCall.isHeld() ? sipSessionCall.resume() :
		// sipSessionCall.hold();
		// if (result != 0) {
		// txtCallStatus.setHTML("<i>Hold / Resume failed</i>");
		// btnHoldResume.setEnabled(true);
		// return;
		// }
		// }
	}

	// terminates the call (SIP BYE or CANCEL)
	private void sipHangUp() {
		if (sipSessionCall != null) {
			txtCallStatus.setHTML("<i>Terminating the call...</i>");
			sipSessionCall.hangup();
		}
	}

	private void sipSendDTMF(char c) {
		if ((sipSessionCall != null) && (c != 0)) {
			if (sipSessionCall.dtmf(c) == 0) {
				dtmfTone.play();
			}
		}
	}

	private void startRingTone() {
		ringtone.play();
	}

	private void stopRingTone() {
		ringtone.pause();
	}

	private void startRingbackTone() {
		ringbacktone.play();
	}

	private void stopRingbackTone() {
		ringbacktone.pause();
	}

	private void toggleFullScreen() {
		// if (videoRemote.webkitSupportsFullscreen) {
		// fullScreen(!videoRemote.webkitDisplayingFullscreen);
		// }
		// else {
		fullScreen(!fullScreen);
		// }
	}

	private void openKeyPad() {
		// divKeyPad.style.visibility = "visible";
		// divKeyPad.style.left = ((document.body.clientWidth -
		// C.divKeyPadWidth) >> 1) + "px";
		// divKeyPad.style.top = "70px";
		// divGlassPanel.style.visibility = "visible";
	}

	private void closeKeyPad() {
		// divKeyPad.style.left = "0px";
		// divKeyPad.style.top = "0px";
		// divKeyPad.style.visibility = "hidden";
		// divGlassPanel.style.visibility = "hidden";
	}

	private void fullScreen(boolean fs) {
		fullScreen = fs;
		// if (tsk_utils_have_webrtc4native() && fullScreen &&
		// videoRemote.webkitSupportsFullscreen) {
		// if (fullScreen) {
		// videoRemote.webkitEnterFullScreen();
		// }
		// else {
		// videoRemote.webkitExitFullscreen();
		// }
		// }
		// else {
		// if (tsk_utils_have_webrtc4npapi()) {
		// try { if(window.__o_display_remote)
		// window.__o_display_remote.setFullScreen(fs); }
		// catch (e) { divVideo.setAttribute("class", fs ? "full-screen" :
		// "normal-screen"); }
		// }
		// else {
		// divVideo.setAttribute("class", fs ? "full-screen" : "normal-screen");
		// }
		// }
	}

	private void showNotifICall(String number) {
		// permission already asked when we registered
		// if (window.webkitNotifications &&
		// window.webkitNotifications.checkPermission() == 0) {
		// if (notifICall != null) {
		// notifICall.cancel();
		// }
		// notifICall =
		// window.webkitNotifications.createNotification("images/sipml-34x39.png",
		// "Incoming call", "Incoming call from " + number);
		// notifICall.onclose = function () { notifICall = null; };
		// notifICall.show();
		// }
		Window.alert("Incoming call from " + number);
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// evt = (evt || window.event);
		if (event.getNativeKeyCode() == 27) {
			fullScreen(false);
		} else if (event.isControlKeyDown() && event.isShiftKeyDown()) { // CTRL
																			// +
																			// SHIFT
			if (event.getNativeKeyCode() == 65
					|| event.getNativeKeyCode() == 86) { // A (65) or V (86)
				disableVideo = (event.getNativeKeyCode() == 65);
				txtCallStatus.setHTML("<i>Video "
						+ (disableVideo ? "disabled" : "enabled") + "</i>");
				Storage.getLocalStorageIfSupported().setItem(
						"org.doubango.expert.disable_video", "" + disableVideo);
			}
		}
	}

	private void onDivCallCtrlMouseMove(MouseMoveEvent event) {
		// try { // IE: DOM not ready
		// if (tsk_utils_have_stream()) {
		// btnCall.disabled = (!tsk_utils_have_stream() || !sipSessionRegister
		// || !sipSessionRegister.is_connected());
		// document.getElementById("divCallCtrl").onmousemove = null; //
		// unsubscribe
		// }
		// }
		// catch (e) { }
	}

	private void uiOnConnectionEvent(boolean connected, boolean connecting) { // should
																				// be
																				// enum:
																				// connecting,
																				// connected,
																				// terminating,
																				// terminated
		btnRegister.setEnabled(!connected && !connecting);
		btnUnRegister.setEnabled(connected || connecting);
		btnCall.setEnabled(connected && SipMl.isWebRtcSupported()
				&& SipMl.haveMediaStream());
		btnHangUp.setEnabled(sipSessionCall != null);
	}

	private void uiVideoDisplayEvent(boolean local, boolean added) {
		Video video = local ? videoLocal : videoRemote;

		if (added) {
			if (SipMl.isWebRtc4AllSupported()) {
				// if (local){ if(window.__o_display_local)
				// window.__o_display_local.style.visibility = "visible"; }
				// else { if(window.__o_display_remote)
				// window.__o_display_remote.style.visibility = "visible"; }
			} else {
				// video.style.opacity = 1;
			}
			uiVideoDisplayShowHide(true);
		} else {
			if (SipMl.isWebRtc4AllSupported()) {
				// if (local){ if(window.__o_display_local)
				// window.__o_display_local.style.visibility = "hidden"; }
				// else { if(window.__o_display_remote)
				// window.__o_display_remote.style.visibility = "hidden"; }
			} else {
				// video.style.opacity = 0;
			}
			fullScreen(false);
		}
	}

	private void uiVideoDisplayShowHide(boolean show) {
		if (show) {
			// tdVideo.style.height = "340px";
			// divVideo.style.height = navigator.appName ==
			// "Microsoft Internet Explorer" ? "100%" : "340px";
		} else {
			// tdVideo.style.height = "0px";
			// divVideo.style.height = "0px";
		}
		// btnFullScreen.setEnabled(show);
	}

	private void uiDisableCallOptions() {
		if (Storage.isLocalStorageSupported()) {
			Storage.getLocalStorageIfSupported().setItem(
					"org.doubango.expert.disable_callbtn_options", "true");
			uiBtnCallSetText("Call");
			Window.alert("Use expert view to enable the options again (/!\\requires re-loading the page)");
		}
	}

	private void uiBtnCallSetText(String text) {
		if (text.equalsIgnoreCase("Call")) {
			final boolean disableCallBtnOptions = false;
			btnCall.setText(disableCallBtnOptions ? "Call"
					: "Call <span id=\"spanCaret\" class=\"caret\">");
			btnCall.setHTML(disableCallBtnOptions ? "Call"
					: "Call <span id=\"spanCaret\" class=\"caret\">");
			btnCall.getElement().setAttribute(
					"class",
					disableCallBtnOptions ? "btn btn-primary"
							: "btn btn-primary dropdown-toggle");
			// need to remove handler also FIXME
			// btnCall.addClickHandler(new ClickHandler() {
			//
			// @Override
			// public void onClick(ClickEvent event) {
			// if (disableCallBtnOptions) {
			// sipCall(disableVideo ? Call.Type.AUDIO : Call.Type.AUDIOVIDEO);
			// }
			// }
			// });
			// ulCallOptions.style.visibility = disableCallBtnOptions ? "hidden"
			// : "visible";
			// if(!disableCallBtnOptions && ulCallOptions.parentNode !=
			// divBtnCallGroup){
			// divBtnCallGroup.appendChild(ulCallOptions);
			// }
			// else if(disableCallBtnOptions && ulCallOptions.parentNode ==
			// divBtnCallGroup) {
			// document.body.appendChild(ulCallOptions);
			// }
		} else {
			btnCall.setText(text);
			btnCall.setHTML(text);
			btnCall.getElement().setAttribute("class", "btn btn-primary");
			btnCall.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					sipCall(disableVideo ? Call.Type.AUDIO
							: Call.Type.AUDIOVIDEO);
				}
			});
			// ulCallOptions.style.visibility = "hidden";
			// if(ulCallOptions.parentNode == divBtnCallGroup){
			// document.body.appendChild(ulCallOptions);
			// }
		}
	}

	private void uiCallTerminated(String description) {
		uiBtnCallSetText("Call");
		btnHangUp.setText("HangUp");
		// btnHoldResume.setText("hold");
		btnCall.setEnabled(true);
		btnHangUp.setEnabled(true);

		sipSessionCall.removeEventListener(Call.EventType.ALL);
		sipSessionCall = null;

		stopRingbackTone();
		stopRingTone();

		txtCallStatus.setHTML("<i>" + description + "</i>");
		uiVideoDisplayShowHide(false);
		// divCallOptions.style.opacity = 0;

		uiVideoDisplayEvent(true, false);
		uiVideoDisplayEvent(false, false);

		Timer timer = new Timer() {
			public void run() {
				if (sipSessionCall != null) {
					txtCallStatus.setHTML("");
				}
			}
		};
		timer.schedule(2500);
	}

	// Callback function for SIP sessions (CALL...)
	@Override
	public void onEvent(AEvent<Call.EventType> aEvent) {
		log.info("Session Event " + aEvent.getDescription() + " "
				+ aEvent.getType());

		ASessionEvent<Call.EventType> event = (ASessionEvent<Call.EventType>) aEvent;

		switch (event.getType()) {
		case CONNECTING:
		case CONNECTED: {
			boolean connected = (event.getType() == Call.EventType.CONNECTED);
			btnHangUp.setText("HangUp");
			btnCall.setEnabled(false);
			btnHangUp.setEnabled(true);
			// btnTransfer.setEnabled(true);

			if (connected) {
				stopRingbackTone();
				stopRingTone();

				// if (oNotifICall) {
				// oNotifICall.cancel();
				// oNotifICall = null;
				// }
			}

			txtCallStatus.setHTML("<i>" + event.getDescription() + "</i>");
			// divCallOptions.style.opacity = connected ? 1 : 0;

			if (SipMl.isWebRtc4AllSupported()) { // IE don"t provide stream
													// callback
				uiVideoDisplayEvent(true, true);
				uiVideoDisplayEvent(false, true);
			}
			break;
		} // "connecting" | "connected"
		case TERMINATING:
		case TERMINATED: {
			uiCallTerminated(event.getDescription());
			break;
		} // "terminating" | "terminated"

		case M_STREAM_VIDEO_LOCAL_ADDED: {
			uiVideoDisplayEvent(true, true);
			break;
		}
		case M_STREAM_VIDEO_LOCAL_REMOVED: {
			uiVideoDisplayEvent(true, false);
			break;
		}
		case M_STREAM_VIDEO_REMOTE_ADDED: {
			uiVideoDisplayEvent(false, true);
			break;
		}
		case M_STREAM_VIDEO_REMOTE_REMOVED: {
			uiVideoDisplayEvent(false, false);
			break;
		}

		case M_STREAM_AUDIO_LOCAL_ADDED:
		case M_STREAM_AUDIO_LOCAL_REMOVED:
		case M_STREAM_AUDIO_REMOTE_ADDED:
		case M_STREAM_AUDIO_REMOTE_REMOVED: {
			break;
		}

		case I_ECT_NEW_CALL: {
			sipSessionTransferCall = (Call) event.getSession();
			break;
		}

		case I_AO_REQUEST: {
			int sipResponseCode = event.getSipResponseCode();
			if (sipResponseCode == 180 || sipResponseCode == 183) {
				startRingbackTone();
				txtCallStatus.setHTML("<i>Remote ringing...</i>");
			}
			break;
		}

		case M_EARLY_MEDIA: {
			stopRingbackTone();
			stopRingTone();
			txtCallStatus.setHTML("<i>Early media started</i>");
			break;
		}

		case M_LOCAL_HOLD_OK: {
			if (transfering) {
				transfering = false;
				// this.AVSession.TransferCall(this.transferUri);
			}
			// btnHoldResume.setText("Resume");
			// btnHoldResume.setEnabled(true);
			txtCallStatus.setHTML("<i>Call placed on hold</i>");
			held = true;
			break;
		}
		case M_LOCAL_HOLD_NOK: {
			transfering = false;
			// btnHoldResume.setText("Hold");
			// btnHoldResume.setEnabled(true);
			txtCallStatus
					.setHTML("<i>Failed to place remote party on hold</i>");
			break;
		}
		case M_LOCAL_RESUME_OK: {
			transfering = false;
			// btnHoldResume.setText("Hold");
			// btnHoldResume.setEnabled(true);
			txtCallStatus.setHTML("<i>Call taken off hold</i>");
			held = false;

			if (SipMl.isWebRtc4AllSupported()) { // IE don"t provide stream
													// callback yet
				uiVideoDisplayEvent(true, true);
				uiVideoDisplayEvent(false, true);
			}
			break;
		}
		case M_LOCAL_RESUME_NOK: {
			transfering = false;
			// btnHoldResume.setEnabled(true);
			txtCallStatus.setHTML("<i>Failed to unhold call</i>");
			break;
		}
		case M_REMOTE_HOLD: {
			txtCallStatus.setHTML("<i>Placed on hold by remote party</i>");
			break;
		}
		case M_REMOTE_RESUME: {
			txtCallStatus.setHTML("<i>Taken off hold by remote party</i>");
			break;
		}

		case O_ECT_TRYING: {
			txtCallStatus.setHTML("<i>Call transfer in progress...</i>");
			break;
		}
		case O_ECT_ACCEPTED: {
			txtCallStatus.setHTML("<i>Call transfer accepted</i>");
			break;
		}
		case O_ECT_COMPLETED:
		case I_ECT_COMPLETED: {
			txtCallStatus.setHTML("<i>Call transfer completed</i>");
			// btnTransfer.setEnabled(true);
			if (sipSessionTransferCall != null) {
				sipSessionCall = sipSessionTransferCall;
			}
			sipSessionTransferCall = null;
			break;
		}
		case O_ECT_FAILED:
		case I_ECT_FAILED: {
			txtCallStatus.setHTML("<i>Call transfer failed</i>");
			// btnTransfer.setEnabled(true);
			break;
		}
		case O_ECT_NOTIFY:
		case I_ECT_NOTIFY: {
			txtCallStatus.setHTML("<i>Call Transfer: <b>"
					+ event.getSipResponseCode() + " " + event.getDescription()
					+ "</b></i>");
			if (event.getSipResponseCode() >= 300) {
				if (held) {
					sipSessionCall.resume();
				}
				// btnTransfer.setEnabled(true);
			}
			break;
		}
		case I_ECT_REQUESTED: {
			String message = "Do you accept call transfer to ["
					+ event.getTransferDestinationFriendlyName() + "]?";
			if (Window.confirm(message)) {
				txtCallStatus.setHTML("<i>Call transfer in progress...</i>");
				sipSessionCall.acceptTransfer();
				break;
			}
			sipSessionCall.rejectTransfer();
			break;
		}
		default: {
			log.warn("Unknown session event type " + event.getType());
			break;
		}
		}
	}
}
