package ch.yarb.service.to;

/**
 * Object representing a single changed path in the repository.
 *
 * @author pellaton
 */
public class ChangedPath {

  private final String path;
  private final ChangeType changeType;


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