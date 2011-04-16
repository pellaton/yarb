package ch.yarb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import ch.yarb.service.api.YarbService;
import ch.yarb.service.to.ChangeType;
import ch.yarb.service.to.ChangedPath;
import ch.yarb.service.to.LogEntry;
import ch.yarb.service.to.RepoConfiguration;
import ch.yarb.service.to.RevisionRange;

import static org.tmatesoft.svn.core.SVNURL.parseURIEncoded;

/**
 * Yarb service implementation.
 *
 * @author pellaton
 */
@Service
public class YarbServiceImpl implements YarbService {

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

    FSRepositoryFactory.setup();

    List<LogEntry> logEntryList = new ArrayList<LogEntry>();
    try {
      SVNRepository repository = SVNRepositoryFactory.create(parseURIEncoded(repoConfiguration.getRepoUrl()));
      ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(
          repoConfiguration.getUserName(), repoConfiguration.getPassword());
      repository.setAuthenticationManager(authManager);

      Collection<?> logEntries = repository.log(new String[]{""}, null, revisionRange.getLowerBound(),
          revisionRange.getUpperBound(), true, true);
      for (Iterator<?> entries = logEntries.iterator(); entries.hasNext();) {
        SVNLogEntry logEntry = (SVNLogEntry) entries.next();
        logEntryList.add(new LogEntry(
            Long.toString(logEntry.getRevision()), new DateTime(logEntry.getDate().getTime()), logEntry.getAuthor(),
            logEntry.getMessage(), getChangedPathsList(logEntry.getChangedPaths())));
      }
    } catch (SVNException e) {
      e.printStackTrace();
    }

    return logEntryList;
  }

  private List<ChangedPath> getChangedPathsList(Map<?, ?> map) {
    List<ChangedPath> changedPathList = new ArrayList<ChangedPath>();
    for (Object path : new TreeSet<Object>(map.keySet())) {
      SVNLogEntryPath changedPath = (SVNLogEntryPath) map.get(path);
      changedPathList.add(new ChangedPath(changedPath.getPath(), decodeChangeType(changedPath.getType())));
    }
    return changedPathList;
  }

  private ChangeType decodeChangeType(char type) {
    switch (type) {
      case 'A' : return ChangeType.ADDED;
      case 'D' : return ChangeType.DELETED;
      case 'R' : return ChangeType.REPLACED;
      case 'M' : return ChangeType.MODIFIED;
      default: throw new IllegalArgumentException("unknown change type '" + type + "'");
    }
  }
}





