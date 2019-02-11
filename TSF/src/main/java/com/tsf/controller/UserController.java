package com.tsf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsf.model.User;
import com.tsf.repository.UserRepository;

@RestController
@RequestMapping("tsf")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@CrossOrigin(origins = {"http://localhost:8080"})
	@GetMapping("/users")
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@CrossOrigin(origins = {"http://localhost:8080"})
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") int id) {
		return userRepository.getOne(id);
	}
	@CrossOrigin(origins = {"http://localhost:8080"})
	@PutMapping("/user1/{id}/{amount}")
	public List<User> updateUser(@RequestBody User user,@PathVariable("id") int id,@PathVariable("amount") int amount) {
		//go to repo and fetch existing user based on id
		User u = userRepository.getOne(user.getId());//existing User
		u.setCurrent_credits(user.getCurrent_credits());
		u=userRepository.save(u);
		List<User> new1=new ArrayList<>();
		new1.add(u);
		u=userRepository.getOne(id);
		u.setCurrent_credits(Float.toString(Float.parseFloat(u.getCurrent_credits())+amount));
		new1.add(u); 
		return(new1);
	}
	
}
