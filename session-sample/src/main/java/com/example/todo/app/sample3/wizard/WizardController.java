package com.example.todo.app.sample3.wizard;

import com.example.todo.app.sample3.wizard.WizardForm.Wizard1;
import com.example.todo.app.sample3.wizard.WizardForm.Wizard2;
import com.example.todo.app.sample3.wizard.WizardForm.Wizard3;
import com.example.todo.domain.model.Entity;
import com.example.todo.domain.service.entity.EntityService;
import com.github.dozermapper.core.Mapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@SuppressWarnings("javadoc")
@Controller
@RequestMapping("wizard")
@SessionAttributes(types = { WizardForm.class, Entity.class })
public class WizardController {

  @Inject
  EntityService entityService;

  @Inject
  Mapper beanMapper;

  @ModelAttribute("wizardForm")
  public WizardForm setUpWizardForm() {
    return new WizardForm();
  }

  // 登録用入力画面を、初期表示するためのハンドラメソッド。
  @GetMapping(value = "create")
  public String initializeCreateWizardForm(SessionStatus sessionStatus) {
    // 操作途中のオブジェクトが、セッションに格納されている可能性があるため、
    // このハンドラメソッドで、セッションに格納されているオブジェクトを削除しておく。
    sessionStatus.setComplete();
    return "redirect:/wizard/create?form1";
  }

  // １ページ目の登録用入力画面を、表示するためのハンドラメソッド
  @RequestMapping(value = "create", params = "form1")
  public String createForm1() {
    return "wizard/form1";
  }

  // 更新用入力画面を、初期表示するためのハンドラメソッド。
  @GetMapping(value = "{id}/update")
  public String initializeUpdateWizardForm(
      @PathVariable("id") Integer id,
      RedirectAttributes redirectAttributes,
      SessionStatus sessionStatus) {
    sessionStatus.setComplete();
    return "redirect:/wizard/{id}/update?form1";
  }

  // １ページ目の更新用入力画面を、表示するためのハンドラメソッド
  @RequestMapping(value = "{id}/update", params = "form1")
  public String updateForm1(
      @PathVariable("id") Integer id,
      WizardForm form,
      Model model) {
    
    // 更新対象のEntityをServiceで取得する。
    Entity loadedEntity = entityService.getEntity(id);
    
    // 取得したエンティティの状態をフォームオブジェクトにマッピングする。
    beanMapper.map(loadedEntity, form); 
    
    // 取得したエンティティを Model オブジェクトに追加し、セッションに格納する。
    model.addAttribute(loadedEntity);
    
    return "wizard/form1";
  }

  // 2ページ目の入力画面を表示するためのハンドラメソッド。
  @PostMapping(value = "save", params = "form2")
  public String saveForm2(
      @Validated(Wizard1.class) WizardForm form, 
      // 1ページ目の入力画面で入力された値のみ入力チェックするために、
      // @Validatedアノテーションのvalue属性に、
      // 1ページ目の入力画面の検証グループ(Wizard1.class)を指定する。
      BindingResult result) {
    
    // 入力値検証でエラーが出たら再表示ページ
    if (result.hasErrors()) {
      return saveRedoForm1();
    }
    
    return "wizard/form2";
  }

  // 3ページ目の入力画面を表示するためのハンドラメソッド。
  @PostMapping(value = "save", params = "form3")
  public String saveForm3(
      @Validated(Wizard2.class) WizardForm form,
      // 2ページ目入力画面表示と同様のところ。 
      BindingResult result) {
    
    // 入力値検証でエラーが出たら再表示ページ
    if (result.hasErrors()) {
      return saveRedoForm2();
    }
    return "wizard/form3";
  }

  // 確認画面を表示するためのハンドラメソッド
  @PostMapping(value = "save", params = "confirm")
  public String saveConfirm(
      @Validated(Wizard3.class) WizardForm form,
      // 最後の3ページの入力のみ検証する。
      BindingResult result) {
    
    // 入力値検証でエラーが出たら再表示ページ
    if (result.hasErrors()) {
      return saveRedoForm3();
    }
    return "wizard/confirm";
  }

  // 保存処理を実行するためのハンドラメソッド
  @PostMapping("save")
  public String save(
      // 入力画面で入力された値を全てチェックするので全部指定する。
      @ModelAttribute @Validated(
          { Wizard1.class,
            Wizard2.class, 
            Wizard3.class }) WizardForm form, 
      BindingResult result,
      // 保存するEntity.classのオブジェクトを取得する
      // 登録処理では新たに生成したオブジェクト、
      // 更新処理では、updateForm1メソッドで格納したオブジェクトが取得される
      Entity entity, 
      SessionStatus sessionStatus,
      RedirectAttributes redirectAttributes) {
    
    // 入力値検証でエラーが出たら、何か不正な操作をしていると思われるので、
    // 最初の登録画面を表示させる
    if (result.hasErrors()) {
      initializeCreateWizardForm(sessionStatus); // (24)
    }

    // フォームの状態をエンティティにマッピングする。
    beanMapper.map(form, entity);

    // 入力値が反映されたentityを保存しつつ、
    // 画面表示用にも再格納。
    entity = entityService.create(entity); // (25)

    // リダイレクト先のハンドラメソッドで見えるように、
    // Flashスコープに格納する。
    redirectAttributes.addFlashAttribute(entity); // (26)

    return "redirect:/wizard/save?complete";
  }

  // 完了画面を表示するためのハンドラメソッド
  @GetMapping(value = "save", params = "complete")
  public String saveComplete(SessionStatus sessionStatus) {
    sessionStatus.setComplete();
    return "wizard/complete";
  }

  // 1ページ目の入力画面を、再表示するためのハンドラメソッド。
  @RequestMapping(value = "save", params = "redoForm1")
  public String saveRedoForm1() {
    return "wizard/form1";
  }

  // 2ページ目の入力画面を、再表示するためのハンドラメソッド
  @RequestMapping(value = "save", params = "redoForm2")
  public String saveRedoForm2() {
    return "wizard/form2";
  }

  // 3ページ目の入力画面を、再表示するためのハンドラメソッド。
  @RequestMapping(value = "save", params = "redoForm3")
  public String saveRedoForm3() {
    return "wizard/form3";
  }

}
