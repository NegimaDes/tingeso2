import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ListarProveedores from './components/ListarProveedores';

function App() {
  return (
    <div>
        <BrowserRouter>
          <Routes>
            <Route path= "/proveedores" element={<ListarProveedores />} />
          </Routes>
        </BrowserRouter>
  </div>
  );
}
export default App;