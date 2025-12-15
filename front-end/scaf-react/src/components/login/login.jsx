import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";

// IMAGENS
import logoPetrobras from "../../assets/images/logo-petrobras.svg";
import backgroundImage from "../../assets/images/background-image.svg";
import eyeOnIcon from "../../assets/images/eye-on.svg";
import eyeOffIcon from "../../assets/images/eye-off.svg";
import infoIcon from "../../assets/images/info.svg";
import checkgIcon from "../../assets/images/checkg.svg";

// ENDPOINT PADRÃO DA SUA API
const API_BASE_URL = "http://localhost:8080/api";

const Login = () => {
  const [showNewPasswordScreen, setShowNewPasswordScreen] = useState(false);

  const [passwordVisible, setPasswordVisible] = useState(false);
  const [newPasswordVisible, setNewPasswordVisible] = useState(false);
  const [confirmPasswordVisible, setConfirmPasswordVisible] = useState(false);

  // Estados para Login e Senha
  const [loginRE, setLoginRE] = useState("");
  const [password, setPassword] = useState("");
  const [loginError, setLoginError] = useState("");

  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [validations, setValidations] = useState({
    hasSpecialChar: false,
    hasUpperCase: false,
    hasMinLength: false,
  });

  const [isFirstAccess, setIsFirstAccess] = useState(true);
  const navigate = useNavigate();

  const validatePassword = (password) => {
    setNewPassword(password);
    const specialCharRegex = /\W/;
    const upperCaseRegex = /[A-Z]/;
    setValidations({
      hasSpecialChar: specialCharRegex.test(password),
      hasUpperCase: upperCaseRegex.test(password),
      hasMinLength: password.length >= 8,
    });
  };

  const handleToggleNewPassword = () => {
    if (isFirstAccess) {
      setShowNewPasswordScreen(true);
    } else {
      console.log(
        'Funcionalidade "Esqueci minha senha" ainda não implementada.'
      );
    }
  };

  // FUNÇÃO DE LOGIN INTEGRADA COM O BACK-END (JWT)
  const handleLogin = async (event) => {
    if (event) {
      event.preventDefault(); // Evita o refresh da página
    }

    setLoginError("");

    if (!loginRE || !password) {
      setLoginError("Por favor, preencha o login e a senha.");
      return;
    }

    try {
      // CORREÇÃO FINAL: Corrigido o payload para o formato DTO (re e senha)
      const loginData = {
        re: loginRE,
        senha: password,
      };

      // A ROTA DE LOGIN NO SPRING SECURITY É /api/auth/login
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });

      if (!response.ok) {
        const errorText = await response.text();
        setLoginError("RE ou senha inválidos. Tente novamente.");
        console.error("Erro de Autenticação:", response.status, errorText);
        return;
      }

      const data = await response.json();
      const jwtToken = data.token; // Espera que o Back-end retorne { token: '...' }

      // 1. Armazenar o Token JWT no LocalStorage
      localStorage.setItem("userToken", jwtToken);

      // 2. Redirecionar para o Dashboard/Home
      navigate("/home");
    } catch (error) {
      setLoginError(
        "Erro de conexão com o servidor. Verifique se o Backend está rodando."
      );
      console.error("Erro de Rede no Login:", error);
    }
  };

  const confirmNewPassword = () => {
    if (newPassword !== confirmPassword) {
      setLoginError("As senhas não coincidem!");
      return;
    }
    if (
      validations.hasSpecialChar &&
      validations.hasUpperCase &&
      validations.hasMinLength
    ) {
      console.log("Nova senha definida com sucesso! Agora faça seu login.");
      setIsFirstAccess(false);
      setShowNewPasswordScreen(false);
      setLoginError("");
    } else {
      setLoginError("A senha não cumpre todos os requisitos.");
    }
  };

  return (
    <div
      className="login-container"
      style={{ backgroundImage: `url(${backgroundImage})` }}
    >
      <div className="diagonal-background"></div>
      <img
        src={logoPetrobras}
        alt="Logo Petrobras"
        className="logo-petrobras"
      />

      <div className="login-card">
        <h2 className="card-title">
          {showNewPasswordScreen
            ? "Definição de Senha"
            : "Sistema de Gestão de Falhas"}
        </h2>

        {!showNewPasswordScreen ? (
          // Tela de Login Principal
          <form onSubmit={handleLogin}>
            <div className="form-group">
              <label htmlFor="login">Login (RE):</label>
              <input
                type="text"
                id="login"
                placeholder="Digite seu RE"
                onChange={(e) => setLoginRE(e.target.value)}
                value={loginRE}
              />
            </div>
            <div className="form-group">
              <label htmlFor="password">Senha:</label>
              <div className="password-input-wrapper">
                <input
                  type={passwordVisible ? "text" : "password"}
                  id="password"
                  placeholder="Digite sua senha"
                  onChange={(e) => setPassword(e.target.value)}
                  value={password}
                />
                <img
                  src={passwordVisible ? eyeOffIcon : eyeOnIcon}
                  alt="Toggle visibility"
                  className="password-toggle-icon"
                  onClick={() => setPasswordVisible(!passwordVisible)}
                />
              </div>
            </div>

            {/* EXIBE MENSAGEM DE ERRO DE LOGIN */}
            {loginError && (
              <p className="password-mismatch-error">{loginError}</p>
            )}

            <a
              className="forgot-password-link"
              onClick={handleToggleNewPassword}
            >
              {isFirstAccess ? "Nova senha" : "Esqueci minha senha"}
            </a>
            {/* O botão é type="submit" para acionar o onSubmit do form */}
            <button type="submit" className="btn-primary">
              ENTRAR
            </button>
          </form>
        ) : (
          // Tela de Nova Senha
          <>
            <h3 className="new-password-title">Crie sua nova senha:</h3>
            <div className="form-group">
              <div className="password-input-wrapper">
                <input
                  type={newPasswordVisible ? "text" : "password"}
                  placeholder="Digite uma senha de 8 caracteres"
                  onChange={(e) => validatePassword(e.target.value)}
                />
                <img
                  src={newPasswordVisible ? eyeOffIcon : eyeOnIcon}
                  alt="Toggle visibility"
                  className="password-toggle-icon"
                  onClick={() => setNewPasswordVisible(!newPasswordVisible)}
                />
              </div>
            </div>
            <ul className="password-rules">
              <li className={validations.hasSpecialChar ? "valid" : ""}>
                <img
                  src={validations.hasSpecialChar ? checkgIcon : infoIcon}
                  alt="icon"
                  className="validation-icon"
                />
                Pelo menos um caractere especial
              </li>
              <li className={validations.hasUpperCase ? "valid" : ""}>
                <img
                  src={validations.hasUpperCase ? checkgIcon : infoIcon}
                  alt="icon"
                  className="validation-icon"
                />
                Pelo menos uma letra maiúscula (A-Z)
              </li>
              <li className={validations.hasMinLength ? "valid" : ""}>
                <img
                  src={validations.hasMinLength ? checkgIcon : infoIcon}
                  alt="icon"
                  className="validation-icon"
                />
                Pelo menos 8 caracteres
              </li>
            </ul>
            <h3 className="confirm-password-title">Confirme sua senha:</h3>
            <div className="form-group">
              <div className="password-input-wrapper">
                <input
                  type={confirmPasswordVisible ? "text" : "password"}
                  placeholder="Digite uma senha de 8 caracteres"
                  onChange={(e) => setConfirmPassword(e.target.value)}
                />
                <img
                  src={confirmPasswordVisible ? eyeOffIcon : eyeOnIcon}
                  alt="Toggle visibility"
                  className="password-toggle-icon"
                  onClick={() =>
                    setConfirmPasswordVisible(!confirmPasswordVisible)
                  }
                />
              </div>
              {confirmPassword && newPassword !== confirmPassword && (
                <p className="password-mismatch-error">
                  As senhas não coincidem.
                </p>
              )}
            </div>
            <button className="btn-primary" onClick={confirmNewPassword}>
              CONFIRMAR
            </button>
          </>
        )}
      </div>
    </div>
  );
};

export default Login;
