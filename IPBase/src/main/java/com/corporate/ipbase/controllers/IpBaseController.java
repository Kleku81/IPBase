/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corporate.ipbase.controllers;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.PropertyAccessException;
//import org.hibernate.PropertyAccessException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.corporate.ipbase.data.IpPrefixv4Repository;
import com.corporate.ipbase.data.UserRepository;
import com.corporate.ipbase.domain.IpPrefixv4;
import com.corporate.ipbase.domain.IpPrefixv4Text;
import com.corporate.ipbase.domain.User;
import com.corporate.ipbase.service.IpPrefixv4Service;
import com.corporate.ipbase.validators.IpPrefixv4Validator;
@Controller
class IpBaseController {

    private final IpPrefixv4Repository repo;
    private final UserRepository userRepo;
    private final IpPrefixv4Service ipPrefixv4Service;
	private final IpPrefixv4Validator ipPrefixv4Validator;
 
	public IpBaseController(IpPrefixv4Repository repo, UserRepository userRepo, IpPrefixv4Service ipPrefixv4Service, IpPrefixv4Validator ipPrefixv4Validator ) {
		super();
		this.repo = repo;
		this.userRepo = userRepo;
		this.ipPrefixv4Service = ipPrefixv4Service;
		this.ipPrefixv4Validator = ipPrefixv4Validator;
	}

    @InitBinder
    public void initBinder ( WebDataBinder binder )
    {
        StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
        //binder.addValidators(ipPrefixv4Validator);
        binder.registerCustomEditor(String.class, stringtrimmer);
        
    }
    


    @RequestMapping(path = "/")
    public String index() {
        return "home";
    }
    @RequestMapping(path = "/tbi")
    public String underConstruction() {
        return "tbi";
    }
    
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test()
    {
    	return "test";
    }
    
    @RequestMapping(path = "/test1", method = RequestMethod.GET)
    public String test1()
    {
    	return "test1";
    }

    @RequestMapping(path = "/prefixes", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
    	Set<IpPrefixv4> unique = new HashSet<>(repo.findByNested(false));
    	List<IpPrefixv4> mainList = new ArrayList<>();
    	IpPrefixv4 prefix = new IpPrefixv4();
    	mainList.addAll(unique);
    	model.addAttribute("prefix_list", mainList);
    	for (IpPrefixv4 prefix_temp : ipPrefixv4Service.getRepo().findByMask(24)){
    	}

    	return "prefix_display";
    }
    
    @GetMapping("/prefix/delete/{id}")
    public String deletePrefixSubmit( @PathVariable(value = "id") String id) 
    {
    	repo.deleteById(id);
    	return "redirect:/prefixes";
    }
	@PostMapping("/prefix/edit/{id}")
 	public String editPrefixSubmit(Model model,@Valid IpPrefixv4 ipPrefixv4, Errors errors, @PathVariable(value = "id") String id) {
	

	Optional<IpPrefixv4> prefixv4_opt1 = ipPrefixv4Service.getRepo().findById(id);
 	if (errors.hasErrors()) {
 		System.out.println("Wystąpiły błedy");
 		System.out.println("%%%%%%%%PostEditHasErrosrs.IpPrefixv4 = "+ ipPrefixv4);
 		model.addAttribute("ipPrefixv4_org", prefixv4_opt1.get());
 		return "edit_prefix";
 	}
 	ipPrefixv4.prefixToBytesMask();
 	System.out.println("Post prefixes start !!!");
 	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(ipPrefixv4);
 	if(prefixv4_opt.isPresent())
 	{
 		errors.rejectValue("prefix", "2345", null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix()+"\r\n czy dodać jako podzakrezs dla tego prefix");
 		return "edit_prefix";
 	}
 	
 	repo.save(ipPrefixv4);
 	
 	System.out.println("Post prefixes stop !!!");
 	return "redirect:/prefixes";
 }

    
    @GetMapping("/user")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "test_user";
    }

    @PostMapping("/user")
    public String greetingSubmit(@Valid User user, Errors errors) {
    	if (errors.hasErrors()) {
    		System.out.println("Wystąpiły  błedy");
    		return "test_user";
    	}
    	System.out.println(user.toString());
    	userRepo.save(user);
        return "test_user_zap";
    }
    @GetMapping("/prefix")
    public String prefixForm(Model model) {
        model.addAttribute("ipPrefixv4", new IpPrefixv4());
        return "testprefix";
    }
    @PostMapping("/prefix")
    public String greetingSubmit(@Valid IpPrefixv4 ipPrefixv4, Errors errors) {
    	System.out.println("<<<<<<<<<<<<<<<<prefix string = ."+ ipPrefixv4.getPrefix()+".");
    	


    	if (errors.hasErrors()) {
    		System.out.println("Wystąpiły błedy");
    		return "testprefix";
    	}
    	ipPrefixv4.prefixToBytesMask();
    	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(ipPrefixv4);
    	if(prefixv4_opt.isPresent())
    	{
    		errors.rejectValue("prefix", "2345", null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix()+"\r\n czy dodać jako podzakrezs dla tego prefix");
    		return "testprefix";
    	}
    	ipPrefixv4.prefixToBytesMask();
    	repo.save(ipPrefixv4);
    	return "ipform_zap";
    }
