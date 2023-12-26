package com.userDetailService.UserDetailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.userDetailService.UserDetailService.entity.UserCredtional;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserCredtional,Integer>{

	Optional<UserCredtional> findByName(String username);

	
	

}
