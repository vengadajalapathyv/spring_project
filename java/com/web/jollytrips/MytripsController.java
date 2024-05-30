package com.web.jollytrips;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Embeddable;




@Controller

public class MytripsController {
	@Autowired
	MytripsRepository repo;

	@GetMapping("/list")
	public String GetAll(Model m)
	{
		List<MytripsModel> list = repo.alldetails();
		m.addAttribute("userlist",list);
		return "list";
	}

	@GetMapping("/dashboard")
	public String ShowAll(Model m)
	{
		List<MytripsModel> list = repo.alldetails();
		m.addAttribute("userlist",list);
		return "dashboard";
	}

	@GetMapping("/edit/{id}")
	public String GetOne(@PathVariable int id, Model m)
	{
		MytripsModel single = (MytripsModel) repo.oneperson(id);
		m.addAttribute("userobj",single);
		return "edit";
	}



	@GetMapping("/login")
	public String LoginPerson1()
	{
		return "login";
	}
	@PostMapping("/login-post")
	public String LoginPerson(@ModelAttribute MytripsModel trip)
	{  	  
		MytripsModel check = repo.loginperson(trip.getEmail(),trip.getPassword());
		if(check != null) {
			return "redirect:/dashboard";
		}
		return "redirect:/login";
	}

	@GetMapping("/signup")
	public String InsertPerson1()
	{
		return "signup";
	}
	@PostMapping("/signup-post")
	public String InsertPerson(@ModelAttribute MytripsModel trip)
	{
		if(trip!=null) {
			repo.insertperson(trip.getName(),trip.getEmail(),trip.getPassword(),trip.getMobilenumber(),trip.getGender());
			return "redirect:/login";
		}
		return "redirect:/signup";
	}

	@PostMapping("/edit-post")
	public String UpdatePerson(@ModelAttribute MytripsModel trip)
	{
		if(trip!=null) {
			repo.updateperson(trip.getId(), trip.getName(), trip.getEmail(),trip.getPassword(),trip.getMobilenumber(),trip.getGender());
			return "redirect:/list";
		}
		return "redirect:/edit/" + trip.getId();
	}

	@GetMapping("/delete/{id}")
	public String Delete(@PathVariable int id)
	{
		repo.deleteperson(id);
		return "redirect:/";
	}

}
