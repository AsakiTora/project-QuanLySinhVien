package fpoly.andoid.ass_canhpdph12975.ActivityQLSV;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.Adapter.TbLopAdapter;
import fpoly.andoid.ass_canhpdph12975.DAO.TbLopDAO;
import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;
import fpoly.andoid.ass_canhpdph12975.R;

public class ViewDB extends ListActivity {
    TbLopAdapter tbLopAdapter;
    ListView listView;
    TbLopDAO tbLopDAO;
    ArrayList<TbLop> arrayList;
    TbLop currentObjLop = null;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_item_lop);
        //input();
        initView();
    }

    private void initView() {
        tbLopDAO = new TbLopDAO(this);
        tbLopDAO.open();
        arrayList = tbLopDAO.GetAll();
        tbLopAdapter = new TbLopAdapter(arrayList);
        listView = findViewById(android.R.id.list);
        listView.setAdapter(tbLopAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentObjLop = arrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewDB.this);
                builder.setMessage("Are you sure you want to delete this entry?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (currentObjLop != null){
                                    int res = tbLopDAO.Delete(currentObjLop);
                                    if (res > 0) {
                                        arrayList.clear();
                                        arrayList.addAll(tbLopDAO.GetAll());
                                        tbLopAdapter.notifyDataSetChanged();
                                        Toast.makeText(ViewDB.this, "Đã xóa thành công: " + currentObjLop.getId_lop(), Toast.LENGTH_SHORT).show();
                                        currentObjLop = null;
                                    }
                                }
                                dialog.cancel();
                            }
                        }
                );
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

    }

//    private void input() {
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("table");
//        TbLop tbLop = new TbLop();
//        String id = bundle.getString("id");
//        String name = bundle.getString("name");
//        tbLop.setId_lop(id);
//        tbLop.setTen_lop(name);
//        long kq = tbLopDAO.AddNew(tbLop);
//        if (kq > 0){
//            arrayList.clear();
//            arrayList.addAll(tbLopDAO.GetAll());
//            tbLopAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "Thêm mới thành công!" + kq, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Thêm mới thất bại!" + kq, Toast.LENGTH_SHORT).show();
//        }
//    }


}
