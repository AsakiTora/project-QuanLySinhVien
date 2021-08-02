package fpoly.andoid.ass_canhpdph12975.ActivityQLSV;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.Adapter.TbLopAdapter;
import fpoly.andoid.ass_canhpdph12975.DAO.TbLopDAO;
import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;
import fpoly.andoid.ass_canhpdph12975.R;


public class QLSVActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAdd;
    private Button btnView;
    private Button btnManagement;
    public static final int CODE = 1;
    TbLopDAO tbLopDAO;
    ArrayList<TbLop> arrayList;
    TbLopAdapter tbLopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsv_main);
        initView();
        tbLopDAO = new TbLopDAO(QLSVActivity.this);
        // vì cái này là tạo mới nhưng cái dao chưa gọi open
        tbLopDAO.open();
        arrayList = tbLopDAO.GetAll();//???? xem ví dụ mẫu e mở lại ví dụ mẫu
        tbLopAdapter = new TbLopAdapter(arrayList);
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add);
        EditText edtId = dialog.findViewById(R.id.edtId);
        EditText edtName = dialog.findViewById(R.id.edtName);
        Button btnDel = dialog.findViewById(R.id.btnDel);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtId.setText("");
                edtName.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TbLop tbLop = new TbLop();
                tbLop.setId_lop(edtId.getText().toString());
                tbLop.setTen_lop(edtName.getText().toString());
                if (edtId.getText().toString().equals("") || edtName.getText().toString().equals("")){
                    Toast.makeText(QLSVActivity.this, "Class id not is empty!", Toast.LENGTH_SHORT).show();
//                } else if (t){
//                    Toast.makeText(QLSVActivity.this, "Class name not is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    long kq = tbLopDAO.AddNew(tbLop);

                    if(kq > 0){
                        //Log.d("DAO--", "Size arrayList before: " + arrayList.size());
                        //xóa phần tử đang hiển thị
                        arrayList.clear();
                        //add từ csdl
                        arrayList.addAll(tbLopDAO.GetAll());
//                    araylist này chưa khởi tạo nên nó bảo null
                        //show ra listview
                        tbLopAdapter.notifyDataSetChanged();
                        edtId.setText("");
                        edtName.setText("");
                        //Log.d("DAO--", "Size arrayList after: " + arrayList.size());
                        Toast.makeText(QLSVActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(QLSVActivity.this, "Thêm mới thất bại!" + kq, Toast.LENGTH_SHORT).show();
                    }

                }
                //check xem phần tử đã đc add chưa

            }
        });
        dialog.show();
    }

    private void initView() {
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnManagement = findViewById(R.id.btnManagement);
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnManagement.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                showDialog();
                break;
            case R.id.btnView:
                startActivity(new Intent(QLSVActivity.this, ViewDB.class));
                break;
            case R.id.btnManagement:
                startActivity(new Intent(QLSVActivity.this, QLSVSpinnerActivity.class));
                break;
            default:
                break;
        }
    }
}