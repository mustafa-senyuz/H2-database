/*QUERY ILE KALICI KAYIT*/

package com.database.test.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.test.entity.User;

import jakarta.validation.Valid;

@RestController
public class UserQueryRestcontroller {
	
	@Autowired JdbcTemplate jdbcTemp;

	public UserQueryRestcontroller() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	@GetMapping("/userdb")
	public List<User> getList(){
		
		String sqlString = "SELECT * FROM usertable";
		
		List<User> userlist = new ArrayList<>();
		
		  userlist = jdbcTemp.query(sqlString, BeanPropertyRowMapper.newInstance(User.class)); //satırdaki sütun adlarını, nesnedeki alan adlarıyla eşleştirmeye çalışır
		
		return userlist;			
	}
	
	@GetMapping("/userdb/{id}")
	public User getUser(@PathVariable Integer id) {
	  // Veritabanından ilgili kullanıcıyı bulma işlemi
	  String sql = "SELECT * FROM usertable WHERE id = ?";
	  User user = jdbcTemp.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(User.class));

	  // Kullanıcıyı döndürme işlemi
	  return user;
	}

	
 
	@PostMapping("/newuserFormjson")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
		
	  // Veritabanına ekleme işlemi  -- INSERT INTO usertable (id, name, birthDate) VALUES (1, 'Ali', '1990-01-01');
	  String sql = "INSERT INTO usertable(id, name, birth_date) VALUES (?, ?, ?)";
	  // Java.util.Date'i java.sql.Date'e çevirme
 	  Date sqlBirthDate = new Date(user.getBirthDate().getTime());
 	  jdbcTemp.update(sql, user.getId() ,user.getName(), sqlBirthDate);

	  
 	  return ResponseEntity.ok(user.toString() + "  was added to DB :)) ");
	}
	
	
	
	  @PostMapping("/newuserFormdata")
	  public String addUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String birthDate) throws java.text.ParseException { // Veritabanına ekleme işlemi 
		  
		  String sql = "INSERT INTO usertable (id, name, birth_date) VALUES (?, ?, ?)"; 
	  
		  Date parsedBirthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);  //1989-04-03  formdata
	 
		  jdbcTemp.update(sql, id, name, parsedBirthDate); 

		  return  name + " and " + birthDate +  "  was added to DB :)) " ;
	  
	  }
	  
	  
	  @PatchMapping("/userdb/{id}")
	  public ResponseEntity<String> updateUserName(@PathVariable Integer id, @RequestParam String name) {
	    // Veritabanından ilgili kullanıcıyı bulma işlemi
	    String sql = "SELECT * FROM usertable WHERE id = ?";
	    User user = jdbcTemp.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(User.class));

	    // Kullanıcının adını değiştirme işlemi
	    user.setName(name);
	    sql = "UPDATE usertable SET name = ? WHERE id = ?";
	    jdbcTemp.update(sql, user.getName(), user.getId());

	    return ResponseEntity.ok(user.toString() + " was updated in DB :)) ");
	  }
	  
	  
	  @DeleteMapping("/userdb/{id}")
	  public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
	    // Veritabanından ilgili kullanıcıyı bulma işlemi
	    String sql = "SELECT * FROM usertable WHERE id = ?";
	    User user = jdbcTemp.queryForObject(sql, new Object[]{id}, BeanPropertyRowMapper.newInstance(User.class));

	    // Kullanıcıyı silme işlemi
	    sql = "DELETE FROM usertable WHERE id = ?";
	    jdbcTemp.update(sql, user.getId());

	    return ResponseEntity.ok(user.toString() + " was deleted from DB :(( ");
	  }


	  
	  
	
}