import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../navbar-comum/navbar.jsx";
import "./consultaf.css";

// --- MOCKS ---
const mockFalhas = [
  {
    id: 1,
    nFalha: "001",
    rta: "RTA-2025-001",
    data: "28/08/2025",
    ativo: "P-51",
    tag: "P-001-B-002",
    tipoEquipamento: "Bomba Centrífuga",
    sistema: "Água de Resfriamento",
    status: "Em Análise",
  },
  {
    id: 2,
    nFalha: "002",
    rta: "RTA-2025-002",
    data: "27/08/2025",
    ativo: "REPAR",
    tag: "T-101-A-001",
    tipoEquipamento: "Trocador de Calor",
    sistema: "Aquecimento de Carga",
    status: "Aprovada",
  },
  {
    id: 3,
    nFalha: "003",
    rta: "RTA-2025-003",
    data: "26/08/2025",
    ativo: "P-70",
    tag: "C-002-G-001",
    tipoEquipamento: "Compressor",
    sistema: "Compressão de Gás",
    status: "Recusada",
  },
  {
    id: 4,
    nFalha: "004",
    rta: "RTA-2025-004",
    data: "25/08/2025",
    ativo: "REDUC",
    tag: "V-003-C-001",
    tipoEquipamento: "Válvula de Controle",
    sistema: "Controle de Processo",
    status: "Aprovada",
  },
  {
    id: 5,
    nFalha: "005",
    rta: "RTA-2025-005",
    data: "24/08/2025",
    ativo: "REDUC",
    tag: "V-003-C-001",
    tipoEquipamento: "Válvula de Controle",
    sistema: "Controle de Processo",
    status: "Rascunho",
  },
];

const mockStatus = [
  { nome: "TODOS", value: "TODOS" },
  { nome: "Em Análise", value: "Em Análise" },
  { nome: "Aprovada", value: "Aprovada" },
  { nome: "Recusada", value: "Recusada" },
  { nome: "Rascunho", value: "Rascunho" },
];

