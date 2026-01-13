package com.scaf.saf.service;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.scaf.saf.entities.falhas.Falhas;
import com.scaf.saf.repositories.falhas.FalhasRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private FalhasRepository falhasRepository;

    public byte[] gerarRelatorioAnaliseFalha(Long falhaId) throws IOException {
        
        // 1. Buscar os dados completos da falha (usando findByIdCompleto para garantir que os dados lazy sejam carregados)
        Falhas falha = falhasRepository.findByIdCompleto(falhaId)
            .orElseThrow(() -> new RuntimeException("Falha não encontrada com o ID: " + falhaId));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        // 2. Inicializar o iText PDF
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {
            
            // Definir margens padrão
            document.setMargins(36, 36, 36, 36); 

            // ----------------------------------------------------
            // CABEÇALHO BÁSICO
            // ----------------------------------------------------
            document.add(new Paragraph("BR PETROBRAS")
                            .setFontSize(10)
                            .setBold()
                            .setTextAlignment(TextAlignment.LEFT)
                            .setBackgroundColor(new DeviceRgb(0, 102, 51))
                            .setPaddingLeft(5)
                            .setPaddingTop(2)
                            .setPaddingBottom(2)
                            .setFontColor(new DeviceRgb(255, 255, 255)));

            document.add(new Paragraph("Relatório de Análise Técnica (RTA)")
                            .setFontSize(10)
                            .setTextAlignment(TextAlignment.RIGHT));
            
            // TÍTULO DO RELATÓRIO
            document.add(new Paragraph(falha.getDescricao())
                            .setFontSize(14)
                            .setBold()
                            .setMarginTop(10));
            
            // ----------------------------------------------------
            // 1. DADOS DA SOLICITAÇÃO DA ANÁLISE
            // ----------------------------------------------------
            document.add(new Paragraph("1 DADOS DA SOLICITAÇÃO DA ANÁLISE")
                            .setFontSize(12)
                            .setBold()
                            .setMarginTop(15)
                            .setUnderline());
            
            Table dadosSolicitacao = new Table(UnitValue.createPercentArray(new float[]{1, 3}))
                                        .useAllAvailableWidth()
                                        .setBorder(new SolidBorder(1));

            // Linha 1: Data de recebimento
            dadosSolicitacao.addCell(createCellHeader("Data de recebimento:"));
            dadosSolicitacao.addCell(createCellData(falha.getData().toString())); 

            // Linha 2: Data de fim da análise
            dadosSolicitacao.addCell(createCellHeader("Data de fim da análise:"));
            dadosSolicitacao.addCell(createCellData("[PENDENTE: Data de Mudança de Status Final]")); 

            // Linha 3: Gerência solicitante
            dadosSolicitacao.addCell(createCellHeader("Gerência solicitante:"));
            dadosSolicitacao.addCell(createCellData(falha.getUsuario().getSetorAtuacao().getName())); 

            // Linha 4: Coordenador
            dadosSolicitacao.addCell(createCellHeader("Coordenador:"));
            dadosSolicitacao.addCell(createCellData(falha.getUsuario().getNome()));

            document.add(dadosSolicitacao);
            
            // ----------------------------------------------------
            // 2. DADOS DO EQUIPAMENTO (CONF. ISO 14224)
            // ----------------------------------------------------
            adicionarDadosEquipamento(document, falha);
            
            // ----------------------------------------------------
            // 3. DADOS DA FALHA
            // ----------------------------------------------------
            adicionarDadosFalha(document, falha);
            
            // ----------------------------------------------------
            // 4. DEFINIÇÃO DA FRONTEIRA (ISO 14224)
            // ----------------------------------------------------
            adicionarDefinicaoFronteira(document, falha);

            // ----------------------------------------------------
            // 5. LINHA DO TEMPO (MARCOS RELEVANTES)
            // ----------------------------------------------------
            adicionarLinhaDoTempo(document, falha);

            // ----------------------------------------------------
            // 6. DIAGRAMA/DESCRIÇÃO DO SISTEMA (OPCIONAL)
            // ----------------------------------------------------
            adicionarDiagramaESistema(document, falha);
            
            // ----------------------------------------------------
            // 7. FOTOS (OPCIONAL)
            // ----------------------------------------------------
            adicionarFotos(document, falha);
            
            // ----------------------------------------------------
            // 8. HISTÓRICO DE INTERVENÇÕES REGISTRADAS NO SAP
            // ----------------------------------------------------
            adicionarHistoricoIntervencoes(document, falha);
            
            // ----------------------------------------------------
            // 9. PLANOS DE MANUTENÇÃO RELEVANTES
            // ----------------------------------------------------
            adicionarPlanosManutencao(document, falha);

            // ----------------------------------------------------
            // 10. RESULTADOS DO MONITORAMENTO PREDITIVO
            // ----------------------------------------------------
            adicionarMonitoramentoPreditivo(document, falha);
            
            // ----------------------------------------------------
            // 11. RESULTADOS DO MONITORAMENTO DO PI SYSTEM
            // ----------------------------------------------------
            adicionarMonitoramentoPISystem(document, falha);
            
            // ----------------------------------------------------
            // 12. RESULTADO DO MONITORAMENTO PELO SYSTEM ONE
            // ----------------------------------------------------
            adicionarMonitoramentoSystemOne(document);

            // ----------------------------------------------------
            // 13. SUMÁRIO EXECUTIVO
            // ----------------------------------------------------
            adicionarSumarioExecutivo(document, falha);
            
            // ----------------------------------------------------
            // 14. ANÁLISE DA FALHA (Árvore de Falhas)
            // ----------------------------------------------------
            adicionarAnaliseFalha(document, falha);
            
            // ----------------------------------------------------
            // 15. RECOMENDAÇÕES
            // ----------------------------------------------------
            adicionarRecomendacoes(document, falha);

            // ----------------------------------------------------
            // 16. DIAGRAMA DE DECISÃO DA MCC
            // ----------------------------------------------------
            adicionarDiagramaDecisao(document);
            
            // ----------------------------------------------------
            // 17. LISTA DE VERIFICAÇÃO DA ANÁLISE DA CONFIABILIDADE
            // ----------------------------------------------------
            adicionarListaVerificacao(document);
            
        } 

        return baos.toByteArray();
    }

    /**
     * Constrói a Seção 2 do relatório com os dados do equipamento da Falha.
     */
    private void adicionarDadosEquipamento(Document document, Falhas falha) {
        document.add(new Paragraph("2 DADOS DO EQUIPAMENTO (CONF. ISO 14224)")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        // Tabela de 4 colunas: Rótulo, Valor, Rótulo, Valor
        Table tabelaEquipamento = new Table(UnitValue.createPercentArray(new float[]{1, 3, 1, 3}))
                                    .useAllAvailableWidth()
                                    .setMarginBottom(10)
                                    .setBorder(new SolidBorder(1));

        // Linha 1: Instalação e Plataforma
        tabelaEquipamento.addCell(createCellHeader("Instalação"));
        tabelaEquipamento.addCell(createCellData(falha.getEquipamento().getInstalacao().getNome())); 
        tabelaEquipamento.addCell(createCellHeader("Plataforma"));
        tabelaEquipamento.addCell(createCellData(falha.getPlataforma().getName()));
        
        // Linha 2: Sistema e TAG
        tabelaEquipamento.addCell(createCellHeader("Sistema"));
        tabelaEquipamento.addCell(createCellData(falha.getSistema().getNome()));
        tabelaEquipamento.addCell(createCellHeader("TAG"));
        tabelaEquipamento.addCell(createCellData(falha.getEquipamento().getTagEquipamento())); 

        // Linha 3: Fabricante e Classe do Equipamento
        tabelaEquipamento.addCell(createCellHeader("Fabricante"));
        tabelaEquipamento.addCell(createCellData(falha.getFabricante().getNome())); 
        tabelaEquipamento.addCell(createCellHeader("Classe Eq."));
        tabelaEquipamento.addCell(createCellData(falha.getClasseEquipamento().getNome())); 
        
        // Linha 4: Categoria Equipamento e Modelo
        tabelaEquipamento.addCell(createCellHeader("Categoria Eq."));
        tabelaEquipamento.addCell(createCellData(falha.getEquipamento().getCategoria().getName()));
        tabelaEquipamento.addCell(createCellHeader("Modelo"));
        
        tabelaEquipamento.addCell(createCellData(falha.getEquipamento().getModelo())); 

        document.add(tabelaEquipamento);
    }
    
    /**
     * Constrói a Seção 3 do relatório com os dados principais da Falha.
     */
    private void adicionarDadosFalha(Document document, Falhas falha) {
        document.add(new Paragraph("3 DADOS DA FALHA")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        // Tabela de 3 colunas: Rótulo, Valor, Fonte
        Table dadosFalha = new Table(UnitValue.createPercentArray(new float[]{1, 3, 1}))
                                    .useAllAvailableWidth()
                                    .setMarginBottom(10)
                                    .setBorder(new SolidBorder(1));

        // Linha 1: Títulos
        dadosFalha.addCell(createCellHeader("Item"));
        dadosFalha.addCell(createCellHeader("Descrição"));
        dadosFalha.addCell(createCellHeader("Fonte"));

        // Linha 2: Anomalia/Descrição
        dadosFalha.addCell(createCellData("1"));
        dadosFalha.addCell(createCellData(falha.getDescricao()));
        dadosFalha.addCell(createCellData("Registro SCAFF")); // Fonte

        // Linha 3: Data de início da falha
        dadosFalha.addCell(createCellData("2"));
        dadosFalha.addCell(createCellData(falha.getData().toString())); // Data de ocorrência
        dadosFalha.addCell(createCellData("ISO 14224"));

        // Linha 4: Data de fim da falha
        dadosFalha.addCell(createCellData("3"));
        dadosFalha.addCell(createCellData("[Data de Fim/Conserto]")); 
        dadosFalha.addCell(createCellData("ISO 14224"));

        // Linha 5: Impacto da Falha (Você terá que definir o campo de impacto, se houver)
        dadosFalha.addCell(createCellHeader("Impacto da Falha (1)"));
        dadosFalha.addCell(createCellData("[PENDENTE: Campo de Impacto]")); 
        dadosFalha.addCell(createCellData(""));

        // Linha de nota de rodapé
        document.add(new Paragraph("(1) Impacto na falha no equipamento, refere-se aos danos aos equipamentos.")
                        .setFontSize(8)
                        .setItalic()
                        .setMarginTop(5));

        document.add(dadosFalha);
    }
    
    /**
     * Constrói a Seção 4 do relatório (Definição da Fronteira).
     */
    private void adicionarDefinicaoFronteira(Document document, Falhas falha) {
        document.add(new Paragraph("4 DEFINIÇÃO DA FRONTEIRA (ISO 14224)")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        // NOTA: A representação do Diagrama de Blocos (Figura 1) seria feita com gráficos ou imagens.
        document.add(new Paragraph("A fronteira do equipamento sob análise é definida conforme a estrutura funcional da ISO 14224.")
                        .setFontSize(10)
                        .setMarginBottom(10));
        
        // Adicionar um placeholder de imagem para o diagrama
        document.add(new Paragraph("[Placeholder para Diagrama de Blocos Funcional - ISO 14224]")); 
    }

    /**
     * Constrói a Seção 5 do relatório (Linha do Tempo).
     */
    private void adicionarLinhaDoTempo(Document document, Falhas falha) {
        document.add(new Paragraph("5 LINHA DO TEMPO (MARCOS RELEVANTES)")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        // Tabela de Linha do Tempo: Data, Dias Acum., Evento
        Table linhaTempo = new Table(UnitValue.createPercentArray(new float[]{1, 1, 4}))
                                    .useAllAvailableWidth()
                                    .setMarginBottom(10)
                                    .setBorder(new SolidBorder(1));

        // Cabeçalho
        linhaTempo.addCell(createCellHeader("Data"));
        linhaTempo.addCell(createCellHeader("Dias Acum."));
        linhaTempo.addCell(createCellHeader("Evento"));
        
        // Linha 1: Evento Zero (Início da Operação / Última Revisão Principal)
        linhaTempo.addCell(createCellData(falha.getEquipamento().getDataInstalacao().toString())); // Data de Instalação
        linhaTempo.addCell(createCellData("0"));
        linhaTempo.addCell(createCellData("Marco Zero: Instalação ou Última Revisão Principal."));

        // Linha 2: Evento da Falha
        linhaTempo.addCell(createCellData(falha.getData().toString())); 
        linhaTempo.addCell(createCellData("[Cálculo Pendente]")); 
        linhaTempo.addCell(createCellData(falha.getDescricao() + " (Registro da Anomalia)."));
        
        document.add(linhaTempo);
    }
    
    /**
     * Constrói a Seção 6 do relatório (Diagrama/Descrição do Sistema).
     */
    private void adicionarDiagramaESistema(Document document, Falhas falha) {
        document.add(new Paragraph("6 DIAGRAMA/DESCRIÇÃO DO SISTEMA (OPCIONAL)")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        document.add(new Paragraph("[Placeholder para o Diagrama Detalhado do Sistema (FT-UT-12...) ]")
                        .setFontSize(10)
                        .setMarginBottom(10));
        
        // Descrição do Sistema
        document.add(new Paragraph("Descrição do Sistema: " + falha.getSistema().getDescricao())
                        .setFontSize(10)
                        .setItalic());
    }

    /**
     * Constrói a Seção 7 do relatório (Fotos).
     */
    private void adicionarFotos(Document document, Falhas falha) {
        document.add(new Paragraph("7 FOTOS (OPCIONAL)")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("[Placeholder para Figura 1: Foto do local de montagem]")
                        .setFontSize(10)
                        .setMarginBottom(5));
        document.add(new Paragraph("[Placeholder para Figura 2: Acoplamento com as lâminas quebradas]")
                        .setFontSize(10)
                        .setMarginBottom(10));
    }

    /**
     * Constrói a Seção 8 do relatório (Histórico de Intervenções SAP).
     */
    private void adicionarHistoricoIntervencoes(Document document, Falhas falha) {
        document.add(new Paragraph("8 HISTÓRICO DE INTERVENÇÕES REGISTRADAS NO SAP")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("Marcar em vermelho os eventos relevantes para a falha")
                        .setFontSize(9)
                        .setMarginBottom(5));

        // Tabela de 6 colunas (Ordem, Nota, Data/Texto, Área, Status Sistema, Status Usuário)
        Table historico = new Table(UnitValue.createPercentArray(new float[]{1.5f, 1, 3, 1.5f, 2, 2}))
                                    .useAllAvailableWidth()
                                    .setMarginBottom(10)
                                    .setBorder(new SolidBorder(1));

        // Cabeçalho
        historico.addCell(createCellHeader("Ordem"));
        historico.addCell(createCellHeader("Nota"));
        historico.addCell(createCellHeader("Data modif. / Texto breve"));
        historico.addCell(createCellHeader("Área operacion."));
        historico.addCell(createCellHeader("Status sistema"));
        historico.addCell(createCellHeader("Status usuário"));
        
        // Placeholder de Exemplo (Você preencherá com dados reais do banco)
        historico.addCell(createCellData("2028586818"));
        historico.addCell(createCellData("15040052"));
        historico.addCell(createCellData("24/01/2024 / Falha no acoplamento"));
        historico.addCell(createCellData("P70"));
        historico.addCell(createCellData("LIB IMPR CAPC..."));
        historico.addCell(createCellData("IMPD TRIA APLT"));

        document.add(historico);
    }

    /**
     * Constrói a Seção 9 do relatório (Planos de Manutenção Relevantes).
     */
    private void adicionarPlanosManutencao(Document document, Falhas falha) {
        document.add(new Paragraph("9 PLANOS DE MANUTENÇÃO RELEVANTES")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("Marcar em vermelho os eventos relevantes para a falha")
                        .setFontSize(9)
                        .setMarginBottom(5));

        // Tabela de 5 colunas (Plano, Item, Última ordem, Nº lista obj., Descrição)
        Table planos = new Table(UnitValue.createPercentArray(new float[]{1.5f, 1.5f, 2, 2, 3}))
                                    .useAllAvailableWidth()
                                    .setMarginBottom(10)
                                    .setBorder(new SolidBorder(1));

        // Cabeçalho
        planos.addCell(createCellHeader("Plano manut."));
        planos.addCell(createCellHeader("Item manut. Última ordem"));
        planos.addCell(createCellHeader("Nº lista obj."));
        planos.addCell(createCellHeader("Texto item man."));
        planos.addCell(createCellHeader("Descrição"));
        
        // Placeholder de Exemplo
        planos.addCell(createCellData("1094586"));
        planos.addCell(createCellData("1862940"));
        planos.addCell(createCellData("2027969790"));
        planos.addCell(createCellData("13.194.470"));
        planos.addCell(createCellData("CJ MOTOBOMBA B-12510010"));

        document.add(planos);
    }

    /**
     * Constrói a Seção 10 do relatório (Monitoramento Preditivo).
     */
    private void adicionarMonitoramentoPreditivo(Document document, Falhas falha) {
        document.add(new Paragraph("10 RESULTADOS DO MONITORAMENTO PREDITIVO")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        document.add(new Paragraph("Diagnóstico: [PENDENTE: Diagnóstico Pred.]") 
                        .setFontSize(10)
                        .setBold());
        
        document.add(new Paragraph("Recomendação: [PENDENTE: Recomendação Pred.]")
                        .setFontSize(10)
                        .setMarginBottom(10));

        // Placeholder para os gráficos de vibração e temperatura
        document.add(new Paragraph("[Placeholder para Gráfico 1: Tendência de Vibração]")
                        .setFontSize(9)
                        .setMarginBottom(5));
        document.add(new Paragraph("[Placeholder para Gráfico 2: Espectro de Vibração]")
                        .setFontSize(9)
                        .setMarginBottom(10));
    }

    /**
     * Constrói a Seção 11 do relatório (Monitoramento PI System).
     */
    private void adicionarMonitoramentoPISystem(Document document, Falhas falha) {
        document.add(new Paragraph("11 RESULTADOS DO MONITORAMENTO DO PI SYSTEM")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        document.add(new Paragraph("O monitoramento do PI System foi analisado, verificando o histórico de TRIPS e a contagem de horímetros.")
                        .setFontSize(10)
                        .setMarginBottom(10));
        
        // Placeholder para os gráficos do PI System
        document.add(new Paragraph("[Placeholder para Gráfico 3: Histórico de Trip (ON/OFF)]")
                        .setFontSize(9)
                        .setMarginBottom(5));
        document.add(new Paragraph("[Placeholder para Gráfico 4: Horímetros Acumulados]")
                        .setFontSize(9)
                        .setMarginBottom(10));
    }
    
    /**
     * Constrói a Seção 12 do relatório (System One).
     */
    private void adicionarMonitoramentoSystemOne(Document document) {
        document.add(new Paragraph("12 RESULTADO DO MONITORAMENTO PELO SYSTEM ONE")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        document.add(new Paragraph("Não foi possível acessar os dados do System One e não foi necessário para a análise da falha.")
                        .setFontSize(10)
                        .setMarginBottom(10));
    }

    /**
     * Constrói a Seção 13 do relatório (Sumário Executivo).
     */
    private void adicionarSumarioExecutivo(Document document, Falhas falha) {
        document.add(new Paragraph("13 SUMÁRIO EXECUTIVO")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("No dia " + falha.getData().toString() + ", a anomalia " + falha.getDescricao() + " foi registrada no equipamento " + falha.getEquipamento().getTagEquipamento() + ". A análise aponta para falha por fadiga devido a [Causa Principal], agravada por falhas no monitoramento preditivo.")
                        .setFontSize(10)
                        .setMarginBottom(10));
    }

    /**
     * Constrói a Seção 14 do relatório (Análise da Falha - Árvore).
     */
    private void adicionarAnaliseFalha(Document document, Falhas falha) {
        document.add(new Paragraph("14 ANÁLISE DA FALHA")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("[Placeholder para Diagrama de Árvore de Falhas / Lógica Causa-Raiz]")
                        .setFontSize(10)
                        .setMarginBottom(5));
        
        document.add(new Paragraph("Causa Raiz Principal: FADIGA DAS LAMINAS (devido a tempo de operação e desalinhamento).")
                        .setFontSize(10)
                        .setBold());
    }

    /**
     * Constrói a Seção 15 do relatório (Recomendações).
     */
    private void adicionarRecomendacoes(Document document, Falhas falha) {
        document.add(new Paragraph("15 RECOMENDAÇÕES")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());

        document.add(new Paragraph("Recomendação A1: Abrir GM para inserir o intertravamento da bomba por vibração elevada.")
                        .setFontSize(10));
        document.add(new Paragraph("Responsável: [PENDENTE] | Prazo: 45 dias.")
                        .setFontSize(9)
                        .setItalic()
                        .setMarginBottom(10));
    }

    /**
     * Constrói a Seção 16 do relatório (Diagrama de Decisão da MCC).
     */
    private void adicionarDiagramaDecisao(Document document) {
        document.add(new Paragraph("16 DIAGRAMA DE DECISÃO DA MCC")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("Aplicando o Diagrama de Decisão da MCC (Matriz de Criticidade de Confiabilidade), a tarefa recomendada é: Tarefa sob condição, inspeções Preditivas (vibração).")
                        .setFontSize(10)
                        .setMarginBottom(5));
        
        document.add(new Paragraph("[Placeholder para Diagrama de Decisão da MCC (Ações Padrão)]")
                        .setFontSize(9)
                        .setMarginBottom(10));
    }
    
    /**
     * Constrói a Seção 17 do relatório (Lista de Verificação da Confiabilidade).
     */
    private void adicionarListaVerificacao(Document document) {
        document.add(new Paragraph("17 LISTA DE VERIFICAÇÃO DA ANÁLISE DA CONFIABILIDADE INTEGRADA")
                        .setFontSize(12)
                        .setBold()
                        .setMarginTop(15)
                        .setUnderline());
        
        document.add(new Paragraph("O FMEA está adequando ao modelo de falha? Se sim, não fazer nada, em caso negativo, deverá ser aberta uma tarefa para a atualização do FMEA pelo EPIM.")
                        .setFontSize(10));
        document.add(new Paragraph("A falha está relacionada a qualidade/condição do fornecedor dos equipamentos/material ou serviços? Se sim, deverá ser planejada uma tarefa para tratar o problema.")
                        .setFontSize(10));
        document.add(new Paragraph("Os requisitos do equipamento atendem às necessidades do processo? Se não, deverá ser consultado a equipe de processos para avaliar uma possível Gestão de Mudança.")
                        .setFontSize(10));
        document.add(new Paragraph("Pode o monitoramento ajudar na redução da falha, incluso o PCA? Se sim, deve-se articular com o COI a abertura de uma tarefa para monitoramento.")
                        .setFontSize(10));
        document.add(new Paragraph("Pode existir alguma manutenção preventiva que reduza o risco de falha? Se sim e não existe, deve-se inserir uma tarefa para esta implementação.")
                        .setFontSize(10));
    }
    
    /**
     * Cria uma célula de cabeçalho (rótulo) para a tabela.
     */
    private com.itextpdf.layout.element.Cell createCellHeader(String text) {
        return new com.itextpdf.layout.element.Cell()
                    .add(new Paragraph(text).setFontSize(9).setBold())
                    .setBackgroundColor(new DeviceRgb(240, 240, 240))
                    .setBorder(new SolidBorder(new DeviceRgb(200, 200, 200), 0.5f))
                    .setPadding(5);
    }
    
    /**
     * Cria uma célula de dados para a tabela.
     */
    private com.itextpdf.layout.element.Cell createCellData(String text) {
        String content = (text != null && !text.isEmpty()) ? text : "[Não Informado]";
        return new com.itextpdf.layout.element.Cell()
                    .add(new Paragraph(content).setFontSize(10))
                    .setBorder(new SolidBorder(new DeviceRgb(200, 200, 200), 0.5f))
                    .setPadding(5);
    }
}