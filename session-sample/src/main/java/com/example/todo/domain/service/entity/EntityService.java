package com.example.todo.domain.service.entity;

import com.example.todo.domain.model.Entity;
import com.example.todo.domain.model.Todo;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface EntityService {
  
  // 全件取得 
  Collection<Entity> findAll();

  // 新規作成
  Entity create(Entity entity);

  // 更新
  Entity update(Entity entity);

  // 削除
  void delete(String wizardId);
}
