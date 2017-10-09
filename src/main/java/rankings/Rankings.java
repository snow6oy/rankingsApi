package net.fnarg.api;

public class Rankings {

  private String id;

  public Long rank;
  public String domain;

  public Rankings() {}

  public Rankings(String domain) {
    this.domain = domain;
  }
 
  public Long getRank() {
    return rank;
  }

  public String getDomain() {
    return domain;
  }

  public void setRank(Long rank) {
    this.rank = rank;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }
}