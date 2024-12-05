package com.example.tpi.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.tpi.model.User;
import com.example.tpi.providers.AuthProvider;

/**
 * Clase que proporciona la lógica de negocio para el registro de usuarios.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class RegisterViewModel extends ViewModel {

    /**
     * LiveData que contiene el resultado del registro de usuario.
     */
    private final MutableLiveData<String> registerResult = new MutableLiveData<>();

    /**
     * Objeto que proporciona la lógica de negocio para la autenticación de usuarios.
     */
    private final AuthProvider authProvider;

    /**
     * Constructor de la clase.
     */
    public RegisterViewModel() {
        // Inicializar el objeto AuthProvider
        this.authProvider = new AuthProvider();
    }

    /**
     * Obtiene el LiveData que contiene el resultado del registro de usuario.
     *
     * @return LiveData que contiene el resultado del registro de usuario.
     */
    public LiveData<String> getRegisterResult() {
        return registerResult;
    }

    /**
     * Realiza el registro de un usuario en la base de datos.
     *
     * @param user Objeto User que contiene la información del usuario a registrar.
     */
    public void register(User user) {
        // Realizar el registro de usuario en la base de datos
        LiveData<String> result = authProvider.signIn(user.getEmail(), user.getPassword());

        // Observar el resultado del registro
        result.observeForever(new Observer<>() {
            @Override
            public void onChanged(String objectId) {
                // Verificar si el registro fue exitoso
                if (objectId != null) {
                    // Establecer el resultado del registro en el LiveData
                    registerResult.setValue(objectId);
                    Log.d("RegisterViewModel", "Usuario registrado con ID: " + objectId);
                } else {
                    // Registro fallido
                    // Establecer el resultado del registro en el LiveData
                    registerResult.setValue(null);
                    Log.e("RegisterViewModel", "Error durante el registro.");
                }

                // Eliminar el observador del LiveData
                result.removeObserver(this);
            }
        });
    }
}