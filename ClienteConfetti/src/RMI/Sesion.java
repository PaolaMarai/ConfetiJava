/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

/**
 *
 * @author Beto Lafarc
 */
public class Sesion {
    private static Sesion sesion;
    private static String usuario;
    private static String pass;
    private static String idUsuario;
    private static boolean autenticado;
    
    private Sesion(){}
    
    public static Sesion getInstance() {
        if(null == sesion) {
            sesion = new Sesion();
        }
        
        return sesion;
    }

    public static Sesion getSesion() {
        return sesion;
    }

    public static void setSesion(Sesion sesion) {
        Sesion.sesion = sesion;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Sesion.usuario = usuario;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        Sesion.pass = pass;
    }

    public static boolean isAutenticado() {
        return autenticado;
    }

    public static void setAutenticado(boolean autenticado) {
        Sesion.autenticado = autenticado;
    }
    
    
    
}
