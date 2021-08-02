package fpoly.andoid.ass_canhpdph12975.ActivityQLSV;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.Adapter.SpinnerAdapter;
import fpoly.andoid.ass_canhpdph12975.Adapter.TbSvAdapter;
import fpoly.andoid.ass_canhpdph12975.DAO.TbLopDAO;
import fpoly.andoid.ass_canhpdph12975.DAO.TbSvDAO;
import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;
import fpoly.andoid.ass_canhpdph12975.DTO.TbSv;
import fpoly.andoid.ass_canhpdph12975.R;

public class QLSVSpinnerActivity extends AppCompatActivity {
    EditText edtNameSv, edtBrith;
    Button btnAddSv;
    TbLopDAO tbLopDAO;
    TbSvDAO tbSvDAO;
    TbSvAdapter tbSvAdapter;
    SpinnerAdapter spinnerAdapter;
    ListView listViewSv;
    ArrayList<TbLop> arrayListLop;
    ArrayList<TbSv> arrayListSv;
    TbSv currentObjSv = null;
    Spinner spinnerLop;
    String tenLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsv);
        initView();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        tbSvDAO.close();
        tbLopDAO.close();
    }

    private void initView() {
        edtNameSv = findViewById(R.id.edtNameSv);
        edtBrith = findViewById(R.id.edtBrith);
        btnAddSv = findViewById(R.id.btnAddSv);
        spinnerLop = findViewById(R.id.spn_class);

        tbLopDAO = new TbLopDAO(this);
        tbLopDAO.open();

        arrayListLop = tbLopDAO.getSpinner();
        spinnerAdapter = new SpinnerAdapter(arrayListLop);
        spinnerLop.setAdapter(spinnerAdapter);

        tbSvDAO = new TbSvDAO(this);
        tbSvDAO.open();

        arrayListSv = tbSvDAO.GetAll();
        tbSvAdapter = new TbSvAdapter(arrayListSv);
        listViewSv = findViewById(R.id.lv_ds_sv);
        listViewSv.setAdapter(tbSvAdapter);
        listViewSv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currentObjSv = arrayListSv.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(QLSVSpinnerActivity.this);
                builder.setMessage("Are you sure you want to delete this entry?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (currentObjSv != null){
                                    int res = tbSvDAO.Delete(currentObjSv);
                                    if (res > 0) {
                                        arrayListSv.clear();
                                        arrayListSv.addAll(tbSvDAO.GetAll());
                                        tbSvAdapter.notifyDataSetChanged();
                                        Toast.makeText(QLSVSpinnerActivity.this, "Đã xóa thành công: " + currentObjSv.getTen_sv(), Toast.LENGTH_SHORT).show();
                                        currentObjSv = null;
                                    }
                                }
                                dialog.cancel();
                            }
                        }
                );
                builder.setNegativeButton(
                        "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }
                );
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        spinnerLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TbLop tbLop = arrayListLop.get(position);
                tenLop = tbLop.getTen_lop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAddSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbSv tbSv = new TbSv();
                tbSv.setTen_lop(tenLop);
                tbSv.setTen_sv(edtNameSv.getText().toString());
                tbSv.setNgay_sinh(edtBrith.getText().toString());
                if (edtNameSv.getText().toString().equalsIgnoreCase("") || edtBrith.getText().toString().equals("")){
                    Toast.makeText(QLSVSpinnerActivity.this, "Name and birth is not null!", Toast.LENGTH_SHORT).show();
                } else {
                    long kq = tbSvDAO.Add(tbSv);

                    if (kq > 0) {
                        arrayListSv.clear();
                        arrayListSv.addAll(tbSvDAO.GetAll());
                        tbSvAdapter.notifyDataSetChanged();
                        edtNameSv.setText("");
                        edtBrith.setText("");
                        Toast.makeText(QLSVSpinnerActivity.this, "Saved successfully!" + kq, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QLSVSpinnerActivity.this, "Saved is existed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

}
