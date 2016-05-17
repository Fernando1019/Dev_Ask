package com.example.fernandoambrosio.devask.Logica;

import java.util.Random;

/**
 * Created by josueChaqui on 9/05/2016.
 */
public class Aleatorio {
    Random ran = new Random();
    int n=0;
    public int[] tresNumerosAleatorios(int num){
        int[] numeros = new int[3];
        int m=0;
        System.out.println(String.valueOf(m));
        while(m<3){
            boolean bandera=true;
            n= ran.nextInt(num+1);
            System.out.println(n);
            for(int b:numeros){
                if(n==b || n==0){
                    bandera=false;
                    break;
                }
            }
            if(bandera){
                numeros[m]= n;
                m++;
            }
            System.out.println(String.valueOf(m));
        }
        return numeros;
    }
    public int[] dosNumeros(){
        int[] numeros = new int[2];
        int m=0;
        while(m<2){
            boolean bandera=true;
            n= ran.nextInt(3);
            for(int b: numeros){
                System.out.println(String.valueOf(b)+" "+String.valueOf(n));
                if(n==b || n==0){
                    System.out.println("negado");
                    bandera=false;
                    break;
                }
            }
            if(bandera){
                numeros[m]= n;
                m++;
            }
        }
        return numeros;
    }
    public int numero(){
        while(true){
            int n = ran.nextInt(3);
            if(n!=0){
                return n;
            }
        }
    }
    public int numero(int num){
        while(true){
            int n = ran.nextInt(num);
                return n;
            }
        }
    public int numeroMenosCero(int num){
        while(true){
            int n = ran.nextInt(num);
            if(n!=0){
                return n;
            }
        }
    }
    }

