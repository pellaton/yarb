package ch.yarb.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class YarbServiceImplTest {

  private YarbService service;

  @Before
  public void init() {
    this.service = new YarbServiceImpl();
  }
  
  @Test
  public void ping() {
    assertEquals("yarb", this.service.ping());
  }
}
