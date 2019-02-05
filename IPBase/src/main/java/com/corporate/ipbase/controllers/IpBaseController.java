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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.corporate.ipbase.data.IpPrefixRepository;
import com.corporate.ipbase.domain.IpPrefix;
import com.corporate.ipbase.domain.IpPrefixv4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
class IpBaseController {

    private final IpPrefixRepository repo;

    public IpBaseController(IpPrefixRepository repo) {
        this.repo= repo;
    }
    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
    	System.out.println("!!! get All products !!!");
        model.addAttribute("products", repo.findAll());
        return "vetList";
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
