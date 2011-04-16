package ch.yarb.gwt.server;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.ChangedPath;
import ch.yarb.api.to.LogEntry;
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
    log.add(new LogEntry("1", new DateTime(), "mro", "foo", new ArrayList<ChangedPath>()));
    log.add(new LogEntry("2", new DateTime(), "mro", "bar", new ArrayList<ChangedPath>()));
    log.add(new LogEntry("3", new DateTime(), "mro", "less", new ArrayList<ChangedPath>()));

    return log;
  }

}
