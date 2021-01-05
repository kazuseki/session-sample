package com.example.todo.domain.service.entity;

import com.example.todo.domain.model.Entity;
import com.example.todo.domain.repository.entity.EntityRepository;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

@SuppressWarnings("javadoc")
@Service
public class EntityServiceImpl implements EntityService {
  
  @Inject
  EntityRepository entityRepository;
  
  @Override
  public Collection<Entity> findAll() {
    return entityRepository.findAll();
  }

  @Override
  public Entity create(Entity entity) {
    
    // 作成用にIdにランダムな値を設定
    String wizardId = UUID.randomUUID().toString();
    entity.setWizardId(wizardId);
    // 他のフィールドは渡されたときに入ってくる。
    
    entityRepository.create(entity);
    return entity;
  }

  @Override
  public Entity update(Entity entity) {
    entityRepository.update(entity);
    return entity;
  }

  @Override
  public void delete(String wizardId) {
    Entity entity = entityRepository.findOne(wizardId);
    entityRepository.delete(entity);
  }
}
