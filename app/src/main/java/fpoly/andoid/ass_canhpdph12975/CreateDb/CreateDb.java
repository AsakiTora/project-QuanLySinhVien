package fpoly.andoid.ass_canhpdph12975.CreateDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CreateDb extends SQLiteOpenHelper {

    public static final String DB_NAME = "TbLop";
    public static final int DB_VERSION = 2;

    public CreateDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

//    public static final String DATABASE_CREATE = "create table "
//            + TB_NAME + "( "
//            + COL_NAME_STT + "interger primary key autoincrement, "
//            + COL_NAME_ID + "text not null, "
//            + COL_NAME_NAME + "text not null);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_cr_tb_lop = "CREATE TABLE tb_lop (" +
                "stt INTEGER NOT NULL UNIQUE," +
                "id_lop TEXT NOT NULL," +
                "ten_lop TEXT NOT NULL UNIQUE," +
                "PRIMARY KEY(stt AUTOINCREMENT) " +
                ")";
        db.execSQL(sql_cr_tb_lop);
        //db.execSQL(DATABASE_CREATE);

        String sql_cr_tb_sv = "CREATE TABLE tb_sv (" +
                "stt INTEGER NOT NULL UNIQUE," +
                "ten_lop TEXT NOT NULL," +
                "ten_sv TEXT NOT NULL," +
                "ngay_sinh DATE NOT NULL," +
                "PRIMARY KEY(stt AUTOINCREMENT) " +
                ")";
        db.execSQL(sql_cr_tb_sv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_lop" );
        // tạo lại bảng. mỗi khi sửa lệnh sql ở trên thì nâng version lên
        onCreate(db);
    }
}
