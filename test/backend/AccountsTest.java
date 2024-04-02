package test.backend;

import backend.Accounts;
import backend.User;
import backend.DataProcessing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import backend.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountsTest {
    private Accounts account;
    ArrayList<User> mockUsers;

    @BeforeAll
    void setUp() {
        // Mock the DataProcessing to return a controlled list of users
        ArrayList<User> mockUsers = new ArrayList<>();
        mockUsers.add(new User("test@example.com"));
        mockUsers.add(new User("another@example.com"));
        account = new Accounts();
        mockUsers.setUserARrayList(mockUsers);
    }

    @Test
    boolean logIn() {
        account.login("test@example.com");
        assertEquals(account.user, "test@example.com");
    }

    @Test
    void logOut() {
    }

    @Test
    void save() {
    }

    @Test
    boolean create() {
    }

    @Test
    boolean exist() {
    }

    @Test
    void find() {
    }
}
