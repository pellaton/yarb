package ch.yarb.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import ch.yarb.api.to.LogEntry;

public interface YarbGuiServiceAsync extends RemoteService {

  void ping(AsyncCallback<String> callback);

  void getRepositoryLog(String url, AsyncCallback<List<LogEntry>> callback);

}
