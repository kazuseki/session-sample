package com.example.todo.app.sample2.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("javadoc")
public class TodoSample2Form implements Serializable {
  
  private static final long serialVersionUID = 1L;

  // こちらもアノテーションをつけるべきだが、
  // Controllerの例の中で煩雑になりそうなので、今回は省略する。
  private String todoId;
  
  @NotNull
  @Size(min = 1, max = 30)
  private String todoTitle;

  public String getTodoTitle() {
      return todoTitle;
  }

  public void setTodoTitle(String todoTitle) {
      this.todoTitle = todoTitle;
  }

  public String getTodoId() {
    return todoId;
  }

  public void setTodoId(String todoId) {
    this.todoId = todoId;
  }
  
}
