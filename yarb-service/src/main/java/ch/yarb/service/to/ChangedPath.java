package ch.yarb.service.to;


public class ChangedPath {

  private final String path;
  private final ChangeType changeType;


  public ChangedPath(String path, ChangeType changeType) {
    this.path = path;
    this.changeType = changeType;
  }


  public String getPath() {
    return this.path;
  }

  public ChangeType getChangeType() {
    return this.changeType;
  }

}

