package com.example.javajobjobproject.repository;

import com.example.javajobjobproject.domain.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,String> {
}
