import java.util.Scanner;

public class Main {
    private static usuario usuarioLogado = null;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Criando usuários
        usuario admin = new usuario("admin@empresa.com", "123", usuario.Perfil.ADMINISTRADOR);
        usuario editor = new usuario("editor@empresa.com", "123", usuario.Perfil.EDITOR);
        usuario leitor = new usuario("leitor@empresa.com", "123", usuario.Perfil.LEITOR);
        
        limparTela();
        exibirCabecalho();
        
        // Login
        if (fazerLogin(admin, editor, leitor)) {
            menuPrincipal();
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("   Obrigado por usar o sistema! Até logo!");
        System.out.println("=".repeat(50));
        scanner.close();
    }
    
    private static boolean fazerLogin(usuario admin, usuario editor, usuario leitor) {
        System.out.println("\n┌" + "─".repeat(48) + "┐");
        System.out.println("│" + centralizarTexto("ACESSO AO SISTEMA", 48) + "│");
        System.out.println("└" + "─".repeat(48) + "┘");
        
        System.out.print("\n Email: ");
        String email = scanner.nextLine();
        System.out.print(" Senha: ");
        String senha = scanner.nextLine();
        
        if (email.equals(admin.getEmail()) && senha.equals(admin.getSenha())) {
            usuarioLogado = admin;
        } else if (email.equals(editor.getEmail()) && senha.equals(editor.getSenha())) {
            usuarioLogado = editor;
        } else if (email.equals(leitor.getEmail()) && senha.equals(leitor.getSenha())) {
            usuarioLogado = leitor;
        }
        
        if (usuarioLogado != null) {
            System.out.println("\n Login realizado com sucesso!");
            System.out.println(" Bem-vindo, " + usuarioLogado.getPerfil());
            pausar();
            return true;
        } else {
            System.out.println("\n Email ou senha incorretos!");
            pausar();
            return false;
        }
    }
    
    private static void menuPrincipal() {
        while (true) {
            limparTela();
            System.out.println("┌" + "─".repeat(48) + "┐");
            System.out.println("│" + centralizarTexto("MENU PRINCIPAL", 48) + "│");
            System.out.println("│" + centralizarTexto("Usuário: " + usuarioLogado.getPerfil(), 48) + "│");
            System.out.println("└" + "─".repeat(48) + "┘");
            
            System.out.println("\n [1] Listar documentos");
            System.out.println(" [2] Ler documento");
            if (usuarioLogado.podeEditar()) {
                System.out.println("  [3] Editar documento");
            }
            if (usuarioLogado.podeAdministrar()) {
                System.out.println("  [4] Deletar documento");
            }
            System.out.println(" [0] Sair");
            System.out.print("\n Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    limparTela();
                    GerenciadorDocumentos.listarDocumentos(usuarioLogado);
                    pausar();
                    break;
                case 2:
                    lerDocumento();
                    break;
                case 3:
                    if (usuarioLogado.podeEditar()) {
                        editarDocumento();
                    } else {
                        System.out.println("\n Você não tem permissão para editar!");
                        pausar();
                    }
                    break;
                case 4:
                    if (usuarioLogado.podeAdministrar()) {
                        deletarDocumento();
                    } else {
                        System.out.println("\n Você não tem permissão para deletar!");
                        pausar();
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n Opção inválida!");
                    pausar();
            }
        }
    }
    
    private static void lerDocumento() {
        limparTela();
        GerenciadorDocumentos.listarDocumentos(usuarioLogado);
        System.out.print("\n Digite o número do documento: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        GerenciadorDocumentos.lerDocumento(usuarioLogado, numero);
        pausar();
    }
    
    private static void editarDocumento() {
        limparTela();
        GerenciadorDocumentos.listarDocumentos(usuarioLogado);
        System.out.print("\n Digite o número do documento para editar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        GerenciadorDocumentos.editarDocumento(usuarioLogado, numero, scanner);
        pausar();
    }
    
    private static void deletarDocumento() {
        limparTela();
        GerenciadorDocumentos.listarDocumentos(usuarioLogado);
        System.out.print("\n Digite o número do documento para deletar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        GerenciadorDocumentos.deletarDocumento(usuarioLogado, numero);
        pausar();
    }
    
    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private static void exibirCabecalho() {
        System.out.println("╔" + "═".repeat(50) + "╗");
        System.out.println("║" + centralizarTexto("SISTEMA DE CONTROLE DE DOCUMENTOS", 50) + "║");
        System.out.println("║" + centralizarTexto("SecureData TI - v1.0", 50) + "║");
        System.out.println("╚" + "═".repeat(50) + "╝");
    }
    
    private static String centralizarTexto(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        String resultado = " ".repeat(espacos) + texto;
        while (resultado.length() < largura) {
            resultado += " ";
        }
        return resultado;
    }
    
    private static void pausar() {
        System.out.print("\n  Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}