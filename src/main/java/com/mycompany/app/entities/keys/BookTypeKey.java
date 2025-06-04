package com.mycompany.app.entities.keys;

import java.io.Serializable;

public class BookTypeKey implements Serializable {
  private String code;
  private String subCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }
}
