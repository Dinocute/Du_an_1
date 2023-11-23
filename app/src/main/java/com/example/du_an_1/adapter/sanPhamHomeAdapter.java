package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.Frame.HomeFragment;
import com.example.du_an_1.R;
import com.example.du_an_1.model.LoaiSanPham;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;

public class sanPhamHomeAdapter extends RecyclerView.Adapter<sanPhamHomeAdapter.ViewHoler> {
    private ArrayList<SanPham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    sanPhamDAO spdao;


    public sanPhamHomeAdapter(Context context, ArrayList<SanPham> list, ArrayList<HashMap<String, Object>> listHM ) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        spdao = new sanPhamDAO(context);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Biến để lưu trữ listener
    private OnItemClickListener mListener;

    // Phương thức để thiết lập listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanphamhome, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        LoaiSanPhamDAO loaispdao = new LoaiSanPhamDAO(context);
        LoaiSanPham loaisp = loaispdao.getLoaiSanPhamByID(list.get(position).getMaLoai());
        holder.txtmasp.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaGiay()));
        holder.txttensp.setText("Tên sản phẩm: " + list.get(position).getTenGiay());
        holder.txtgiasp.setText("Giá sản phẩm: " + String.valueOf(list.get(position).getGiaTien()));
        holder.txtmaloaisp.setText("Mã loại sản phẩm: " + loaisp.getMaLoai() + "");
        SanPham sp = list.get(position);

        holder.sanphamhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView txtmasp, txttensp, txtgiasp, txtmaloaisp, txttenloaisp;
        ImageView delete_sp;
        LinearLayout sanphamhome;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmasp = itemView.findViewById(R.id.txtma_san_pham);
            txttensp = itemView.findViewById(R.id.txt_ten_san_pham);
            txtgiasp = itemView.findViewById(R.id.txtgia_san_pham);
            txtmaloaisp = itemView.findViewById(R.id.txtma_loai_san_pham2);
            sanphamhome = itemView.findViewById(R.id.sanphamhome);



        }
    }


}