package com.example.fernandoambrosio.devask.Logica;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Cronometro extends Service {
    private  int segundos;
    private int minutos;
    private int milisegundos;

    public int getSegundos() {
        return segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getMilisegundos() {
        return milisegundos;
    }

    private IBinder mBinder = new LocalBinder();
    public Cronometro() {
        segundos=0;
        minutos=0;
        milisegundos=0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        this.sumatoria();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
    }
    private void sumatoria(){
        int milisegundo=0;
        while(true){
            milisegundo = sumaMilisengundos(milisegundo);
            milisegundos=milisegundo;
            if(milisegundo==1000){
                segundos=segundos+1;
                milisegundo=0;
            }
            if(segundos==60){
                minutos++;
                segundos=0;
            }
        }
    }
    private int sumaMilisengundos(int milisegundo){
        int nMilisengundo=0;
        try {
            Thread.sleep(10);
            nMilisengundo=milisegundo+10;
        } catch(InterruptedException e) {}
        finally{
            return nMilisengundo;
        }
    }
    private class LocalBinder extends Binder{
        public Cronometro getService(){
            return Cronometro.this;
        }
    }

}
