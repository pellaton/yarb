package ch.yarb.service.to;

import java.io.Serializable;

/**
 * Object representing a repository configuration.
 *
 * @author pellaton
 */
public class RepoConfiguration implements Serializable {

  private final String repoUrl;
  private final String userName;
  private final String password;


  /**
   * Constructor.
   *
   * @param repoUrl the repository URL
   * @param userName the user name
   * @param password the password
   */
  public RepoConfiguration(String repoUrl, String userName, String password) {
    this.repoUrl = repoUrl;
    this.userName = userName;
    this.password = password;
  }

  /**
   * Gets the repository url.
   *
   * @return the repository url
   */
  public String getRepoUrl() {
    return this.repoUrl;
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  public String getUserName() {
    return this.userName;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return this.password;
  }
}