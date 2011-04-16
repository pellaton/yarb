package ch.yarb.gwt.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Yarb implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    YarbGuiServiceAsync yarbGuiService = GWT.create(YarbGuiService.class);

    yarbGuiService.ping(new AsyncCallback<String>() {

      @Override
      public void onSuccess(String result) {

        StringBuilder sb = new StringBuilder();
        sb.append(result);

        final Label label = new Label("yarb says: " + sb.toString());
        RootPanel.get().add(label);
      }

      @Override
      public void onFailure(Throwable arg0) {
        final Label label = new Label("yarb failed to say anything.. : " + arg0);
        RootPanel.get().add(label);
      }
    });


    yarbGuiService.getRepositoryLog(new AsyncCallback<List<String>>() {

      @Override
      public void onFailure(Throwable caught) {
        final Label label = new Label("list : " + caught);
        RootPanel.get().add(label);
      }

      @Override
      public void onSuccess(List<String> result) {
        StringBuilder sb = new StringBuilder();

        for (String curResult : result) {
          sb.append(curResult);
        }

        final Label label = new Label("yarb says: " + sb.toString());
        RootPanel.get().add(label);

      }});


    System.out.println("got called and wrote out the label.. ");
  }
}
