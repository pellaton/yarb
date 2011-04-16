package ch.yarb.gwt.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface YarbGuiService extends RemoteService {

  String ping();

  List<String> getRepositoryLog();

}
