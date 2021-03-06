package com.jasapp.fernandoambrosio.devask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ViewFlipper;

/**
 * Created by josueChaqui on 25/04/2016.
 */
public class Slide  extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper flipp;
    int conteo =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.slide_ayuda);
        flipp = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        flipp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if(conteo<1) {
         flipp.startFlipping();
         flipp.setFlipInterval(2000);
         conteo++;

       }
       else{
         Intent intent = new Intent(Slide.this, Menu.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
         startActivity(intent);
         finish();
       }

    }
}
