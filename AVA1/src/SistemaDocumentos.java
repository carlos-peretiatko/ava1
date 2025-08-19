public class SistemaDocumentos {
    
    public static void main(String[] args) {
        // Criando usuários com diferentes perfis
        usuario admin = new usuario("admin@empresa.com", "123456", usuario.Perfil.ADMINISTRADOR);
        usuario editor = new usuario("editor@empresa.com", "123456", usuario.Perfil.EDITOR);
        usuario leitor = new usuario("leitor@empresa.com", "123456", usuario.Perfil.LEITOR);
        
        System.out.println("=== SISTEMA DE CONTROLE DE DOCUMENTOS ===\n");
        
        // Testando permissões do ADMINISTRADOR
        System.out.println(">>> TESTANDO USUÁRIO ADMINISTRADOR <<<");
        controleAcesso.exibirPermissoes(admin);
        controleAcesso.validarAcesso(admin, "LER");
        controleAcesso.validarAcesso(admin, "EDITAR");
        controleAcesso.validarAcesso(admin, "DELETAR");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Testando permissões do EDITOR
        System.out.println(">>> TESTANDO USUÁRIO EDITOR <<<");
        controleAcesso.exibirPermissoes(editor);
        controleAcesso.validarAcesso(editor, "LER");
        controleAcesso.validarAcesso(editor, "EDITAR");
        controleAcesso.validarAcesso(editor, "DELETAR");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Testando permissões do LEITOR
        System.out.println(">>> TESTANDO USUÁRIO LEITOR <<<");
        controleAcesso.exibirPermissoes(leitor);
        controleAcesso.validarAcesso(leitor, "LER");
        controleAcesso.validarAcesso(leitor, "EDITAR");
        controleAcesso.validarAcesso(leitor, "DELETAR");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Simulando tentativas de acesso inválido
        System.out.println(">>> SIMULANDO ACESSOS INVÁLIDOS <<<");
        controleAcesso.validarAcesso(null, "LER"); // Usuário não autenticado
        controleAcesso.validarAcesso(leitor, "DELETAR"); // Ação inválida
        
        System.out.println("\n=== FIM DOS TESTES ===");
    }
}