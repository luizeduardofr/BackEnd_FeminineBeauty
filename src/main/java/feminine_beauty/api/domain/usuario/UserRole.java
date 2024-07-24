package feminine_beauty.api.domain.usuario;

public enum UserRole {
    ADMIN("admin"),
    FUNC("funcionario"),
    USER("usuario");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
