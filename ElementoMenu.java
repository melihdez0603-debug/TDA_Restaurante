/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uthh.edd.unidad1.tda;

/**
 *
 * @author melih
 */
/**
 * Alumno: Hernandez Hernandez Jesus Meliton
 * Matrícula: 20251054
 * Clase base abstracta que representa cualquier artículo o paquete del menú.
 */
public abstract class ElementoMenu {
    private String id;
    private String nombre;
    
     // Constructor con validaciones estructurales.
     
    public ElementoMenu(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    // Getters y Setters con Encapsulamiento 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del producto no puede estar vacío.");
        }
        this.id = id.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }
}