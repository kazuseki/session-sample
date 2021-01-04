package com.example.todo.app.sample1.todo;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

import javax.inject.Inject;

@SuppressWarnings("javadoc")
@Controller
@RequestMapping("/todo/sample1")
public class TodoSample1Controller {

  @Inject
  TodoService todoService;

  // メソッドに@ModelAttributeアノテーションをつけて、
  // @RequestMappingが設定されたメソッドの前にこの処理を実行するよう設定する。
  // また、valueで @SessionAttributes と同じ名前を設定し、sessionスコープで管理するよう設定する。
  @ModelAttribute
  public TodoForm setUpForm() {
      return new TodoForm();
  }

  @GetMapping("list1")
  public String list(Model model) {
      Collection<Todo> todos = todoService.findAll();
      model.addAttribute("todos", todos);
      return "todo/sample1/list1";
  }
  
}
