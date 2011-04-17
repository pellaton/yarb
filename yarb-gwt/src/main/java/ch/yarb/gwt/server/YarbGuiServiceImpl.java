package ch.yarb.gwt.server;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.yarb.api.service.YarbService;
import ch.yarb.api.to.LogEntry;
import ch.yarb.api.to.RepoConfiguration;
import ch.yarb.api.to.RevisionRange;
import ch.yarb.gwt.client.YarbGuiService;
import ch.yarb.service.impl.YarbServiceImpl;

public class YarbGuiServiceImpl extends RemoteServiceServlet implements YarbGuiService {

  private static final Logger LOG = LoggerFactory.getLogger(YarbServiceImpl.class);

  private transient WebApplicationContext applicationContext;
  private transient ch.yarb.api.service.YarbService yarbService;


  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    // spring application context
    this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    this.yarbService = BeanFactoryUtils.beanOfType(this.applicationContext, YarbService.class);

    LOG.info("initialized yarbGuiService - appContext: {}, yarbService: {}",
        this.applicationContext, this.yarbService);
  }

  public String ping() {
    return this.yarbService.ping();
  }

  public List<LogEntry> getRepositoryLog(String url) {
    LOG.debug("start fetching log for {}", url);
    RepoConfiguration repoConfiguration = new RepoConfiguration(url, "", "");
    RevisionRange revisionRange = RevisionRange.ALL;
    List<LogEntry> repositoryLog = this.yarbService.getRepositoryLog(repoConfiguration, revisionRange);
    LOG.debug("finished fetching log for {}", url);
    return repositoryLog;
  }

}
