package com.example.tpi.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpi.providers.AuthProvider;

/**
 * Clase que proporciona la lógica de negocio para la pantalla principal de la aplicación.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class MainViewModel extends ViewModel {
    /**
     * Instancia del proveedor de autenticación.
     */
    public final AuthProvider authProvider;

    /**
     * Constructor de la clase.
     */
    public MainViewModel(Context context) {
        // Crea una instancia del proveedor de autenticación.
        authProvider = new AuthProvider();
    }

    /**
     * Inicia sesión con el usuario proporcionado.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return LiveData que contiene el ID del usuario si el login es exitoso, null en caso de error.
     */
    public LiveData<String> login(String email, String password) {
        // Crea un LiveData para almacenar el resultado del login.
        MutableLiveData<String> loginResult = new MutableLiveData<>();
        // Inicia sesión con el usuario proporcionado.
        // Establece el ID del usuario en el LiveData si el login es exitoso.
        authProvider.signIn(email, password).observeForever(loginResult::setValue);
        // Regresa el LiveData.
        return loginResult;
    }

    /**
     * Verifica si la sesión del usuario está activa.
     *
     * @return LiveData que contiene true si la sesión está activa, false en caso contrario.
     */
    public LiveData<Boolean> verificarSesionActiva() {
        // Crea un LiveData para almacenar el resultado de la verificación de la sesión.
        MutableLiveData<Boolean> si = new MutableLiveData<>();
        // Obtiene el ID del usuario actualmente conectado.
        if (authProvider.getCurrentUserID() != null) {
            // Establece true en el LiveData si la sesión está activa.
            si.setValue(true);
        } else {
            // Establece false en el LiveData si la sesión no está activa.
            si.setValue(false);
        }
        // Regresa el LiveData.
        return si;
    }
}