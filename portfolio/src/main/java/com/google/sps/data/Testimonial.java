package com.google.sps.data;

/** Class containing testimonial information. */
public final class Testimonial {

  private final String name;
  private final String relationship;
  private final String text;
  private final String sentiment;
  private long upvote;

  public Testimonial(String n, String r, String t) {
      this.name = n;
      this.relationship = r;
      this.text = t;
      upvote = 0;
      sentiment = "Positive";
  }

  public Testimonial(String n, String r, String t, long count, String s) {
      this.name = n;
      this.relationship = r;
      this.text = t;
      upvote = count;
      sentiment = s;
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

  public String getSentiment() {
    return sentiment;
  }
  
  public long incrementUpvote () {
    return upvote++;
  }
}
