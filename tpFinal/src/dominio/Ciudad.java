package dominio;

import java.util.Objects;

public class Ciudad {
	private String nombre;
	private boolean alojamientoDisponible;
	private boolean esSede;
	
	
	
	public Ciudad(String nombre, boolean alojamientoDisponible, boolean esSede) {
		this.nombre = nombre;
		this.alojamientoDisponible = alojamientoDisponible;
		this.esSede = esSede;
	}
	public Ciudad(String nombre) {
		this.nombre = nombre;
		this.alojamientoDisponible = false;
		this.esSede =false;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean getAlojamientoDisponible() {
		return alojamientoDisponible;
	}
	public void setAlojamientoDisponible(boolean alojamientoDisponible) {
		this.alojamientoDisponible = alojamientoDisponible;
	}
	public boolean getEsSede() {
		return esSede;
	}
	public void setEsSede(boolean esSede) {
		this.esSede = esSede;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ciudad other = (Ciudad) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equalsIgnoreCase(other.nombre))
            return false;
        return true;
    }
	@Override
	public String toString() {
		return nombre;
	}




}
