package es.etg.dam;

public class Grep {

    public static final String[] comandoHIJO = {"grep", "a"};

    public static Process lanzar() throws Exception {

        return  Runtime.getRuntime().exec(comandoHIJO);
        
    }
    
}
