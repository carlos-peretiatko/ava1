public class controleAcesso {
    
    public static boolean validarAcesso(usuario user, String acao) {
        if (user == null) {
            System.out.println("ACESSO NEGADO: Usuário não autenticado");
            return false;
        }
        
        switch (acao.toUpperCase()) {
            case "LER":
                if (user.podeLer()) {
                    System.out.println("ACESSO PERMITIDO: " + user.getEmail() + " pode LER documentos");
                    return true;
                } else {
                    System.out.println("ACESSO NEGADO: " + user.getEmail() + " não pode LER documentos");
                    return false;
                }
                
            case "EDITAR":
                if (user.podeEditar()) {
                    System.out.println("ACESSO PERMITIDO: " + user.getEmail() + " pode EDITAR documentos");
                    return true;
                } else {
                    System.out.println("ACESSO NEGADO: " + user.getEmail() + " não pode EDITAR documentos");
                    return false;
                }
                
            case "ADMINISTRAR":
                if (user.podeAdministrar()) {
                    System.out.println("ACESSO PERMITIDO: " + user.getEmail() + " pode DELETAR o sistema");
                    return true;
                } else {
                    System.out.println("ACESSO NEGADO: " + user.getEmail() + " não pode DELETAR o sistema");
                    return false;
                }
                
            default:
                System.out.println("ACESSO NEGADO: Ação inválida - " + acao);
                return false;
        }
    }
    
    public static void exibirPermissoes(usuario user) {
        System.out.println("\n=== PERMISSÕES DO USUÁRIO ===");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Perfil: " + user.getPerfil());
        System.out.println("Pode Ler: " + (user.podeLer() ? "SIM" : "NÃO"));
        System.out.println("Pode Editar: " + (user.podeEditar() ? "SIM" : "NÃO"));
        System.out.println("Pode Deletar: " + (user.podeAdministrar() ? "SIM" : "NÃO"));
        System.out.println("=============================\n");
    }
}