package com.example.todo.app.sample1.todo;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;
import com.github.dozermapper.core.Mapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;

@SuppressWarnings("javadoc")
@Controller
@RequestMapping("/todo/sample1")
public class TodoSample1Controller {

  @Inject
  TodoService todoService;
  
  @Inject
  Mapper beanMapper;

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
  
  @PostMapping("create1")
  public String create(@Valid TodoForm todoForm,
        BindingResult bindingResult, 
        Model model,
        RedirectAttributes attributes) {

      // フォームの入力チェックでエラーがあった場合一覧画面に戻る。
      if (bindingResult.hasErrors()) {
          return list(model);
      }

      // DozerのMapperインタフェースを使い、TodoFormオブジェクトからTodoオブジェクトを作成する。
      Todo todo = beanMapper.map(todoForm, Todo.class);

      // BusinessExceptionが発生しうるので、そのときはメッセージを格納しつつ、一覧画面に戻る。
      try {
          todoService.create(todo);
      } catch (BusinessException e) {
          model.addAttribute(e.getResultMessages());
          return list(model);
      }

      // 正常に作成が完了した場合、結果メッセージをflashスコープに追加して、一覧画面でリダイレクトする。
      // PRGパターンにより、ブラウザ再読み込みをしてもPOSTが来ない。
      attributes.addFlashAttribute(ResultMessages.success().add(
              ResultMessage.fromText("Created successfully!")));
      return "redirect:/todo/sample1/list1";
  }
  
}
