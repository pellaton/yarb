package ch.yarb.service.to;


/**
 * Enumeration representing the type of change of a path in the repository.
 *
 * @author pellaton
 */
public enum ChangeType {

  /** The path was added. */
  ADDED,

  /** The path was deleted. */
  DELETED,

  /** The path was modified. */
  MODIFIED,

  /** The path was replaced. */
  REPLACED
}
