package ch.yarb.api.service;

import java.util.List;

import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;


/**
 * Yarb service interface.
 *
 * @author pellaton
 */
public interface YarbService {

  /**
   * A simple ping method.
   *
   * @return a ping message
   */
  String ping();

  /**
   * Gets the log for the repository and the revision range specified.
   *
   * @param repoConfiguration the repository configuration (must not be null)
   * @param revisionRange the revision range to fetch (must not be null)
   * @return the log entries
   * @throws IllegalArgumentException if either {@code repoConfiguration} or {@code revisionRange} is {@code null}
   */
  List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange);

  /**
   * Gets the log for the repository and the revision range specified.
   *
   * @param repoConfiguration the repository configuration (must not be null)
   * @param revisionRange the revision range to fetch (must not be null)
   * @param paths the repository url relative paths to get the logs for
   * @return the log entries
   * @throws IllegalArgumentException if either {@code repoConfiguration} or {@code revisionRange} is {@code null}
   */
  List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange, String... paths);

  /**
   * Gets the diff between two revisions of a file in the repository. The file is specified by the repo URL and the
   * path. The diff is created between the lower and upper bound in the revision range.
   *
   * @param repoConfiguration the repository configuration
   * @param revisionRange the revision range
   * @param path the path of the file within the repository
   * @return the diff
   * @throws IllegalArgumentException if either {@code repoConfiguration}, {@code revisionRange} or {@code path} is
   * {@code null}
   */
  List<String> getDiff(RepoConfiguration repoConfiguration, RevisionRange revisionRange, String path);
}
