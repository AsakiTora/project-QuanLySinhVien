package fpoly.andoid.ass_canhpdph12975;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import fpoly.andoid.ass_canhpdph12975.ActivityQLSV.QLSVActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<>();
    CustomView customView;
    Bitmap studentIcon,mapsIcon,newIcon,facebookIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentIcon = BitmapFactory.decodeResource(this.getResources(),R.drawable.student);
        mapsIcon = BitmapFactory.decodeResource(this.getResources(),R.drawable.laban);
        newIcon = BitmapFactory.decodeResource(this.getResources(),R.drawable.news);
        facebookIcon  = BitmapFactory.decodeResource(this.getResources(),R.drawable.face);

        gridArray.add(new Item(studentIcon,"Student"));
        gridArray.add(new Item(mapsIcon,"Maps"));
        gridArray.add(new Item(newIcon,"News"));
        gridArray.add(new Item(facebookIcon,"Social"));

        gridView = findViewById(R.id.gridView);
        customView = new CustomView(this,R.layout.row,gridArray);
        gridView.setAdapter(customView);
        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view,int position, long id){
        switch (position){
            case 0:
                Intent intent = new Intent(getApplicationContext(), QLSVActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(getApplicationContext(),NewsActivity.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(getApplicationContext(),SocialsActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
