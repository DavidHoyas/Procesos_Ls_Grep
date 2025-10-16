package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ProcessLs_Grep {
    public static final String CONTENIDO = """
            Me gusta PSP y java
            PSP se programa en java
            es un modulo de DAM
            y se programa de forma concurrente en PSP
            PSP es programacion.""";

    public static final String comandoPADRE = "ls";
    public static final String[] comandoHIJO = {"grep", "a"};
    public static final String RESULTADO = "Lineas que contienen a : \n";

    public static void main(String[] args) throws Exception {

        Process ls = Runtime.getRuntime().exec(comandoPADRE);
        Process grep = Runtime.getRuntime().exec(comandoHIJO);

        try (OutputStream os = grep.getOutputStream(); PrintWriter pw = new PrintWriter(new OutputStreamWriter(os))) {
            ls.getInputStream().transferTo(os);
            pw.println(CONTENIDO);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(grep.getInputStream()))) {
            String linea;
            System.out.println(RESULTADO);
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }

        ls.waitFor();
        grep.waitFor();

    }

}