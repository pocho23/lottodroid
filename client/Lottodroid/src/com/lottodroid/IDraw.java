package com.lottodroid;

import android.content.Context;
import android.view.View;


public interface IDraw {	
	public void bindData(View v);
	
	/* Como no puedo definir en una interfaz
	 * atributos y despues si quiero acceder 
	 * al xml de cada sorteo, he creado este
	 * metodo --> Pablo!! :S */
	public int getLayoutResource();
}
