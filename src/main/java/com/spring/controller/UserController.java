package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.common.ICommonController;
import com.spring.dao.UserDAO;
import com.spring.model.Category;
import com.spring.model.User;



//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/pharma")
public class UserController implements ICommonController<User> {
	
//	@Autowired
//	private UserDAO userDao;
//
//    @GetMapping("/user")
//    public List<User> getAll() {
//        return userDao.getAll();
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getById(@PathVariable(value = "id") Long userId) {
//    	User user = userDao.getUserById(userId);
//        return ResponseEntity.ok().body(user);
//    }
//
//    @PostMapping("/user")
//    public User save(@RequestBody User user) {
//        
//    	return userDao.save(user);
//    }
//    
//    @PostMapping("/user/login")
//    public User login(@RequestBody User user) {
//        
//    	return userDao.getUserByUsername(user);
//    }
//
//    @PutMapping("/user/{id}")
//    public ResponseEntity<User> update(@PathVariable(value = "id") Long userId,
//         @Validated @RequestBody User userDetails) {
//    	User user = userDao.getUserById(userId);
//    	user.setName(userDetails.getName());
//    	user.setEmail(userDetails.getEmail());
//    	user.setPhone(userDetails.getPhone());
//    	user.setUsername(userDetails.getUsername());
//    	user.setPassword(userDetails.getPassword());
//    	user.setConfirmpassword(userDetails.getConfirmpassword());
//        final User updateduser = userDao.save(user);
//        return ResponseEntity.ok(updateduser);
//    }
//
//    @DeleteMapping("/user/{id}")
//    public Map<String, Boolean> delete(@PathVariable(value = "id") Long userId){
//    	User user = userDao.getUserById(userId);
//    	userDao.delete(user);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
	
	
	//rolebased
	
	@Autowired
    private UserDAO userDao;

    @GetMapping("/user")
    public List<User> getAll() {
        return userDao.getAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") Long userId) {
        User user = userDao.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        return userDao.save(user);
        
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        User user = userDao.login(username, password);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("role", user.getRole());
        response.put("name", user.getName());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long userId,
                                       @Validated @RequestBody User userDetails) {
        User user = userDao.getUserById(userId);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setConfirmpassword(userDetails.getConfirmpassword());
        user.setRole(userDetails.getRole());

        final User updateduser = userDao.save(user);
        return ResponseEntity.ok(updateduser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long userId) {
        User user = userDao.getUserById(userId);
        userDao.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
