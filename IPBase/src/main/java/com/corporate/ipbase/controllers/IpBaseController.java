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
        /*binder.registerCustomEditor(byte[].class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
            	System.out.println("Wywołanie  getAsText()");
            	 byte[] bytes = (byte[]) getValue();
            	 
            	 return  Byte.toString(bytes[0])+"."+Byte.toString(bytes[1])+"."+Byte.toString(bytes[2])+"."+Byte.toString(bytes[3]);

            	 if(bytes[bytes.length-1]==0) {
            		 String check = Byte.toString(bytes[0])+"."+Byte.toString(bytes[1])+"."+Byte.toString(bytes[2])+"."+Byte.toString(bytes[3]);
            		 r
            	 }
            	 else {
            	 return new String(decrementByteArray(bytes));
            	 }
                //return String.valueOf(prefix.toStringPrefix());
            }
            @Override
            public void setAsText(String prefix) throws IllegalArgumentException {
            	System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Wywołanie setAsText("+prefix+")");
            	byte[] bytes;
            	byte[] result;
            	  
            	if(prefix.matches("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)")) {
            		System.out.println("%%%%%%%%%%%%%%%%%Reg maches");
            		bytes = IpPrefixv4.stringToBytes(prefix);	
            		//result = Arrays.copyOf(bytes, bytes.length+1);
            		//result[result.length-1]= (byte) 255;
            		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!Result reg maches = "+result);
            			
            	}
            	else {
            		//System.out.println("%%%%%%%%%%%%%%%%%Reg not maches");
            		//bytes =  prefix.getBytes();
            		//result = Arrays.copyOf(bytes, bytes.length+1);
            		//result[result.length-1]= (byte) 0;
            		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!Result reg not maches = "+result);
            		throw new IllegalArgumentException("Something goes wrong!");
            	}
                
                setValue(bytes);
            }
        });*/
       
       /* binder.setBindingErrorProcessor(new DefaultBindingErrorProcessor() {
            @Override
            public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
                String propertyName = ex.getPropertyName();
                Object value = ex.getValue();
                bindingResult.addError(new FieldError(bindingResult.getObjectName(), propertyName, value, true,
                new String[] { "moderation.field.error" }, new Object[] { propertyName, value },
                "Invalid value for " + propertyName + "(" + value + ")"));
            }
    });*/
        
    }
    
    /*public byte[] decrementByteArray(byte[] bytes)
    {
    	byte[] result = new byte[bytes.length-1];
    	for(int i=0; i<bytes.length-2;i++ )
    	{	
    		result[i] = bytes[i];
    	}
    	return result;
    }*/

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
    	Set<IpPrefixv4> unique = new HashSet<>(repo.findByNested(false));
    	List<IpPrefixv4> mainList = new ArrayList<>();
    	IpPrefixv4 prefix = new IpPrefixv4();
    	mainList.addAll(unique);
        //model.addAttribute("prefix_list", repo.findAll());
    	model.addAttribute("prefix_list", mainList);
        //model.addAttribute("prefix",prefix);
        //return "vetList";
        //return "tree_disp";
    	//System.out.println("Prefixes from service");
    	for (IpPrefixv4 prefix_temp : ipPrefixv4Service.getRepo().findByMask(24)){
    	
    		//System.out.println(prefix_temp);
    	}
    	//System.out.println("koniec");
    	return "prefix_display";
    }
    
    @GetMapping("/prefix/delete/{id}")
    public String deletePrefixSubmit( @PathVariable(value = "id") String id) 
    {
    	repo.deleteById(id);
    	return "redirect:/prefixes";
    }
 //=====================================================================================
	@PostMapping("/prefix/edit/{id}")
 	public String editPrefixSubmit(@Valid IpPrefixv4 ipPrefixv4, Errors errors, @PathVariable(value = "id") String id) {
 	
 	System.out.println("Post prefixes start !!!");

 	
 	/*IpPrefixv4Text prefix_text = null;
 	if(repo.findById(ipPrefixv4Text.getId()).isPresent())
 	{
 		prefix_text  = repo.findById(ipPrefixv4Text.getId()).get().converter();
 	}
 	kkk
 	if(ipPrefixv4Text.compare(prefix_text))
 	{
 		errors.rejectValue("prefix", "3456", null, "Nie wprowadzono żadnych  zmian.");
 		errors.rejectValue("description", "3456", null, "Nie wprowadzono żadnych  zmian.");
 		errors.rejectValue("AS", "3456", null, "Nie wprowadzono żadnych  zmian.");
 		errors.rejectValue("VRF", "3456", null, "Nie wprowadzono żadnych  zmian.");
 		
 		return "testprefixedit";
 	}
 	if (errors.hasErrors()) {
 		System.out.println("Wystąpiły błedy");
 		
 		return "testprefixedit";
 	}
 	System.out.println("print date start");
 	System.out.println("CreationDate = "+ipPrefixv4Text.getCreationDate());
 	System.out.println("LastUpdate = "+ipPrefixv4Text.getLastUpdate());
 	System.out.println("print date stop");
 	IpPrefixv4 prefixv4 = ipPrefixv4Text.converter();
 	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(prefixv4);
 	if(prefixv4_opt.isPresent())
 	{

 		errors.rejectValue("prefix", "2345", null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix()+"\r\n czy dodać jako podzakrezs dla tego prefix");
 		return "testprefixedit";
 	}
 	ipPrefixv4Text.setLastUpdate(LocalDateTime.now());
 	System.out.println("Id prefixu text = " + ipPrefixv4Text.getId());
 	IpPrefixv4 prefix = ipPrefixv4Text.converter();
 	System.out.println("Id prefix do zapisu = "+ prefix.getId());*/
 	
 	if (errors.hasErrors()) {
 		System.out.println("Wystąpiły błedy");
 		
 		return "testprefix1";
 	}
 	
 	repo.save(ipPrefixv4);
 	
 	System.out.println("Post prefixes stop !!!");
 	return "redirect:/prefixes";
 }