//////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(path = "/prefix/edit/addsub/{id}", method = RequestMethod.GET)
    public String addSubPrefixGet(Model model, @PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> opt_prefixv4 =  ipPrefixv4Service.getRepo().findById(id);
    	if((opt_prefixv4.isPresent())) {
    		model.addAttribute("ipPrefixv4", new IpPrefixv4());
    		model.addAttribute("ipPrefixv4_mod", opt_prefixv4.get());
    		return "add_sub_prefix";
    	}
    	return "prefix_does_not_exist";
    }
    
    @RequestMapping(path = "/prefix/edit/addsub/{id}", method = RequestMethod.POST)
    public String addSubPrefixPost(Model model ,@Valid IpPrefixv4 ipPrefixv4, Errors errors ,@PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.getRepo().findById(id);
    	
    	if (errors.hasErrors()) {
    		
    		model.addAttribute("ipPrefixv4_mod", prefixv4_opt.get());
    		return "add_sub_prefix";
     	}
     	ipPrefixv4.prefixToBytesMask();
    	
    	if(prefixv4_opt.isPresent()) {
    		
    		IpPrefixv4 super_prefix = prefixv4_opt.get();
    		if(!ipPrefixv4.isContained(super_prefix))
    		{
    			errors.rejectValue("prefix", "2345", null, "Incorrect prefix");
    			model.addAttribute("ipPrefixv4_mod", prefixv4_opt.get());
    			return "add_sub_prefix";
    		}
    		else {
    			ipPrefixv4.prefixToBytesMask();
    			ipPrefixv4.setId(null);
    			ipPrefixv4.setNested(true);
    			super_prefix.getSubNets().add(ipPrefixv4);
    			
    			repo.save(super_prefix);
    			return "redirect:/prefixes";
    			
    		}
    	}
    	return "prefix_does_not_exist";
    }
//////////////////////////////////////////////////////////////////////////////////////
    
    @RequestMapping(path = "/prefix/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> opt_prefixv4 =  ipPrefixv4Service.getRepo().findById(id);
    	if((opt_prefixv4.isPresent())) {
    		model.addAttribute("ipPrefixv4",opt_prefixv4.get() );
    		model.addAttribute("ipPrefixv4_org",opt_prefixv4.get() );
    		return "edit_prefix";
    	}
    	return "prefix_does_not_exist";
    }
    
    @RequestMapping(path = "/prefix/show/{id}", method = RequestMethod.GET)
    public String showProduct(Model model, @PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> opt_prefixv4 =  ipPrefixv4Service.getRepo().findById(id);
    	if((opt_prefixv4.isPresent())) {
    		model.addAttribute("ipPrefixv4",opt_prefixv4.get() );
    		return "show_prefix";
    	}
    	return "prefix_does_not_exist";
    }
    


}
