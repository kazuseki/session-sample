package com.example.todo.app.sample2.todo;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.todo.TodoService;
import com.github.dozermapper.core.Mapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import java.util.Collection;

import javax.inject.Inject;

@SuppressWarnings("javadoc")
@Controller
@RequestMapping("todo/sample2")
@SessionAttributes(value = {"sessionTodoSample2Form"})
// 上の記載で、ModelAttribute("sessionTodoForm")はすべてsessionスコープで管理されるようになる。
public class TodoSample2Controller {
  
  @Inject
  TodoService todoService;

  @Inject
  Mapper beanMapper;
  
  // メソッドに@ModelAttributeアノテーションをつけて、
  // @RequestMappingが設定されたメソッドよりも前に、setUpForm()メソッドを実行するよう設定する。
  // これは「メソッドに@ModelAttributeアノテーションを付けたとき」の、@ModelAttributeの機能。
  // 
  // メソッドの戻り値 TodoSample2Formオブジェクトが @ModelAttributeアノテーションの設定に応じてModelオブジェクトに格納される。
  // これで、新規登録画面で入力値に関連づくオブジェクトが最初からある状態になる。
  // 
  // また、valueで @SessionAttributes のvalueと同じ名前を設定し、
  // このオブジェクトをsessionスコープで管理するよう設定する。
  @ModelAttribute(value = "sessionTodoSample2Form")
  public TodoSample2Form setUpForm() {
      return new TodoSample2Form();
  }

  // 一覧表示用
  @GetMapping("list2")
  public String list(Model model) {
    
      Collection<Todo> todos = todoService.findAll();
      model.addAttribute("todos", todos);
      return "todo/sample2/list2";
  }
  
  //一覧表示から、新規作成の入力ボックスがある画面に遷移する用。
  @GetMapping("create2")
  public String showEnterForm(Model model) {

    // todoServiceを使って、登録している数が全体上限数か確認する。
    try {
      todoService.checkCount();
    } catch (BusinessException e) {
      model.addAttribute(e.getResultMessages());

      // 例外が起きたら、入力画面に遷移しないで一覧表示画面を再表示する。 
      return list(model);
    }

    return "todo/sample2/enter2";
  }
  
}