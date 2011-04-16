package ch.yarb.gwt.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ch.yarb.gwt.client.YarbGuiService;
import ch.yarb.service.api.YarbService;

public class YarbGuiServiceImpl extends RemoteServiceServlet implements YarbGuiService {

  private transient WebApplicationContext applicationContext;
  private transient YarbService yarbService;


  public YarbGuiServiceImpl() {
    System.out.println("##################### hello world");
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    // TODO Auto-generated method stub
    super.init(config);

    System.out.println("initializing the gui service.. ");

    // spring application context
    this.applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
    this.yarbService = BeanFactoryUtils.beanOfType(this.applicationContext, YarbService.class);

    System.out.println("appContext: " + this.applicationContext);
    System.out.println("yarbService: " + this.yarbService);
  }

  public String ping() {
    return this.yarbService.ping();
  }

}
