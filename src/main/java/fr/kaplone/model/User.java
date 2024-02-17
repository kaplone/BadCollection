package fr.kaplone.model;


import java.io.Serializable;

public class User implements Serializable {
    private static Long idCount = 0L;
    private Long id;
    private String name;
    private Role role;

    public User(String name, Role role) {
        this.id = ++idCount;
        this.name = name;
        this.role = role;
    }

    public static Long getIdCount() {
        return idCount;
    }

    public static void setIdCount(Long idCount) {
        User.idCount = idCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
