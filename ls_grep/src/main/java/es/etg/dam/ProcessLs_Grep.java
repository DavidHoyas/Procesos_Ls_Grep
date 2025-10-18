package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ProcessLs_Grep {

    public static void main(String[] args) throws Exception {

        // 1. Lanzar proceso padre (ls)
        Process ls = Ls.lanzar();

        // 2. Leer salida del proceso ls y guardarla
        StringBuilder salidaLs = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ls.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                salidaLs.append(linea).append("\n");
            }
        }
        ls.waitFor();

        // 3. Lanzar proceso hijo (grep)
        Process grep = Grep.lanzar();

        // 4. Pasar la salida de ls al proceso grep
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(grep.getOutputStream()))) {
            pw.print(salidaLs.toString());
        }

        // 5. Leer la salida final de grep
        System.out.println("Lineas que contienen 'a' : \n");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(grep.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        }

        grep.waitFor();
    }
}

