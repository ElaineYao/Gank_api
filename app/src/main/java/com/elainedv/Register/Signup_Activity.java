package com.elainedv.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elainedv.Gank.MainActivity;
import com.elainedv.Gank.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class Signup_Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = Signup_Activity.class.getSimpleName();
    private EditText signup_user, signup_pass;
    private Button signup_bt;
    private TextView signup_link;
    private String user_name, password;
    private ProgressDialog progressDialog;
    private User user = new User();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Realm.init(this);
        Log.i("------" + TAG + "-------", TAG);
        initView();
    }

    public void initView() {

        Log.i("---" + TAG + "   initview---", "findViewById");
        signup_user = (EditText) findViewById(R.id.signup_user);
        signup_pass = (EditText) findViewById(R.id.signup_password);
        signup_bt = (Button) findViewById(R.id.signup_bt);
        signup_link = (TextView) findViewById(R.id.signup_tv);

        Log.i("---" + TAG + "   initview---", "progressDialog");
        progressDialog = new ProgressDialog(Signup_Activity.this,
                R.style.AppTheme_Dialog);
        Log.i("---" + TAG + "   initview---", "configuration");
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("myRealm.realm")
                .build();
        realm = Realm.getInstance(configuration);

        signup_bt.setOnClickListener(this);
        signup_link.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            user_name = signup_user.getText().toString();
            password = signup_pass.getText().toString();
            Log.i("---" + TAG + "   onClick---", user_name);
            Log.i("---" + TAG + "   onClick---", password);
            //user.setUser(user_name);
            //user.setPassword(password);
            switch (view.getId()) {
                case R.id.signup_bt:
                    signup_bt.setEnabled(false);
                    sign_up();
                    break;

                case R.id.signup_tv:
                    Intent intent=new Intent(Signup_Activity.this,Login_Activity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                    break;

        }
    }

    public void sign_up() {
        Log.i("-----" + TAG + " signup-----", "signup");
        if (!validate()) {
            signup_bt.setEnabled(true);
            return;
        }

        progressDialog.setMessage("Creating account...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        //这里realm只能写异步，否则dialog得不到关闭
        //查询看用户名是否已经存在
        try {
            RealmResults<User> userList = realm.where(User.class).findAll();
            for (User user00 : userList) {
                if (user_name.equals(user00.getUser())) {
                    progressDialog.dismiss();
                    signup_user.setError("The name has already been used!");
                    signup_bt.setEnabled(true);
                    return;
                }
            }

            //若不存在则进行添加操作
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user0 = realm.createObject(User.class);
                    user0.setUser(user_name);
                    user0.setPassword(password);
                    Log.i("------Task onExecute---", user0.getUser());
                }
            });
            //User user100=realm.where(User.class).findFirst();
            //Log.i("------Task test---",user100.getUser());
            RealmResults<User> users = realm.where(User.class).findAll();
            for (User user2 : users) {
                Log.i("------Task onClick-----", user2.getUser());
                Log.i("------Task onClick-----", user2.getPassword());
                Intent intent=new Intent(Signup_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
            }
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
            Toast.makeText(Signup_Activity.this, "Unknown error.", Toast.LENGTH_SHORT).show();
            signup_bt.setEnabled(true);
            return;
        }

    }

    //用户名非空，3～20字符,密码同理
    public boolean validate() {
        Boolean valid = true;
        if (user_name.isEmpty() || user_name.length() < 3 || user_name.length() > 20) {
            signup_user.setError("between 3 and 20 characters");
            valid = false;
        } else {
            signup_user.setError(null);
        }
        if (password.isEmpty() || password.length() < 3 || password.length() > 20) {
            signup_pass.setError("between 3 and 20 characters");
            valid = false;
        } else {
            signup_pass.setError(null);
        }
        return valid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
