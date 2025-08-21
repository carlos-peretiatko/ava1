import java.util.ArrayList;

public class GerenciadorDocumentos {
    private static ArrayList<Documento> documentos = new ArrayList<>();
    
    static {
        // Documentos de exemplo
        documentos.add(new Documento("Manual do Usuário", "Este é o manual básico do sistema...", "PUBLICO"));
        documentos.add(new Documento("Relatório Mensal", "Dados financeiros do mês...", "RESTRITO"));
        documentos.add(new Documento("Plano Estratégico", "Informações confidenciais da empresa...", "CONFIDENCIAL"));
        documentos.add(new Documento("Política de Segurança", "Diretrizes de segurança da informação...", "RESTRITO"));
        documentos.add(new Documento("FAQ Sistema", "Perguntas frequentes sobre o sistema...", "PUBLICO"));
    }
    
    public static void listarDocumentos(usuario user) {
        System.out.println("┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centralizarTexto("DOCUMENTOS DISPONÍVEIS", 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        
        System.out.println("\n Total no sistema: " + documentos.size() + " documentos");
        System.out.println("─".repeat(50));
        
        int contador = 1;
        for (int i = 0; i < documentos.size(); i++) {
            Documento doc = documentos.get(i);
            if (podeAcessarDocumento(user, doc)) {
                String icone = getIconeNivel(doc.getNivelAcesso());
                System.out.println(icone + " [" + (i + 1) + "] " + doc.getTitulo() + " (" + doc.getNivelAcesso() + ")");
                contador++;
            }
        }
        
        if (contador == 1) {
            System.out.println("  Nenhum documento disponível para seu perfil.");
        }
    }
    
    public static void lerDocumento(usuario user, int indice) {
        if (indice < 1 || indice > documentos.size()) {
            System.out.println(" Documento não encontrado!");
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        
        if (!podeAcessarDocumento(user, doc)) {
            System.out.println(" ACESSO NEGADO: Você não tem permissão para ler este documento!");
            return;
        }
        
        System.out.println("┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centralizarTexto(doc.getTitulo(), 48) + "│");
        System.out.println("│" + centralizarTexto("Nível: " + doc.getNivelAcesso(), 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        
        System.out.println("\n CONTEÚDO:");
        System.out.println("─".repeat(50));
        System.out.println(doc.getConteudo());
        System.out.println("─".repeat(50));
    }
    
    public static void editarDocumento(usuario user, int indice, java.util.Scanner scanner) {
        if (indice < 1 || indice > documentos.size()) {
            System.out.println(" Documento não encontrado!");
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        
        if (!podeEditarDocumento(user, doc)) {
            System.out.println(" ACESSO NEGADO: Você não tem permissão para editar este documento!");
            return;
        }
        
        System.out.println("┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centralizarTexto("EDITANDO DOCUMENTO", 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        
        System.out.println("\n Título: " + doc.getTitulo());
        System.out.println(" Conteúdo atual: " + doc.getConteudo());
        System.out.print("\n  Novo conteúdo: ");
        String novoConteudo = scanner.nextLine();
        
        doc.setConteudo(novoConteudo);
        System.out.println("\n Documento editado com sucesso!");
    }
    
    public static void deletarDocumento(usuario user, int indice) {
        if (!user.podeAdministrar()) {
            System.out.println(" ACESSO NEGADO: Apenas administradores podem deletar documentos!");
            return;
        }
        
        if (indice < 1 || indice > documentos.size()) {
            System.out.println(" Documento não encontrado! IDs válidos: 1 a " + documentos.size());
            return;
        }
        
        Documento doc = documentos.get(indice - 1);
        String titulo = doc.getTitulo();
        
        System.out.println("┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centralizarTexto("CONFIRMAÇÃO DE EXCLUSÃO", 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        
        System.out.println("\n  Documento: " + titulo);
        
        // Remove o documento do ArrayList
        documentos.remove(indice - 1);
        
        System.out.println("\n Documento deletado com sucesso!");
        System.out.println(" Documentos restantes: " + documentos.size());
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
    
    private static String getIconeNivel(String nivel) { //nao funciona 
        switch (nivel) {
            case "PUBLICO": return "🔓";
            case "RESTRITO": return "🔒";
            case "CONFIDENCIAL": return "🔐";
            default: return "📄";
        }
    }
    
    private static String centralizarTexto(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        String resultado = " ".repeat(espacos) + texto;
        while (resultado.length() < largura) {
            resultado += " ";
        }
        return resultado;
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