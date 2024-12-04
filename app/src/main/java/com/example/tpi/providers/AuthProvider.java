/**
 * Clase que proporciona métodos para autenticar y registrar usuarios en Parse.
 *
 * @author Javier Zader
 * @version 1.0
 */
package com.example.tpi.providers;

import static com.parse.Parse.getApplicationContext;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpi.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Clase que proporciona métodos para autenticar y registrar usuarios en Parse.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class AuthProvider {
    /**
     * Constructor de la clase.
     *
     * @param context Contexto de la aplicación.
     */
    public AuthProvider(Context context) {
        // Inicializa Parse con la configuración de la aplicación.
        Parse.initialize(new Parse.Configuration.Builder(context).applicationId(context.getString(R.string.back4app_app_id)).clientKey(context.getString(R.string.back4app_client_key)).server(context.getString(R.string.back4app_server_url)).build());
    }
    /**
     * Constructor de la clase.
     */
    public AuthProvider() {

    }

    /**
     * Inicia sesión con el usuario proporcionado.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return LiveData que contiene el ID del usuario si el login es exitoso, null en caso de error.
     */
    public LiveData<String> signIn(String email, String password) {
        // Crea un LiveData para almacenar el resultado del login.
        MutableLiveData<String> authResult = new MutableLiveData<>();
        // Inicia sesión con el usuario proporcionado.
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                // Si el login es exitoso, establece el ID del usuario en el LiveData.
                if (e == null) {
                    authResult.setValue(user.getObjectId());
                    Log.d("AuthProvider", "Usuario autenticado exitosamente: " + user.getObjectId());
                } else {
                    // Si hay un error en el login, establece null en el LiveData.
                    Log.e("AuthProvider", "Error en inicio de sesión: ", e);
                    authResult.setValue(null);
                }
            }
        });
        // Regresa el LiveData.
        return authResult;
    }

    /**
     * Registra un nuevo usuario en Parse.
     *
     * @param email    Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return LiveData que contiene el ID del usuario si el registro es exitoso, null en caso de error.
     */
    // Registro con Parse
    public LiveData<String> signUp(String email, String password) {
        // Crea un LiveData para almacenar el resultado del registro.
        MutableLiveData<String> authResult = new MutableLiveData<>();
        // Crea un nuevo usuario en Parse.
        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(password);
        // Registra el usuario en Parse.
        user.signUpInBackground(e -> {
            // Si el registro es exitoso, establece el ID del usuario en el LiveData.
            if (e == null) {
                authResult.setValue(user.getObjectId());
                Log.d("AuthProvider", "Usuario registrado exitosamente: " + user.getObjectId());
            } else {
                // Si hay un error en el registro, establece null en el LiveData.
                Log.e("AuthProvider", "Error en registro: ", e);
                authResult.setValue(null);
            }
        });
        // Regresa el LiveData.
        return authResult;
    }

    /**
     * Obtiene el ID del usuario actualmente conectado.
     *
     * @return LiveData que contiene el ID del usuario si está conectado, null en caso contrario.
     */
    public LiveData<String> getCurrentUserID() {
        // Crea un LiveData para almacenar el ID del usuario.
        MutableLiveData<String> currentUserId = new MutableLiveData<>();
        // Obtiene el usuario actualmente conectado.
        ParseUser currentUser = ParseUser.getCurrentUser();
        // Si el usuario está conectado, establece su ID en el LiveData.
        if (currentUser != null) {
            currentUserId.setValue(currentUser.getObjectId());
        }
        // Regresa el LiveData.
        return currentUserId;
    }

    /**
     * Desconecta al usuario actualmente conectado.
     *
     * @return LiveData que contiene true si el desconexión es exitosa, false en caso de error.
     */
    public LiveData<Boolean> logout() {
        // Crea un LiveData para almacenar el resultado de la desconexión.
        MutableLiveData<Boolean> logoutResult = new MutableLiveData<>();
        // Desconecta al usuario actualmente conectado.
        ParseUser.logOutInBackground(e -> {
            // Si la desconexión es exitosa, establece true en el LiveData.
            if (e == null) {
                logoutResult.setValue(true);
                // Elimina la caché de la aplicación.
                if (getApplicationContext() != null) {
                    getApplicationContext().getCacheDir().delete();
                }
                Log.d("AuthProvider", "Caché eliminada y usuario desconectado.");
            } else {
                // Si hay un error en la desconexión, establece false en el LiveData.
                logoutResult.setValue(false);
                Log.e("AuthProvider", "Error al desconectar al usuario: ", e);
            }
        });
        // Regresa el LiveData.
        return logoutResult;
    }
}