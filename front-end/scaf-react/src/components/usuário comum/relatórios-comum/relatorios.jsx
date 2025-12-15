import React, { useState, useEffect, useCallback } from "react";

import { useNavigate } from "react-router-dom";

import Navbar from "../navbar-comum/navbar.jsx";

import "./relatorios.css";

// Se for usar um componente de gráfico real (ex: Chart.js ou Recharts),

// ele seria importado aqui. Por enquanto, será simulado com DIVs.

/*

 * MAPEAMENTO DAS CHAVES DO BACK-END PARA O FRONT-END

 * (Reutilizado da home.jsx)

 */

const statusMap = {
  totalFalhas: { label: "TOTAL DE FALHAS", color: "#212529" },

  emAnalise: { label: "EM ANÁLISE", color: "#FFC107" },

  aprovadas: { label: "APROVADAS", color: "#00875F" },

  recusadas: { label: "RECUSADAS", color: "#DC3545" },

  // rascunhos: { label: "RASCUNHOS", color: "#6c757d" }, // Não exibido no layout principal
};

// ENDPOINT PADRÃO DA SUA API

const API_BASE_URL = "http://localhost:8080/api";

// --- MOCKS DE DADOS ---

// Mock para simular dados do gráfico (últimos 6 meses)

const mockChartData = [
  { mes: "Abr", valor: 105 },

  { mes: "Mai", valor: 36 },

  { mes: "Jun", valor: 48 },

  { mes: "Jul", valor: 14 },

  { mes: "Ago", valor: 132 },

  { mes: "Set", valor: 93 },
];

// Mock para simular Relatórios Gerados

const mockRelatoriosDisponiveis = [
  { nome: "Relatório Mensal", data: "Nov, 2025", paginas: "31 páginas", id: 1 },

  {
    nome: "Análise Por Equipamento",
    data: "Maio, 2025",
    paginas: "23 páginas",
    id: 2,
  },

  {
    nome: "Falhas Por Ativo",
    data: "Julho, 2025",
    paginas: "18 páginas",
    id: 3,
  },

  {
    nome: "Indicadores de Performance",
    data: "Mai, 2025",
    paginas: "23 páginas",
    id: 4,
  },

  {
    nome: "Relatório Mensal",
    data: "Abril, 2025",
    paginas: "25 páginas",
    id: 5,
  },
];

