package com.example.tpi.view;

import android.app.Application;

import com.example.tpi.R;
import com.parse.Parse;
import com.parse.ParseACL;

public class MyApplication extends Application {

    /**
     * Se llama cuando la aplicación se crea.
     */
    @Override
    public void onCreate() {
        // Llama al método de la clase padre para realizar las inicializaciones necesarias
        super.onCreate();

        // Habilita el almacenamiento local de datos.
        Parse.enableLocalDatastore(this);

        // Inicializa Parse con la configuración de la aplicación.
        Parse.initialize(new Parse.Configuration.Builder(this)
                // Obtiene el ID de la aplicación de Back4App.
                .applicationId(getString(R.string.back4app_app_id))
                // Obtiene la clave de cliente de Back4App.
                .clientKey(getString(R.string.back4app_client_key))
                // Obtiene la URL del servidor de Back4App.
                .server(getString(R.string.back4app_server_url))
                // Construye la configuración de Parse.
                .build()
        );

        // Crea un objeto ACL (Access Control List) por defecto.
        ParseACL defaultACL = new ParseACL();

        // Establece el acceso público para leer y escribir en el objeto ACL.
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);

        // Establece el objeto ACL por defecto para la aplicación.
        ParseACL.setDefaultACL(defaultACL, true);
    }
}