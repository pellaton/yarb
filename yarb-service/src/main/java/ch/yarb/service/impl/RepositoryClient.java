package ch.yarb.service.impl;

import java.util.List;

import ch.yarb.service.to.LogEntry;
import ch.yarb.service.to.RepoConfiguration;
import ch.yarb.service.to.RevisionRange;

/**
 * Interface to repository clients.
 *
 * @author pellaton
 */
public interface RepositoryClient {

  /**
   * Gets the log for the repository and the revision range specified.
   *
   * @param repoConfiguration the repository configuration
   * @param revisionRange the revision range
   * @return the log for the repository and the revision range specified
   */
  List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange);

}
