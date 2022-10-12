package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import core.User;

public class UserRegisterTest {
    User user1 = new User("ellica", "ellica123");
    User user2 = new User("rebekka", "rebekka123");
    User user3 = new User("sofie", "sofie123");

    UserRegister register = new UserRegister();

    @Test
    public void testRegisterNewUser(){
        register.registerNewUser(user1);
        //Testing if user is registered 
        assertEquals(user1, register.getUser("ellica"));

        //Testing IllegalArgumentException if user already exists
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.registerNewUser(user1);
        }, "User already in register");
    }

    @Test
    public void testGetUsers() {
        UserRegister register = new UserRegister();
        register.registerNewUser(user1);
        register.registerNewUser(user2);
        register.registerNewUser(user3);

        List<User> testList = new ArrayList<>();
        testList.add(user1);
        testList.add(user2);
        testList.add(user3);

        //test that method returns all users from register
        assertEquals(testList, register.getUsers());
    }

    @Test
    public void testGetUser(){
        UserRegister register = new UserRegister();
        register.registerNewUser(user1);
        register.registerNewUser(user2);

        //Test thhat method returns given user from register
        assertEquals("ellica", register.getUser("ellica").getUsername());
        
        //Test that method retursn null if the user is not registered
        assertEquals(null, register.getUser("sofie"));
    }


    @Test
    public void testExistingUser(){
        UserRegister register = new UserRegister();

        //Test IllegalArgumentException if register is empty
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.existingUser("ellica", "ellica123");
        }, "User register emtpy");

        //Test illegalArgumentException if user is not registered
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.existingUser("ellica", "ellica123");;
        }, "User not in register");

        register.registerNewUser(user1);
        //Test IllegalArgumentException if the password is invalid
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.existingUser("ellica", "ellica1234");;
        }, "Invalid password");
    }


}


