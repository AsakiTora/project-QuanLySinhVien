package fpoly.andoid.ass_canhpdph12975.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.CreateDb.CreateDb;
import fpoly.andoid.ass_canhpdph12975.DTO.TbLop;

public class TbLopDAO {
    private SQLiteDatabase database;
    private CreateDb createDbLop;

    public TbLopDAO(Context context){

        createDbLop = new CreateDb(context);
    }
    public void open() throws SQLException {
        database = createDbLop.getWritableDatabase();
    }
    public void close(){
        createDbLop.close();
    }

    public long AddNew(TbLop tbLop){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbLop.COL_NAME_ID, tbLop.getId_lop());
        contentValues.put(TbLop.COL_NAME_NAME, tbLop.getTen_lop());
        long res = database.insert(TbLop.TB_NAME, null, contentValues);
        return res;
    }

    public int Update(TbLop tbLop){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbLop.COL_NAME_ID, tbLop.getId_lop());
        contentValues.put(TbLop.COL_NAME_NAME, tbLop.getTen_lop());
        int res = database.update(TbLop.TB_NAME, contentValues, "stt" + tbLop.getStt(), null);
        return res;
    }

    public int Delete(TbLop tbLop){
        return database.delete(TbLop.TB_NAME, "stt = " + tbLop.getStt(), null);
    }

    public ArrayList<TbLop> GetAll(){
        ArrayList<TbLop> dsLop = new ArrayList<>();
        String[] danh_sach_cot_lay_du_lieu =
                new String[]{TbLop.COL_NAME_STT, TbLop.COL_NAME_ID, TbLop.COL_NAME_NAME};
        Cursor cursor = database.query(TbLop.TB_NAME, danh_sach_cot_lay_du_lieu, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int stt = cursor.getInt(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            TbLop tbLop = new TbLop();
            tbLop.setStt(stt);
            tbLop.setId_lop(id);
            tbLop.setTen_lop(name);
            dsLop.add(tbLop);
            cursor.moveToNext();
        }
        return dsLop;
    }

    public ArrayList<TbLop> getSpinner(){
        ArrayList<TbLop> list = new ArrayList<>();

        String[] lay_ds_cot = new String[]{TbLop.COL_NAME_NAME};

        Cursor cursor = database.query(TbLop.TB_NAME,lay_ds_cot, null, null, null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String ten_lop =cursor.getString(0);
            TbLop tbLop = new TbLop();
            tbLop.setTen_lop(ten_lop);
            list.add(tbLop);
            cursor.moveToNext();
        }
        return list;
    }
}
