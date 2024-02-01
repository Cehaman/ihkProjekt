package org.customportal.ihkprojekt.repository;

import org.customportal.ihkprojekt.dto.UserDto;
import org.customportal.ihkprojekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


}
