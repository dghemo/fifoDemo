package sk.fifodemo.model;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents a user with an ID, GUID, and name.
 */
@Data
public class User {
    @NonNull
    private int userId;
    @NonNull
    private String userGuid;
    @NonNull
    private String userName;
}
