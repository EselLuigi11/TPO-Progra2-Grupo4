package modelo;

import interfaces.IPersona;

public class Persona implements IPersona{
	private String nombre;
	private int dni;
	private String genero;

public Persona(String nombre, int dni, String genero) {
	this.nombre = nombre;
	this.dni = dni;
	this.genero = genero;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public int getDni() {
	return dni;
}

public void setDni(int dni) {
	this.dni = dni;
}

public String getGenero() {
	return genero;
}

public void setGenero(String genero) {
	this.genero = genero;
}

@Override
public String toString() {
	return "Persona [nombre=" + nombre + ", dni=" + dni + ", genero=" + genero + "]";
}


}///Cierre Clase
