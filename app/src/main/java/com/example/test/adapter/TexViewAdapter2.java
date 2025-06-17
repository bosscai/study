package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.TexItem;
import com.example.test.model.TexItem2;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TexViewAdapter2 extends RecyclerView.Adapter<TexViewAdapter2.TaxViewHolder> {

    private ArrayList<TexItem2> data;

    public TexViewAdapter2() { }

    public TexViewAdapter2(ArrayList<TexItem2> data) {
        this.data = data;
    }

    public void setData(ArrayList<TexItem2> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_tax2, parent, false);
        return new TaxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaxViewHolder holder, int position) {
        holder.tvMonth.setText(data.get(position).getMonth());
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvCompany.setText(data.get(position).getCompany());
        holder.tvIncome.setText("收入：" + data.get(position).getIncome());
        holder.tvTax.setText("已申报税额：" + data.get(position).getTax());
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return -1;
    }

    class TaxViewHolder extends RecyclerView.ViewHolder{
        TextView tvMonth;
        TextView tvTitle;
        TextView tvCompany;
        TextView tvIncome;
        TextView tvTax;
        public TaxViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMonth = itemView.findViewById(R.id.tv_month);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCompany = itemView.findViewById(R.id.tv_company);
            tvIncome = itemView.findViewById(R.id.tv_income);
            tvTax = itemView.findViewById(R.id.tv_tax);


        }
    }
}
