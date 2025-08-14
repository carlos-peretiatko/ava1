public class Usuario {
    //variaveis
    private String email = "";
    private String senha = "";
    private String permicao = "";

    //gets and setts 

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

    public String getPermicao() {
        return permicao;
    }

    public void setPermicao(String permicao) {
        this.permicao = permicao;
    }

    //toString
    @Override
    public String toString() {
        return "Usuario [email=" + email + ", senha=" + senha + "]";
    }
}