// =====================================================================================   
    /*@PostMapping("/prefix/edit/{id}")
    public String editPrefixSubmit(@Valid IpPrefixv4Text ipPrefixv4Text, Errors errors, @PathVariable(value = "id") String id) {
    	
    	System.out.println("Post prefixes start !!!");
    	
    	
    	IpPrefixv4Text prefix_text = null;
    	if(repo.findById(ipPrefixv4Text.getId()).isPresent())
    	{
    		prefix_text  = repo.findById(ipPrefixv4Text.getId()).get().converter();
    	}
    	
    	if(ipPrefixv4Text.compare(prefix_text))
    	{
    		errors.rejectValue("prefix", "3456", null, "Nie wprowadzono żadnych  zmian.");
    		errors.rejectValue("description", "3456", null, "Nie wprowadzono żadnych  zmian.");
    		errors.rejectValue("AS", "3456", null, "Nie wprowadzono żadnych  zmian.");
    		errors.rejectValue("VRF", "3456", null, "Nie wprowadzono żadnych  zmian.");
    		
    		return "testprefixedit";
    	}
    	if (errors.hasErrors()) {
    		System.out.println("Wystąpiły błedy");
    		
    		return "testprefixedit";
    	}
    	System.out.println("print date start");
    	System.out.println("CreationDate = "+ipPrefixv4Text.getCreationDate());
    	System.out.println("LastUpdate = "+ipPrefixv4Text.getLastUpdate());
    	System.out.println("print date stop");
    	IpPrefixv4 prefixv4 = ipPrefixv4Text.converter();
    	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(prefixv4);
    	if(prefixv4_opt.isPresent())
    	{

    		errors.rejectValue("prefix", "2345", null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix()+"\r\n czy dodać jako podzakrezs dla tego prefix");
    		return "testprefixedit";
    	}
    	ipPrefixv4Text.setLastUpdate(LocalDateTime.now());
    	System.out.println("Id prefixu text = " + ipPrefixv4Text.getId());
    	IpPrefixv4 prefix = ipPrefixv4Text.converter();
    	System.out.println("Id prefix do zapisu = "+ prefix.getId());
    	repo.save(prefix);
    	
    	System.out.println("Post prefixes stop !!!");
    	return "redirect:/prefixes";
    }*/
// ================================================================================
    
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
        model.addAttribute("ipPrefixv4", new IpPrefixv4());
        return "testprefix";
    }
    @PostMapping("/prefix")
    public String greetingSubmit(@Valid IpPrefixv4 ipPrefixv4, Errors errors) {
    	
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
    	//IpPrefixv4 prefixv4 = ipPrefixv4Text.converter();
    	Optional<IpPrefixv4> prefixv4_opt = ipPrefixv4Service.checkExistance(ipPrefixv4);
    	if(prefixv4_opt.isPresent())
    	{

    		errors.rejectValue("prefix", "2345", null, "Prefix jest  zawarty w "+prefixv4_opt.get().toStringPrefix()+"\r\n czy dodać jako podzakrezs dla tego prefix");
    		return "testprefix";
    	}
    	//System.out.println(ipPrefixv4Text.toString());
    	ipPrefixv4.prefixToBytesMask();
    	repo.save(ipPrefixv4);
    	return "ipform_zap";
    }
//=========================================================================================
    
    @RequestMapping(path = "/prefix/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> opt_prefixv4 =  ipPrefixv4Service.getRepo().findById(id);
    	if((opt_prefixv4.isPresent())) {
    		model.addAttribute("ipPrefixv4",opt_prefixv4.get() );
    		return "testprefix1";
    	}
    	return "prefix_does_not_exist";
    }
    
//=========================================================================================
    /*@RequestMapping(path = "/prefix/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
    	
    	Optional<IpPrefixv4> opt_prefixv4 =  ipPrefixv4Service.getRepo().findById(id);
    	if((opt_prefixv4.isPresent())) {
    		IpPrefixv4Text text_prefix = opt_prefixv4.get().converter();
    		System.out.println("!!!!!!prefix tekst  to string start");
    		System.out.println(text_prefix.toString());
    		System.out.println("!!!!!!prefix tekst  to string start");
    		
    		model.addAttribute("ipPrefixv4Text",text_prefix );
    		return "testprefixedit";
    	}
    	return "prefix_does_not_exist";
    }*/
//===========================================================================================

    /*@RequestMapping(path = "/prefix/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") String id) {
        productRepository.delete(id);
        return "redirect:/products";
    }*/
    /*@GetMapping({ "/vets" })
    public @ResponseBody IpList showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        IpList vets = new IpList();
        vets.getVetList().addAll(this.vets.findAll());
        return vets;
    }*/

}
