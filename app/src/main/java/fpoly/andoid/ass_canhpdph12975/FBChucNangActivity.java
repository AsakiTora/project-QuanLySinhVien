package fpoly.andoid.ass_canhpdph12975;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class FBChucNangActivity extends AppCompatActivity {
    EditText edTitle,edScription, edUrl;
    Button btnShareLink, btnShareAnh, btnShareVideo,btnChonVideo;
    ImageView imageView;
    VideoView videoView;
    ShareDialog shareDialog;
    ShareVideo shareVideo;
    ShareLinkContent shareLinkContent;

    public static  int Select_Image=1;
    public static  int Pick_Video=2;
    Bitmap bitmap;
    Uri selectVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbchuc_nang);


        AnhXa();
        shareDialog=new ShareDialog(FBChucNangActivity.this);

        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShareDialog.canShow(ShareLinkContent.class)){
                    shareLinkContent = new ShareLinkContent.Builder().setContentTitle(edTitle.getText().toString())
                            .setContentDescription(edScription.getText().toString())
                            .setContentUrl(Uri.parse(edUrl.getText().toString())).build();
                }
                shareDialog.show(shareLinkContent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,Select_Image);
            }
        });
        btnShareAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePhoto photo = new SharePhoto.Builder().
                        setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);
            }
        });
        btnChonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
                startActivityForResult(intent,Pick_Video);
            }
        });
        btnShareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareVideo = new ShareVideo.Builder()
                        .setLocalUrl(selectVideo)
                        .build();
                ShareVideoContent content  = new ShareVideoContent.Builder()
                        .setVideo(shareVideo)
                        .build();
                shareDialog.show(content);
                videoView.stopPlayback();
            }
        });
    }
    private  void AnhXa(){
        edTitle = findViewById(R.id.edTitle);
        edScription = findViewById(R.id.edscription);
        edUrl = findViewById(R.id.edUrl);
        btnShareLink = findViewById(R.id.btnShareLink);
        btnShareAnh = findViewById(R.id.btnShareImage);
        btnShareVideo = findViewById(R.id.btnShareVideo);
        btnChonVideo = findViewById(R.id.btnChonVideo);
        imageView = findViewById(R.id.imghinh);
        videoView = findViewById(R.id.video);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode==Select_Image && resultCode == RESULT_OK){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (requestCode ==Pick_Video && resultCode == RESULT_OK){
                selectVideo = data.getData();
                videoView.setVideoURI(selectVideo);
                videoView.start();
            }
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
