package com.corporate.ipbase.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corporate.ipbase.domain.User;


	@Repository
	public interface UserRepository extends JpaRepository<User,Long> {


}
