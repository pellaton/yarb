package ch.yarb.service.api;

import java.util.List;

import ch.yarb.service.to.LogEntry;
import ch.yarb.service.to.RepoConfiguration;
import ch.yarb.service.to.RevisionRange;


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
