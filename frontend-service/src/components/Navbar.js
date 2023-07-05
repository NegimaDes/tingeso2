import React from 'react';
import styles from '../styles/NavbarHome.module.css';

function Navbar1() {
    return(
        <div>
            <header className={styles.navbar}>
                <div className={`${styles.navbarTitle} ${styles.roundedButton}`}>
                    <h1>MilkStgo</h1>
                </div>
                <div className={styles.navbarButtons}>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/">Volver al menú principal</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/subirAcopio">Subir archivos</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/proveedores/nuevo">Ingresar Proveedor</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/pagos/index">Ver pagos</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/pagos/calcular">Calcular pagos</a>
                </div>
            </header>
        </div>
    )
}

export default Navbar1;