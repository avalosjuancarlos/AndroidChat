package edu.galileo.android.androidchat.signup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.androidchat.AndroidChatApplication;
import edu.galileo.android.androidchat.R;
import edu.galileo.android.androidchat.contactlist.ui.ContactListActivity;
import edu.galileo.android.androidchat.login.LoginPresenter;
import edu.galileo.android.androidchat.login.ui.LoginView;

public class SignUpActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editTxtEmail)
    EditText inputEmail;
    @BindView(R.id.editTxtPassword)
    EditText inputPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout container;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setTitle(getString(R.string.signup_title));

        setupInjection();
    }

    private void setupInjection() {
        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        app.getSignUpComponent(this).inject(this);
    }

    @Override
    protected void onResume() {
        loginPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(), inputPassword.getText().toString());
    }

    @Override
    public void handleSignIn() {
        throw new UnsupportedOperationException("Operation is not valid in SignUpActivity");
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent = new Intent(this, ContactListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        throw new UnsupportedOperationException("Operation is not valid in SignUpActivity");
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_sign_up, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_sign_up), error);
        inputPassword.setError(msgError);
    }

    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }
}
