package ch.yarb.api.to;

import java.io.Serializable;

/**
 * Object representing a single changed path in the repository.
 *
 * @author pellaton
 */
public class ChangedPath implements Serializable {

  private String path;
  private ChangeType changeType;


  /**
   * Constructor.
   *
   * @param path the path
   * @param changeType the change type
   * @see ChangeType
   */
  public ChangedPath(String path, ChangeType changeType) {
    this.path = path;
    this.changeType = changeType;
  }

  /**
   * default contstructor for GWT.
   */
  public ChangedPath() {

  }


  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }


  /**
   * @param changeType the changeType to set
   */
  public void setChangeType(ChangeType changeType) {
    this.changeType = changeType;
  }

  /**
   * Gets the path.
   *
   * @return the path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Gets the change type.
   *
   * @return the change type
   * @see ChangeType
   */
  public ChangeType getChangeType() {
    return this.changeType;
  }
}
