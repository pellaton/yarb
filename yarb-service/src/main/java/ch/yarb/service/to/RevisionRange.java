package ch.yarb.service.to;


public class RevisionRange {

  public static final Long LOWER_MIN = 0L;
  public static final Long UPPER_MAX = -1L;
  public static final RevisionRange ALL = new RevisionRange(LOWER_MIN, UPPER_MAX);

  private final Long lowerBound;
  private final Long upperBound;

  public RevisionRange(Long lowerBound, Long upperBound) {
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
  }

  public Long getLowerBound() {
    return this.lowerBound;
  }

  public Long getUpperBound() {
    return this.upperBound;
  }
}
