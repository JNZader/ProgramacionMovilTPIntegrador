package com.example.tpi.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityCompat;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utilidades para manejar imágenes en la aplicación.
 */
public class ImageUtils {

    /**
     * Pide permisos para acceder a imágenes en la galería.
     *
     * @param activity La actividad que solicita los permisos.
     * @param permisos Los permisos que se solicitan.
     * @param requestCode El código de solicitud de permisos.
     */
    public static void pedirPermisos(Activity activity, String[] permisos, int requestCode) {
        // Se ajusta la lista de permisos según la versión de Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 o superior
            // En Android 13 o superior, solo se necesita el permiso para leer imágenes
            permisos = new String[]{Manifest.permission.READ_MEDIA_IMAGES};
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android 6.0 o superior
            // En Android 6.0 o superior, se necesita el permiso para leer el almacenamiento externo
            permisos = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        // Se solicitan los permisos
        ActivityCompat.requestPermissions(activity, permisos, requestCode);
    }

    /**
     * Abre la galería para seleccionar una imagen.
     *
     * @param context El contexto de la aplicación.
     * @param launcher El launcher de actividad que maneja la respuesta de la galería.
     */
    public static void openGallery(Context context, ActivityResultLauncher<Intent> launcher) {
        // Se crea un intent para abrir la galería
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Se lanza la galería
        launcher.launch(intent);
    }

    /**
     * Sube una imagen a Parse.
     *
     * @param context El contexto de la aplicación.
     * @param imageUri La URI de la imagen a subir.
     * @param callback El callback que maneja el resultado de la subida.
     */
    public static void subirImagenAParse(Context context, Uri imageUri, ImageUploadCallback callback) {
        // Se abre el flujo de entrada de la imagen
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(imageUri);
            // Se lee el contenido de la imagen en un arreglo de bytes
            byte[] bytes = getBytesFromInputStream(inputStream);

            // Si el arreglo de bytes es null, se llama al callback con una excepción
            if (bytes == null) {
                callback.onFailure(new Exception("El arreglo de bytes es null"));
                return;
            }
            // Se crea un archivo de Parse con la imagen
            ParseFile parseFile = new ParseFile("image.jpg", bytes);
            // Se sube el archivo de Parse de forma asíncrona
            parseFile.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    // Si la subida es exitosa, se llama al callback con la URL de la imagen
                    if (e == null) {
                        String imageUrl = parseFile.getUrl();
                        callback.onSuccess(imageUrl);
                    } else {
                        // Si la subida falla, se llama al callback con la excepción
                        callback.onFailure(e);
                    }
                }
            });
        } catch (IOException e) {
            // Si hay un error al leer la imagen, se llama al callback con la excepción
            callback.onFailure(e);
        } finally {
            // Se cierra el flujo de entrada de la imagen
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Lee los bytes de un flujo de entrada.
     *
     * @param inputStream El flujo de entrada a leer.
     * @return El arreglo de bytes leído.
     * @throws IOException Si hay un error al leer el flujo de entrada.
     */
    private static byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        // Se crea un flujo de salida para almacenar los bytes leídos
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        // Se define el tamaño del buffer para leer los bytes
        int bufferSize = 1024;
        // Se crea un arreglo de bytes para almacenar el contenido del buffer
        byte[] buffer = new byte[bufferSize];

        // Se lee el contenido del flujo de entrada en el buffer
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            // Se escribe el contenido del buffer en el flujo de salida
            byteBuffer.write(buffer, 0, len);
        }

        // Se devuelve el arreglo de bytes leído
        return byteBuffer.toByteArray();
    }

    /**
     * Interfaz para manejar el resultado de la subida de imágenes.
     */
    public interface ImageUploadCallback {
        /**
         * Se llama cuando la subida de la imagen es exitosa.
         *
         * @param imageUrl La URL de la imagen subida.
         */
        void onSuccess(String imageUrl);

        /**
         * Se llama cuando la subida de la imagen falla.
         *
         * @param e La excepción que causó el error.
         */
        void onFailure(Exception e);
    }
}