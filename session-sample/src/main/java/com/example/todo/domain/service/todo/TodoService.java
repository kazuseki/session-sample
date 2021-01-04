package com.example.todo.domain.service.todo;

import com.example.todo.domain.model.Todo;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface TodoService {
  
  // 全件取得 
  Collection<Todo> findAll();

  // 新規作成
  Todo create(Todo todo);

  // 完了
  Todo finish(String todoId);

  // 削除
  void delete(String todoId);
  
  // sample2用。制限数に達していないかを確認する
  void checkCount();
}
