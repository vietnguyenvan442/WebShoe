package com.example.WebShoe.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebShoe.DAO.UserDAO;
import com.example.WebShoe.Model.User;

@RestController
@CrossOrigin
public class UserController {
	private UserDAO uDAO = new UserDAO();
	
	@PostMapping("/checkLogin")
	public ResponseEntity<User> checkLogin(@RequestBody User u) {
		User user = uDAO.checkLogin(u);
		if(user!=null) return ResponseEntity.ok(user);
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/logup")
	public ResponseEntity<User> getLogup(@RequestBody User u){
		if(!uDAO.checkLogup(u)) {
			uDAO.addUser(u);
			return ResponseEntity.ok(uDAO.checkLogin(u));
		}
		return ResponseEntity.notFound().build();
	}
	@PostMapping("/change-password")
	public ResponseEntity<User> changePassword(@RequestBody User u){
		uDAO.updateUser(u);
		return ResponseEntity.ok(u);
	}
	
	@GetMapping("/admin/users")
	public List<User> getUsers(){
		List<User> users = uDAO.selectUsers();
		return users;
	}
	
	@GetMapping("/admin/user/{id}")
	public User getUser(@PathVariable int id) {
		User u = uDAO.selectUserByID(id);
		return u;
	}
	
	@PostMapping("/admin/user/save/{id}")
	public ResponseEntity<String> addOrUpdateUser(@PathVariable int id, @RequestBody User user) {
		if (user.getId() <= 0) {
			uDAO.insertUser(user);
		} else {
			uDAO.updateUser(user);
		}
		return ResponseEntity.ok("Save complete");
	}
	@DeleteMapping("/admin/user/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
	    User user = uDAO.selectUserByID(id);
	    if (user != null) {
	        uDAO.DeleteUser(id);
	        return ResponseEntity.ok("User deleted with ID: "+user.getId());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
