package com.learn.users.service

import com.learn.users.entity.KotlinUser
import com.learn.users.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(private val userRepository: IUserRepository) {
    fun fetchAllUsers() : List<KotlinUser> = userRepository.findAll()
    fun fetchUserById(id: String) : KotlinUser = userRepository.getById(id)
    fun createNewUser(user: KotlinUser) : KotlinUser {
        user.id = UUID.randomUUID().toString()
        return userRepository.save(user)
    }
    fun updateUser(user: KotlinUser) : KotlinUser = userRepository.save(user)
    fun deleteUser(id: String) : Unit = userRepository.deleteById(id)

    fun getAllUsersName() : List<String> {
        val users = userRepository.findAll()
        return users.map { kotlinUser -> kotlinUser.name }
    }

}