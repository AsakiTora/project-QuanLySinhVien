package fpoly.andoid.ass_canhpdph12975;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomView extends ArrayAdapter<Item> {
    Context context;
    int resource;
    ArrayList<Item> data= new ArrayList<>();

    public CustomView(@NonNull Context context, int resource, ArrayList<Item> data) {
        super(context, resource,data);
        this.context = context;
        this.resource = resource;
        this.data =data;
    }
    static class RecordHoder{
        TextView txtTitle;
        ImageView imageItem;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View row= convertView;
        RecordHoder hoder = null;
        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row =inflater.inflate(resource,parent,false);
            hoder = new RecordHoder();
            hoder.txtTitle=row.findViewById(R.id.itemText);
            hoder.imageItem=row.findViewById(R.id.itemImage);
            row.setTag(hoder);
        }
        else {
            hoder= (RecordHoder)row.getTag();
        }
        Item item = data.get(position);
        hoder.txtTitle.setText(item.getTitile());
        hoder.imageItem.setImageBitmap(item.getImage());
        return row;
    }
}
