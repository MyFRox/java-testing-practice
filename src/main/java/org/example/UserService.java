package org.example;

public class UserService {
    public boolean isCanLogin(Status status ){

        return status == Status.ACTIVE;
    }
}
