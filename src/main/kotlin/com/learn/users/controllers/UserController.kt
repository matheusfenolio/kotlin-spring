package com.learn.users.controllers

import com.learn.users.entity.KotlinUser
import com.learn.users.model.request.KotlinUserRequest
import com.learn.users.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {
    @GetMapping()
    fun getAllUsers() : List<KotlinUser> = userService.fetchAllUsers()

    @GetMapping("/name")
    fun getAllUsersName() : List<String> = userService.getAllUsersName()

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: String) : KotlinUser = userService.fetchUserById(id)

    @PostMapping()
    fun saveUser(@RequestBody userRequest: KotlinUserRequest) : KotlinUser = userService.createNewUser(KotlinUser("", userRequest.name))

    @PutMapping("/{id}")
    fun updateUser(@RequestBody userRequest: KotlinUserRequest, @PathVariable("id") id: String) : KotlinUser = userService.updateUser(KotlinUser(id, userRequest.name))

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: String) : Unit = userService.deleteUser(id)
}