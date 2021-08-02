package fpoly.andoid.ass_canhpdph12975.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;
import fpoly.andoid.ass_canhpdph12975.R;

public class TbLopAdapter extends BaseAdapter {
    final ArrayList<TbLop> tbLopArrayList;

    public TbLopAdapter(ArrayList<TbLop> tbLopArrayList) {
        this.tbLopArrayList = tbLopArrayList;
    }

    @Override
    public int getCount() {
        return tbLopArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tbLopArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tbLopArrayList.get(position).getStt();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView;
        if (convertView == null){
            itemView = View.inflate(parent.getContext(), R.layout.list_item, null);
        } else
            itemView = convertView;
        TbLop tbLop = tbLopArrayList.get(position);
        TextView txt_stt = itemView.findViewById(R.id.txt_stt);
        TextView txt_id = itemView.findViewById(R.id.txt_id);
        TextView txt_name = itemView.findViewById(R.id.txt_name);

        txt_stt.setText(""+tbLop.getStt());
        txt_id.setText(tbLop.getId_lop());
        txt_name.setText(tbLop.getTen_lop());
        return itemView;
    }
}
