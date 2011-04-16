package ch.yarb.gwt.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import ch.yarb.api.to.LogEntry;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Yarb implements EntryPoint {


  private YarbGuiServiceAsync yarbGuiService;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    this.yarbGuiService = GWT.create(YarbGuiService.class);

    final Label label = new Label("welcome to yarb: ");
    final TextBox box = new TextBox();
    final Button button = new Button("go");

    RootPanel.get().add(label);
    RootPanel.get().add(box);
    RootPanel.get().add(button);

    button.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {

        String url = box.getValue();

        Yarb.this.yarbGuiService.getRepositoryLog(url, new RepoCallback());

      }
    });

  }

  private final class RepoCallback implements AsyncCallback<List<LogEntry>> {

    @Override
    public void onFailure(Throwable caught) {
      System.out.println("onFailure: " + caught);
    }

    @Override
    public void onSuccess(List<LogEntry> result) {
      for (LogEntry curResult : result) {
        RootPanel.get().add(
            new Label(curResult.getRevision() + " - " + curResult.getChangedPathList()));
      }
    }
  }


}