const ConsultaDeFalhas = () => {
  const navigate = useNavigate();

  const [falhasOriginais] = useState(mockFalhas);
  const [falhasFiltradas, setFalhasFiltradas] = useState(mockFalhas);
  const [termoBusca, setTermoBusca] = useState("");
  const [filtroStatus, setFiltroStatus] = useState("TODOS");
  const [showDropdown, setShowDropdown] = useState(false);

  // Mantendo o totalFalhas fixo conforme solicitado para o mock
  const totalFalhas = 191;

  /*
   * LÓGICA DE FILTRAGEM CORRETA
   */
  useEffect(() => {
    let results = falhasOriginais;

    // 1. FILTRO POR STATUS
    if (filtroStatus !== "TODOS") {
      results = results.filter((f) => f.status === filtroStatus);
    }

    // 2. FILTRO POR TERMO DE BUSCA
    if (termoBusca) {
      const termo = termoBusca.toLowerCase().trim();

      results = results.filter((f) => {
        // Tenta PARSEAR o termo de busca como número
        const termoNumerico = parseInt(termo, 10);

        // Verifica se o termo é um número válido para busca exata de 'Nº Falha'
        if (!isNaN(termoNumerico)) {
          // Garante que o número da falha também seja tratado como string para comparação
          const nFalhaString = String(f.nFalha).toLowerCase();

          // Lógica de CORRESPONDÊNCIA EXATA ROBUSTA:
          // Permite que '4' encontre '004', '1' encontre '001', etc.
          const termoBuscadoPadded = termo.padStart(3, "0"); // Ex: '4' -> '004'
          const nFalhaNumericoPuro = String(parseInt(nFalhaString, 10)); // Ex: '004' -> '4'

          if (
            // Correspondência exata no Nº Falha (ex: '004' === '004')
            nFalhaString === termoBuscadoPadded ||
            // Correspondência exata ignorando zeros à esquerda (ex: '4' === '4')
            String(termoNumerico) === nFalhaNumericoPuro
          ) {
            return true;
          }
        }

        // 3. Mantém a busca por inclusão (string parcial) nos campos RTA ou TAG
        // Isso só funcionará se RTA ou TAG contiverem a parte numérica que foi digitada.
        return (
          f.rta.toLowerCase().includes(termo) ||
          f.tag.toLowerCase().includes(termo)
        );
      });
    }

    // Simulação do Empty State (mantida conforme seu código)
    if (termoBusca === "999" && filtroStatus === "TODOS") {
      results = [];
    }

    setFalhasFiltradas(results);
  }, [termoBusca, filtroStatus, falhasOriginais]);

  return (
    <div className="consulta-falha-container">
      <Navbar />

      <main className="main-content">
        <div className="form-header-box consulta-header">
          <div>
            <h1>Consulta de Falhas</h1>
            <p>
              Visualize, filtre e gerencie todas as falhas operacionais
              registradas no sistema.
            </p>
          </div>
        </div>

        <div className="search-filter-section">
          <h3>Consultar Falha</h3>
          <form
            className="main-search-group"
            onSubmit={(e) => e.preventDefault()}
          >
            <div className="form-group">
              <label>Campo de Busca</label>
              <input
                type="text"
                placeholder="Digite o ID da Falha"
                value={termoBusca}
                onChange={(e) => {
                  const valor = e.target.value;
                  // Aceita apenas números
                  if (valor === "" || /^\d*$/.test(valor)) {
                    setTermoBusca(valor);
                  }
                }}
              />
            </div>

            <div className="filter-actions-search">
              <button className="btn btn-primary">Buscar</button>

              <button
                type="button"
                className="btn btn-grey"
                onClick={() => {
                  setTermoBusca("");
                  setFiltroStatus("TODOS");
                }}
              >
                Limpar
              </button>
            </div>
          </form>
        </div>

        <div className="results-container">
          <div className="results-header">
            <div className="results-title-wrapper">
              <h2>Resultados da Consulta</h2>
              <p className="results-info">
                Mostrando {falhasFiltradas.length} de {totalFalhas} falhas
              </p>
            </div>

            <div className="table-actions">
              <button className="btn btn-primary btn-action-table">
                Baixar Relatório
              </button>

              <div className="filter-dropdown">
                <button
                  type="button"
                  className="btn-filter btn-action-table"
                  onClick={() => setShowDropdown(!showDropdown)}
                >
                  Filtrar ({filtroStatus === "TODOS" ? "Status" : filtroStatus})
                  <span className="arrow-icon"></span>
                </button>

                {showDropdown && (
                  <div className="dropdown-menu-filtro">
                    {mockStatus.map((s) => (
                      <div
                        key={s.value}
                        className="dropdown-item-filtro"
                        onClick={() => {
                          setFiltroStatus(s.value);
                          setShowDropdown(false);
                        }}
                      >
                        {s.nome}
                      </div>
                    ))}
                  </div>
                )}
              </div>
            </div>
          </div>

          <div className="falha-table-wrapper">
            {falhasFiltradas.length > 0 ? (
              <table className="falha-table">
                <thead>
                  <tr>
                    <th>Nº Falha</th>
                    <th>RTA</th>
                    <th>Data</th>
                    <th>Ativo</th>
                    <th>TAG</th>
                    <th>Tipo de Equipamento</th>
                    <th>Sistema</th>
                    <th>Status</th>
                    <th>Ações</th>
                  </tr>
                </thead>

                <tbody>
                  {falhasFiltradas.map((f) => (
                    <tr key={f.id}>
                      <td>{f.nFalha}</td>
                      <td>{f.rta}</td>
                      <td>{f.data}</td>
                      <td>{f.ativo}</td>
                      <td>{f.tag}</td>
                      <td>{f.tipoEquipamento}</td>
                      <td>{f.sistema}</td>
                      <td>
                        <span
                          className={`status-cell status-${f.status
                            .toLowerCase()
                            .replace(" ", "-")}`}
                        >
                          {f.status}
                        </span>
                      </td>
                      <td>
                        <button
                          className="btn-ver"
                          onClick={() => navigate(`/visualizar-falha/${f.id}`)}
                        >
                          Ver
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            ) : (
              <table className="falha-table empty-table">
                <thead>
                  <tr>
                    <th>Nº Falha</th>
                    <th>RTA</th>
                    <th>Data</th>
                    <th>Ativo</th>
                    <th>TAG</th>
                    <th>Tipo de Equipamento</th>
                    <th>Sistema</th>
                    <th>Status</th>
                    <th>Ações</th>
                  </tr>
                </thead>
                <tbody>
                  <tr className="empty-state-message-row">
                    <td colSpan="9">Nenhuma Falha Encontrada</td>
                  </tr>
                </tbody>
              </table>
            )}
          </div>

          {falhasFiltradas.length === 0 && (
            <div className="empty-state-footer">
              <button
                className="btn-register-new"
                onClick={() => navigate("/registrar-falha")}
              >
                Registra Nova Falha
              </button>
            </div>
          )}
        </div>
      </main>
    </div>
  );
};

export default ConsultaDeFalhas;
