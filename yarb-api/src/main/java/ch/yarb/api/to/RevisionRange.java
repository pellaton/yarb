package ch.yarb.api.to;

import java.io.Serializable;

/**
 * Object representing a fetch revision range.
 */
public class RevisionRange implements Serializable {

  /** Convenience instance that can be used to fetch all revisions. */
  public static final RevisionRange ALL = new RevisionRange(null, Long.MAX_VALUE);

  private Long revision;
  private Long fetchCount;

  /**
   * Constructor.
   *
   * @param revision the revision to fetch from (upper bound, {@code null} means HEAD)
   * @param fetchCount the max number of log entries returned
   */
  public RevisionRange(Long revision, Long fetchCount) {
    this.setRevision(revision);
    this.setFetchCount(fetchCount);
  }

  /**
   * Gets the revision.
   *
   * @return the revision
   */
  public Long getRevision() {
    return this.revision;
  }

  /**
   * Sets the revision.
   *
   * @param revision the revision to set
   */
  public void setRevision(Long revision) {
    this.revision = revision;
  }

  /**
   * Gets the fetch count.
   *
   * @return the fetch count
   */
  public Long getFetchCount() {
    return this.fetchCount;
  }

  /**
   * Sets the fetch count.
   *
   * @param fetchCount the fetch count to set
   */
  public void setFetchCount(Long fetchCount) {
    this.fetchCount = fetchCount;
  }
}
