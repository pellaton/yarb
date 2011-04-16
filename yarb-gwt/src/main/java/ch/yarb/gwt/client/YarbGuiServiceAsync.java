package ch.yarb.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface YarbGuiServiceAsync extends RemoteService {

  void ping(AsyncCallback<String> callback);

}
