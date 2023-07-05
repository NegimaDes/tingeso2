import React from 'react';
import { Component } from 'react';
import NavbarComponent from './Navbar2';
import '../styles/Home.css';

class HomeComponent extends Component {
  render() {
    return (
      <div>
        <NavbarComponent />
        <div class="home-container">
          <div class="card create-provider-card">
            <a class="card-link" href="/proveedores/nuevo">Crear Nuevo Proveedor</a>
          </div>
          <div class="card view-providers-card">
            <a class="card-link" href="/proveedores/index">Visualizar Listado de Proveedores</a>
          </div>
          <div class="card enter-collection-card">
            <a class="card-link" href="/subirAcopio">Ingresar Documentos de Acopios y Calidades</a>
          </div>
          <div class="card view-payments-card">
            <a class="card-link" href="/pagos/index">Visualizar Listado de Pagos</a>
          </div>
        </div>

      </div>
    );
  }
}

export default HomeComponent;