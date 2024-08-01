package dominio;

import java.util.Objects;

public class ClavePartido {
    private String equipo1;
    private String equipo2;

    public ClavePartido(String equipo1, String equipo2) {
    	if((equipo1.compareTo(equipo2))<0) {
    	     this.equipo1 = equipo1;
    	        this.equipo2 = equipo2;
    		
    	}else {  
    		
    		
    		this.equipo1 = equipo2;
        this.equipo2 = equipo1;
		
    		
    		
    	}
 
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    @Override
    public String toString() {
        return equipo1 + "-" + equipo2;
    }

    @Override
    public int hashCode() {
        // Convertimos a minúsculas para asegurar consistencia con equals
        return Objects.hash(equipo1 != null ? equipo1.toLowerCase() : null, 
                            equipo2 != null ? equipo2.toLowerCase() : null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClavePartido other = (ClavePartido) obj;
        return equipo1 != null && equipo1.equalsIgnoreCase(other.equipo1)
            && equipo2 != null && equipo2.equalsIgnoreCase(other.equipo2);
    }
}
