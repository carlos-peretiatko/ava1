import java.util.Scanner;

public class SistemaLeituraDocumentos {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Usuários disponíveis
        usuario admin = new usuario("admin@empresa.com", "123456", usuario.Perfil.ADMINISTRADOR);
        usuario editor = new usuario("editor@empresa.com", "123456", usuario.Perfil.EDITOR);
        usuario leitor = new usuario("leitor@empresa.com", "123456", usuario.Perfil.LEITOR);
        
        System.out.println("=== SISTEMA DE LEITURA DE DOCUMENTOS ===\n");
        
        // Simulação com diferentes usuários
        testarLeituraDocumentos(admin, "ADMINISTRADOR");
        testarLeituraDocumentos(editor, "EDITOR");
        testarLeituraDocumentos(leitor, "LEITOR");
        
        scanner.close();
    }
    
    private static void testarLeituraDocumentos(usuario user, String perfil) {
        System.out.println(">>> USUÁRIO: " + perfil + " <<<");
        System.out.println("Email: " + user.getEmail());
        
        // Listar documentos disponíveis
        GerenciadorDocumentos.listarDocumentos(user);
        
        // Tentar ler alguns documentos
        System.out.println("\n--- Tentando ler documento 1 ---");
        GerenciadorDocumentos.lerDocumento(user, 1);
        
        System.out.println("\n--- Tentando ler documento 3 (Confidencial) ---");
        GerenciadorDocumentos.lerDocumento(user, 3);
        
        System.out.println("\n" + "=".repeat(60) + "\n");
    }
}