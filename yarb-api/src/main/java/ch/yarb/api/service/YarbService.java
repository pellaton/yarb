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
}
