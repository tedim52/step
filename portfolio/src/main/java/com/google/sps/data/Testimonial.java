package com.google.sps.data;



/** Class containing testimonial information. */
public final class Testimonial {

  private final String name;
  private final String relationsip;
  private final String text;
  private int upVote;
  private int downVote;

  public Testimonial(String n, String r, String t) {
      this.name = n;
      this.relationsip = r;
      this.text = t;
      upVote = 0;
      downVote = 0;
  }

  public String getName() {
    return name;
  }

  public String getRelationship() {
    return relationsip;
  }

  public String getText() {
    return text;
  }

  public int getUpVote () {
    return upVote;
  }

  public int incrementUpVote () {
    return upVote++;
  }

  public int incremenDownVote () {
    return downVote;
  }
}
