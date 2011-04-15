package ch.yarb.gwt.client;

import com.google.gwt.core.client.EntryPoint;
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
    final Label label = new Label(
        "yarb");
    RootPanel.get().add(label);

    System.out.println("got called and wrote out the label.. ");
  }
}
