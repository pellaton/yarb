package ch.yarb.service.to;

/**
 * Object representing a revision range.
 *
 * @author pellaton
 */
public class RevisionRange {

  /** The lowest minimal version number. */
  public static final Long LOWER_MIN = 0L;

  /** The maximum upper version number (using it means that all entries up to the current one are included). */
  public static final Long UPPER_MAX = -1L;

  /** Convenience instance that can be used to fetch all revisions. */
  public static final RevisionRange ALL = new RevisionRange(LOWER_MIN, UPPER_MAX);

  private final Long lowerBound;
  private final Long upperBound;


  /**
   * Constructor.
   *
   * @param lowerBound the lower version range bound (inclusive)
   * @param upperBound the upper version range bound (inclusive)
   */
  public RevisionRange(Long lowerBound, Long upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  /**
   * Gets the lower version range bound (inclusive).
   *
   * @return the lower version range bound (inclusive)
   */
  public Long getLowerBound() {
    return this.lowerBound;
  }

  /**
   * Gets the upper version range bound (inclusive).
   *
   * @return the upper version range bound (inclusive)
   */
  public Long getUpperBound() {
    return this.upperBound;
  }
}
