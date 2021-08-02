package fpoly.andoid.ass_canhpdph12975.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.DTO.TbSv;
import fpoly.andoid.ass_canhpdph12975.R;

public class TbSvAdapter extends BaseAdapter {
    final ArrayList<TbSv> tbSvArrayList;

    public TbSvAdapter(ArrayList<TbSv> tbSvArrayList) {
        this.tbSvArrayList = tbSvArrayList;
    }

    @Override
    public int getCount() {
        return tbSvArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tbSvArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tbSvArrayList.get(position).getStt();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = View.inflate(parent.getContext(), R.layout.list_item_sv, null);
        } else {
            view = convertView;
        }
        TbSv tbSv = tbSvArrayList.get(position);
        TextView txt_stt_sv = view.findViewById(R.id.txt_stt_sv);
        TextView txt_name_sv = view.findViewById(R.id.txt_name_sv);
        TextView txt_brith_sv = view.findViewById(R.id.txt_brith_sv);

        txt_stt_sv.setText("" + tbSv.getStt());
        txt_name_sv.setText(tbSv.getTen_sv());
        txt_brith_sv.setText(tbSv.getNgay_sinh());

        return view;
    }
}
