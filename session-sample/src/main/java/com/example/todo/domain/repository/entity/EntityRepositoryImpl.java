package com.example.todo.domain.repository.entity;

import com.example.todo.domain.model.Entity;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("javadoc")
@Repository
public class EntityRepositoryImpl implements EntityRepository {

  private static final Map<String, Entity> ENTITY_MAP = new ConcurrentHashMap<>();

  @Override
  public Entity findOne(String wizardId) {
    return ENTITY_MAP.get(wizardId);
  }

  @Override
  public Collection<Entity> findAll() {
    return ENTITY_MAP.values();
  }

  @Override
  public void create(Entity entity) {
    ENTITY_MAP.put(entity.getWizardId(), entity);
  }

  @Override
  public boolean update(Entity entity) {
    ENTITY_MAP.put(entity.getWizardId(), entity);
    return true;
  }

  @Override
  public void delete(Entity entity) {
    ENTITY_MAP.remove(entity.getWizardId());
  }
  
}
