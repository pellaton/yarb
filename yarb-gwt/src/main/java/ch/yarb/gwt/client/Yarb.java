package ch.yarb.gwt.client;

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
        final Label label = new Label("yarb says: " + result);
        RootPanel.get().add(label);
      }

      @Override
      public void onFailure(Throwable arg0) {
        final Label label = new Label("yarb failed to say anything.. : " + arg0);
        RootPanel.get().add(label);
      }
    });


    System.out.println("got called and wrote out the label.. ");
  }
}
