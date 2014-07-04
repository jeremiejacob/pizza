package com.roo.pizzashop.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.roo.pizzashop.domain.Base;
import com.roo.pizzashop.form.BaseEditForm;

@RequestMapping("/bases/**")
@Controller
@RooWebScaffold(path = "bases", formBackingObject = Base.class)
public class BaseController {

	/**
	 * Display List Screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model model) {
		model.addAttribute("bases", Base.findAllBases());
		return "bases/list";
	}

	/**
	 * Display Create New Base screen
	 */
	@RequestMapping(method = RequestMethod.GET, value = "create")
	public String create(Model model) {
		return showEditForm(model, new BaseEditForm(), null);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "save")
	public String save(Model model, @Valid @ModelAttribute("form") BaseEditForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return showEditForm(model, form, bindingResult);
		}
		form.toEntity().persist();
		return "redirect:/bases/list";
	}

	private String showEditForm(Model model, BaseEditForm form, BindingResult bindingResult) {
		model.addAttribute("form", form);
		return "bases/create";
	}
	
    /**
     * Show Base
     */
    @RequestMapping(method = RequestMethod.GET, value = "show/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
    	model.addAttribute("base", Base.findBase(id));
    	return "bases/show";
    }
    
    /**
     * Update Base
     */
    @RequestMapping(method = RequestMethod.GET, value = "update/{id}")
    public String update(Model model, @PathVariable("id") Integer id) {
    	model.addAttribute("base", Base.findBase(id));
    	return showEditForm(model, new BaseEditForm(), null);
    }
    
    /**
     * Delete Base
     */
    @RequestMapping(method = RequestMethod.GET, value = "remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
    	Base base = Base.findBase(id);
        base.remove();
        model.asMap().clear();
        return "redirect:/bases";
    }
    

}
