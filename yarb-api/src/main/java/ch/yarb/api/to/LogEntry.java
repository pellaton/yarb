package ch.yarb.api.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * An object representing a single entry in the repository log.
 *
 * @author pellaton
 */
public class LogEntry implements Serializable {

  private String revision;
  private Date timestamp;
  private String author;
  private String comment;
  private List<ChangedPath> changedPathList;

  /**
   * Constructor.
   *
   * @param revision the revision identifier
   * @param timestamp the timestamp
   * @param author the author
   * @param comment the comment
   * @param changedPathList the list of changed paths
   */
  public LogEntry(String revision,
      Date timestamp,
      String author,
      String comment,
      List<ChangedPath> changedPathList) {
    this.revision = revision;
    this.timestamp = new Date(timestamp.getTime());
    this.author = author;
    this.comment = comment;
    this.changedPathList = changedPathList;
  }

  /**
   *  Default constructor for GWT.
   */
  public LogEntry() {

  }

  /**
   * Gets the author.
   *
   * @return the author
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * Gets the list of changed paths.
   *
   * @return the list of changed paths
   */
  public List<ChangedPath> getChangedPathList() {
    return this.changedPathList;
  }

  /**
   * Gets the comment.
   *
   * @return the comment
   */
  public String getComment() {
    return this.comment;
  }

  /**
   * Gets the revision identifier.
   *
   * @return the revision identifier
   */
  public String getRevision() {
    return this.revision;
  }

  /**
   * Gets the timestamp.
   *
   * @return the timestamp
   */
  public Date getTimestamp() {
    return this.timestamp;
  }


  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @param changedPathList the changedPathList to set
   */
  public void setChangedPathList(List<ChangedPath> changedPathList) {
    this.changedPathList = changedPathList;
  }

  /**
   * @param comment the comment to set
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * @param revision the revision to set
   */
  public void setRevision(String revision) {
    this.revision = revision;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

}
