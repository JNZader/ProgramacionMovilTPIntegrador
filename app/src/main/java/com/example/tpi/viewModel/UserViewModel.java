package com.example.tpi.viewModel;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.tpi.model.User;
import com.example.tpi.providers.AuthProvider;
import com.example.tpi.providers.UserProvider;

/**
 * Clase que representa el modelo de vista para la gestión de usuarios.
 */
public class UserViewModel extends ViewModel {
    /**
     * Instancia del proveedor de autenticación.
     */
    private final AuthProvider authProvider;

    /**
     * Instancia del proveedor de usuarios.
     */
    private final UserProvider userProvider;

    /**
     * LiveData que contiene el usuario actual.
     */
    private final MutableLiveData<User> currentUser = new MutableLiveData<>();

    /**
     * LiveData que contiene el estado de la operación.
     */
    private final MutableLiveData<String> operationStatus = new MutableLiveData<>();

    /**
     * Constructor de la clase.
     */
    public UserViewModel() {
        // Inicializa el proveedor de autenticación.
        authProvider = new AuthProvider();
        // Inicializa el proveedor de usuarios.
        userProvider = new UserProvider();
    }

    /**
     * Obtiene el LiveData que contiene el usuario actual.
     * @return El LiveData que contiene el usuario actual.
     */
    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    /**
     * Obtiene el LiveData que contiene el estado de la operación.
     * @return El LiveData que contiene el estado de la operación.
     */
    public LiveData<String> getOperationStatus() {
        return operationStatus;
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     * @param user El usuario a crear.
     * @param lifecycleOwner El dueño del ciclo de vida.
     */
    public void createUser(User user, LifecycleOwner lifecycleOwner) {
        // Obtiene la respuesta del proveedor de autenticación.
        authProvider.signUp(user.getEmail(), user.getPassword()).observe(lifecycleOwner, uid -> {
            // Si la respuesta es exitosa, crea el usuario en la base de datos.
            if (uid != null) {
                user.setId(uid);
                // Obtiene la respuesta del proveedor de usuarios.
                userProvider.createUser(user).observe(lifecycleOwner, status -> {
                    // Si la respuesta es exitosa, establece el estado de la operación.
                    if (status != null) {
                        operationStatus.setValue(status);
                    } else {
                        // Si la respuesta es fallida, establece el estado de la operación.
                        operationStatus.setValue("Error al crear usuario en Firestore");
                    }
                });
            } else {
                // Si la respuesta es fallida, establece el estado de la operación.
                operationStatus.setValue("Error al registrar usuario en FirebaseAuth");
            }
        });
    }

    /**
     * Actualiza un usuario en la base de datos.
     * @param user El usuario a actualizar.
     * @param lifecycleOwner El dueño del ciclo de vida.
     */
    public void updateUser(User user, LifecycleOwner lifecycleOwner) {
        // Obtiene la respuesta del proveedor de usuarios.
        LiveData<String> result = userProvider.updateUser(user);
        // Obtiene la respuesta del proveedor de usuarios.
        result.observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(String status) {
                // Establece el estado de la operación.
                operationStatus.setValue(result.getValue());
            }
        });
    }

    /**
     * Elimina un usuario de la base de datos.
     * @param userId El ID del usuario a eliminar.
     * @param lifecycleOwner El dueño del ciclo de vida.
     */
    public void deleteUser(String userId, LifecycleOwner lifecycleOwner) {
        // Obtiene la respuesta del proveedor de usuarios.
        LiveData<String> result = userProvider.deleteUser(userId);
        // Obtiene la respuesta del proveedor de usuarios.
        result.observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(String status) {
                // Establece el estado de la operación.
                operationStatus.setValue(status);
            }
        });
    }

    /**
     * Obtiene un usuario de la base de datos.
     * @param email El correo electrónico del usuario a obtener.
     * @param lifecycleOwner El dueño del ciclo de vida.
     */
    public void getUser(String email, LifecycleOwner lifecycleOwner) {
        // Obtiene la respuesta del proveedor de usuarios.
        LiveData<User> user = userProvider.getUser(email);
        // Obtiene la respuesta del proveedor de usuarios.
        user.observe(lifecycleOwner, new Observer<User>() {
            @Override
            public void onChanged(User foundUser) {
                // Si el usuario es encontrado, establece el usuario actual.
                if (foundUser != null) {
                    Log.d("User Info", "ID: " + foundUser.getId() + ", Username: " + foundUser.getUsername());
                    currentUser.setValue(foundUser);
                } else {
                    // Si el usuario no es encontrado, establece el estado de la operación.
                    operationStatus.setValue("No encontrado");
                }
            }
        });
    }
}