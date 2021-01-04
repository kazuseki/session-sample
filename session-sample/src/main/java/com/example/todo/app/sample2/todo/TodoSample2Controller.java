package com.example.todo.app.sample2.todo;

import com.example.todo.app.sample1.todo.TodoForm;
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
  public String list(Model model, SessionStatus sessionStatus) {

    // 画面表示前に変なものが残っていないよう、sessionに保管している sessionTodoSample2Form を破棄する。
    sessionStatus.setComplete();

    Collection<Todo> todos = todoService.findAll();
    model.addAttribute("todos", todos);
    return "todo/sample2/list2";
  }
  
  //一覧表示から、新規作成の入力ボックスがある画面に遷移する用。
  @GetMapping("create2")
  public String showEnterForm(Model model, SessionStatus sessionStatus) {

    // todoServiceを使って、登録している数が全体上限数か確認する。
    try {
      todoService.checkCount();
    } catch (BusinessException e) {
      model.addAttribute(e.getResultMessages());

      // 例外が起きたら、入力画面に遷移しないで一覧表示画面を再表示する。 
      return list(model,sessionStatus);
    }

    return "todo/sample2/enter2";
  }
  
  // 新規作成から次へを押して、確認画面を表示する。
  // リクエストパラメータに event_proceed(進行中)があれば動く。
  @PostMapping(value = "create2" , params = "event_proceed")
  public String showReview(@Validated @ModelAttribute("sessionTodoSample2Form") TodoSample2Form todoSample2Form,
      BindingResult bindingResult,
      Model model,
      SessionStatus sessionStatus) {

    // 入力値検証でエラーがあったら元の画面に返却する。
    if(bindingResult.hasErrors()) {
      return showEnterForm(model,sessionStatus);
    }

    // 検証が通ったら確認画面へ。
    return "todo/sample2/confirm2";
  }
  
  // 確認画面からOKを押して実際に登録する。
  // 完了後は完了画面へ。
  // 失敗したら入力画面ではなく、一覧画面に戻す
  @PostMapping(value = "create2" , params = "create_complete")
  public String create(@ModelAttribute("sessionTodoSample2Form") TodoSample2Form todoSample2Form,
      Model model,
      RedirectAttributes redirectAttributes,
      SessionStatus sessionStatus) {

    // フォームとエンティティをマッピング。
    Todo todo = beanMapper.map(todoSample2Form, Todo.class);

    // todoServiceを使って登録する。
    // 例外が起きたら、入力画面ではなく一覧画面に戻す
    try {
      todoService.create(todo);
    } catch (BusinessException e) {
      model.addAttribute(e.getResultMessages());
      return list(model,sessionStatus);
    }

    // 正常に更新が完了した場合、結果メッセージをflashスコープに追加して、一覧画面でリダイレクトする。
    // PRGパターンにより、ブラウザ再読み込みをしてもPOSTが来ない。
    redirectAttributes.addFlashAttribute(ResultMessages.success().add(
        ResultMessage.fromText("Created successfully!")));

    // PRGパターンのため、GETメソッドで完了画面が表示されるよう、リダイレクト。
    return "redirect:/todo/sample2/complete2";
  }
  
  // 確認画面からキャンセルを押したときに使われる。
  // ゴミを削除して再度、新規作成画面へ。
  @PostMapping(value = "create2" , params = "create_cancel")
  public String cancel(SessionStatus sessionStatus) {
    // キャンセルするとき、セッションに残っているtodoFormオブジェクトを削除する。
    sessionStatus.setComplete();
    return "redirect:/todo/sample2/create2";
  }
  
  //新規登録の完了画面表示。
  @GetMapping("complete2")
  public String showComplete(SessionStatus sessionStatus) {
    // 使い終わったのでセッションに格納したオブジェクトを削除する。
    sessionStatus.setComplete();
    // なお、ここでセッションから削除しても問題ない。
    // ModelAttribute("sessionTodoForm")オブジェクトはクライアントにレスポンスが返るまで
    // requestスコープで有効なので、完了画面を初めて表示するまでは、JSPで表示できる。
    // 完了画面でページ更新(ブラウザでF5など)を行うと、表示は消えてしまう。
    return "todo/sample/complete2";
  }
}