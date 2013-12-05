package org.sipml5.gwt.sipml5.handler;

import org.sipml5.gwt.sipml5.response.InitResponse;

public interface IInitHandler {

	public void onSuccess(InitResponse response);

	public void onError(InitResponse response);
}
