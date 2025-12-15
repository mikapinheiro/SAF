import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './home.css';

// === CORREÇÃO AQUI ===
// Adicione a extensão .jsx ao final do caminho
import Navbar from '../navbar-comum/navbar.jsx'; 

import addIcon from '../../../assets/images/add.svg';

const statsData = [
  { count: '23', label: 'EM ANÁLISE', color: '#FFC107' },
  { count: '156', label: 'APROVADAS', color: '#00875F' },
  { count: '12', label: 'RECUSADAS', color: '#DC3545' },
  { count: '191', label: 'TOTAL DE FALHAS', color: '#212529' },
];

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <Navbar /> {/* navbar com dropdown e notificações */}

      <main className="main-content">
        <section className="welcome-banner">
          <h1>Bem-vindo ao Sistema de Gestão de Falhas</h1>
          <p>Monitore, registre e gerencie falhas operacionais com mais eficiência e organização.</p>
        </section>

        <section className="stats-grid">
          {statsData.map((stat, index) => (
            <div key={index} className="stat-card" style={{ borderTopColor: stat.color }}>
              <span className="stat-count" style={{ color: stat.color }}>{stat.count}</span>
              <span className="stat-label">{stat.label}</span>
            </div>
          ))}
        </section>

        <section className="actions-grid">
          <div className="action-card">
            <h3>Registrar Nova Falha</h3>
            <p>Registre uma nova falha operacional utilizando formulários padronizados para garantir consistência dos dados.</p>
            <button className="action-button" onClick={() => navigate('/registrar-falha')}>
              <img src={addIcon} alt="+" className="button-icon" /> Nova Falha
            </button>
          </div>

          <div className="action-card">
            <h3>Consultar Falhas</h3>
            <p>Visualize e pesquise falhas registradas, filtre por status, data, tipo de equipamento e outros critérios.</p>
            <button className="action-button" onClick={() => navigate('/consultar-falhas')}>
              Consultar
            </button>
          </div>

          <div className="action-card">
            <h3>Relatórios e Métricas</h3>
            <p>Acesse dashboards completos com indicadores de performance, gráficos e relatórios detalhados para tomada de decisão.</p>
            <button className="action-button" onClick={() => navigate('/relatorios')}>
              Ver relatórios
            </button>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Home;