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
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import ch.yarb.api.to.ChangeType;
import ch.yarb.api.to.ChangedPath;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;

import static org.tmatesoft.svn.core.SVNURL.parseURIEncoded;

/**
 * Repository client implementation for Subversion using theSvnKit library.
 *
 * @author pellaton
 */
@Service
public class SvnKitRepositoryClientImpl  implements RepositoryClient {

  /**
   * Default constructor.
   */
  SvnKitRepositoryClientImpl() {
    // initialize the support for the svn and svn+ssh protocols
    SVNRepositoryFactoryImpl.setup();
    // initialize the support for the http and https protocols
    DAVRepositoryFactory.setup();
    // initialize the support for the local file access
    FSRepositoryFactory.setup();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration, RevisionRange revisionRange) {
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
