package com.queless.que.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.queless.que.Entity.User;
import com.queless.que.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public String usertest()
	{
		return"user api is working";
	}

	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
	    return service.saveUser(user);
	}

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
	    return service.getAllUsers();
	}

	@GetMapping("/get/{id}")
	public User getUserById(@PathVariable Integer id) {
	    return service.getUserById(id);
	}

	@PutMapping("/update")
	public User updateUser(@RequestBody User user) {
	    return service.updateUser(user);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
	    service.deleteUser(id);
	    return "User Deleted Successfully";
	}
}