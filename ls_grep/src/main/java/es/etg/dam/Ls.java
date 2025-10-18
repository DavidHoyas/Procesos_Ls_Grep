package es.etg.dam;

public class Ls {
    
    public static final String comandoPADRE = "ls";

    public static Process lanzar() throws Exception {
        return Runtime.getRuntime().exec(comandoPADRE);
            
    }
}
