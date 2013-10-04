package it.netgrid.gwt.sipml5.config;


public class ConfigurationFactory {

	private static ConfigurationFactory instance;

	public static ConfigurationFactory get() {
		if(ConfigurationFactory.instance == null) {
			ConfigurationFactory.instance = new ConfigurationFactory();
		}

		return ConfigurationFactory.instance;
	}

	private ConfigurationFactory() {}

	/**
	 * Example: { expires : 200, audio_remote:
	 * document.getElementById('audio_remote'), // <audio id="audio_remote"
	 * .../> video_local: document.getElementById('video_local'), // <video
	 * id="video_local" .../> video_remote:
	 * document.getElementById('video_remote'), // <video id="video_remote"
	 * .../> sip_caps : [ {name: '+g.oma.sip-im'}, {name: '+sip.ice'}, {name:
	 * 'language', value: '\"en,fr\"'} ], sip_headers : [ {name: 'What', value:
	 * 'Audio/Video call', session: false}, {name: 'Organization', value:
	 * 'Doubango Telecom', session: false} ] };
	 * 
	 * @return
	 */
	public final native Configuration buildConfig() /*-{
		return {
			expires : 200,
			sip_caps : [],
			sip_headers : []
		};
	}-*/;

	public final native SipHeader buildSipHeader() /*-{
		return {
			"name" : "",
			"value" : ""
		};
	}-*/;

	public final native SipCap buildSipCap() /*-{
		return {
			"name" : ""
		};
	}-*/;

	public final native StackConfig buildStackConfig() /*-{
		return {
			"realm" : "",
			"impi" : "",
			"impu" : "",
			"sip_headers" : [],
			"ice_servers" : []
		};
	}-*/;

	public final native Bandwidth buildBandwidth() /*-{
		return {};
	}-*/;

	public final native VideoSize buildVideoSize() /*-{
		return {};
	}-*/;
}
