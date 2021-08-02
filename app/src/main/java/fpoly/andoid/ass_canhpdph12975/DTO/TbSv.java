package fpoly.andoid.ass_canhpdph12975.DTO;

public class TbSv {
    String ten_sv, ten_lop, ngay_sinh;
    int stt;
    public static final String TB_NAME = "tb_sv";
    public static final String COL_STT = "stt";
    public static final String COL_CLASS = "ten_lop";
    public static final String COL_NAME_SV = "ten_sv";
    public static final String COL_BIRTH = "ngay_sinh";

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTen_sv() {
        return ten_sv;
    }

    public void setTen_sv(String ten_sv) {
        this.ten_sv = ten_sv;
    }

    public String getTen_lop() {
        return ten_lop;
    }

    public void setTen_lop(String ten_lop) {
        this.ten_lop = ten_lop;
    }

    public String getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }
}
