package com.lottodroid;

import android.view.View;

// TODO(omar): javadoc, y piensa sobre el nombre de la interfaz
interface IDraw {
  void bindData(View v);

  /*
   * Como no puedo definir en una interfaz atributos y despues si quiero acceder
   * al xml de cada sorteo, he creado este metodo --> Pablo!! :S
   * 
   * No veo el problema. El que dice qué layout resource usar es la clase
   * que implementa a esta interfaz, asi que esto esta bien asi.
   */
  int getLayoutResource();
}
