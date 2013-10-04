package it.netgrid.gwt.sipml5.session;

import it.netgrid.gwt.sipml5.AEventTarget;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class ASession extends AEventTarget {

	protected ASession(JavaScriptObject instance) {
		super(instance);
	}

}
