public class Documento {
    private String titulo;
    private String conteudo;
    private String nivelAcesso; // "PUBLICO", "RESTRITO", "CONFIDENCIAL"
    
    public Documento(String titulo, String conteudo, String nivelAcesso) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.nivelAcesso = nivelAcesso;
    }
    
    public String getTitulo() { return titulo; }
    public String getConteudo() { return conteudo; }
    public String getNivelAcesso() { return nivelAcesso; }
    
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
}