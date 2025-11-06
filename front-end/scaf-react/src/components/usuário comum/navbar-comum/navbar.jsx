import React, { useState, useRef, useEffect } from "react";
import "./navbar.css";
import { useNavigate } from "react-router-dom";

import navLogo from "../../../assets/images/navlogo.svg";
import bellIcon from "../../../assets/images/notification.svg";
import userIcon from "../../../assets/images/user.svg";
import exitIcon from "../../../assets/images/exit.svg";

const Navbar = () => {
  const [showDropdown, setShowDropdown] = useState(false);
  const [showNotifications, setShowNotifications] = useState(false);
  const navigate = useNavigate();

  const userRef = useRef(null);
  const notRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (
        userRef.current &&
        !userRef.current.contains(event.target) &&
        notRef.current &&
        !notRef.current.contains(event.target)
      ) {
        setShowDropdown(false);
        setShowNotifications(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleLogout = () => {
    navigate("/");
  };

  return (
    <div className="navbar-wrapper">
      {/* LOGO */}
      <div className="navbar-logo-container">
        <img src={navLogo} alt="Logo Petrobras" className="navbar-logo" />
      </div>

      {/* NAVBAR PRINCIPAL */}
      <header className="navbar-container">
        <nav className="navbar-center">
          <a onClick={() => navigate("/dashboard")} className="nav-link">Dashboard</a>
          <a onClick={() => navigate("/registrarfalha")} className="nav-link">Registrar Falha</a>
          <a onClick={() => navigate("/consultar")} className="nav-link">Consultar Falhas</a>
          <a onClick={() => navigate("/relatorios")} className="nav-link">Relatórios</a>
          <a onClick={() => navigate("/config")} className="nav-link">Configurações</a>
        </nav>

        <div className="navbar-right">
          {/* NOTIFICAÇÕES */}
          <div className="nav-item" ref={notRef}>
            <img
              src={bellIcon}
              alt="Notificações"
              className="nav-icon"
              onClick={() => {
                setShowNotifications(!showNotifications);
                setShowDropdown(false);
              }}
            />

            <div className={`notification-popup ${showNotifications ? "show" : ""}`}>
              <div className="arrow-up"></div>
              <div className="notification-header">
                <span>Notificações</span>
                <a href="#">Todas &gt;</a>
              </div>

              <ul>
                <li>
                  <div className="circle red">R</div>
                  <div>
                    <p>
                      MUDANÇA DE STATUS - REGISTRO DE FALHA -{" "}
                      <span className="status approved">APROVADO</span>
                    </p>
                  </div>
                </li>
                <li>
                  <div className="circle blue">J</div>
                  <div>
                    <p>
                      MUDANÇA DE STATUS - REGISTRO DE FALHA -{" "}
                      <span className="status denied">RECUSADA</span>
                    </p>
                  </div>
                </li>
                <li>
                  <div className="circle green">M</div>
                  <div>
                    <p>REGISTRO DE FALHA - RASCUNHO EM ABERTO</p>
                  </div>
                </li>
                <li>
                  <div className="circle red">R</div>
                  <div>
                    <p>
                      MUDANÇA DE STATUS - REGISTRO DE FALHA -{" "}
                      <span className="status denied">RECUSADA</span>
                    </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>

          {/* USUÁRIO */}
          <div className="nav-item" ref={userRef}>
            <img
              src={userIcon}
              alt="Usuário"
              className="nav-icon"
              onClick={() => {
                setShowDropdown(!showDropdown);
                setShowNotifications(false);
              }}
            />

            <div className={`user-dropdown ${showDropdown ? "show" : ""}`}>
              <div className="arrow-up"></div>
              <div className="user-top">
                <div className="user-photo">
                  <img src={userIcon} alt="Foto usuário" />
                </div>
                <div className="user-info">
                  <h4>Mikaelle Pinheiro</h4>
                  <p>mikaelle.pinheiro.estudante@petrobras.com.br</p>
                </div>
                <span className="user-badge">COMUM</span>
              </div>

              <div className="user-details">
                <p><strong>Mikaelle Giovanna Pinheiro</strong></p>
                <p>Registro do empregado (RE): 789012</p>
                <p>Cargo: Técnico de Operações</p>
                <p>Setor de atuação: Refino – Manutenção</p>
              </div>

              <div className="user-logout" onClick={handleLogout}>
                <img src={exitIcon} alt="Sair" className="logout-icon" />
                <span>Sair da conta</span>
              </div>
            </div>
          </div>
        </div>
      </header>
    </div>
  );
};

export default Navbar;
