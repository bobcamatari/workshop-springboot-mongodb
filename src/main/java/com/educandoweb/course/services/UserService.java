package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.domain.User;
import com.educandoweb.course.dto.UserDTO;
import com.educandoweb.course.repository.UserRepository;
import com.educandoweb.course.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();		
		
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void deleteById (String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update (User obj, String id) {
		User user = findById(id);
		updateData(user, obj);
		return repo.save(user);
		
		
	}
	
	private void updateData(User user, User obj) {
		user.setEmail(obj.getEmail());
		user.setName(obj.getName());
		
	}

	public User formDTO (UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
	

}
