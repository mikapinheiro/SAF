import React, { useState, useContext, useEffect } from "react";
import { ThemeContext } from "../../../context/themecontext.jsx";
import Navbar from "../navbar-comum/navbar.jsx";
import "./config.css";

import perfilIcon from "../../../assets/images/person.svg";
import notificacaoIcon from "../../../assets/images/notconfig.svg";
import preferenciasIcon from "../../../assets/images/preference.svg";
import privacidadeIcon from "../../../assets/images/priv.svg";

const Config = () => {
  const [abaAtiva, setAbaAtiva] = useState("perfil");
  const { theme, toggleTheme } = useContext(ThemeContext);

  // aplica classe de tema ao body
  useEffect(() => {
    document.body.classList.remove("theme-light", "theme-dark");
    document.body.classList.add(
      theme === "dark" ? "theme-dark" : "theme-light"
    );
  }, [theme]);

  return (
    <div
      className={`config-container ${
        theme === "dark" ? "theme-dark" : "theme-light"
      }`}
    >
      <Navbar />

      <main className="config-content">
        <div className="config-header-box">
          <div>
            <h1>Configurações</h1>
            <p>Personalize sua experiência no sistema de gestão de falhas.</p>
          </div>
        </div>

        <div className="config-body">
          {/* ==== MENU LATERAL ==== */}
          <aside className="config-sidebar">
            <h2>Configurações</h2>
            <ul>
              <li
                className={abaAtiva === "perfil" ? "active" : ""}
                onClick={() => setAbaAtiva("perfil")}
              >
                <img src={perfilIcon} alt="Perfil" />
                Perfil
              </li>
              <li
                className={abaAtiva === "notificacoes" ? "active" : ""}
                onClick={() => setAbaAtiva("notificacoes")}
              >
                <img src={notificacaoIcon} alt="Notificações" />
                Notificações
              </li>
              <li
                className={abaAtiva === "preferencias" ? "active" : ""}
                onClick={() => setAbaAtiva("preferencias")}
              >
                <img src={preferenciasIcon} alt="Preferências" />
                Preferências
              </li>
              <li
                className={abaAtiva === "privacidade" ? "active" : ""}
                onClick={() => setAbaAtiva("privacidade")}
              >
                <img src={privacidadeIcon} alt="Privacidade" />
                Privacidade
              </li>
            </ul>
          </aside>

          {/* ==== CONTEÚDO PRINCIPAL ==== */}
          <section className="config-main">
            {abaAtiva === "perfil" && (
              <>
                <h2>Informações do Perfil</h2>
                <div className="perfil-grid">
                  <div className="form-group">
                    <label>Nome Completo</label>
                    <input type="text" defaultValue="Gabrielly Zanit" />
                  </div>
                  <div className="form-group">
                    <label>E-mail Corporativo</label>
                    <input
                      type="text"
                      defaultValue="gabriellyzanit@petrobras.com.br"
                    />
                  </div>
                  <div className="form-group">
                    <label>Registro do Empregado (RE)</label>
                    <input type="text" defaultValue="12345678" />
                  </div>
                  <div className="form-group">
                    <label>Cargo</label>
                    <input type="text" defaultValue="Operador de Suporte" />
                  </div>
                  <div className="form-group">
                    <label>Setor</label>
                    <input type="text" defaultValue="Operações" />
                  </div>
                  <div className="form-group">
                    <label>Unidade</label>
                    <input type="text" defaultValue="P-70 (Marlim Sul)" />
                  </div>
                </div>

                <div className="perfil-actions">
                  <button className="btn-solicitar">Solicitar Alteração</button>
                </div>
              </>
            )}

            {abaAtiva === "notificacoes" && (
              <>
                <h2>Notificações do Sistema</h2>
                <div className="notificacoes-lista">
                  {[
                    {
                      title: "Notificações Push",
                      desc: "Receber notificações instantâneas quando ativo.",
                    },
                    {
                      title: "Sons de Notificação",
                      desc: "Reproduzir som ao receber notificações.",
                    },
                    {
                      title: "Notificações de Lembrete",
                      desc: "Receber lembretes sobre falhas pendentes.",
                    },
                  ].map((item, i) => (
                    <div className="notificacao-item" key={i}>
                      <div>
                        <h3>{item.title}</h3>
                        <p>{item.desc}</p>
                      </div>
                      <label className="switch">
                        <input type="checkbox" defaultChecked />
                        <span className="slider"></span>
                      </label>
                    </div>
                  ))}
                </div>
              </>
            )}

            {abaAtiva === "preferencias" && (
              <>
                <h2>Preferências do Sistema</h2>

                <div className="preferencias-section">
                  <h3>Aparência e Tema</h3>
                  <div className="tema-container">
                    <label
                      className={`tema-opcao ${
                        theme === "light" ? "ativo" : ""
                      }`}
                      onClick={() => theme !== "light" && toggleTheme()}
                    >
                      <div className="tema-box light">
                        <h4>Tema Claro</h4>
                        <p>Interface clara e tradicional.</p>
                      </div>
                    </label>

                    <label
                      className={`tema-opcao ${
                        theme === "dark" ? "ativo" : ""
                      }`}
                      onClick={() => theme !== "dark" && toggleTheme()}
                    >
                      <div className="tema-box dark">
                        <h4>Tema Escuro</h4>
                        <p>Reduz fadiga visual.</p>
                      </div>
                    </label>
                  </div>

                  <div className="switch-group">
                    <div>
                      <h4>Modo Alto Contraste</h4>
                      <p>Aumente o contraste para melhor acessibilidade.</p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" />
                      <span className="slider"></span>
                    </label>
                  </div>

                  <div className="switch-group">
                    <div>
                      <h4>Animações da Interface</h4>
                      <p>Habilitar transições suaves.</p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>
                </div>
              </>
            )}

            {abaAtiva === "privacidade" && (
              <>
                <h2>Privacidade e Segurança</h2>
                <div className="info-box">
                  Gerencie suas permissões, visibilidade de dados e políticas de
                  segurança do sistema.
                </div>
              </>
            )}
          </section>
        </div>
      </main>
    </div>
  );
};

export default Config;
