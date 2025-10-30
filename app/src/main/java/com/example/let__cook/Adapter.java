package com.example.let__cook;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends BaseAdapter {

    //Khai bao cac tham so can dung
    private HelperFunction helperFunction;
    private Context mainActivity;
    private int Layout_item_id;
    private ArrayList<Food> arr_food;
    private ArrayList<Food> arr_food_filter;

    // Ham khoi tao adapter
    public Adapter(Context context,int layout, ArrayList<Food> arr_f,HelperFunction helper){
        this.mainActivity=context;
        this.Layout_item_id=layout;
        this.arr_food=arr_f;
        this.arr_food_filter=arr_f;
        this.helperFunction=helper;
    }

    // tra ve do dai cua DS  dung cho bo loc, de ListView biet ddc co bao nhieu hang r hien thi
    @Override
    public int getCount() {
        return arr_food_filter!=null?arr_food_filter.size():0;
    }
    //Tra ve doi tuong (Food) tai vi tri Position => Lay Du lieu tai 1 hang cu the
    @Override
    public Object getItem(int position) {
        return arr_food_filter.get(position);
    }
    // Tra ve food tai vi tri position thong qua getIdWork() nhung dang khac ve kieu DL nen de tam ntn
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Dinh nghia lop cho food items
    public class ViewHolder{
        TextView item_food_name,item_dif;
        ImageView item_seemore,item_food_img_path,item_staricon;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder small_item;
        if(convertView==null)
        {
            small_item=new ViewHolder();
            LayoutInflater layoutInflater=(LayoutInflater) mainActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(Layout_item_id,null);
            // Gan id khop voi cac phan tu cua doi tuong ViewHolder
            small_item.item_food_name=(TextView) convertView.findViewById(R.id.food_FI_small_name);
            small_item.item_dif=(TextView) convertView.findViewById(R.id.food_FI_small_diff);
            small_item.item_food_img_path=(ImageView) convertView.findViewById(R.id.food_FI_small_img);
            small_item.item_staricon=(ImageView) convertView.findViewById(R.id.food_FI_small_staricon);
            small_item.item_seemore=(ImageView) convertView.findViewById(R.id.food_FI_small_seemore);

            convertView.setTag(small_item);
        }
        else {
            small_item=(ViewHolder) convertView.getTag();
        }

        Food food=arr_food_filter.get(position);
        small_item.item_food_name.setText(food.getName_food());
        small_item.item_dif.setText(String.valueOf(food.getDifficulty()));
        small_item.item_food_img_path.setImageResource(food.getImgResId(mainActivity));
        // mainActivity là phần tử cần thiết Vì Android cần Context để truy cập tài nguyên (drawable) — không liên quan gì đến DataBase cả.

        // Các hàm ở nac UAD nhưng chưa làm kịp
        return convertView;
    };
    // Lay tu khoa o o tim kiem => loc du lieu trong FilterWork theo tu khoa do => cap nhat len ListView
    public Filter getFilter(){
        return  new Filter() {
            // ham xu ly loc du lieu  Tao 1 List<Work> moi -filtered- chua cac cong viec trng khop,
            // neu nhu ng dung ko nhap j thi cho filtered = FilterWork,
            // sau do tra ve doi tuong FilterResult chua cv da loc
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Food> filtered=new ArrayList<>();
                if(constraint==null||constraint.length()==0){
                    filtered=arr_food;
                }
                else{
                    String keyword=constraint.toString().trim().toLowerCase();
                    for(Food item:arr_food){
                        if(item.getName_food().trim().toLowerCase().contains(keyword)){
                            filtered.add(item);
                        }
                    }
                }
                FilterResults results=new FilterResults();
                results.values=filtered;
                results.count= filtered.size();
                return  results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // ham cap nhat giao dien voi du lieu da duoc loc
                arr_food_filter = new ArrayList<>((List<Food>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public void updateArrMain(List<Food> filter) {
        this.arr_food = new ArrayList<>(filter);
        this.arr_food_filter = new ArrayList<>(filter);
        notifyDataSetChanged();
    }
}
