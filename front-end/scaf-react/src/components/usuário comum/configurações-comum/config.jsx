import { useContext } from "react";
import { ThemeContext } from "../../../context/themecontext.jsx";
import React, { useState } from "react";
import "./config.css";
import Navbar from "../navbar-comum/navbar.jsx";
import perfilIcon from "../../../assets/images/person.svg";
import notificacaoIcon from "../../../assets/images/notconfig.svg";
import preferenciasIcon from "../../../assets/images/preference.svg";
import privacidadeIcon from "../../../assets/images/priv.svg";

const Config = () => {
  const [abaAtiva, setAbaAtiva] = useState("perfil");
  const { theme, toggleTheme } = useContext(ThemeContext);
  
  return (
    <div className="config-container">
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
                  <div className="notificacao-item">
                    <div>
                      <h3>Notificações Push</h3>
                      <p>
                        Receber notificações instantâneas no topo da tela atual
                        quando estiver ativo.
                      </p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>

                  <div className="notificacao-item">
                    <div>
                      <h3>Sons de Notificação</h3>
                      <p>
                        Reproduzir som quando receber notificações no sistema.
                      </p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>

                  <div className="notificacao-item">
                    <div>
                      <h3>Notificações de Lembrete</h3>
                      <p>Receber lembretes sobre falhas pendentes.</p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>
                </div>
              </>
            )}

            {abaAtiva === "preferencias" && (
              <>
                <h2>Preferências do Sistema</h2>

                {/* APARÊNCIA E TEMA */}
                <div className="preferencias-section">
                  <h3>Aparência e Tema</h3>

                  <div className="tema-container">
                    <label className="tema-opcao ativo">
                      <input type="radio" name="tema" defaultChecked />
                      <div className="tema-box">
                        <h4>Tema Claro</h4>
                        <p>Interface clara e tradicional.</p>
                      </div>
                    </label>

                    <label className="tema-opcao">
                      <input type="radio" name="tema" />
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
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>

                  <div className="switch-group">
                    <div>
                      <h4>Animações da Interface</h4>
                      <p>Habilitar animações e transições suaves.</p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>
                </div>

                {/* IDIOMA E LOCALIZAÇÃO */}
                <div className="preferencias-section">
                  <h3>Idioma e Localização</h3>

                  <div className="form-group">
                    <label>Idioma da Interface</label>
                    <select>
                      <option>🇧🇷 Português (Brasil)</option>
                      <option>🇺🇸 English (United States)</option>
                      <option>🇪🇸 Español (Latinoamérica)</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <label>Fuso Horário</label>
                    <select>
                      <option>São Paulo (GMT-3)</option>
                      <option>Manaus (GMT-4)</option>
                      <option>Lisboa (GMT+1)</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <label>Formato de Data</label>
                    <select>
                      <option>DD/MM/AAAA (31/12/2025)</option>
                      <option>MM/DD/YYYY (12/31/2025)</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <label>Formato de Hora</label>
                    <select>
                      <option>24h (14:30)</option>
                      <option>12h (2:30 PM)</option>
                    </select>
                  </div>
                </div>

                {/* FUNCIONALIDADES */}
                <div className="preferencias-section">
                  <h3>Funcionalidades</h3>

                  <div className="switch-group">
                    <div>
                      <h4>Auto-save de Rascunhos</h4>
                      <p>Salvar automaticamente formulários em progresso.</p>
                    </div>
                    <label className="switch">
                      <input type="checkbox" defaultChecked />
                      <span className="slider"></span>
                    </label>
                  </div>
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
