package ch.yarb.gwt.client;

import java.util.Collections;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import ch.yarb.api.to.ChangedPath;
import ch.yarb.api.to.LogEntry;


public class LogEntryPanel {

  private CellTable<LogEntry> logEntryTable;

  public FlowPanel buildLogPanel() {

    FlowPanel log = new FlowPanel();
    log.setStyleName("container");
    FlowPanel logEntries = new FlowPanel();

    FlowPanel changedPaths = new FlowPanel();
    CellTable<ChangedPath> changedPathsTable = buildChangedPathsTable();
    changedPaths.add(changedPathsTable);
    changedPaths.setStyleName("changedPaths");

    logEntries.setStyleName("logEntries");
    this.logEntryTable = buildLogEntryTable(changedPathsTable);
    logEntries.add(this.logEntryTable);


    log.add(logEntries);
    log.add(changedPaths);


    return log;
  }

  public void setLogEntryTableData(List<LogEntry> rowData) {
    this.logEntryTable.setRowData(rowData);
  }

  private CellTable<ChangedPath> buildChangedPathsTable() {
    CellTable<ChangedPath> changedPathsTable = new CellTable<ChangedPath>();
    TextColumn<ChangedPath> path = new TextColumn<ChangedPath>() {

      @Override
      public String getValue(ChangedPath changedPath) {
        return changedPath.getPath();
      }
    };
    changedPathsTable.addColumn(path, "path");
    return changedPathsTable;
  }

  private CellTable<LogEntry> buildLogEntryTable(final CellTable<ChangedPath> changedPathsTable) {

    CellTable<LogEntry> logEntriesTbl = new CellTable<LogEntry>();
    logEntriesTbl.setStyleName("tblLogEntries");

    TextColumn<LogEntry> revision = new TextColumn<LogEntry>() {

      @Override
      public String getValue(LogEntry logEntry) {
        return logEntry.getRevision();
      }
    };
    logEntriesTbl.addColumn(revision, "revision");

    TextColumn<LogEntry> author = new TextColumn<LogEntry>() {

      @Override
      public String getValue(LogEntry logEntry) {
        return logEntry.getAuthor();
      }
    };
    logEntriesTbl.addColumn(author, "author");

    TextColumn<LogEntry> timestamp = new TextColumn<LogEntry>() {

      @Override
      public String getValue(LogEntry logEntry) {
        return "";
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(logEntry.getTimestamp());
      }
    };
    logEntriesTbl.addColumn(timestamp, "timestamp");


    TextColumn<LogEntry> comment = new TextColumn<LogEntry>() {

      @Override
      public String getValue(LogEntry logEntry) {
        return logEntry.getComment();
      }
    };
    logEntriesTbl.addColumn(comment, "comment");


    final SingleSelectionModel<LogEntry> selectionModel = new SingleSelectionModel<LogEntry>();
    logEntriesTbl.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new Handler() {

      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        LogEntry curLogEntry = selectionModel.getSelectedObject();
        if (curLogEntry != null) {
          changedPathsTable.setRowData(curLogEntry.getChangedPathList());
        } else {
          List<? extends ChangedPath> emptyList = Collections.emptyList();
          changedPathsTable.setRowData(emptyList);
        }
      }
    });

    return logEntriesTbl;
  }
}
