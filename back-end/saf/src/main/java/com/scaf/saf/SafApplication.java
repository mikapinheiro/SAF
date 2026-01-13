package com.scaf.saf;

import com.scaf.saf.entities.falhas.Plataforma; 
import com.scaf.saf.entities.usuario.SetorAtuacao;
import com.scaf.saf.entities.usuario.TipoUsuario;
import com.scaf.saf.entities.usuario.Usuario;
import com.scaf.saf.repositories.usuario.UsuarioRepository;
import com.scaf.saf.service.UsuarioService;
import java.util.Optional; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.security.core.userdetails.UserDetails; // Removido, não é necessário aqui.

@SpringBootApplication
public class SafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafApplication.class, args);
	}

    @Bean
    CommandLineRunner init(
            UsuarioRepository usuarioRepository, 
            UsuarioService usuarioService
    ) {
        return args -> {
             
            String adminRE = "12345678"; 
            String adminSenha = "admin123";
            String adminEmail = "admin@petrobras.com.br";

            // Linha 36 corrigida para usar o método do Repositório (findByMatricula)
            Optional<Usuario> adminExistente = usuarioRepository.findByMatricula(adminRE);
             
            if (adminExistente.isEmpty()) { 
                System.out.println("!!! CRIANDO USUARIO ADMINISTRADOR PADRAO (RE: 12345678) !!!");
                 
                // Instancia as dependências com IDs válidos do banco de dados
                TipoUsuario tipoAdmin = new TipoUsuario();
                tipoAdmin.setId(3L); // ID 3 = Administrador
                
                SetorAtuacao setorAdmin = new SetorAtuacao();
                setorAdmin.setId(1L); 
                
                Plataforma plataformaAdmin = new Plataforma();
                plataformaAdmin.setId(1L); // ID 1 = Plataforma P-66
                 
                Usuario admin = new Usuario();
                admin.setNome("Administrador Padrao");
                
                // --- CORREÇÃO NA LINHA 49: Usando o setter correto (setRegistroEmpregado) ---
                // Se o campo na sua Entidade é 'registroEmpregado', este é o método correto.
                admin.setRegistroEmpregado(adminRE);
                
                admin.setSenhaTemporaria(adminSenha); 
                admin.setEmailCoorporativo(adminEmail);
                
                // Atribuição de objetos com apenas o ID. 
                admin.setTipoUsuario(tipoAdmin);
                admin.setSetorAtuacao(setorAdmin);
                admin.setPlataforma(plataformaAdmin); 
                 
                usuarioService.save(admin);
                 
                System.out.println("!!! ADMINISTRADOR CRIADO COM SUCESSO !!!");
            } else {
                System.out.println("Usuario Administrador (RE: 12345678) ja existe. Pulando criacao.");
            }
        };
    }
}