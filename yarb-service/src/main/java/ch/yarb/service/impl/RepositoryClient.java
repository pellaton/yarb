package ch.yarb.service.impl;

import java.util.List;

import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;

/**
 * Interface to repository clients.
 *
 * @author pellaton
 */
public interface RepositoryClient {

  /**
   * Gets the log for the repository and the revision range specified.
   *
   * @param repoConfiguration the repository configuration (must not be null)
   * @param revisionRange the revision range to fetch (must not be null)
   * @param paths the repository url relative paths to get the logs for
   * @return the log entries
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
   */
  List<String> getDiff(RepoConfiguration repoConfiguration, RevisionRange revisionRange, String path);

  /**
   * Gets the latest repo revision.
   *
   * @param repoConfiguration the repository coinfiguration
   * @return the latest revision
   */
  long getLatestRepoRevision(RepoConfiguration repoConfiguration);

}
