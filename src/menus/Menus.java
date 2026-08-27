package menus;

import java.util.Scanner;

/**
 *
 * @author joeld
 */
public class Menus {
    /**
     * Este metodo se encarga de crear todos los menus del sistema.
     * El metodo recorre todo el arreglo donde se almacenan las opciones que tendra
     * ese menu y se encarga de imprimirlo como un menu y las opciones junto con un numero para
     * que sea mas facil de elegir una opcion.
     * dentro de la iteracion, se evalua si i es igual a longitud - 1, es decir,
     * si es igual al ultimo indice del arreglo, entonces que se ponga 0 para
     * la ultima opcion del menu.
     * @param titulo titulo que llevara el menu
     * @param opciones donde se almacenan las opciones
     */
    public static void createMenu(String titulo, String[] opciones) {
        System.out.printf("----- %s -----%n", titulo);
        for(int i = 0; i <opciones.length; i++) {
            if(i == opciones.length - 1)
                System.out.printf("%d. %s%n", 0, opciones[i]);
            else
                System.out.printf("%d. %s%n",i + 1, opciones[i]); // le sumamos 1 a i para que en la impresion de la primera opcion aparezca 1 y no 0.
        }
    }
    
    public static void MainMenu(Scanner entrada) {
        String[] opcionesMain = {"Iniciar Sesion", "Registrarse", "Finalizar programa"};
        int opcion = -1;
        
        do {
            Menus.createMenu("Bienvenido a la Biblioteca Online", opcionesMain);
            System.out.print("Elija una opcion: ");
            opcion = entrada.nextInt();
            entrada.nextLine();
            
            switch(opcion) {
                case 1:
                    System.out.println("Iniciando sesion...");
                    break;
                case 2:
                    System.out.println("Registrando...");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
            }
        }while(opcion != 0);
    }
}
