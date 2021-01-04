package com.example.todo.domain.service.todo;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.todo.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

@SuppressWarnings("javadoc")
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

  private static final long MAX_UNFINISHED_COUNT = 5;

  @Inject
  TodoRepository todoRepository;

  // 1件取得用のメソッドを用意しておく。
  private Todo findOne(String todoId) {
    Todo todo = todoRepository.findOne(todoId);
    
    // 見つからなかったら例外をなげる。
    if (todo == null) {
      ResultMessages messages = ResultMessages.error();
      messages.add(ResultMessage
          .fromText("[E404] そのtodoIdの Todo は見つかりませんでした。 (id="
              + todoId + ")"));
      throw new ResourceNotFoundException(messages);
    }
    return todo;
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Todo> findAll() {
    return todoRepository.findAll();
  }

  @Override
  public Todo create(Todo todo) {

    // 引数にfalseを入れることで、未完了のTodoの件数を取得する。
    long unfinishedCount = todoRepository.countByFinished(false);

    // 未完了のTodoは MAX_UNFINISHED_COUNT までしか許可しないので、
    // 以上になっていたら例外をなげる。
    if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
      ResultMessages messages = ResultMessages.error();
      messages.add(ResultMessage
          .fromText("[E001] The count of un-finished Todo must not be over "
              + MAX_UNFINISHED_COUNT + "."));
      throw new BusinessException(messages);
    }

    // 登録用にTodoを作成する
    String todoId = UUID.randomUUID().toString();
    Date createdAt = new Date();

    todo.setTodoId(todoId);
    todo.setCreatedAt(createdAt);
    todo.setFinished(false);

    todoRepository.create(todo);

    return todo;
  }

  @Override
  public Todo finish(String todoId) {
    
    Todo todo = findOne(todoId);
    
    // ステータスが完了になっているものを再度完了にする操作は受け付けない。例外をなげる。
    if (todo.isFinished()) {
      ResultMessages messages = ResultMessages.error();
      messages.add(ResultMessage
          .fromText("[E002] The requested Todo is already finished. (id="
              + todoId + ")"));
      throw new BusinessException(messages);
    }
    
    todo.setFinished(true);
    todoRepository.update(todo);
    return todo;
  }

  @Override
  public void delete(String todoId) {
    Todo todo = findOne(todoId);
    todoRepository.delete(todo);
  }
}