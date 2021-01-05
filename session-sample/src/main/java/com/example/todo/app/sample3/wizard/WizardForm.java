package com.example.todo.app.sample3.wizard;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

@SuppressWarnings("javadoc")
public class WizardForm implements Serializable {
  
  // 検証グループ化用のインタフェース。
  public static interface Wizard1 {
  }

  public static interface Wizard2 {
  }

  public static interface Wizard3 {
  }
  
  private static final long serialVersionUID = 1L;
  
  @NotEmpty(groups = { Wizard1.class })
  private String field1;

  @NotEmpty(groups = { Wizard2.class })
  private String field2;

  @NotEmpty(groups = { Wizard3.class })
  private String field3;

  public String getField1() {
    return field1;
  }

  public void setField1(String field1) {
    this.field1 = field1;
  }

  public String getField2() {
    return field2;
  }

  public void setField2(String field2) {
    this.field2 = field2;
  }

  public String getField3() {
    return field3;
  }

  public void setField3(String field3) {
    this.field3 = field3;
  }


}
