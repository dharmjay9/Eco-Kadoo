package kadoo.myecotrip.kadoo.login;


import android.Manifest;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.Permission;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseActivity;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;
import zinka.com.permissionlib.ErrorCode;
import zinka.com.permissionlib.PermissionCallBack;
import zinka.com.permissionlib.PermissionFragment;

public class LoginActivity extends BaseActivity {

    private static final String PERMISSION_FRAGMENT = "permission_fragment";
    private EditText etUserName;
    private EditText etPassawrd;
    private Button btnGo;
    private String pass;
    private String email;
    private PermissionFragment permissionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.bt_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        etPassawrd = findViewById(R.id.et_password);
        etUserName = findViewById(R.id.et_username);
        //etPassawrd.setText("123");
        // etUserName.setText("gopal.kumar353@gmail.com");
        btnGo = findViewById(R.id.bt_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkValidation()) {
                    if (!permissionFragment.isPermissionGRanted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        getPermission();
                        return;
                    }

                    callApi();
                }
            }
        });

        getPermission();
    }

    private void getPermission() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        permissionFragment = (PermissionFragment) fragmentManager.findFragmentByTag(PERMISSION_FRAGMENT);
        if (permissionFragment == null) {
            permissionFragment = new PermissionFragment();
            fragmentManager.beginTransaction().add(permissionFragment, PERMISSION_FRAGMENT).commitNow();

        }
        permissionFragment.requestForPermission(new PermissionCallBack() {
            @Override
            public void permissionApproved(String... permissions) {

            }

            @Override
            public void permissionDenied(String... permissionName) {

            }

            @Override
            public void permissionNotInManifest(String... permissionName) {

            }

            @Override
            public void onError(ErrorCode errorCode) {

            }
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);

    }

    @Override
    protected void initView() {

    }

    private void callApi() {
        displayProgressDialog();
        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(email);
        loginRequest.setPassword(pass);
        RestClient.getInstance().doLogin(loginRequest, new KadooCallBack<LoginResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                hideProgressDialog();
            }

            @Override
            public void onSuccess(LoginResponse loginResponse) {

                //   Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();

                hideProgressDialog();
                Gson gson = new Gson();
                String appUser = gson.toJson(loginResponse.getContent());
                kadooLocalUser.setUser(appUser);
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();


            }
        });
    }

    private boolean checkValidation() {
        email = etUserName.getText().toString();
        pass = etPassawrd.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "User name is empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}

