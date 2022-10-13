// package data;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;

// import core.Movie;
// import core.User;

// public class UserRegisterTest {
//     User user1;
//     User user2;
//     User user3;
//     Movie movie; 
//     UserRegister register;

//     @BeforeEach
//     public void setUp(){
//         this.user1 = new User("ellica", "ellica123");
//         this.user2 = new User("rebekka", "rebekka123");
//         this.user3 = new User("sofie", "sofie123"); 
//         this.movie = new Movie("Snow", "fantasy");

//         this.register = new UserRegister();

//     }

//     @DisplayName("Testing to register a new user")
//     @Test
//     public void testRegisterNewUser(){
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.registerNewUser(user1);
//         }, "User haven't rated a movie");
//         user1.rateMovie(movie, 1);
//         register.registerNewUser(user1);
//         //Testing if user is registered 
//         assertTrue(user1.equals(register.getUser("ellica")), "Ellica should be found in register and be equal to user1.");

//         //Testing IllegalArgumentException if user already exists
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.registerNewUser(user1);
//         }, "User already in register");
//     }

//     @DisplayName("Testing to get all users")
//     @Test
//     public void testGetUsers() {
//         user1.rateMovie(movie, 4);
//         user2.rateMovie(movie, 3);
//         register.registerNewUser(user1);
//         register.registerNewUser(user2);
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.registerNewUser(user3);
//         }, "Cannot register user without rating a movie.");

//         List<User> testList = new ArrayList<>();
//         testList.add(user1);
//         testList.add(user2);
//         testList.add(user3);
//         //Tests that user3 hasn't been added to register yet.
//         assertFalse(register.getUsers().containsAll(testList), "User3 shouldn't have been added to register without rating a movie.");
//         assertTrue(testList.containsAll(register.getUsers()), "Remaining users should have been added to register.");
//         user3.rateMovie(movie, 5);
//         register.registerNewUser(user3);

//         assertTrue(register.getUsers().containsAll(testList), "More users than user1, user2 and user3 was in register.");
//         assertTrue(testList.containsAll(register.getUsers()), "Not all users were added to register.");
//     }

//     @DisplayName("Testing to get a user by username")
//     @Test
//     public void testGetUser(){
//         user1.rateMovie(movie, 4);
//         user2.rateMovie(movie, 1);
//         register.registerNewUser(user1);
//         register.registerNewUser(user2);

//         //Test thhat method returns given user from register
//         assertEquals("ellica", register.getUser("ellica").getUsername(), "User retrieved from file not equal to user written to file.");
        
//         //Test that method retursn null if the user is not registered
//         assertEquals(null, register.getUser("sofie"), "User should not exist in file.");
//     }

//     @DisplayName("Testing existing user")
//     @Test
//     public void testExistingUser(){
//         UserRegister register = new UserRegister();

//         //Test IllegalArgumentException if register is empty
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.existingUser("ellica", "ellica123");
//         }, "User register emtpy");

//         //Test illegalArgumentException if user is not registered
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.existingUser("ellica", "ellica123");;
//         }, "User not in register");
//         user1.rateMovie(movie, 5);
//         register.registerNewUser(user1);
//         //Test IllegalArgumentException if the password is invalid
//         Assertions.assertThrows(IllegalArgumentException.class, () -> {
//             register.existingUser("ellica", "ellica1234");;
//         }, "Invalid password");
//     }

//     @AfterEach
//     public void tearDown(){
//         UserHandler handler = new UserHandler();
//         try{
//             Files.delete(handler.getFile().toPath());
//         }
//         catch (IOException e){
//             throw new IllegalArgumentException();
//         }
//     }  
// }


