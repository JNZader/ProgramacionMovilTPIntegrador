package com.example.tpi.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpi.R;
import com.example.tpi.databinding.ActivityUserBinding;
import com.example.tpi.model.User;
import com.example.tpi.viewModel.UserViewModel;

/**
 * Actividad para interactuar con los usuarios.
 */
public class UserActivity extends AppCompatActivity {

    /**
     * Instancia de la clase ActivityUserBinding para interactuar con la interfaz de usuario.
     */
    private ActivityUserBinding binding;

    /**
     * Instancia del modelo de vista para la actividad de usuarios.
     */
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Infla la interfaz de usuario.
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        // Establece la interfaz de usuario como contenido de la actividad.
        setContentView(binding.getRoot());

        // Obtiene una instancia del modelo de vista para la actividad de usuarios.
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Configura los observadores para la interfaz de usuario.
        setupObservers();
        // Configura los listeners para los botones de la interfaz de usuario.
        setupListeners();
    }

    /**
     * Método que configura los observadores para la interfaz de usuario.
     */
    private void setupObservers() {
        // Observamos los cambios en el estado de la operación para mostrar mensajes de éxito o error
        viewModel.getOperationStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String status) {
                // Muestra un mensaje de éxito o error en la interfaz de usuario.
                Toast.makeText(UserActivity.this, status, Toast.LENGTH_SHORT).show();
                // Limpia los campos de la interfaz de usuario.
                limpiar();
            }
        });

        // Observamos el usuario actual para mostrarlo en la interfaz.
        viewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // Si el usuario no es null, muestra sus datos en la interfaz.
                if (user != null) {
                    mostrarUsuarioEnUI(user);
                }
            }
        });
    }

    /**
     * Método que configura los listeners para los botones de la interfaz de usuario.
     */
    private void setupListeners() {
        // Listener para botón de crear usuario.
        binding.btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos del usuario desde la interfaz.
                User usuario = obtenerDatosDeUsuario();
                // Crea un nuevo usuario con los datos obtenidos.
                viewModel.createUser(usuario, UserActivity.this);
            }
        });

        // Listener para botón de actualizar usuario.
        binding.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos del usuario desde la interfaz.
                User usuario = obtenerDatosDeUsuario();
                // Actualiza el usuario con los datos obtenidos.
                viewModel.updateUser(usuario, UserActivity.this);
            }
        });

        // Listener para botón de eliminar usuario.
        binding.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el ID del usuario desde la interfaz.
                String id = binding.itId.getText().toString().trim();
                // Elimina el usuario con el ID obtenido.
                viewModel.deleteUser(id, UserActivity.this);
            }
        });

        // Listener para botón de búsqueda de usuario.
        binding.btnReadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el correo electrónico del usuario desde la interfaz.
                String email = binding.itEmail.getText().toString().trim();
                // Busca el usuario con el correo electrónico obtenido.
                viewModel.getUser(email, UserActivity.this);
            }
        });

        // Listener para botón de regreso.
        binding.circleImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cierra la actividad.
                finish();
            }
        });
    }

    /**
     * Método que obtiene los datos del usuario desde la interfaz.
     *
     * @return Los datos del usuario.
     */
    private User obtenerDatosDeUsuario() {
        // Obtiene el nombre de usuario desde la interfaz.
        String username = binding.itUsuario.getText().toString();
        // Obtiene el correo electrónico desde la interfaz.
        String email = binding.itEmail.getText().toString().trim();
        // Obtiene el ID del usuario desde la interfaz.
        String id = binding.itId.getText().toString().trim();
        // Obtiene la contraseña del usuario desde la interfaz.
        String password = binding.itPassword.getText().toString().trim();
        // Crea un nuevo usuario con los datos obtenidos.
        return new User(id, username, email, password);
    }

    /**
     * Método que muestra los datos del usuario en la interfaz.
     *
     * @param user El usuario a mostrar.
     */
    private void mostrarUsuarioEnUI(User user) {
        // Muestra el nombre de usuario en la interfaz.
        binding.itUsuario.setText(user.getUsername());
        // Muestra el correo electrónico en la interfaz.
        binding.itEmail.setText(user.getEmail());
        // Muestra el ID del usuario en la interfaz.
        binding.itId.setText(user.getId());
        // Muestra la contraseña del usuario en la interfaz.
        binding.itPassword.setText(user.getPassword());
        // Muestra un mensaje de log en la consola.
        Log.d("mostrar", user.getId() + "-" + user.getUsername());
    }

    /**
     * Método que limpia los campos de la interfaz.
     */
    private void limpiar() {
        // Limpia el campo de nombre de usuario.
        binding.itUsuario.setText("");
        // Limpia el campo de correo electrónico.
        binding.itEmail.setText("");
        // Limpia el campo de ID.
        binding.itId.setText("");
        // Limpia el campo de contraseña.
        binding.itPassword.setText("");
    }
}