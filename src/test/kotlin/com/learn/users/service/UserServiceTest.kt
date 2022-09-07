package com.learn.users.service

import com.learn.users.entity.KotlinUser
import com.learn.users.repository.IUserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {
    @Mock
    lateinit var userRepository: IUserRepository

    @InjectMocks
    lateinit var userService: UserService

    @Test
    @DisplayName("Test mapping from to get user's name")
    fun testGetAllUsersName() {
        val userOne = KotlinUser(name = "One", id = "1")
        val userTwo = KotlinUser(name = "Two", id = "2")

        val users = listOf(userOne, userTwo)

        Mockito.`when`(userRepository.findAll()).thenReturn(users)
        val results = userService.getAllUsersName()

        assertFalse(results.isEmpty())
        assertTrue(results.contains("One"))
        assertTrue(results.contains("Two"))
    }

    @Test
    @DisplayName("Test fetch all users")
    fun testFetchAllUsers() {
        val userOne = KotlinUser(name = "One", id = "1")
        val userTwo = KotlinUser(name = "Two", id = "2")

        val users = listOf(userOne, userTwo)

        Mockito.`when`(userRepository.findAll()).thenReturn(users)
        val results = userService.fetchAllUsers()

        assertFalse(results.isEmpty())
        assertEquals(users, results)
    }

    @Test
    @DisplayName("Test fetch user by id")
    fun testFetchUserById() {
        val userOne = KotlinUser(name = "One", id = "1")

        Mockito.`when`(userRepository.getById("1")).thenReturn(userOne)
        val result = userService.fetchUserById("1")

        assertEquals(userOne, result)
        verify(userRepository, times(1)).getById("1")
    }

    @Test
    @DisplayName("Test create user")
    fun testCreateUser() {
        val userOne = KotlinUser(name = "One", id = "1")

        Mockito.`when`(userRepository.save(userOne)).thenReturn(userOne)
        val result = userService.createNewUser(userOne)

        assertEquals(userOne, result)
        verify(userRepository, times(1)).save(userOne)
    }

    @Test
    @DisplayName("Test update user")
    fun testUpdateUser() {
        val userOne = KotlinUser(name = "One", id = "1")

        Mockito.`when`(userRepository.save(userOne)).thenReturn(userOne)
        val result = userService.updateUser(userOne)

        assertEquals(userOne, result)
        verify(userRepository, times(1)).save(userOne)
    }

    @Test
    @DisplayName("Test delete user by id")
    fun testDeleteUserById() {
        Mockito.doNothing().`when`(userRepository).deleteById("1")
        userService.deleteUser("1")

        verify(userRepository, times(1)).deleteById("1")
    }
}