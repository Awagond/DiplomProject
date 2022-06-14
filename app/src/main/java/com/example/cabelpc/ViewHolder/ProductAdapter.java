package com.example.cabelpc.ViewHolder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cabelpc.Model.Products;
import com.example.cabelpc.ProductDetailsActivity;
import com.example.cabelpc.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products,ProductAdapter.ProductViewHolder>
{
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model)
    {
        holder.txtProductName.setText(model.getPname());
        holder.txtProductPrice.setText("$" + model.getPrice() );
        Picasso.get().load(model.getimage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ProductDetailsActivity.class);
                intent.putExtra("pid",model.getPid());
                v.getContext().startActivity(intent);
            }
        });

        holder.product_details_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ProductDetailsActivity.class);
                intent.putExtra("pid",model.getPid());
                v.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout,parent,false);
        return new ProductViewHolder(view);
    }


    class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public Button product_details_send_button;
        public TextView txtProductName, txtProductPrice;
        public ImageView imageView;
        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            txtProductName = (TextView) itemView.findViewById(R.id.product_name);
            txtProductPrice = (TextView) itemView.findViewById(R.id.product_prices);
            product_details_send_button = (Button) itemView.findViewById(R.id.product_details_send_button);
        }
    }
}
