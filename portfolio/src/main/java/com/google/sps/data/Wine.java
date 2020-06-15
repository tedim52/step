package com.google.sps.data;

/** Class containing Wine information. */
public final class Wine {

  private final String description;
  private final String designation;
  private final String province;
  

  public Wine(String des, String desig, String p) {
      this.description= des;
      this.designation = desig;
      this.province = p;
  }
  
  public String getDescription() {
    return description;
  }

  public String getDesignation() {
    return designation;
  }

  public String getProvince() {
    return province;
  }
}
