import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider } from './context/themecontext';

// Importa seus componentes de página
import Login from './components/login/login'; 
import Home from './components/usuário comum/home-comum/home';
import RegistrarFalha from './components/usuário comum/registrarFalha-comum/registrarf';
import Config from "./components/usuário comum/configurações-comum/config";

function App() {
  return (
    <ThemeProvider> {}
      <Router>
        <Routes>
          {/* Rota para a página de login */}
          <Route path="/login" element={<Login />} />

          {/* Rota para a página inicial */}
          <Route path="/home" element={<Home />} />

          {/* Rota para a página de Registro de Falha */}
          <Route path="/registrar-falha" element={<RegistrarFalha />} />

          {/* Rota para a página de Configurações */}
          <Route path="/config" element={<Config />} />

          {/* Rota padrão: redireciona para o login */}
          <Route path="/" element={<Navigate to="/login" />} />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App;
