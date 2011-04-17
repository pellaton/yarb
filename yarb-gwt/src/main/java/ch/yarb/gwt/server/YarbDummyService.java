package ch.yarb.gwt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.ChangedPath;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.LogFilter;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;


/**
 * Dummy implementation of the {@link YarbService}.
 */
public class YarbDummyService implements YarbService {

  @Override
  public String ping() {
    return "yarb-dummy";
  }

  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration,
      RevisionRange revisionRange) {
    List<LogEntry> log = new ArrayList<LogEntry>();
    log.add(new LogEntry("1", new Date(), "mro", "foo", new ArrayList<ChangedPath>()));
    log.add(new LogEntry("2", new Date(), "mro", "bar", new ArrayList<ChangedPath>()));
    log.add(new LogEntry("3", new Date(), "mro", "less", new ArrayList<ChangedPath>()));

    return log;
  }

  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration,
      RevisionRange revisionRange,
      String... paths) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<String> getDiff(RepoConfiguration repoConfiguration,
      RevisionRange revisionRange,
      String path) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration,
      RevisionRange revisionRange,
      LogFilter filter) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<LogEntry> getRepositoryLog(RepoConfiguration repoConfiguration,
      RevisionRange revisionRange,
      LogFilter filter,
      String... paths) {
    // TODO Auto-generated method stub
    return null;
  }

}
