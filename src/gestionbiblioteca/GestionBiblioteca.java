package gestionbiblioteca;

import java.util.Scanner;
import libro.GestionLibros;
import menus.Menus;
import prestamo.GestionPrestamos;
import usuario.GestionUsuarios;

/**
 *
 * @author joeld
 */
public class GestionBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        
        GestionUsuarios.leerUsuarios();
        GestionLibros.leerLibros();
        GestionPrestamos.leerPrestamos();
        
        
        Menus.MainMenu(entrada);
        
    }
    
}
