package fr.kaplone;

import fr.kaplone.bad.BadCollection;
import fr.kaplone.bad.BadGenericField;
import fr.kaplone.model.Role;
import fr.kaplone.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    @BadGenericField(cls = User.class, name = "User")
    static User user;

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        BadCollection<User> users = new BadCollection<>();
        BadCollection<String> usersRefs = new BadCollection<>();
        List<User> userList = new ArrayList<>();

        for (String name : names){

            user = new User(name, Role.FISHER);
            String ref = users.addByRef(user);
            usersRefs.add(ref);
            userList.add(user);
        }
    }
}