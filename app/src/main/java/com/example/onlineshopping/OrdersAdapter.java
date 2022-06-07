package com.example.onlineshopping;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>
{
    private OnItemClickListener ml;
public interface  OnItemClickListener
{
    void OnItemClick(int position);
    void OnDeleteClick(int position);
    void OnSum(int position);
    void Onsub(int position);
}
public void setOnItemClickListener(OnItemClickListener listener)
{
    ml=listener;
}
  public static class ViewHolder extends RecyclerView.ViewHolder
  {
      TextView name,quantity,price;
       ImageView delete;
       Button sumqu;
       Button subqu;

      public ViewHolder(@NonNull View itemView,OnItemClickListener listener) {
          super(itemView);
           name=(TextView)itemView.findViewById(R.id.txt_disname);
           quantity=(TextView)itemView.findViewById(R.id.txt_disquantity);
           price=(TextView)itemView.findViewById(R.id.txt_disprice);
           delete=(ImageView) itemView.findViewById(R.id.image_del);
           sumqu=(Button) itemView.findViewById(R.id.btn_sumqua);
           subqu=(Button)itemView.findViewById(R.id.btn_subque);
           sumqu.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(listener !=null)
                   {
                       int position=getAdapterPosition();
                       if(position !=RecyclerView.NO_POSITION)
                       {
                           listener.OnSum(position);
                       }
                   }
               }
           });
           subqu.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(listener !=null)
                   {
                       int position=getAdapterPosition();
                       if(position !=RecyclerView.NO_POSITION)
                       {
                           listener.Onsub(position);
                       }
                   }
               }
           });
          delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(listener !=null)
                  {
                      int position=getAdapterPosition();
                      if(position !=RecyclerView.NO_POSITION)
                      {
                          listener.OnDeleteClick(position);
                      }
                  }
              }
          });
      }
  }
  private Context con;
  private List<DisplayOrders> orders;
  public OrdersAdapter(Context c, List<DisplayOrders> OrdersList)
  {
      this.con=c;
      orders=OrdersList;

  }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(con).inflate(R.layout.displayorders,parent,false);
        return new ViewHolder(v,ml);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
     DisplayOrders o=orders.get(position);
     holder.name.setText(o.getName());
     holder.quantity.setText(o.getQuantity());
     holder.price.setText(o.getPrice());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
