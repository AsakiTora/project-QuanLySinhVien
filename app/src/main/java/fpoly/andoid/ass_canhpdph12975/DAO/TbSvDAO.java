package fpoly.andoid.ass_canhpdph12975.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.CreateDb.CreateDb;
import fpoly.andoid.ass_canhpdph12975.DTO.TbSv;


public class TbSvDAO {
    private SQLiteDatabase database;
    private CreateDb createDbSv;

    public TbSvDAO(Context context){
        createDbSv = new CreateDb(context);
    }
    public void open(){
        database = createDbSv.getWritableDatabase();
    }
    public void close(){
        createDbSv.close();
    }

    public long Add(TbSv tbSv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TbSv.COL_CLASS, tbSv.getTen_lop());
        contentValues.put(TbSv.COL_NAME_SV, tbSv.getTen_sv());
        contentValues.put(TbSv.COL_BIRTH, tbSv.getNgay_sinh());
        long res = database.insert(TbSv.TB_NAME, null, contentValues);
        return res;
    }
    public int Delete(TbSv tbSv){
        return database.delete(TbSv.TB_NAME, "stt = " + tbSv.getStt(), null);
    }
    public ArrayList<TbSv> GetAll(){
        ArrayList<TbSv> dsSv = new ArrayList<>();
        String[] allCol = new String[]{TbSv.COL_STT, TbSv.COL_NAME_SV, TbSv.COL_BIRTH};
        Cursor cursor = database.query(TbSv.TB_NAME, allCol, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int stt = cursor.getInt(0);
            String ten_sv = cursor.getString(1);
            String ngay_sinh = cursor.getString(2);

            TbSv tbSv = new TbSv();
            tbSv.setStt(stt);
            tbSv.setTen_sv(ten_sv);
            tbSv.setNgay_sinh(ngay_sinh);


            dsSv.add(tbSv);
            cursor.moveToNext();
        }
        return dsSv;
    }
}
