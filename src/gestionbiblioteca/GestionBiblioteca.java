package gestionbiblioteca;

import java.util.Scanner;
import menus.Menus;

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
        Menus.MainMenu(entrada);
        
        entrada.close();
    }
    
}
