package com.learn.users.service

import com.learn.users.entity.KotlinUser
import com.learn.users.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val userRepository: IUserRepository) {
    public fun fetchAllUsers() : List<KotlinUser> = userRepository.findAll()
    public fun fetchUserById(id: String) : KotlinUser = userRepository.getById(id)
    public fun createNewUser(user: KotlinUser) : KotlinUser = userRepository.save(user)
    public fun updateUser(user: KotlinUser) : KotlinUser = userRepository.save(user)
    public fun deleteUser(id: String) : Unit = userRepository.deleteById(id)
}