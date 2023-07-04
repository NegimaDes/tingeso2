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
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/subirDatos">Subir archivos</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/proveedores/nuevo">Ingresar nuevo Proveedor</a>
                    <a className={`${styles.navbarButton} ${styles.roundedButton}`} href="/pago/index">Ver pagos</a>
                </div>
            </header>
        </div>
    )
}

export default Navbar1;