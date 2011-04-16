package ch.yarb.service.impl;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.ChangeType;
import ch.yarb.api.to.ChangedPath;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link YarbServiceImpl}.
 *
 * @author pellaton
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-context.xml", inheritLocations = false)
public class YarbServiceImplTest {

  @Autowired
  private YarbService service;

  /**
   * Tests {@link YarbServiceImpl#ping()}.
   */
  @Test
  public void ping() {
    assertEquals("yarb", this.service.ping());
  }

  /**
   * Tests the {@code null} check of the {@code logRepoConfigurationNull}
   * {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getRepositoryLogRepoConfigurationNull() {
    this.service.getRepositoryLog(null, RevisionRange.ALL);
  }

  /**
   * Tests the {@code null} check of the {@code revisionRange}
   * {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getRepositoryLogRevisionRangeNull() {
    this.service.getRepositoryLog(new RepoConfiguration(null, null, null), null);
  }

  /**
   * Tests {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)} for the full test
   * repository and the full version range.
   */
  @Test
  public void getRepositoryLogRevision() {
    List<LogEntry> repositoryLog = this.service.getRepositoryLog(new RepoConfiguration(
        "file://" + new File("./src/test/resources/svntestrepo").getAbsolutePath(),
        "anonymous", "anonymous"),
        RevisionRange.ALL);
    assertNotNull(repositoryLog);
    assertFalse(repositoryLog.isEmpty());
    assertTrue(repositoryLog.size() >= 8);
    LogEntry logEntry = repositoryLog.get(1);
    assertNotNull(logEntry);
    assertEquals("commit revision", "1", logEntry.getRevision());
    assertEquals("commit author", "michael", logEntry.getAuthor());
    assertEquals("commit comment", "bump basic directory structure", logEntry.getComment());
    assertNotNull("commit timestamp", logEntry.getTimestamp());
    List<ChangedPath> changedPathList = logEntry.getChangedPathList();
    assertNotNull(changedPathList);
    assertFalse(changedPathList.isEmpty());
    assertEquals(3, changedPathList.size());
    assertEquals("/branches", changedPathList.get(0).getPath());
    assertEquals(ChangeType.ADDED, changedPathList.get(0).getChangeType());
  }

  /**
   * Tests {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)} for a specific path.
   */
  @Test
  public void getRepositoryLogRevisionSpecificPath() {
    List<LogEntry> repositoryLog = this.service.getRepositoryLog(new RepoConfiguration(
        "file://" + new File("./src/test/resources/svntestrepo/trunk/module1/file1.txt").getAbsolutePath(),
        "anonymous", "anonymous"),
        RevisionRange.ALL);
    assertNotNull(repositoryLog);
    assertFalse(repositoryLog.isEmpty());
    assertTrue(repositoryLog.size() >= 2);
    LogEntry logEntry = repositoryLog.get(1);
    assertNotNull(logEntry);
    assertEquals("commit revision", "4", logEntry.getRevision());
    assertEquals("commit author", "michael", logEntry.getAuthor());
    assertEquals("commit comment", "corrected text", logEntry.getComment());
    assertNotNull("commit timestamp", logEntry.getTimestamp());
    for (ChangedPath changedPath :  logEntry.getChangedPathList()) {
      assertTrue("changed path", changedPath.getPath().endsWith("/trunk/module1/file1.txt"));
    }

  }

  /**
   * Tests {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)} for a specific version range.
   */
  @Test
  public void getRepositoryLogRevisionSpecificVersionRange() {
    List<LogEntry> repositoryLog = this.service.getRepositoryLog(new RepoConfiguration(
        "file://" + new File("./src/test/resources/svntestrepo").getAbsolutePath(),
        "anonymous", "anonymous"),
        new RevisionRange(Long.valueOf(4), Long.valueOf(4)));
    assertNotNull(repositoryLog);
    assertFalse(repositoryLog.isEmpty());
    assertEquals(1, repositoryLog.size());
    LogEntry logEntry = repositoryLog.get(0);
    assertNotNull(logEntry);
    assertEquals("commit revision", "4", logEntry.getRevision());
    assertEquals("commit author", "michael", logEntry.getAuthor());
    assertEquals("commit comment", "corrected text", logEntry.getComment());
    assertNotNull("commit timestamp", logEntry.getTimestamp());
    List<ChangedPath> changedPathList = logEntry.getChangedPathList();
    assertNotNull("changed paths", changedPathList);
    assertEquals(1, changedPathList.size());
    ChangedPath changedPath = changedPathList.get(0);
    assertNotNull("changed path", changedPath);
    assertEquals("changed path", "/trunk/module1/file1.txt", changedPath.getPath());
    assertEquals("change type", ChangeType.MODIFIED, changedPath.getChangeType());
  }

  /**
   * Tests {@link YarbServiceImpl#getRepositoryLog(RepoConfiguration, RevisionRange)} for an external repo.
   */
  @Test
  public void getRepositoryLogRevisionExternal() {
    List<LogEntry> repositoryLog = this.service.getRepositoryLog(new RepoConfiguration(
        "http://eclipse-cheatsheet.googlecode.com/svn/trunk/",
        "anonymous", "anonymous"),
        RevisionRange.ALL);
    assertNotNull(repositoryLog);
    assertFalse(repositoryLog.isEmpty());
    assertTrue(repositoryLog.size() >= 4);
  }
}
