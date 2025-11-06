import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './login.css'; 

// IMAGENS
import logoPetrobras from '../../assets/images/logo-petrobras.svg';
import backgroundImage from '../../assets/images/background-image.svg';
import eyeOnIcon from '../../assets/images/eye-on.svg';
import eyeOffIcon from '../../assets/images/eye-off.svg';
import infoIcon from '../../assets/images/info.svg';
import checkgIcon from '../../assets/images/checkg.svg'; 

const Login = () => {
  const [showNewPasswordScreen, setShowNewPasswordScreen] = useState(false); 
  
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [newPasswordVisible, setNewPasswordVisible] = useState(false);
  const [confirmPasswordVisible, setConfirmPasswordVisible] = useState(false);

  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState(''); 
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
    // Se for primeiro acesso, mostra a tela de nova senha.
    if (isFirstAccess) {
      setShowNewPasswordScreen(true);
    } else {
      // Se não, implementaria a lógica de "esqueci a senha".
      alert('Funcionalidade "Esqueci minha senha" ainda não implementada.');
    }
  };
  
  const performLogin = () => { navigate('/home'); };
  
  const confirmNewPassword = () => {
    if (newPassword !== confirmPassword) {
      alert('As senhas não coincidem!');
      return;
    }
    if (validations.hasSpecialChar && validations.hasUpperCase && validations.hasMinLength) {
        alert('Nova senha definida com sucesso! Agora faça seu login.');
        setIsFirstAccess(false);
        setShowNewPasswordScreen(false);
    } else {
        alert('A senha não cumpre todos os requisitos.');
    }
  };

  return (
    <div className="login-container" style={{ backgroundImage: `url(${backgroundImage})` }}>
      <div className="diagonal-background"></div>
      <img src={logoPetrobras} alt="Logo Petrobras" className="logo-petrobras" />

      <div className="login-card">
        {/* Usamos um ternário para mudar o título principal do card */}
        <h2 className="card-title">
            {showNewPasswordScreen ? 'Definição de Senha' : 'Sistema de Gestão de Falhas'}
        </h2>

        { !showNewPasswordScreen ? (
          // Tela de Login Principal
          <>
            <div className="form-group">
              <label htmlFor="login">Login:</label>
              <input type="text" id="login" placeholder="Digite seu RE" />
            </div>
            <div className="form-group">
              <label htmlFor="password">Senha:</label>
              <div className="password-input-wrapper">
                <input type={passwordVisible ? "text" : "password"} id="password" placeholder="Digite sua senha" />
                <img src={passwordVisible ? eyeOffIcon : eyeOnIcon} alt="Toggle visibility" className="password-toggle-icon" onClick={() => setPasswordVisible(!passwordVisible)} />
              </div>
            </div>
            <a className="forgot-password-link" onClick={handleToggleNewPassword}>
              {isFirstAccess ? 'Nova senha' : 'Esqueci minha senha'}
            </a>
            <button className="btn-primary" onClick={performLogin}>ENTRAR</button>
          </>
        ) : (
          // Tela de Nova Senha
          <>
            {/* O código da tela de nova senha que já fizemos continua o mesmo aqui */}
            <h3 className="new-password-title">Crie sua nova senha:</h3>
             <div className="form-group">
              <div className="password-input-wrapper">
                <input type={newPasswordVisible ? "text" : "password"} placeholder="Digite uma senha de 8 caracteres" onChange={(e) => validatePassword(e.target.value)} />
                <img src={newPasswordVisible ? eyeOffIcon : eyeOnIcon} alt="Toggle visibility" className="password-toggle-icon" onClick={() => setNewPasswordVisible(!newPasswordVisible)} />
              </div>
            </div>
            <ul className="password-rules">
              <li className={validations.hasSpecialChar ? 'valid' : ''}><img src={validations.hasSpecialChar ? checkgIcon : infoIcon} alt="icon" className="validation-icon"/>Pelo menos um caractere especial</li>
              <li className={validations.hasUpperCase ? 'valid' : ''}><img src={validations.hasUpperCase ? checkgIcon : infoIcon} alt="icon" className="validation-icon"/>Pelo menos uma letra maiúscula (A-Z)</li>
              <li className={validations.hasMinLength ? 'valid' : ''}><img src={validations.hasMinLength ? checkgIcon : infoIcon} alt="icon" className="validation-icon"/>Pelo menos 8 caracteres</li>
            </ul>
            <h3 className="confirm-password-title">Confirme sua senha:</h3>
            <div className="form-group">
              <div className="password-input-wrapper">
                <input type={confirmPasswordVisible ? "text" : "password"} placeholder="Digite uma senha de 8 caracteres" onChange={(e) => setConfirmPassword(e.target.value)} />
                <img src={confirmPasswordVisible ? eyeOffIcon : eyeOnIcon} alt="Toggle visibility" className="password-toggle-icon" onClick={() => setConfirmPasswordVisible(!confirmPasswordVisible)} />
              </div>
              {confirmPassword && newPassword !== confirmPassword && (<p className="password-mismatch-error">As senhas não coincidem.</p>)}
            </div>
            <button className="btn-primary" onClick={confirmNewPassword}>CONFIRMAR</button>
          </>
        )}
      </div>
    </div>
  );
};

export default Login;