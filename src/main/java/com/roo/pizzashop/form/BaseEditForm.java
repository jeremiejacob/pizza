package com.roo.pizzashop.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;

import com.roo.pizzashop.domain.Base;

/**
 *Bean for Editing Base.
 */
@RooJavaBean
public class BaseEditForm {

	@NotEmpty
	private String name;
		
	/**
     * Generate Base entity object from this instance values.
     */
	public Base toEntity() {
		Base base = new Base();
		base.setName(name);
		return base;
	}
	
	public static BaseEditForm fromEntity(Base base) {
		BaseEditForm form = new BaseEditForm();
		form.setName(base.getName());
		return form;
	}
}