package fpoly.andoid.ass_canhpdph12975;

import android.graphics.Bitmap;

public class Item {
    Bitmap image;
    String titile;

    public Item(Bitmap image, String titile) {
        this.image = image;
        this.titile = titile;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }


}
