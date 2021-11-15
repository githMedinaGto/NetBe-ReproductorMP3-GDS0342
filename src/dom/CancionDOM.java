/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

import java.util.ArrayList;
import java.util.List;
import modelo.Cancion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jonathan Medina
 */
public class CancionDOM {
    public List<Cancion> read(String pathFile){
        List<Cancion> canciones = new ArrayList();
        Cancion cancion;
        try{
            Document d = DOMHelper.getDocument(pathFile);
            NodeList nodos = d.getElementsByTagName("cancion");
            //recorrer la lista de nodos
            String titulo;
            String interprete;
            String genero;
            String album;
            int year;
            String ruta;
            
            for(int i = 0; i < nodos.getLength(); i++){
                Element item = (Element)nodos.item(i);
                //Leer los datos del DOM
                titulo = item.getElementsByTagName("titulo").item(0).getTextContent();
                interprete = item.getElementsByTagName("interprete").item(0).getTextContent();
                genero = item.getElementsByTagName("genero").item(0).getTextContent();
                album = item.getElementsByTagName("album").item(0).getTextContent();
                year = Integer.parseInt(item.getElementsByTagName("year").item(0).getTextContent());
                ruta = item.getElementsByTagName("ruta").item(0).getTextContent();
                //Crear objeto Cancion
                cancion = new Cancion(titulo, interprete, album, genero, year, ruta);
                //Agregar el objeto Canción a la lista
                canciones.add(cancion);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return canciones;
    }
    
    public void save(List<Cancion> lista, String pathFileTarget){
        Cancion cancion;
        String pathFileSource = getClass().getResource("/recursos/FormatoCanciones.xml").toString();
        try{
            Document d = DOMHelper.getDocument(pathFileSource);
            Element canciones = d.getDocumentElement();
            //Recorrer la lista e insertar una a una las canciones
            String titulo;
            String interprete;
            String genero;
            String album;
            int year;
            String ruta;
            
            for(int i = 0; i < lista.size(); i ++){
                cancion = lista.get(i);//leer un objeto de la lista
                titulo = cancion.getTitulo();
                //recuperar los datos del objeto
                interprete = cancion.getInterprete();
                genero = cancion.getGenero();
                album = cancion.getAlbun();
                year = cancion.getYear();
                ruta = cancion.getRuta();
                //Crear la estructura del DOM
                
                //Crear tag cancion
                Element item = d.createElement("cancion");
                //Crear tag titulo
                Element tit = d.createElement("titulo");
                tit.appendChild(d.createTextNode(titulo));
                item.appendChild(tit);
                //Crear tag interprete
                Element inter = d.createElement("interprete");
                inter.appendChild(d.createTextNode(interprete));
                item.appendChild(inter);
                //Crear tag genero
                Element gen = d.createElement("genero");
                gen.appendChild(d.createTextNode(genero));
                item.appendChild(gen);
                //Crear tag album
                Element alb = d.createElement("album");
                alb.appendChild(d.createTextNode(album));
                item.appendChild(alb);
                //Crear tag year
                Element yea = d.createElement("year");
                yea.appendChild(d.createTextNode("" + year));
                item.appendChild(yea);
                //Crear tag ruta
                Element rut = d.createElement("ruta");
                rut.appendChild(d.createTextNode(ruta));
                item.appendChild(rut);
                
                canciones.appendChild(item);
            }
            //Guardar archivo XML
            DOMHelper.saveXML(d, pathFileTarget);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
