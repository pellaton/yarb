package ch.yarb.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link YarbServiceImpl}.
 * 
 * @author pellaton
 */
public class YarbServiceImplTest {

  private YarbService service;

  /**
   * Initializes the test fixture.
   */
  @Before
  public void init() {
    this.service = new YarbServiceImpl();
  }
  
  /**
   * Tests {@link YarbServiceImpl#ping()}.
   */
  @Test
  public void ping() {
    assertEquals("yarb", this.service.ping());
  }
}
