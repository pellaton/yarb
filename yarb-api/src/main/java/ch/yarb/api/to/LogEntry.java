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

  private final String revision;
  private final Date timestamp;
  private final String author;
  private final String comment;
  private final List<ChangedPath> changedPathList;

  /**
   * Constructor.
   *
   * @param revision the revision identifier
   * @param timestamp the timestamp
   * @param author the author
   * @param comment the comment
   * @param changedPathList the list of changed paths
   */
  public LogEntry(String revision, Date timestamp, String author, String comment,
      List<ChangedPath> changedPathList) {
    this.revision = revision;
    this.timestamp = timestamp;
    this.author = author;
    this.comment = comment;
    this.changedPathList = changedPathList;
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
   * Gets the author.
   *
   * @return the author
   */
  public String getAuthor() {
    return this.author;
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
   * Gets the list of changed paths.
   *
   * @return the list of changed paths
   */
  public List<ChangedPath> getChangedPathList() {
    return this.changedPathList;
  }

}