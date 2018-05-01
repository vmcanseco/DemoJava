package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Persona {

    private String nombre;
    private String apellidos;
    private int edad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona(String nombre, String apellidos, int edad) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
public class DemoPredicate {

    public static void main(String[] args) {
        Predicate<Persona> predicadoNombre=new Predicate<Persona>() {
            @Override
            public boolean test(Persona persona) {
                    return persona.getNombre().equals("pepe");
            }
        };

        List<Persona> lista = new ArrayList<>();
        Persona p1 = new Persona("pepe", "perez", 20);
        Persona p2 = new Persona("angel", "sanchez", 30);
        Persona p3 = new Persona("pepe", "blanco", 40);
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);

        lista.stream().forEach(persona -> System.out.println(persona.getNombre()));
        lista.stream().filter(predicadoNombre).forEach(persona -> System.out.println(persona.getNombre()));
        /*lista.stream().forEach(new Consumer<Persona>() {
            @Override
            public void accept(Persona persona) {
                    System.out.println(persona.getNombre());
            }
        });*/
        //lista.stream().map(persona -> persona.getNombre()).collect(Collectors.toList());
    }
}
