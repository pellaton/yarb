package ch.yarb.api.to;

import java.io.Serializable;

/**
 * Object containing the log filtering arguments.
 *
 * @author pellaton
 */
public class LogFilter implements Serializable {

  private String authorFilter;
  private String commentFilter;

  /**
   * Default constructor.
   */
  public LogFilter() {
  }

  /**
   * Constructor.
   *
   * @param authorFilter the author filter
   * @param commentFilter the comment filter
   */
  public LogFilter(String authorFilter, String commentFilter) {
    this.authorFilter = authorFilter;
    this.commentFilter = commentFilter;
  }


  /**
   * Gets the author filter.
   *
   * @return the author filter
   */
  public String getAuthorFilter() {
    return this.authorFilter;
  }

  /**
   * Gets the comment filter.
   *
   * @return the comment filter
   */
  public String getCommentFilter() {
    return this.commentFilter;
  }

  /**
   * Sets the comment filter.
   *
   * @param commentFilter the comment filter to set
   */
  public void setCommentFilter(String commentFilter) {
    this.commentFilter = commentFilter;
  }

  /**
   * Sets the author filter.
   *
   * @param authorFilter the author filter to set
   */
  public void setAuthorFilter(String authorFilter) {
    this.authorFilter = authorFilter;
  }
}
