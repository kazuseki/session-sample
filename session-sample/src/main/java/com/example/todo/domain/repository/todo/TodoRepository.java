package com.example.todo.domain.repository.todo;

import java.util.Collection;

import com.example.todo.domain.model.Todo;

@SuppressWarnings("javadoc")
public interface TodoRepository {

  // 1件取得
  Todo findOne(String todoId);

  // 全件取得
  Collection<Todo> findAll();

  // 1件作成
  void create(Todo todo);

  // 1件更新
  boolean update(Todo todo);

  // 1件削除
  void delete(Todo todo);
  
  // 完了済みTODO件数の取得
  long countByFinished(boolean finished);
}