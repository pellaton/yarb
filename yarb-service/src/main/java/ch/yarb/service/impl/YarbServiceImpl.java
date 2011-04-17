package ch.yarb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.LogFilter;
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
    return getRepositoryLog(repoConfiguration, revisionRange, "");
  }

  /** {@inheritDoc} */
  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange,
      LogFilter filter, String... paths) {

    if (repoConfiguration == null) {
      throw new IllegalArgumentException("The argument 'repoConfiguration' must not be null");
    }

    if (revisionRange == null) {
      throw new IllegalArgumentException("The argument 'revisionRange' must not be null");
    }

    List<LogEntry> log = this.repositoryClient.getRepositoryLog(repoConfiguration, revisionRange, paths);
    List<String> authorFilter = getAuthorFilter(filter);
    List<String> commentFilter = getCommentFilter(filter);
    if (authorFilter.isEmpty() && commentFilter.isEmpty()) {
      return log;
    }

    List<LogEntry> filteredList = new ArrayList<LogEntry>();
    for (LogEntry each : log) {
      if (!(authorFilterVeto(authorFilter, each) || commentFilterVeto(commentFilter, each))) {
        filteredList.add(each);
      }
    }
    return filteredList;
  }

  private boolean authorFilterVeto(List<String> authorFilter, LogEntry each) {
    if (each.getAuthor() == null) {
      return false;
    }

    String author = each.getAuthor().toLowerCase();
    for (String filter : authorFilter) {
      if (author.contains(filter.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  private boolean commentFilterVeto(List<String> commentFilter, LogEntry each) {
    if (each.getAuthor() == null) {
      return false;
    }

    String comment = each.getComment().toLowerCase();
    for (String filter : commentFilter) {
      if (comment.contains(filter.toLowerCase())) {
        return false;
      }
    }
    return true;
  }

  private List<String> getCommentFilter(LogFilter filter) {
    if (filter == null || filter.getCommentFilter() == null) {
      return Collections.emptyList();
    }
    String[] split = filter.getCommentFilter().split(" ");
    List<String> commentFilers = new ArrayList<String>();
    for (String each : split) {
      if (!"".equals(each.trim())) {
        commentFilers.add(each.trim());
      }
    }
    return commentFilers;
  }

  private List<String> getAuthorFilter(LogFilter filter) {
    if (filter == null || filter.getAuthorFilter() == null) {
      return Collections.emptyList();
    }
    String[] split = filter.getAuthorFilter().split(" ");
    List<String> authorFilers = new ArrayList<String>();
    for (String each : split) {
      if (!"".equals(each.trim())) {
        authorFilers.add(each.trim());
      }
    }
    return authorFilers;
  }

  /** {@inheritDoc} */
  @Override
  public List<String> getDiff(RepoConfiguration repoConfiguration, Long revision, String path) {

    if (repoConfiguration == null) {
      throw new IllegalArgumentException("The argument 'repoConfiguration' must not be null");
    }

    if (revision == null) {
      throw new IllegalArgumentException("The argument 'revisionRange' must not be null");
    }

    if (path == null) {
      throw new IllegalArgumentException("The argument 'path' must not be null");
    }

    return this.repositoryClient.getDiff(repoConfiguration, revision, path);
  }

  /** {@inheritDoc} */
  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange,
      LogFilter filter) {
    return getRepositoryLog(repoConfiguration, revisionRange, filter, "");
  }

  /** {@inheritDoc} */
  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange,
      String... paths) {
    return getRepositoryLog(repoConfiguration, revisionRange, null, paths);
  }
}





