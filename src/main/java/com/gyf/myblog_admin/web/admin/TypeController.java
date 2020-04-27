package com.gyf.myblog_admin.web.admin;

import com.gyf.myblog_admin.domain.Type;
import com.gyf.myblog_admin.service.impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/types")
    public String types(
            @PageableDefault(size=3,sort = {"id"},direction = Sort.Direction.DESC)
            Pageable pageable, Model model){

        model.addAttribute("page", typeService.listType(pageable));

        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    //跳转到新增页面
    //PathVariable接受动态参数
    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    //校验Type对象，BindingResult接受校验后的结果
    @PostMapping("/types")
    public String Post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        //重复检验
        Type type1 = typeService.getTypeByName(type.getName());
        if(null != type1) {
            result.rejectValue("name","nameError", "分类名称已存在");
        }

        if(result.hasErrors()){
            return "admin/type-input";
        }
        //非空校验
        Type type2 = typeService.saveType(type);
        if (null == type2) {
            attributes.addFlashAttribute("message","新增失败");
        } else {
            attributes.addFlashAttribute("message","新增成功");
        }

        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {

        //重复检验
        Type type1 = typeService.getTypeByName(type.getName());
        if(null != type1) {
            result.rejectValue("name","nameError", "分类名称已存在,不能重复更改");
        }
        if(result.hasErrors()){
            return "admin/type-input";
        }
        Type type2 = typeService.updateType(id,type);
        if (null == type2) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
