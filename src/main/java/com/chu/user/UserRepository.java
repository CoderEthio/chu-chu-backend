package com.chu.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserModel,Long> {
    UserModel findByUsername(String username);

    Page<UserModel> findByUsernameNot(String username, Pageable page);

}
