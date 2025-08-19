public class usuario {
    public enum Perfil { //fixo 
        ADMINISTRADOR,
        EDITOR,
        LEITOR
    }

    private String email;
    private String senha;
    private Perfil perfil;

    public usuario(String email, String senha, Perfil perfil) {
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    // Métodos de verificação de permissões
    public boolean podeEditar() {
        return perfil == Perfil.ADMINISTRADOR || perfil == Perfil.EDITOR;
    }

    public boolean podeLer() {
        return true; // Todos podem ler
    }

    public boolean podeAdministrar() {
        return perfil == Perfil.ADMINISTRADOR;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", perfil=" + perfil + "]";
    }
}
