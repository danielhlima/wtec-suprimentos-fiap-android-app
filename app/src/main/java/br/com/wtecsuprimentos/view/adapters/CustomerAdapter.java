package br.com.wtecsuprimentos.view.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.wtecsuprimentos.R;
import br.com.wtecsuprimentos.domain.entities.Customer;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{

    private List<Customer> customers;
    private ClickRecyclerViewHelper clickRecyclerViewHelper;

    public CustomerAdapter(List<Customer> customers, ClickRecyclerViewHelper clickRecyclerViewHelper){
        this.customers = customers;
        this.clickRecyclerViewHelper = clickRecyclerViewHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("DABUEK", "Customer: "+position+" set.");

        holder.getTextView().setText((customers.get(position).getRazaoSocial()));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DABUEK", "Customer selecionado "+getAdapterPosition());
                    clickRecyclerViewHelper.onCustomClick(customers.get(getAdapterPosition()));
                }
            });

            textView = (TextView) itemView.findViewById(R.id.tv_cusmtomer_name);
        }

        public TextView getTextView(){
            return textView;
        }
    }
}
