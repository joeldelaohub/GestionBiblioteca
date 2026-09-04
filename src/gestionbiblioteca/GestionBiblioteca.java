package gestionbiblioteca;

import java.util.Scanner;
import menus.Menus;
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
        
        Menus.MainMenu(entrada);
        
    }
    
}
