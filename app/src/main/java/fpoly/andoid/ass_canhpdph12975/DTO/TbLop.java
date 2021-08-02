package fpoly.andoid.ass_canhpdph12975.DTO;

public class TbLop {
    int stt;
    String id_lop;
    String ten_lop;

    public static final String TB_NAME = "tb_lop";
    public static final String COL_NAME_STT = "stt";
    public static final String COL_NAME_ID = "id_lop";
    public static final String COL_NAME_NAME = "ten_lop";

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getId_lop() {
        return id_lop;
    }

    public void setId_lop(String id_lop) {
        this.id_lop = id_lop;
    }

    public String getTen_lop() {
        return ten_lop;
    }

    public void setTen_lop(String ten_lop) {
        this.ten_lop = ten_lop;
    }
}
