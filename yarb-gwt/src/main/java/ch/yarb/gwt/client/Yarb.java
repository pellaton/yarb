package ch.yarb.gwt.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import ch.yarb.api.to.LogEntry;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Yarb implements EntryPoint {

  private YarbGuiServiceAsync yarbGuiService;
  private FlowPanel main;
  private LogEntryPanel logEntries;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    this.yarbGuiService = GWT.create(YarbGuiService.class);

    this.main = new FlowPanel();
    this.main.setStyleName("main");
    RootPanel.get().add(this.main);

    final Label label = new Label("welcome to yarb - enter the url, you want to browse: ");

    FlowPanel input = new FlowPanel();
    input.setStyleName("container");
    final TextBox repoUrl = new TextBox();
    repoUrl.setStyleName("inputRepoUrl");
    final Button button = new Button("go");
    button.setStyleName("buttonGo");
    input.add(repoUrl);
    input.add(button);

    this.logEntries = new LogEntryPanel();

    this.main.add(label);
    this.main.add(input);
    this.main.add(this.logEntries.buildLogPanel());


    button.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {

        String url = repoUrl.getValue();

        GWT.log("start loading log for url " + url);

        Yarb.this.yarbGuiService.getRepositoryLog(url, new RepoCallback());

      }
    });

    // TODO MRO remove default value, just for testing..
    repoUrl.setValue("http://eclipse-cheatsheet.googlecode.com/svn/trunk/");

  }

  private final class RepoCallback implements AsyncCallback<List<LogEntry>> {

    @Override
    public void onFailure(Throwable caught) {
      RootPanel.get().add(new ErrorDialog("onFailure: " + caught));
    }

    @Override
    public void onSuccess(List<LogEntry> result) {
      Yarb.this.logEntries.setLogEntryTableData(result);
    }
  }


}
