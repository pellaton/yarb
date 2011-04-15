package ch.yarb.service.to;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;


public class LogEntry implements Serializable {

  private final String revision;
  private final DateTime timestamp;
  private final String author;
  private final String comment;
  private final List<ChangedPath> changedPathList;


  public LogEntry(String revision, DateTime timestamp, String author, String comment, List<ChangedPath> changedPathList) {
    this.revision = revision;
    this.timestamp = timestamp;
    this.author = author;
    this.comment = comment;
    this.changedPathList = changedPathList;
  }


  public String getRevision() {
    return this.revision;
  }

  public DateTime getTimestamp() {
    return this.timestamp;
  }

  public String getAuthor() {
    return this.author;
  }

  public String getComment() {
    return this.comment;
  }

  public List<ChangedPath> getChangedPathList() {
    return this.changedPathList;
  }
}

