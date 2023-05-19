package tqs.amanacu.estore;

import tqs.amanacu.estore.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest; 
import static org.junit.Assert.*;
 
@SpringBootTest
class User_UnitTest {

	@Test
    public void testCreateUser() {
        // Arrange
        String username = "John";
        String email = "john@example.com";
        String password = "password";

        // Act
        User user = new User(username, email, password);

        // Assert
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

}
