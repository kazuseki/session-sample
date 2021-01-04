package com.example.todo.app.sample1.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("javadoc")
public class TodoForm implements Serializable {

  // 入力チェックのグループ化用のインタフェース。
  // こちらは新規作成処理用。
  public static interface TodoCreate {
  };

  // こちらは完了処理用。
  public static interface TodoFinish {
  };

  private static final long serialVersionUID = 1L;

  //アノテーションのgroup属性に、グループを示す任意のjava.lang.Classオブジェクトを設定する
  @NotNull(groups = { TodoFinish.class })
  private String todoId;

  // アノテーションのgroup属性に、グループを示す任意のjava.lang.Classオブジェクトを設定する
  @NotNull(groups = { TodoCreate.class })
  @Size(min = 1, max = 30)
  private String todoTitle;


  public String getTodoId() {
    return todoId;
  }

  public void setTodoId(String todoId) {
    this.todoId = todoId;
  }

  public String getTodoTitle() {
    return todoTitle;
  }

  public void setTodoTitle(String todoTitle) {
    this.todoTitle = todoTitle;
  }
}