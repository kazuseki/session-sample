package com.example.todo.app.sample1.todo;

import java.io.Serializable;

@SuppressWarnings("javadoc")
public class TodoForm implements Serializable {
  
  private static final long serialVersionUID = 1L;

  private String todoTitle;

  public String getTodoTitle() {
      return todoTitle;
  }

  public void setTodoTitle(String todoTitle) {
      this.todoTitle = todoTitle;
  }
}