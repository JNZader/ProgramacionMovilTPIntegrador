package com.example.tpi.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpi.model.User;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Clase que proporciona métodos para interactuar con la base de datos de usuarios.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class UserProvider {

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param user Objeto User que contiene la información del usuario a crear.
     * @return LiveData que contiene el resultado de la operación de creación de usuario.
     */
    public LiveData<String> createUser(User user) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Validar si el usuario ya existe en la base de datos
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("email", user.getEmail());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null && users.size() > 0) {
                    // Si el usuario ya existe, devolver un mensaje de error
                    result.postValue("Usuario ya existe");
                } else {
                    // Crear un nuevo objeto User en Parse
                    ParseObject userObject = new ParseObject("User");
                    userObject.put("user_id", user.getId());
                    userObject.put("email", user.getEmail());
                    userObject.put("password", user.getPassword());

                    // Guardar el objeto User en Parse
                    userObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de creación de usuario es exitosa, devolver un mensaje de éxito
                                result.postValue("Usuario creado correctamente");
                            } else {
                                // Si la operación de creación de usuario falla, devolver un mensaje de error
                                result.postValue("Error al crear usuario");
                            }
                        }
                    });
                }
            }
        });

        return result;
    }

    /**
     * Obtiene un usuario de la base de datos por su correo electrónico.
     *
     * @param email Correo electrónico del usuario a obtener.
     * @return LiveData que contiene el objeto User obtenido.
     */
    public LiveData<User> getUser(String email) {
        final MutableLiveData<User> userData = new MutableLiveData<>();

        // Crear una consulta para obtener el usuario por su correo electrónico
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("email", email);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null && users.size() > 0) {
                    // Si se encuentra un usuario, crear un objeto User y establecer sus propiedades
                    ParseObject userObject = users.get(0);
                    User user = new User();
                    user.setId(userObject.getString("user_id"));
                    user.setEmail(userObject.getString("email"));
                    user.setPassword(userObject.getString("password"));

                    // Establecer el objeto User en el LiveData
                    userData.postValue(user);
                } else {
                    // Si no se encuentra un usuario, establecer el objeto User en null
                    userData.postValue(null);
                }
            }
        });

        return userData;
    }

    /**
     * Actualiza un usuario en la base de datos.
     *
     * @param user Objeto User que contiene la información del usuario a actualizar.
     * @return LiveData que contiene el resultado de la operación de actualización de usuario.
     */
    public LiveData<String> updateUser(User user) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Crear una consulta para obtener el usuario por su ID
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("user_id", user.getId());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null && users.size() > 0) {
                    // Si se encuentra un usuario, actualizar sus propiedades
                    ParseObject userObject = users.get(0);
                    userObject.put("email", user.getEmail());
                    userObject.put("password", user.getPassword());

                    // Guardar el usuario actualizado en Parse
                    userObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de actualización de usuario es exitosa, devolver un mensaje de éxito
                                result.postValue("Usuario actualizado correctamente");
                            } else {
                                // Si la operación de actualización de usuario falla, devolver un mensaje de error
                                result.postValue("Error al actualizar usuario");
                            }
                        }
                    });
                } else {
                    // Si no se encuentra un usuario, devolver un mensaje de error
                    result.postValue("Usuario no encontrado");
                }
            }
        });

        return result;
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param userId ID del usuario a eliminar.
     * @return LiveData que contiene el resultado de la operación de eliminación de usuario.
     */
    public LiveData<String> deleteUser(String userId) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Crear una consulta para obtener el usuario por su ID
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("user_id", userId);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> users, ParseException e) {
                if (e == null && users.size() > 0) {
                    // Si se encuentra un usuario, eliminarlo de Parse
                    ParseObject userObject = users.get(0);
                    userObject.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de eliminación de usuario es exitosa, devolver un mensaje de éxito
                                result.postValue("Usuario eliminado correctamente");
                            } else {
                                // Si la operación de eliminación de usuario falla, devolver un mensaje de error
                                result.postValue("Error al eliminar usuario");
                            }
                        }
                    });
                } else {
                    // Si no se encuentra un usuario, devolver un mensaje de error
                    result.postValue("Usuario no encontrado");
                }
            }
        });

        return result;
    }
}