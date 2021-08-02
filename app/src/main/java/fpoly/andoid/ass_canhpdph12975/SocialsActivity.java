package fpoly.andoid.ass_canhpdph12975;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialsActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Button btndangxuat,btnchucnang;
    TextView tvname,tvemail,tvfirstname;
    CallbackManager callbackManager;
    String name, email, firstname;

    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

//        btnchucnang.setVisibility(View.INVISIBLE);
//        btndangxuat.setVisibility(View.INVISIBLE);
//        tvname.setVisibility(View.INVISIBLE);
//        tvemail.setVisibility(View.INVISIBLE);
//        tvfirstname.setVisibility(View.INVISIBLE);

        Anhxa();
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));

        setLogin_Button();
        setLogout_Button();
        chucNang();
    }
    private void chucNang(){
        btnchucnang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SocialsActivity.this,FBChucNangActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setLogout_Button(){
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                btnchucnang.setVisibility(View.INVISIBLE);
                btndangxuat.setVisibility(View.INVISIBLE);
                tvname.setVisibility(View.INVISIBLE);
                tvemail.setVisibility(View.INVISIBLE);
                tvfirstname.setVisibility(View.INVISIBLE);

                tvname.setText("");
                tvemail.setText("");
                tvfirstname.setText("");

                profilePictureView.setProfileId(null);
                loginButton.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setLogin_Button(){
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setVisibility(View.INVISIBLE);
                btnchucnang.setVisibility(View.INVISIBLE);
                btndangxuat.setVisibility(View.INVISIBLE);
                tvname.setVisibility(View.INVISIBLE);
                tvemail.setVisibility(View.INVISIBLE);
                tvfirstname.setVisibility(View.INVISIBLE);
                result();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    private void result(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");
                    firstname = object.getString("first_name");

                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                    tvemail.setText(email);
                    tvname.setText(name);
                    tvfirstname.setText(firstname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
    public void Anhxa(){
        profilePictureView = (ProfilePictureView)findViewById(R.id.imageprofilepictureview);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        btndangxuat = findViewById(R.id.bottondangxuat);
        btnchucnang = findViewById(R.id.buttonchucnang);
        tvname = findViewById(R.id.textviewname);
        tvemail = findViewById(R.id.textviewemail);
        tvfirstname = findViewById(R.id.textviewfirstname);
    }
    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
