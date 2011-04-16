package ch.yarb.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;

/**
 * Yarb service implementation.
 *
 * @author pellaton
 */
@Service
public class YarbServiceImpl implements YarbService {

  private static final Logger LOG = LoggerFactory.getLogger(YarbServiceImpl.class);

  @Autowired
  private RepositoryClient repositoryClient;

  /** {@inheritDoc} */
  @Override
  public String ping() {
    return "yarb";
  }

  /** {@inheritDoc} */
  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange) {

    if (repoConfiguration == null) {
      throw new IllegalArgumentException("The argument 'repoConfiguration' must not be null");
    }

    if (revisionRange == null) {
      throw new IllegalArgumentException("The argument 'revisionRange' must not be null");
    }

    return this.repositoryClient.getRepositoryLog(repoConfiguration, revisionRange);
  }
}





