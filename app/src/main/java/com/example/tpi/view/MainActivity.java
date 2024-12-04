package com.example.tpi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpi.databinding.ActivityMainBinding;
import com.example.tpi.providers.AuthProvider;
import com.example.tpi.util.Validaciones;
import com.example.tpi.viewModel.MainViewModel;
import com.example.tpi.viewModel.MainViewModelFactory;

/**
 * Clase que representa la actividad principal de la aplicación.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Instancia de la vista de la actividad principal.
     */
    private ActivityMainBinding binding;

    /**
     * Instancia del modelo de vista de la actividad principal.
     */
    private MainViewModel viewModel;

    /**
     * Instancia del proveedor de autenticación.
     */
    private AuthProvider authProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla la vista de la actividad principal.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Crea una instancia del modelo de vista de la actividad principal.
        viewModel = new ViewModelProvider(this, new MainViewModelFactory(this)).get(MainViewModel.class);

        // Crea una instancia del proveedor de autenticación.
        authProvider = new AuthProvider(this);

        // Maneja los eventos de la vista.
        manejarEventos();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Maneja los eventos de la vista.
     */
    private void manejarEventos() {
        // Establece el evento de clic en el botón de registro.
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia la actividad de registro.
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // Establece el evento de clic en el botón de login.
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los valores de los campos de email y password.
                String email = binding.etEmail.getText().toString().trim();
                String pass = binding.etPassword.getText().toString().trim();

                // Valida los campos de email y password.
                if (!Validaciones.validarEmail(email)) {
                    // Muestra un mensaje de error si el email es incorrecto.
                    showToast("Email incorrecto");
                    return;
                }
                if (!Validaciones.controlarPassword(pass)) {
                    // Muestra un mensaje de error si la password es incorrecta.
                    showToast("Password incorrecto");
                    return;
                }

                // Inicia la autenticación con el usuario.
                viewModel.login(email, pass).observe(MainActivity.this, user_id -> {
                    // Si la autenticación es exitosa, inicia la actividad de home.
                    if (user_id != null) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //   intent.putExtra("user_id",user_id);
                        startActivity(intent);
                    } else {
                        // Muestra un mensaje de error si la autenticación falla.
                        showToast("Login fallido");
                    }
                });
            }
        });
    }

    /**
     * Muestra un mensaje de error en la pantalla.
     *
     * @param message Mensaje de error a mostrar.
     */
    private void showToast(String message) {
        // Muestra un mensaje de error en la pantalla.
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Limpia los campos de la vista.
        limpiarCampos();
    }

    /**
     * Limpia los campos de la vista.
     */
    private void limpiarCampos() {
        // Limpia el campo de email.
        binding.etEmail.setText("");
        // Limpia el campo de password.
        binding.etPassword.setText("");
    }
}