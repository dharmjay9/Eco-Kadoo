package kadoo.myecotrip.kadoo.login;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kadoo.myecotrip.kadoo.R;
import kadoo.myecotrip.kadoo.base.BaseActivity;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.network.ErrorCodes;
import kadoo.myecotrip.kadoo.network.KadooCallBack;
import kadoo.myecotrip.kadoo.network.RestClient;

public class LoginActivity extends BaseActivity {

    private EditText etUserName;
    private EditText etPassawrd;
    private Button btnGo;
    private String pass;
    private String email;


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
    }

    @Override
    protected void initView() {
        etPassawrd = findViewById(R.id.et_password);
        etUserName = findViewById(R.id.et_username);
        btnGo = findViewById(R.id.bt_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkValidation()) {
                    callApi();
                }
            }
        });
    }

    private void callApi() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(email);
        loginRequest.setPassword(pass);
        RestClient.getInstance().doLogin(loginRequest, new KadooCallBack<LoginResponse>() {
            @Override
            public void onFailure(String s, ErrorCodes errorCodes) {

            }

            @Override
            public void onSuccess(LoginResponse loginResponse) {

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

