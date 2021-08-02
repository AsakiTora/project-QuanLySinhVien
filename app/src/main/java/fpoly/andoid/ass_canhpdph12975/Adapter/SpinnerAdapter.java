package fpoly.andoid.ass_canhpdph12975.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;
import fpoly.andoid.ass_canhpdph12975.R;


public class SpinnerAdapter extends BaseAdapter {
    final ArrayList<TbLop> tbLopArrayList;

    public SpinnerAdapter(ArrayList<TbLop> tbLopArrayList) {
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
        View viewSpinner;
        if (convertView == null){
            viewSpinner = View.inflate(parent.getContext(), R.layout.spinner_item, null);
        } else {
            viewSpinner = convertView;
        }
        TbLop tbLop = tbLopArrayList.get(position);

        TextView txt_it_sp = viewSpinner.findViewById(R.id.txt_item_spinner);

        txt_it_sp.setText(tbLop.getTen_lop());

        return viewSpinner;
    }
}
