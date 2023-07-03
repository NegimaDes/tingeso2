import React from 'react';
import styles from '../styles/NavbarHome.module.css';

function Navbar2() {
    return(
        <div>
            <header className={styles.navbar}>
                <div className={`${styles.navbarTitle} ${styles.roundedButton}`}>
                    <h1>MilkStgo</h1>
                </div>
            </header>
        </div>
    )
}

export default Navbar2;