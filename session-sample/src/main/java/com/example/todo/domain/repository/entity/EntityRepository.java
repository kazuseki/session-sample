package com.example.todo.domain.repository.entity;


import com.example.todo.domain.model.Entity;

import java.util.Collection;

@SuppressWarnings("javadoc")
public interface EntityRepository {
  
  // 1件取得
  Entity findOne(String wizardId);

  // 全件取得
  Collection<Entity> findAll();

  // 1件作成
  void create(Entity entity);

  // 1件更新
  boolean update(Entity entity);

  // 1件削除
  void delete(Entity entity);
  
}
