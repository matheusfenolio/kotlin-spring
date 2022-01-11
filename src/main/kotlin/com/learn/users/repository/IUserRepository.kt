package com.learn.users.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.learn.users.entity.KotlinUser;

@Repository
interface IUserRepository : JpaRepository<KotlinUser, String> {
}