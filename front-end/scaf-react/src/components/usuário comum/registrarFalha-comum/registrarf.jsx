import React, { useState, forwardRef, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import InputMask from 'react-input-mask';
import DatePicker, { registerLocale } from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import { ptBR } from 'date-fns/locale/pt-BR';
import './registrarf.css';
import Navbar from '../navbar-comum/navbar.jsx';
import calendarioIcon from '../../../assets/images/calendario.svg';

registerLocale('pt-BR', ptBR);

const RegistrarFalha = () => {
  const navigate = useNavigate();
  const [dataOcorrencia, setDataOcorrencia] = useState(null);
  const datepickerRef = useRef(null);

  const abrirCalendario = () => {
    if (datepickerRef.current) {
      datepickerRef.current.setOpen(true);
    }
  };

  const CustomDateInput = forwardRef(({ value, onChange, placeholder, id }, ref) => (
    <div className="input-with-icon">
      <InputMask
        mask="99/99/9999"
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        id={id}
        className="custom-datepicker-input"
        ref={ref}
        onClick={(e) => e.stopPropagation()} // impede o input de abrir o calendário
      />
      <img
        src={calendarioIcon}
        alt="Calendário"
        className="input-icon"
        onClick={(e) => {
          e.stopPropagation();
          abrirCalendario();
        }}
      />
    </div>
  ));

  return (
    <div className="registrar-falha-container">
      <Navbar />

      <main className="form-content">
        <div className="form-header-box">
          <div>
            <h1>Registro de Falhas</h1>
            <p>Registre uma nova falha operacional utilizando formulários padronizados.</p>
          </div>
          <button className="btn btn-grey">Rascunhos</button>
        </div>

        <div className="form-alert">
          <strong>Importante:</strong> Todos os campos marcados com (*) são obrigatórios. Certifique-se de preencher todas as informações necessárias para uma análise completa.
        </div>

        <form className="falha-form">
          <section className="form-section">
            <h2>Identificação da Falha</h2>
            <div className="form-grid grid-col-3">
              <div className="form-group">
                <label htmlFor="num-falha">Número da Falha <span>*</span></label>
                <input type="text" id="num-falha" placeholder="123" />
              </div>

              <div className="form-group">
                <label htmlFor="rta">RTA <span>*</span></label>
                <input type="text" id="rta" placeholder="Digite o RTA" />
              </div>

              <div className="form-group">
                <label htmlFor="data-ocorrencia">Data da Ocorrência <span>*</span></label>
                <DatePicker
                  ref={datepickerRef}
                  selected={dataOcorrencia}
                  onChange={(date) => setDataOcorrencia(date)}
                  locale="pt-BR"
                  dateFormat="dd/MM/yyyy"
                  placeholderText="dd / mm / aaaa"
                  id="data-ocorrencia"
                  maxDate={new Date()}
                  customInput={<CustomDateInput />}
                  popperPlacement="bottom-end"   // <-- faz o calendário abrir à direita
                />
              </div>

              <div className="form-group">
                <label htmlFor="ativo">Ativo <span>*</span></label>
                <div className="select-wrapper">
                  <select id="ativo" defaultValue="">
                    <option value="" disabled>Selecione o ativo</option>
                  </select>
                </div>
              </div>
            </div>
          </section>


          <section className="form-section">
            <h2>Localização e Equipamento</h2>
            <div className="form-grid grid-col-3">
              <div className="form-group">
                <label htmlFor="plataforma">
                  Plataforma <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="plataforma" defaultValue="">
                    <option value="" disabled>
                      Selecione a plataforma
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="num-tag">
                  Número da TAG <span>*</span>
                </label>
                <input type="text" id="num-tag" placeholder="Ex: P-101A, K-201B, V-301" />
              </div>

              <div className="form-group">
                <label htmlFor="tipo-equipamento">
                  Tipo de equipamento <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="tipo-equipamento" defaultValue="">
                    <option value="" disabled>
                      Selecione o tipo de equipamento
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="fabricante">
                  Fabricante <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="fabricante" defaultValue="">
                    <option value="" disabled>
                      Nome do fabricante do equipamento
                    </option>
                  </select>
                </div>
              </div>
            </div>
          </section>

          <section className="form-section">
            <h2>Sistema e Descrição</h2>
            <div className="form-grid grid-col-1">
              <div className="form-group full-width">
                <label htmlFor="sistema">
                  Sistema <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="sistema" defaultValue="">
                    <option value="" disabled>
                      Selecione o tipo de sistema
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group full-width">
                <label htmlFor="descricao-falha">
                  Descrição da Falha <span>*</span>
                </label>
                <textarea
                  id="descricao-falha"
                  placeholder="Descreva brevemente a falha ocorrida."
                  rows="5"
                ></textarea>
              </div>
            </div>
          </section>

          <section className="form-section">
            <h2>Mecanismo e Causa</h2>
            <div className="form-grid grid-col-3">
              <div className="form-group">
                <label htmlFor="mecanismo">
                  Mecanismo da Falha <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="mecanismo" defaultValue="">
                    <option value="" disabled>
                      Selecione o mecanismo
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="sub-mecanismo">
                  Subdivisão do Mecanismo <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="sub-mecanismo" defaultValue="">
                    <option value="" disabled>
                      Selecione o tipo de mecanismo
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="causa">
                  Causa da Falha <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="causa" defaultValue="">
                    <option value="" disabled>
                      Selecione a Causa
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="metodo-deteccao">
                  Método de Detecção <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="metodo-deteccao" defaultValue="">
                    <option value="" disabled>
                      Selecione o método
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="origem-falha">
                  Origem da Falha <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="origem-falha" defaultValue="">
                    <option value="" disabled>
                      Selecione a origem
                    </option>
                  </select>
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="sub-causa">
                  Subdivisão da Causa da Falha <span>*</span>
                </label>
                <div className="select-wrapper">
                  <select id="sub-causa" defaultValue="">
                    <option value="" disabled>
                      Selecione a origem
                    </option>
                  </select>
                </div>
              </div>
            </div>
          </section>

          <footer className="form-actions">
            <button type="button" className="btn btn-secondary">
              Salvar como Rascunho
            </button>
            <button type="button" className="btn btn-cancel" onClick={() => navigate('/home')}>
              Cancelar
            </button>
            <button type="submit" className="btn btn-primary">
              Registrar Falha
            </button>
          </footer>
        </form>
      </main>
    </div>
  );
};

export default RegistrarFalha;
