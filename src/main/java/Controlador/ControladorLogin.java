/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Vista.VistaConsola;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 *
 * @author SaulRC1
 */
public class ControladorLogin {

    private Conexion conexion_bd;
    private VistaConsola vista = new VistaConsola();
    private boolean conexionOK;
    private boolean desconexionOK;

    public ControladorLogin() {

        this.conexionOK = conectar();

        try {

            this.vista.vistaMetadatos(this.recuperar_metadatos());

        } catch (SQLException e) {
        }

        this.desconexionOK = this.desconectar();
    }

    private boolean conectar() {

        boolean resultado = false;

        try {
            this.conexion_bd = new Conexion();
            this.vista.vistaConsolaLogin("Conexion Correcta... Enhorabuena!!");
            resultado = true;
        } catch (SQLException e) {

            this.vista.vistaConsolaLogin("Error en la conexion", e.getMessage());
        }

        return resultado;

    }

    private boolean desconectar() {

        boolean resultado = false;

        try {
            this.conexion_bd.desconexion();
            this.vista.vistaConsolaLogin("Desonectado de la BD con exito!");
            resultado = true;
        } catch (Exception e) {
            this.vista.vistaConsolaLogin("Error al desconectarse de la BD", e.getMessage());
        }

        return resultado;

    }
    
    public DatabaseMetaData recuperar_metadatos() throws SQLException {
        
        return this.conexion_bd.informacionBD();
    }

}
