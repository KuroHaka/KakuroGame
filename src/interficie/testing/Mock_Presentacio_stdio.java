package interficie.testing;

import java.io.IOException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mock_Presentacio_stdio {
	public Mock_Presentacio_stdio(){}
	
	//Llegir String
	public static String llegirString(){
            String retorn = "";
            try {
                retorn=new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException ex) {
                Logger.getLogger(Mock_Presentacio_stdio.class.getName()).log(Level.SEVERE, null, ex);
            }
            return retorn;
	}
	
        //Llegir Caracter
	public static char llegirCaracter(){
            char retorn;
            String i=null;
            try{
                i = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error de lectura");
            }
            retorn = i.charAt(0);
            return retorn;
	}
        
	//Llegir Enter
	public static int llegirEnter(){
            int retorn;
            String i=null;
            try{
                i=new BufferedReader (new InputStreamReader (System.in)).readLine();
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error de lectura");
            }
            retorn=Integer.parseInt(i);
            return retorn;
	}
	
	//Llegir Float	
	public static float llegirFloat(){
            float retorn;
            String i=null;
            try{
                i=new BufferedReader (new InputStreamReader (System.in)).readLine();
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("Error de lectura");
            }
            retorn=Float.parseFloat(i);
            return retorn;
	}
	
	//Llegir Double	
	public static double llegirDouble(){
		double retorn;
		String i=null;
		try{
                    i=new BufferedReader (new InputStreamReader (System.in)).readLine();
		}catch(IOException e){
                    e.printStackTrace();
                    System.out.println("Error de lectura");
		}
		retorn=Double.parseDouble(i);
		return retorn;
	}
	
	// Fer print segons tipus
	public static void escriure(String nom){
            System.out.println(nom);
	}
	public static void escriure(int nom){
            System.out.println(nom);
	}
	public static void escriure(float nom){
            System.out.println(nom);
	}
	public static void escriure(double nom){
            System.out.println(nom);
	}
}
