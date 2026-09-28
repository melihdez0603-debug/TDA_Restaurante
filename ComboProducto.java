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
 * Autor: Hernandez Hernandez Jesus Meliton
 * Matrícula: 20251054
 * Descripción: TDA ComboProducto que hereda de ElementoMenu. Permite empaquetar productos y otros combos.
 */
import java.util.ArrayList;
import java.util.List;

public class ComboProducto extends ElementoMenu {
    // Mínimo dos atributos propios del modelo
    private double precioBase;
    private List<ComboProducto> elementosIncluidos;

    
     // Constructor del TDA ComboProducto.
     
    public ComboProducto(String id, String nombre, double precioBase) {
        super(id, nombre); // Herencia
        setPrecioBase(precioBase);
        this.elementosIncluidos = new ArrayList<>();
    }

    // Métodos de Acceso

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase < 0) {
            throw new IllegalArgumentException("El precio no puede ser un valor negativo.");
        }
        this.precioBase = precioBase;
    }

    public List<ComboProducto> getElementosIncluidos() {
        return new ArrayList<>(elementosIncluidos); // Protección de encapsulamiento
    }

    //  MÉTODOS DE LÓGICA / PROCESAMIENTO ESPECÍFICOS DEL DOMINIO 

    
     // Añade un alimento, bebida o sub-combo al paquete actual.
     
    public void agregarAlCombo(ComboProducto elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento a agregar no puede ser nulo.");
        }
        this.elementosIncluidos.add(elemento);
    }
    
     // Cuenta cuántos ítems directos componen este combo.
     
    public int obtenerCantidadItems() {
        return this.elementosIncluidos.size();
    }

    
     // Genera una línea de texto descriptiva con el precio individual.
     
    public String obtenerResumenMenu() {
        return String.format("ID: %s | %s | Costo Base: $%.2f | Contiene: %d Producto´s", 
                getId(), getNombre(), getPrecioBase(), obtenerCantidadItems());
    }

    /**
     * Método Recursivo:
     * Recorre todo el árbol del combo de manera recursiva sumando el precio base
     * del paquete actual más el precio de todos los productos y sub-combos incluidos.
     */
    public double calcularPrecioTotalRecursivo() {
        // Caso Base de Avance: Si es un artículo individual sin elementos adentro, su costo es su precio base.
        if (this.elementosIncluidos.isEmpty()) {
            return this.precioBase;
        }

        // Caso Recursivo: Suma el precio propio más el precio calculado recursivamente de cada sub-elemento
        double precioAcumulado = this.precioBase;
        for (ComboProducto item : this.elementosIncluidos) {
            precioAcumulado += item.calcularPrecioTotalRecursivo(); // Llamada recursiva
        }

        return precioAcumulado;
    }
}