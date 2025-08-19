import java.util.ArrayList;
import java.util.List;

public class GerenciadorDocumentos {
    private static List<Documento> documentos = new ArrayList<>();
    
    static {
        // Documentos de exemplo
        documentos.add(new Documento("Manual do Usuário", "Este é o manual básico do sistema...", "PUBLICO"));
        documentos.add(new Documento("Relatório Mensal", "Dados financeiros do mês...", "RESTRITO"));
        documentos.add(new Documento("Plano Estratégico", "Informações confidenciais da empresa...", "CONFIDENCIAL"));
        documentos.add(new Documento("Política de Segurança", "Diretrizes de segurança da informação...", "RESTRITO"));
        documentos.add(new Documento("FAQ Sistema", "Perguntas frequentes sobre o sistema...", "PUBLICO"));
    }
    
    public static void listarDocumentos(usuario user) {
        System.out.println("=== DOCUMENTOS DISPONÍVEIS ===");
        
        for (int i = 0; i < documentos.size(); i++) {
            Documento doc = documentos.get(i);
            if (podeAcessarDocumento(user, doc)) {
                System.out.println((i + 1) + ". " + doc.getTitulo() + " [" + doc.getNivelAcesso() + "]");
            }
        }
    }
    
    public static void lerDocumento(usuario user, int indice) {
        if (indice < 1 || indice > documentos.size()) {
            System.out.println("❌ Documento não encontrado!");
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        
        if (!podeAcessarDocumento(user, doc)) {
            System.out.println("❌ ACESSO NEGADO: Você não tem permissão para ler este documento!");
            return;
        }
        
        System.out.println("\n📄 === " + doc.getTitulo() + " ===");
        System.out.println("Nível: " + doc.getNivelAcesso());
        System.out.println("Conteúdo: " + doc.getConteudo());
        System.out.println("=".repeat(50));
    }
    
    public static void editarDocumento(usuario user, int indice, java.util.Scanner scanner) {
        if (indice < 1 || indice > documentos.size()) {
            System.out.println("❌ Documento não encontrado!");
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        
        if (!podeEditarDocumento(user, doc)) {
            System.out.println("❌ ACESSO NEGADO: Você não tem permissão para editar este documento!");
            return;
        }
        
        System.out.println("📝 Editando: " + doc.getTitulo());
        System.out.println("Conteúdo atual: " + doc.getConteudo());
        System.out.print("Novo conteúdo: ");
        String novoConteudo = scanner.nextLine();
        
        doc.setConteudo(novoConteudo);
        System.out.println("✅ Documento editado com sucesso!");
    }
    
    public static void deletarDocumento(usuario user, int indice) {
        if (indice < 1 || indice > documentos.size()) {
            System.out.println("❌ Documento não encontrado!");
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        
        if (!user.podeAdministrar()) {
            System.out.println("❌ ACESSO NEGADO: Apenas administradores podem deletar documentos!");
            return;
        }
        
        documentos.remove(indice - 1);
        System.out.println("🗑️ Documento '" + doc.getTitulo() + "' deletado com sucesso!");
    }
    
    private static boolean podeEditarDocumento(usuario user, Documento doc) {
        if (!user.podeEditar()) return false;
        
        // Editores podem editar apenas documentos PUBLICO e RESTRITO
        // Administradores podem editar todos
        if (user.getPerfil() == usuario.Perfil.ADMINISTRADOR) {
            return true;
        }
        
        return !doc.getNivelAcesso().equals("CONFIDENCIAL");
    }
    
    private static boolean podeAcessarDocumento(usuario user, Documento doc) {
        if (user == null) return false;
        
        switch (doc.getNivelAcesso()) {
            case "PUBLICO":
                return true;
            case "RESTRITO":
                return user.getPerfil() == usuario.Perfil.EDITOR || 
                       user.getPerfil() == usuario.Perfil.ADMINISTRADOR;
            case "CONFIDENCIAL":
                return user.getPerfil() == usuario.Perfil.ADMINISTRADOR;
            default:
                return false;
        }
    }
}