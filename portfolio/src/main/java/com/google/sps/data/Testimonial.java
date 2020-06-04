package com.google.sps.data;

/** Class containing testimonial information. */
public final class Testimonial {

  private final String name;
  private final String relationship;
  private final String text;
  private long upvote;

  public Testimonial(String n, String r, String t) {
      this.name = n;
      this.relationship = r;
      this.text = t;
      upvote = 0;
  }

  public Testimonial(String n, String r, String t, long count) {
      this.name = n;
      this.relationship = r;
      this.text = t;
      upvote = count;
  }
  
  public String getName() {
    return name;
  }

  public String getRelationship() {
    return relationship;
  }

  public String getText() {
    return text;
  }

  public long getUpvote () {
    return upvote;
  }
  
  public long incrementUpvote () {
    return upvote++;
  }
}
