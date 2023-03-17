package com.th.repository;
import org.springframework.data.repository.CrudRepository;
import com.th.model.User;

public interface UserRepository extends CrudRepository<User,Integer>{

}
