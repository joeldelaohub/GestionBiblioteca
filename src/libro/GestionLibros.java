/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libro;

import gestionbiblioteca.Biblioteca;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author joeld
 */
public class GestionLibros {
    
    public static void imprimirLibros() {
        System.out.printf("%-15s | %-15s | %-15s | %-15s%n",
                "Titulo", "Autor", "Categoria", "Disponibilidad");
        
        for (int i = 0; i < Biblioteca.biblioteca.size(); i++) {
            System.out.printf("%-15s %-15s %-15s %-15s%n",
                Biblioteca.biblioteca.get(i).getTitulo(),
                Biblioteca.biblioteca.get(i).getAutor(),
                Biblioteca.biblioteca.get(i).getCategoria(),
                Biblioteca.biblioteca.get(i).getDisponibilidad() ? "Disponible" : "No Disponible");
        }
        
    }
    
    public static Libro buscar(String palabra) {
        Libro libroEncontrado = null;
        
        for(int i = 0; i < Biblioteca.biblioteca.size(); i++) {
            
            if(Biblioteca.biblioteca.get(i).getTitulo().toLowerCase().contains(palabra.toLowerCase()) ||
               Biblioteca.biblioteca.get(i).getAutor().toLowerCase().contains(palabra.toLowerCase())  || 
               Biblioteca.biblioteca.get(i).getCategoria().toLowerCase().contains(palabra.toLowerCase())
               ) {
                
                libroEncontrado = Biblioteca.biblioteca.get(i);
                break;
            }
        }
        
        return libroEncontrado;
    }
    
    public static void guardarLibros() {
        try(FileWriter fw = new FileWriter("libros.csv")) {
            fw.write("titulo;autor;categoria;disponibilidad\n");
            for(Libro libro: Biblioteca.biblioteca) {
                fw.write(libro.toCsv() + "\n");
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void leerLibros() {
        String line;
        
        try(BufferedReader br = new BufferedReader(new FileReader("libros.csv"))) {
            
            line = br.readLine();
            
            while((line = br.readLine()) != null) {
                String[] valores = line.split(";");
                
                Biblioteca.biblioteca.add(new Libro(valores[0],valores[1], valores[2],
                Boolean.parseBoolean(valores[3])));
            }
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
