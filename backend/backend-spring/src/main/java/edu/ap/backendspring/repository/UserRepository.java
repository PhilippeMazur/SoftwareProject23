package edu.ap.backendspring.repository;

import edu.ap.backendspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> deleteUserByEmail(@Param("email") String email);

}
