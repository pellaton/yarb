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
   * @throws IllegalArgumentException if either {@code repoConfiguration} or {@code revisionRange} is {@code null}
   */
  List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange, String... paths);

}
