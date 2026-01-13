package com.scaf.saf.service;



import com.scaf.saf.dto.AnaliseRequestDTO; 

import com.scaf.saf.entities.falhas.Falhas;

import com.scaf.saf.entities.falhas.HistoricoAcao; 

import com.scaf.saf.entities.falhas.StatusFalha;

import com.scaf.saf.entities.usuario.Usuario; 

import com.scaf.saf.repositories.falhas.FalhasRepository;

import com.scaf.saf.repositories.falhas.HistoricoAcaoRepository; 

import com.scaf.saf.repositories.falhas.StatusFalhaRepository;

import com.scaf.saf.repositories.usuario.UsuarioRepository; 



import java.time.LocalDate;

import java.time.LocalDateTime; 

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional; 



@Service

public class FalhasService {

    

    @Autowired

    private FalhasRepository falhasRepository;

    

    @Autowired

    private StatusFalhaRepository statusRepository;

    

    @Autowired

    private UsuarioRepository usuarioRepository;



    @Autowired

    private HistoricoAcaoRepository historicoAcaoRepository;



    // --- MÉTODO ATUALIZADO ---

    // Agora ele aceita um Optional para o filtro

    public List<Falhas> findAll(Optional<Long> statusId) {

        if (statusId.isPresent()) {

            // Se um ID de status foi passado (ex: ?status=1), filtra por ele

            return falhasRepository.findByStatusId(statusId.get());

        } else {

            // Se nenhum ID foi passado, retorna tudo

            return falhasRepository.findAll();

        }

    }



    public Optional<Falhas> findById(Long id) {

        return falhasRepository.findByIdCompleto(id);

    }

    

    @Transactional 

    public Falhas save(Falhas falha) {



        boolean isNew = (falha.getId() == null || falha.getId() == 0);



        if (isNew) { 

            falha.setData(LocalDate.now());

            StatusFalha statusEmAnalise = statusRepository.findById(1L)

                .orElseThrow(() -> new RuntimeException("Erro Crítico: Status 'Em Análise' (ID 1) não encontrado."));

            falha.setStatus(statusEmAnalise);

        }

        

        Falhas falhaSalva = falhasRepository.save(falha);



        if (isNew) {

            Usuario usuario = usuarioRepository.findById(falha.getUsuario().getId())

                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o histórico."));

            

            // --- INÍCIO DA LÓGICA DE HISTÓRICO ATUALIZADA ---

            

            // 1. Cria o histórico "Falha Registrada"

            HistoricoAcao historicoRegistro = new HistoricoAcao();

            historicoRegistro.setFalha(falhaSalva);

            historicoRegistro.setUsuario(usuario);

            historicoRegistro.setDescricaoAcao("Falha registrada no sistema por " + usuario.getNome());

            historicoRegistro.setDataHoraAcao(LocalDateTime.now());

            historicoAcaoRepository.save(historicoRegistro);

            

            // 2. Cria o histórico "Notificação Enviada" (simulação)

            HistoricoAcao historicoNotificacao = new HistoricoAcao();

            historicoNotificacao.setFalha(falhaSalva);

            historicoNotificacao.setUsuario(usuario); // Ou pode ser um usuário "SISTEMA"

            historicoNotificacao.setDescricaoAcao("Gerente de área notificado automaticamente");

            historicoNotificacao.setDataHoraAcao(LocalDateTime.now().plusSeconds(1)); // Adiciona 1 seg

            historicoAcaoRepository.save(historicoNotificacao);



            // --- FIM DA LÓGICA DE HISTÓRICO ATUALIZADA ---

        }



        return falhaSalva;

    }

    

    @Transactional

    public Falhas analisarFalha(Long falhaId, AnaliseRequestDTO analise) {

        

        // 1. Encontra as entidades necessárias

        Falhas falha = falhasRepository.findById(falhaId)

                .orElseThrow(() -> new RuntimeException("Falha não encontrada: " + falhaId));

        

        StatusFalha novoStatus = statusRepository.findById(analise.getNewStatusId())

                .orElseThrow(() -> new RuntimeException("Status não encontrado: " + analise.getNewStatusId()));

        

        Usuario usuarioAcao = usuarioRepository.findById(analise.getUsuarioAcaoId())

                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + analise.getUsuarioAcaoId()));



        // 2. Atualiza a falha

        falha.setStatus(novoStatus);

        Falhas falhaAtualizada = falhasRepository.save(falha);



        // 3. Cria o novo registro no Histórico

        HistoricoAcao historico = new HistoricoAcao();

        historico.setFalha(falhaAtualizada);

        historico.setUsuario(usuarioAcao);

        historico.setDescricaoAcao(analise.getDescricaoAcao()); 

        historico.setDataHoraAcao(LocalDateTime.now());

        

        historicoAcaoRepository.save(historico);



        return falhaAtualizada;

    }



    public void deleteById(Long id) {

        falhasRepository.deleteById(id);

    }

}