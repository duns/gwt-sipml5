package org.sipml5.gwt.sipml5.session;

import org.sipml5.gwt.sipml5.AEventTarget;
import org.sipml5.gwt.sipml5.config.Configuration;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ASession<T> extends AEventTarget<T> {

	protected ASession(JavaScriptObject instance) {
		super(instance);
	}

	public final native int getId() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance.getId();
	}-*/;

	public final native String getRemoteFriendlyName() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance
				.getRemoteFriendlyName();
	}-*/;

	public final native String getRemoteUri() /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance
				.getRemoteUri();
	}-*/;

	public final native void setConfiguration(Configuration configuration) /*-{
		this.@org.sipml5.gwt.sipml5.AEventTarget::instance
				.setConfiguration(configuration);
	}-*/;

	public final native int accept(Configuration configuration) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance
				.accept(configuration);
	}-*/;

	public final native int reject(Configuration configuration) /*-{
		return this.@org.sipml5.gwt.sipml5.AEventTarget::instance
				.reject(configuration);
	}-*/;
}
