package ch.yarb.service.impl;

import org.springframework.stereotype.Service;

import ch.yarb.service.api.YarbService;

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

}
