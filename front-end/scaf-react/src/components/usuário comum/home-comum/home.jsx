import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./home.css";

import Navbar from "../navbar-comum/navbar.jsx";
import addIcon from "../../../assets/images/add.svg";

/*
 * MAPEAMENTO DAS CHAVES DO BACK-END PARA O FRONT-END
 * O layout NÃO é alterado — apenas associamos as chaves da API às labels do dashboard.
 */
const statusMap = {
  totalFalhas: { label: "TOTAL DE FALHAS", color: "#212529" },
  emAnalise: { label: "EM ANÁLISE", color: "#FFC107" },
  aprovadas: { label: "APROVADAS", color: "#00875F" },
  recusadas: { label: "RECUSADAS", color: "#DC3545" },
  rascunhos: { label: "RASCUNHOS", color: "#6c757d" },
};

const Home = () => {
  const navigate = useNavigate();

  const [metrics, setMetrics] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // ENDPOINT PADRÃO DA SUA API
  const API_BASE_URL = "http://localhost:8080/api";

  /*
   * BUSCAR DADOS DO DASHBOARD DO BACK-END
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
          // Mantém o tratamento para status 500 (Erro SQL no Back-end)
          throw new Error("Falha ao carregar estatísticas da API.");
        }

        const data = await response.json();

        // Garantir que não venha nulo
        const safeData = {
          totalFalhas: data.totalFalhas || 0,
          emAnalise: data.emAnalise || 0,
          aprovadas: data.aprovadas || 0,
          recusadas: data.recusadas || 0,
          rascunhos: data.rascunhos || 0,
        };

        setMetrics(safeData);
      } catch (err) {
        setError(
          err.message ||
            "Erro ao conectar com o servidor. Verifique se o Backend está rodando."
        );
      } finally {
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, [navigate]);

  /*
   * FUNÇÃO PARA RENDERIZAR OS CARDS DAS MÉTRICAS
   * AGORA CONTÉM AS TELAS DE LOADING E ERRO
   */
  const renderStats = () => {
    if (loading) {
      // Retorna uma mensagem de loading no lugar dos cards
      return (
        <div className="loading-state">Carregando dados do Dashboard...</div>
      );
    }

    if (error) {
      // Retorna o erro no lugar dos cards
      return <div className="error-state">Erro: {error}</div>;
    }

    if (!metrics) return null;

    // Lógica original para renderizar os cards
    const displayedMetrics = Object.keys(metrics).filter(
      (key) => key !== "rascunhos"
    );

    return displayedMetrics.map((key, index) => {
      const info = statusMap[key];
      if (!info) return null;

      return (
        <div
          key={index}
          className="stat-card"
          style={{ borderTopColor: info.color }}
        >
          <span className="stat-count" style={{ color: info.color }}>
            {metrics[key]}
          </span>
          <span className="stat-label">{info.label}</span>
        </div>
      );
    });
  };

  /*
   * TELAS E LAYOUT PRINCIPAL (NÃO ALTERADO)
   */
  return (
    <div className="home-container">
      <Navbar />

      <main className="main-content">
        <section className="welcome-banner">
          <h1>Bem-vindo ao Sistema de Gestão de Falhas</h1>
          <p>
            Monitore, registre e gerencie falhas operacionais com mais
            eficiência e organização.
          </p>
        </section>

        {/* Exibe as métricas dentro de uma grid */}
        <section className="stats-grid">{renderStats()}</section>

        <section className="actions-grid">
          <div className="action-card">
            <h3>Registrar Nova Falha</h3>
            <p>
              Registre uma nova falha operacional utilizando formulários
              padronizados para garantir consistência dos dados.
            </p>
            <button
              className="action-button"
              onClick={() => navigate("/registrar-falha")}
            >
              <img src={addIcon} alt="+" className="button-icon" /> Nova Falha
            </button>
          </div>

          <div className="action-card">
            <h3>Consultar Falhas</h3>
            <p>
              Visualize e pesquise falhas registradas, filtre por status, data,
              tipo de equipamento e outros critérios.
            </p>
            <button
              className="action-button"
              onClick={() => navigate("/consultar-falhas")}
            >
              Consultar
            </button>
          </div>

          <div className="action-card">
            <h3>Relatórios e Métricas</h3>
            <p>
              Acesse dashboards completos com indicadores de performance,
              gráficos e relatórios detalhados para tomada de decisão.
            </p>
            <button
              className="action-button"
              onClick={() => navigate("/relatorios")}
            >
              Ver relatórios
            </button>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Home;
