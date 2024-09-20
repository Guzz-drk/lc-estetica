package br.com.dev.guzz.lc_estetica.enums;

public enum UserRole {
    ADMIN("ADMIN"),
    STAFF("STAFF"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
