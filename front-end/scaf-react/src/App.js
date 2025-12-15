import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { ThemeProvider } from "./context/themecontext";

// Importa seus componentes de página
import Login from "./components/login/login.jsx";
import Home from "./components/usuário comum/home-comum/home.jsx";
import RegistrarFalha from "./components/usuário comum/registrarFalha-comum/registrarf.jsx";
import Config from "./components/usuário comum/configurações-comum/config.jsx";
// CORREÇÃO CRÍTICA DO CAMINHO: Apontando para o arquivo consultaf.jsx
import ConsultaDeFalhas from "./components/usuário comum/consultafalha-comum/consultaf.jsx";
import Relatorios from "./components/usuário comum/relatórios-comum/relatorios";

// Componente principal para o controle de rotas
function App() {
  return (
    <ThemeProvider>
      <Router>
        <Routes>
          {/* Rota para a página de login */}
          <Route path="/login" element={<Login />} />

          {/* Rota padrão: redireciona para o login */}
          <Route path="/" element={<Navigate to="/login" />} />

          {/* ROTAS PROTEGIDAS PELO FRONT-END (Após Login) */}

          {/* 1. Dashboard / Home */}
          <Route path="/home" element={<Home />} />

          {/* 2. Registrar Falha */}
          <Route path="/registrar-falha" element={<RegistrarFalha />} />

          {/* 3. Consultar Falhas */}
          <Route path="/consultar-falhas" element={<ConsultaDeFalhas />} />

          {/* 4. Relatórios */}
          <Route path="/relatorios" element={<Relatorios />} />

          {/* 5. Configurações */}
          <Route path="/configuracoes" element={<Config />} />

          {/* Rota de Visualização de Falha (Se existir) */}
          <Route
            path="/visualizar-falha/:id"
            element={<div>Visualização Detalhada</div>}
          />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App;
