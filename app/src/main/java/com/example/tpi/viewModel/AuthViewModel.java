package com.example.tpi.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpi.providers.AuthProvider;

public class AuthViewModel extends ViewModel {

    /**
     * El proveedor de autenticación utilizado para realizar operaciones de autenticación.
     */
    private final AuthProvider authProvider;

    /**
     * Constructor para crear un objeto AuthViewModel.
     */
    public AuthViewModel() {
        // Se crea un objeto AuthProvider para realizar operaciones de autenticación.
        this.authProvider = new AuthProvider();
    }

    /**
     * Obtiene un flujo de datos que indica si el usuario ha realizado una operación de cierre de sesión.
     *
     * @return Un flujo de datos que indica si el usuario ha realizado una operación de cierre de sesión.
     */
    public LiveData<Boolean> logout() {
        // Se llama al método logout del objeto AuthProvider para realizar la operación de cierre de sesión.
        return authProvider.logout();
    }
}