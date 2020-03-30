package br.com.erudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
	
	@Query("SELECT u FROM User WHERE u.userName =: userName")
	User findByUsername(@Param("userName") String userName);

}
