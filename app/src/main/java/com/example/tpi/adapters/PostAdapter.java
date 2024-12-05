package com.example.tpi.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpi.R;
import com.example.tpi.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adaptador para mostrar una lista de posts en una RecyclerView.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    /**
     * La lista de posts que se mostrarán en la RecyclerView.
     */
    private List<Post> posts;

    /**
     * Constructor para crear un adaptador con una lista de posts.
     *
     * @param posts La lista de posts que se mostrarán en la RecyclerView.
     */
    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * Crea un nuevo ViewHolder para un item en la lista.
     *
     * @param parent El contenedor de la vista.
     * @param viewType El tipo de vista a crear.
     * @return El nuevo ViewHolder.
     */
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Se infla la vista para el item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);

        // Se crea un nuevo ViewHolder con la vista inflada
        return new PostViewHolder(view);
    }

    /**
     * Asigna los datos a un ViewHolder existente.
     *
     * @param holder El ViewHolder que se asignará los datos.
     * @param position La posición del item en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        // Se obtiene el post en la posición actual
        Post post = posts.get(position);

        // Se establecen los textos de los campos del post
        holder.tvTitulo.setText(post.getTitulo());
        holder.tvDescripcion.setText(post.getDescripcion());

        // Se muestra el título del post en la consola
        Log.d("PostAdapter", "Título: " + post.getImagenes());

        // Se muestra la primera imagen del post si existe
        if (post.getImagenes().size() > 0) {
            // Se carga la primera imagen con Picasso
            Picasso.get()
                    .load(post.getImagenes().get(0))
                    .into(holder.ivImage1);
            // Se muestra la imagen
            holder.ivImage1.setVisibility(View.VISIBLE);
        }

        // Se muestra la segunda imagen del post si existe
        if (post.getImagenes().size() > 1) {
            // Se carga la segunda imagen con Picasso
            Picasso.get()
                    .load(post.getImagenes().get(1)) // Cargar la segunda imagen
                    .into(holder.ivImage2);
            // Se muestra la imagen
            holder.ivImage2.setVisibility(View.VISIBLE);
        }

        // Se muestra la tercera imagen del post si existe
        if (post.getImagenes().size() > 2) {
            // Se carga la tercera imagen con Picasso
            Picasso.get()
                    .load(post.getImagenes().get(2)) // Cargar la tercera imagen
                    .into(holder.ivImage3);
            // Se muestra la imagen
            holder.ivImage3.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Obtiene el número de items en la lista.
     *
     * @return El número de items en la lista.
     */
    @Override
    public int getItemCount() {
        return posts.size();
    }

    /**
     * ViewHolder para un item en la lista.
     */
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        /**
         * El campo de texto para el título del post.
         */
        TextView tvTitulo;

        /**
         * El campo de texto para la descripción del post.
         */
        TextView tvDescripcion;

        /**
         * La imagen 1 del post.
         */
        ImageView ivImage1;

        /**
         * La imagen 2 del post.
         */
        ImageView ivImage2;

        /**
         * La imagen 3 del post.
         */
        ImageView ivImage3;

        /**
         * Constructor para crear un ViewHolder con una vista.
         *
         * @param itemView La vista del item.
         */
        public PostViewHolder(View itemView) {
            super(itemView);
            // Se obtienen los campos de la vista
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ivImage1 = itemView.findViewById(R.id.ivImage1);
            ivImage2 = itemView.findViewById(R.id.ivImage2);
            ivImage3 = itemView.findViewById(R.id.ivImage3);
        }
    }
}