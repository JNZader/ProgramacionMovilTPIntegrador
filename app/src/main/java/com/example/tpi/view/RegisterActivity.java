package com.example.tpi.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import com.example.tpi.databinding.ActivityRegisterBinding;
import com.example.tpi.model.User;
import com.example.tpi.viewModel.RegisterViewModel;
import com.example.tpi.util.Validaciones;

/**
 * Clase que representa la actividad de registro de usuarios.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Objeto que se utiliza para vincular la vista con la lógica de negocio.
     */
    private ActivityRegisterBinding binding;

    /**
     * Objeto que se utiliza para obtener la lógica de negocio para el registro de usuarios.
     */
    private RegisterViewModel viewModel;

    /**
     * Método que se llama cuando la actividad se crea.
     *
     * @param savedInstanceState Objeto que contiene los datos de la actividad anterior.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflar la vista de la actividad
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtener la lógica de negocio para el registro de usuarios
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // Observar el resultado del registro de usuario
        viewModel.getRegisterResult().observe(this, result -> showToast(result));
        // Manejar los eventos de la vista
        manejarEventos();
    }

    /**
     * Método que se llama cuando se presiona el botón de registro.
     */
    private void manejarEventos() {
        // Evento volver a login
        binding.imageBack.setOnClickListener(v -> finish());

        // Evento de registro
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Realizar el registro de usuario
                realizarRegistro();
            }
        });
    }

    /**
     * Método que se llama cuando se presiona el botón de registro.
     */
    private void realizarRegistro() {
        // Obtener los datos de los campos de texto
        String usuario = binding.etUsername.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String pass = binding.etPassword.getText().toString().trim();
        String pass1 = binding.etPassword2.getText().toString().trim();
        // Validaciones de entrada
        if (!Validaciones.validarUsuario(usuario)) {
            // Mostrar un mensaje de error si el usuario es incorrecto
            showToast("Usuario incorrecto");
            return;
        }
        if (!Validaciones.validarEmail(email)) {
            // Mostrar un mensaje de error si el correo es inválido
            showToast("El correo no es válido");
            return;
        }
        String passError = Validaciones.validarPass(pass, pass1);
        if (passError != null) {
            // Mostrar un mensaje de error si las contraseñas no coinciden
            showToast(passError);
            return;
        }
        // Crear un objeto User con los datos del usuario
        User user = new User(usuario, email, pass);
        // Realizar el registro de usuario
        viewModel.register(user);
    }

    /**
     * Método que muestra un mensaje de toast con el texto proporcionado.
     *
     * @param message Texto del mensaje de toast.
     */
    private void showToast(String message) {
        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
    }
}