package fr.kaplone.model;


import fr.kaplone.bad.BadGenericConstructor;

public class User {
    private static Long uidCount = 0L;
    private Long uid;
    private String name;
    private Role role;

    @BadGenericConstructor(cls = User.class, name = "User")
    public User(String name, Role role) {
        this.uid = ++uidCount;
        this.name = name;
        this.role = role;
    }
}
