package com.learn.users.service.controllers

import com.learn.users.controllers.UserController
import com.learn.users.entity.KotlinUser
import com.learn.users.model.request.KotlinUserRequest
import com.learn.users.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class UserControllerTest {
    @Mock
    lateinit var userService: UserService

    @InjectMocks
    lateinit var userController: UserController

    @Test
    @DisplayName("Test getAllUsers endpoint")
    fun testGetAllUsersEndpoint(){
        val users = getListOfUsers()
        Mockito.`when`(userService.fetchAllUsers()).thenReturn(users)

        val response = userController.getAllUsers()

        assertFalse(response.isEmpty())
        assertEquals(users, response)
    }

    @Test
    @DisplayName("Test getAllUsersName endpoint")
    fun testGetAllUsersNameEndpoint(){
        val users = getListOfUsers().map { KotlinUser -> KotlinUser.name }

        Mockito.`when`(userService.getAllUsersName()).thenReturn(users)

        val response = userController.getAllUsersName()

        assertFalse(response.isEmpty())
        assertEquals(users, response)
    }

    @Test
    @DisplayName("Test getUserById endpoint")
    fun testGetUserByIdEndpoint(){
        Mockito.`when`(userService.fetchUserById("1")).thenReturn(getSingleUser())

        val response = userController.getUser("1")

        assertEquals(getSingleUser(), response)
    }

    @Test
    @DisplayName("Test saveUser endpoint")
    fun testSaveUserEndpoint(){
        val user = KotlinUser(id = "", name = "One")

        val userRequest = KotlinUserRequest(name = "One")

        Mockito.`when`(userService.createNewUser(user)).thenReturn(user)

        val response = userController.saveUser(userRequest)

        assertEquals(user.name, response.name)
    }

    @Test
    @DisplayName("Test updateUser endpoint")
    fun testUpdateUserEndpoint(){
        val user = getSingleUser()
        val userRequest = KotlinUserRequest(name = "One")

        Mockito.`when`(userService.updateUser(user)).thenReturn(user)

        val response = userController.updateUser(userRequest, "1")

        assertEquals(getSingleUser(), response)
    }

    @Test
    @DisplayName("Test deleteUser endpoint")
    fun testDeleteUserEndpoint(){

        Mockito.doNothing().`when`(userService).deleteUser("1")

        userController.deleteUser("1")

        Mockito.verify(userService, Mockito.times(1)).deleteUser("1")
    }

    private fun getSingleUser(): KotlinUser {
        return KotlinUser(name = "One", id = "1")
    }

    private fun getListOfUsers(): List<KotlinUser> {
        val userOne = KotlinUser(name = "One", id = "1")
        val userTwo = KotlinUser(name = "Two", id = "2")

        return listOf(userOne, userTwo)
    }
}