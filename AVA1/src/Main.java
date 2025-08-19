import java.util.Scanner;

public class Main {
    private static usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Criando usuários
        usuario admin = new usuario("admin@empresa.com", "123", usuario.Perfil.ADMINISTRADOR);
        usuario editor = new usuario("editor@empresa.com", "123", usuario.Perfil.EDITOR);
        usuario leitor = new usuario("leitor@empresa.com", "123", usuario.Perfil.LEITOR);
        
        System.out.println("=== SISTEMA DE CONTROLE DE DOCUMENTOS ===");
        
        // Login
        if (fazerLogin(admin, editor, leitor)) {
            menuPrincipal();
        }
        
        scanner.close();
    }
    
    private static boolean fazerLogin(usuario admin, usuario editor, usuario leitor) {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        if (email.equals(admin.getEmail()) && senha.equals(admin.getSenha())) {
            usuarioLogado = admin;
        } else if (email.equals(editor.getEmail()) && senha.equals(editor.getSenha())) {
            usuarioLogado = editor;
        } else if (email.equals(leitor.getEmail()) && senha.equals(leitor.getSenha())) {
            usuarioLogado = leitor;
        }
        
        if (usuarioLogado != null) {
            System.out.println("✅ Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + usuarioLogado.getPerfil());
            return true;
        } else {
            System.out.println("❌ Email ou senha incorretos!");
            return false;
        }
    }
    
    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Listar documentos");
            System.out.println("2. Ler documento");
            if (usuarioLogado.podeEditar()) {
                System.out.println("3. Editar documento");
            }
            if (usuarioLogado.podeAdministrar()) {
                System.out.println("4. Deletar documento");
            }
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    GerenciadorDocumentos.listarDocumentos(usuarioLogado);
                    break;
                case 2:
                    lerDocumento();
                    break;
                case 3:
                    if (usuarioLogado.podeEditar()) {
                        editarDocumento();
                    } else {
                        System.out.println("❌ Você não tem permissão para editar!");
                    }
                    break;
                case 4:
                    if (usuarioLogado.podeAdministrar()) {
                        deletarDocumento();
                    } else {
                        System.out.println("❌ Você não tem permissão para deletar!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void lerDocumento() {
        System.out.print("Digite o número do documento: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        GerenciadorDocumentos.lerDocumento(usuarioLogado, numero);
    }
    
    private static void editarDocumento() {
        System.out.print("Digite o número do documento para editar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        GerenciadorDocumentos.editarDocumento(usuarioLogado, numero, scanner);
    }
    
    private static void deletarDocumento() {
        System.out.print("Digite o número do documento para deletar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        GerenciadorDocumentos.deletarDocumento(usuarioLogado, numero);
    }
}