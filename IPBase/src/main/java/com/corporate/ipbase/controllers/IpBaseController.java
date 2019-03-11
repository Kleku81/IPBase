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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corporate.ipbase.data.IpPrefixv4Repository;
import com.corporate.ipbase.data.UserRepository;
import com.corporate.ipbase.domain.IpPrefix;
import com.corporate.ipbase.domain.IpPrefixv4;
import com.corporate.ipbase.domain.IpPrefixv4Text;
import com.corporate.ipbase.domain.User;
import com.corporate.ipbase.service.IpPrefixv4Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

@Controller
class IpBaseController {

    private final IpPrefixv4Repository repo;
    private final UserRepository userRepo;
    private final IpPrefixv4Service ipPrefixv4Service;
 
	public IpBaseController(IpPrefixv4Repository repo, UserRepository userRepo, IpPrefixv4Service ipPrefixv4Service) {
		super();
		this.repo = repo;
		this.userRepo = userRepo;
		this.ipPrefixv4Service = ipPrefixv4Service;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    @RequestMapping(path = "/")
    public String index() {
        return "index";
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
    	System.out.println("!!! get All products !!!");
    	Set<IpPrefixv4> unique = new HashSet<>(repo.findByNested(false));
    	List<IpPrefixv4> mainList = new ArrayList<>();
    	IpPrefixv4 prefix = new IpPrefixv4();
    	mainList.addAll(unique);
        //model.addAttribute("prefix_list", repo.findAll());
    	model.addAttribute("prefix_list", mainList);
        //model.addAttribute("prefix",prefix);
        //return "vetList";
        //return "tree_disp";
    	System.out.println("Prefixes from service");
    	for (IpPrefixv4 prefix_temp : ipPrefixv4Service.getRepo().findByMask(24)){
    	
    		System.out.println(prefix_temp);
    	}
    	System.out.println("koniec");
    	return "prefix_display.html";
    }
    
    @GetMapping("/user")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "test_user";
    }

    @PostMapping("/user")
    public String greetingSubmit(@Valid User user, Errors errors) {
    	//System.out.println("request" + request);
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
        model.addAttribute("ipPrefixv4Text", new IpPrefixv4Text("",""));
        return "testprefix";
    }
    @PostMapping("/prefix")
    public String greetingSubmit(@Valid IpPrefixv4Text ipPrefixv4Text, Errors errors) {
    	
    	/*ObjectError oerror = new ObjectError("checkExistError","message");
		List<ObjectError> ol = new ArrayList<>();
		ol.add(oerror);
		errors.rejectValue("prefix", null, null, "test message");
    	
    	for (ObjectError oe : errors.getAllErrors())
    	{
    		System.out.println("Error = "+ oe);
    	}*/

    	if (errors.hasErrors()) {
    		System.out.println("Wystąpiły błedy");
    		
    		return "testprefix";
    	}
    	IpPrefixv4 prefixv4 = ipPrefixv4Text.converter();
    	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(prefixv4);
    	if(prefixv4_opt.isPresent())
    	{
    		errors.rejectValue("prefix", null, null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix());
    		return "testprefix";
    	}
    	System.out.println(ipPrefixv4Text.toString());
    	repo.save(ipPrefixv4Text.converter());
        return "ipform_zap";
    }

    /*@GetMapping({ "/vets" })
    public @ResponseBody IpList showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        IpList vets = new IpList();
        vets.getVetList().addAll(this.vets.findAll());
        return vets;
    }*/

}
