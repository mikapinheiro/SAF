package com.scaf.saf.entities.usuario;

import com.scaf.saf.entities.falhas.Plataforma; // Importar se Plataforma for de 'falhas'
import com.scaf.saf.entities.usuario.SetorAtuacao;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tb_cadastro_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_completo")
    private String nome;

    @Column(name = "matricula")
    private String registroEmpregado; // Este será o 'Username'

    @Column(name = "email")
    private String emailCoorporativo;

    @Column(name = "senha_hash")
    private String senhaTemporaria; // Esta será a 'Password'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario; // Este será o 'Authorities'

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo")
    private SetorAtuacao setorAtuacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade")
    private Plataforma plataforma; // Assumindo que você usa a Plataforma de 'falhas' ou tem a importação correta

    public Usuario() {
    }

    // --- MÉTODOS DO USERDETAILS (Obrigações do Spring Security) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mapeia o TipoUsuario para uma permissão (Role) do Spring Security.
        // Usa o campo 'name' da sua entidade TipoUsuario (ex: "ADMINISTRADOR").
        String roleName = "ROLE_COMUM"; // Padrão de segurança
        if (this.tipoUsuario != null && this.tipoUsuario.getName() != null) {
            roleName = "ROLE_" + this.tipoUsuario.getName().toUpperCase();
        }
        // O SimpleGrantedAuthority é a classe concreta que implementa GrantedAuthority.
        return List.of(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return this.senhaTemporaria; // Retorna o campo da senha
    }

    @Override
    public String getUsername() {
        return this.registroEmpregado; // Retorna o campo de login (RE/Matrícula)
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // --- GETTERS E SETTERS (Padrão) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRegistroEmpregado() { return registroEmpregado; }
    public void setRegistroEmpregado(String registroEmpregado) { this.registroEmpregado = registroEmpregado; }
    public String getEmailCoorporativo() { return emailCoorporativo; }
    public void setEmailCoorporativo(String emailCoorporativo) { this.emailCoorporativo = emailCoorporativo; }
    public String getSenhaTemporaria() { return senhaTemporaria; }
    public void setSenhaTemporaria(String senhaTemporaria) { this.senhaTemporaria = senhaTemporaria; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public SetorAtuacao getSetorAtuacao() { return setorAtuacao; }
    public void setSetorAtuacao(SetorAtuacao setorAtuacao) { this.setorAtuacao = setorAtuacao; }
    public Plataforma getPlataforma() { return plataforma; }
    public void setPlataforma(Plataforma plataforma) { this.plataforma = plataforma; }
}