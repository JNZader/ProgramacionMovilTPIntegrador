package com.example.tpi.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpi.model.Post;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Clase que proporciona métodos para interactuar con la base de datos de posts.
 *
 * @author Javier Zader
 * @version 1.0
 */
public class PostProvider {

    /**
     * Crea un nuevo post en la base de datos.
     *
     * @param post Objeto Post que contiene la información del post a crear.
     * @return LiveData que contiene el resultado de la operación de creación de post.
     */
    public LiveData<String> createPost(Post post) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Validar si el post ya existe en la base de datos
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("titulo", post.getTitulo());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> posts, ParseException e) {
                if (e == null && posts.size() > 0) {
                    // Si el post ya existe, devolver un mensaje de error
                    result.postValue("Post ya existe");
                } else {
                    // Crear un nuevo objeto Post en Parse
                    ParseObject postObject = new ParseObject("Post");
                    postObject.put("titulo", post.getTitulo());
                    postObject.put("descripcion", post.getDescripcion());
                    postObject.put("duracion", post.getDuracion());
                    postObject.put("categoria", post.getCategoria());
                    postObject.put("presupuesto", post.getPresupuesto());
                    postObject.put("imagenes", post.getImagenes());

                    // Guardar el objeto Post en Parse
                    postObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de creación de post es exitosa, devolver un mensaje de éxito
                                result.postValue("Post creado correctamente");
                            } else {
                                // Si la operación de creación de post falla, devolver un mensaje de error
                                result.postValue("Error al crear post");
                            }
                        }
                    });
                }
            }
        });

        return result;
    }

    /**
     * Obtiene un post de la base de datos por su título.
     *
     * @param titulo Título del post a obtener.
     * @return LiveData que contiene el objeto Post obtenido.
     */
    public LiveData<Post> getPost(String titulo) {
        final MutableLiveData<Post> post = new MutableLiveData<>();

        // Crear una consulta para obtener el post por su título
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("titulo", titulo);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> posts, ParseException e) {
                if (e == null && posts.size() > 0) {
                    // Si se encuentra un post, crear un objeto Post y establecer sus propiedades
                    ParseObject postObject = posts.get(0);
                    Post postObjectPost = new Post();
                    postObjectPost.setTitulo(postObject.getString("titulo"));
                    postObjectPost.setDescripcion(postObject.getString("descripcion"));
                    postObjectPost.setDuracion(postObject.getInt("duracion"));
                    postObjectPost.setCategoria(postObject.getString("categoria"));
                    postObjectPost.setPresupuesto(postObject.getDouble("presupuesto"));
                    postObjectPost.setImagenes(postObject.getList("imagenes"));

                    // Establecer el objeto Post en el LiveData
                    post.postValue(postObjectPost);
                } else {
                    // Si no se encuentra un post, establecer el objeto Post en null
                    post.postValue(null);
                }
            }
        });

        return post;
    }

    /**
     * Actualiza un post en la base de datos.
     *
     * @param post Objeto Post que contiene la información del post a actualizar.
     * @return LiveData que contiene el resultado de la operación de actualización de post.
     */
    public LiveData<String> updatePost(Post post) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Crear una consulta para obtener el post por su título
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("titulo", post.getTitulo());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> posts, ParseException e) {
                if (e == null && posts.size() > 0) {
                    // Si se encuentra un post, actualizar sus propiedades
                    ParseObject postObject = posts.get(0);
                    postObject.put("descripcion", post.getDescripcion());
                    postObject.put("duracion", post.getDuracion());
                    postObject.put("categoria", post.getCategoria());
                    postObject.put("presupuesto", post.getPresupuesto());
                    postObject.put("imagenes", post.getImagenes());

                    // Guardar el post actualizado en Parse
                    postObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de actualización de post es exitosa, devolver un mensaje de éxito
                                result.postValue("Post actualizado correctamente");
                            } else {
                                // Si la operación de actualización de post falla, devolver un mensaje de error
                                result.postValue("Error al actualizar post");
                            }
                        }
                    });
                } else {
                    // Si no se encuentra un post, devolver un mensaje de error
                    result.postValue("Post no encontrado");
                }
            }
        });

        return result;
    }

    /**
     * Elimina un post de la base de datos.
     *
     * @param titulo Título del post a eliminar.
     * @return LiveData que contiene el resultado de la operación de eliminación de post.
     */
    public LiveData<String> deletePost(String titulo) {
        final MutableLiveData<String> result = new MutableLiveData<>();

        // Crear una consulta para obtener el post por su título
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("titulo", titulo);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> posts, ParseException e) {
                if (e == null && posts.size() > 0) {
                    // Si se encuentra un post, eliminarlo de Parse
                    ParseObject postObject = posts.get(0);
                    postObject.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // Si la operación de eliminación de post es exitosa, devolver un mensaje de éxito
                                result.postValue("Post eliminado correctamente");
                            } else {
                                // Si la operación de eliminación de post falla, devolver un mensaje de error
                                result.postValue("Error al eliminar post");
                            }
                        }
                    });
                } else {
                    // Si no se encuentra un post, devolver un mensaje de error
                    result.postValue("Post no encontrado");
                }
            }
        });

        return result;
    }
}