const Relatorios = () => {
  const navigate = useNavigate();

  const [metrics, setMetrics] = useState(null);

  const [loading, setLoading] = useState(true);

  const [error, setError] = useState(null);

  const [periodo, setPeriodo] = useState("6 meses"); // Estado para o filtro do gráfico

  /*

   * BUSCAR DADOS DO DASHBOARD DO BACK-END

   * (Reutilizando a lógica da home.jsx)

   */

  useEffect(() => {
    const token = localStorage.getItem("userToken");

    if (!token) {
      navigate("/");

      return;
    }

    const fetchDashboardData = async () => {
      try {
        setLoading(true);

        const response = await fetch(`${API_BASE_URL}/dashboard/estatisticas`, {
          method: "GET",

          headers: {
            "Content-Type": "application/json",

            Authorization: `Bearer ${token}`,
          },
        });

        if (response.status === 401 || response.status === 403) {
          localStorage.removeItem("userToken");

          navigate("/");

          throw new Error("Sessão expirada. Faça login novamente.");
        }

        if (!response.ok) {
          throw new Error("Falha ao carregar estatísticas da API.");
        }

        const data = await response.json();

        const safeData = {
          totalFalhas: data.totalFalhas || 0,

          emAnalise: data.emAnalise || 0,

          aprovadas: data.aprovadas || 0,

          recusadas: data.recusadas || 0,

          rascunhos: data.rascunhos || 0, // Incluído, mas não será exibido nos cards
        };

        setMetrics(safeData);
      } catch (err) {
        setError(
          err.message ||
            "Erro ao conectar com o servidor. Verifique se o backend está rodando."
        );
      } finally {
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, [navigate]);

  /*

   * FUNÇÃO PARA RENDERIZAR OS CARDS DAS MÉTRICAS

   * (Reutilizando a lógica da home.jsx)

   */

  const renderStats = () => {
    if (!metrics) return null;

    // Define a ordem e as chaves a serem exibidas nos cards

    const displayedKeys = [
      "emAnalise",
      "aprovadas",
      "recusadas",
      "totalFalhas",
    ];

    return displayedKeys.map((key) => {
      const info = statusMap[key];

      if (!info) return null;

      const count = metrics[key] || 0;

      // Cor de borda superior personalizada para o TOTAL DE FALHAS (preto/cinza)

      const borderColor = key === "totalFalhas" ? "#212529" : info.color;

      const countColor = key === "totalFalhas" ? "#212529" : info.color;

      return (
        <div
          key={key}
          className="stat-card"
          style={{ borderTopColor: borderColor }}
        >
          <span className="stat-count" style={{ color: countColor }}>
            {count}
          </span>

          <span className="stat-label">{info.label}</span>
        </div>
      );
    });
  };

  /*

   * FUNÇÃO PARA RENDERIZAR O GRÁFICO (SIMULADO)

   */

  const renderChart = () => {
    // Encontra o valor máximo para dimensionar as barras

    const maxVal = Math.max(...mockChartData.map((d) => d.valor));

    return (
      <div className="chart-wrapper">
        <div className="chart-bar-container">
          {mockChartData.map((data, index) => (
            <div key={index} className="chart-bar-group">
              <div className="chart-bar-value">{data.valor}</div>

              <div
                className="chart-bar"
                style={{ height: `${(data.valor / maxVal) * 90}%` }} // Altura ajustada
              ></div>

              <div className="chart-bar-label">{data.mes}</div>
            </div>
          ))}
        </div>
      </div>
    );
  };

  /*

   * FUNÇÃO PARA RENDERIZAR OS RELATÓRIOS DISPONÍVEIS

   */

  const renderReports = () => (
    <div className="reports-list">
      {mockRelatoriosDisponiveis.map((report) => (
        <div key={report.id} className="report-item">
          <div className="report-details">
            <span className="report-name">{report.nome}</span>

            <span className="report-metadata">
              {report.data} • {report.paginas}
            </span>
          </div>

          <div className="report-actions">
            <button className="btn btn-view">Visualizar</button>

            <button className="btn btn-export">Exportar</button>
          </div>
        </div>
      ))}
    </div>
  );

  // TELAS DE LOADING E ERRO

  if (loading)
    return (
      <div className="relatorios-container">
        <Navbar />

        <main className="main-content">
          <div className="loading-state">Carregando dados das Métricas...</div>
        </main>
      </div>
    );

  if (error)
    return (
      <div className="relatorios-container">
        <Navbar />

        <main className="main-content">
          <div className="error-state">Erro: {error}</div>
        </main>
      </div>
    );

  // LAYOUT PRINCIPAL

  return (
    <div className="relatorios-container">
      <Navbar />

      <main className="main-content">
        <section className="header-section">
          <h1>Relatórios e Métricas</h1>

          <p>
            Visualize indicadores, gere relatórios e acompanhe o desempenho
            operacional das falhas.
          </p>
        </section>

        {/* Métrica Cards (Dashboard) */}

        <section className="stats-grid">{renderStats()}</section>

        {/* Falhas por Mês (Gráfico Simulado) */}

        <section className="chart-section">
          <div className="chart-header">
            <h2>Falhas por Mês</h2>

            <div className="period-filter">
              <select
                value={periodo}
                onChange={(e) => setPeriodo(e.target.value)}
              >
                <option value="12 meses">Últimos 12 meses</option>

                <option value="6 meses">Últimos 6 meses</option>

                <option value="3 meses">Últimos 3 meses</option>

                <option value="1 mês">Último mês</option>
              </select>
            </div>
          </div>

          {renderChart()}
        </section>

        {/* Relatórios Disponíveis */}

        <section className="reports-section">
          <h2>Relatórios Disponíveis</h2>

          {renderReports()}
        </section>
      </main>
    </div>
  );
};

export default Relatorios;
