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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corporate.ipbase.data.IpPrefixRepository;
import com.corporate.ipbase.data.UserRepository;
import com.corporate.ipbase.domain.IpPrefix;
import com.corporate.ipbase.domain.IpPrefixv4;
import com.corporate.ipbase.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
class IpBaseController {

    private final IpPrefixRepository repo;
    private final UserRepository userRepo;

 
    
    public IpBaseController(IpPrefixRepository repo, UserRepository userRepo) {
		super();
		this.repo = repo;
		this.userRepo = userRepo;
	}
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/prefixes", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
    	System.out.println("!!! get All products !!!");
    	Set<IpPrefixv4> unique = new HashSet<>(repo.findAll());
    	List<IpPrefixv4> mainList = new ArrayList<>();
    	IpPrefixv4 prefix = new IpPrefixv4();
    	mainList.addAll(unique);
        model.addAttribute("prefix_list", repo.findAll());
        model.addAttribute("prefix",prefix);
        //return "vetList";
        return "tree_disp";
    }
    
    @GetMapping("/user")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "test_user";
    }

    @PostMapping("/user")
    public String greetingSubmit(@ModelAttribute User user) {
    	System.out.println(user.toString());
    	userRepo.save(user);
        return "test_user_zap";
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
