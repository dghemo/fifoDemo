package sk.fifodemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sk.fifodemo.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataAccessTest {
    private final DataAccess dataAccess = new DataAccess();

    @AfterEach
    public void tearDown() {
        dataAccess.deleteAll();
    }

    @Test
    public void testAdd() {
        User user = new User(1, "guid-1", "User 1");
        dataAccess.add(user);

        List<User> users = dataAccess.getAll();
        assertEquals(1, users.size());
        assertEquals(user.getUserId(), users.get(0).getUserId());
        assertEquals(user.getUserGuid(), users.get(0).getUserGuid());
        assertEquals(user.getUserName(), users.get(0).getUserName());
    }


    @Test
    public void testAddMultipleUsers() {
        User user1 = new User(1, "guid-1", "User 1");
        User user2 = new User(2, "guid-2", "User 2");
        dataAccess.add(user1);
        dataAccess.add(user2);

        List<User> users = dataAccess.getAll();
        assertEquals(2, users.size());
    }


    @Test
    public void testDelete() {
        User user = new User(1, "guid-1", "User 1");
        dataAccess.add(user);

        dataAccess.deleteAll();

        List<User> users = dataAccess.getAll();
        assertEquals(0, users.size());
    }
}
