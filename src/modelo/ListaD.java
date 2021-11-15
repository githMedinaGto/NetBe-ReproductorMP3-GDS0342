/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonathan Medina 
 * date: 29/10/2021
 */
public class ListaD {

    private NodoD inicio;
    private NodoD fin;
    private NodoD actual;

    public void insertar(NodoD nuevo) {
        NodoD aux = inicio;
        if (inicio == null) {//caso 1: Lista vacia
            fin = inicio = nuevo;
        } else {
            while (aux != null && nuevo.getDatos().getTitulo().compareTo(aux.getDatos().getTitulo()) > 0) {
                aux = aux.getSig();
            }
            if (aux == null) {//Caso 2: insertar al final
                fin.setSig(nuevo);
                nuevo.setAnt(fin);
                fin = nuevo;
            } else if (aux == inicio) {//Caso 3: insertar al inicio
                inicio = nuevo;
                nuevo.setSig(aux);
                aux.setAnt(nuevo);
            } else {//Caso 4: insertar en medio de dos nodos
                NodoD ant = aux.getAnt();
                //aux.getAnt().setSig(nuevo);
                nuevo.setSig(aux);
                aux.setAnt(nuevo);
                //nuevo.setAnt(aux.getAnt());
                nuevo.setAnt(ant);
            }
        }
        actual = nuevo;
    }

    public boolean estaVacia() {//isEmpty()
        return (inicio == null);
    }

    public void irInicio() {
        actual = inicio;
    }

    public void irFin() {
        actual = fin;
    }

    public void irAnterior() {
        //if(esInicio()){
        if (actual == inicio) {
            actual = fin;
        } else if (actual.getAnt() != null) {
            actual = actual.getAnt();
        }
    }

    public void irSiguiente() {
        //if(esFin()){
        if (actual == fin) {
            actual = inicio;
        } else if (actual.getSig() != null) {
            actual = actual.getSig();
        }
    }

    public boolean esInicio() {
        return (actual == inicio);
    }

    public boolean esFin() {
        return (actual == fin);
    }

    public NodoD getActual() {
        return actual;
    }

    public NodoD buscar(String titulo) {
        NodoD nodo = null;
        //Completar código
        NodoD aux = actual;
        while(aux != null){
            //Hcae la busqueda a partir del titulo de la cancion
           if(titulo.equalsIgnoreCase(aux.getDatos().getTitulo().toString())){
               nodo = aux;
               //Retorna los valores encontrados de la busqueda
               return(actual = aux);
           } 
           //Imprime los valores de la busqueda en el metodo siguiente
           aux = aux.getSig();
        }
        return nodo;
    }

    public void eliminar(String titulo) {
        //Completar código
        //Se implementa el metoto buscar
        NodoD nodo = this.buscar(titulo);
        NodoD aux;
        NodoD m;//<-- Utilizado para eliminar en el medio
        
        if(nodo != null){
            //Limpia la lista al no encontrar ningun nodo
            if (nodo.getSig() == null && nodo.getAnt() == null){
                inicio = null;
                fin = null;
                actual = null;
            //Elimina al inicio
            }else if(nodo.getAnt() == null){
                inicio = nodo.getSig();
                inicio.setAnt(null);
                actual = inicio;
            //Elimina al final
            }else if(nodo.getSig() == null){
                fin = nodo.getAnt();
                fin.setSig(null);
                actual = fin;
            //Elimina en medio
            }else{
                aux = nodo.getAnt();
                m = nodo.getSig();
                aux.setSig(m);
                m.setAnt(aux);
            }
        }
//        Se me hace un buen metodo, pero al momento de usarlo hay problemas ya 
//        que al eleminar y al dar siguiente aparecen los que estan borrados
//        y al dar final y sigueinte bota la aplicación
//        NodoD aux = inicio;
//        //Se deja en falso minetras que no se encuentre el nodo
//        boolean encontrado = false;
//        //Se hace la busqueda del nodo en la lista
        
//        while(aux != null && ! encontrado){
//            encontrado = (titulo.equalsIgnoreCase(aux.getDatos().getTitulo().toString()));
//            if(!encontrado){
//                aux = aux.getSig();
//            }
//        }
//        //Si encontrado es verdadero
//        if(encontrado){
//            //Elimina el primero de la lista
//            if(aux == inicio){
//                inicio = aux.getSig();
//                //Si la lista se encuentra vacia
//            }else {
//                aux.getAnt().setSig(aux.getSig());
//                if(aux.getSig() != null){
//                    aux.getSig().setAnt(aux.getAnt());
//                }
//            }
//            aux = null;
//        }
        
    }

    public List<Cancion> getLis() {
        List<Cancion> lista = new ArrayList();
        NodoD aux;

        aux = inicio;
        while (aux != null) {
            lista.add(aux.getDatos());
            aux = aux.getSig();
        }
        return lista;
    }
}
