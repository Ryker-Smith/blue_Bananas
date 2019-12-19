package net.fachtnaroe.blue_bananas;
// more stuff stuff

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;

import org.json.JSONException;
import org.json.JSONObject;

// Hello
public class MainActivity extends Form implements HandlesEventDispatching {

    private Label lblScreenTitle, lblSpacing01, lblUsername, lblPassword, lblSpacing02;
    private TextBox txtUsername;
    private PasswordTextBox pwdPassword;
    private CheckBox chkBuyer, chkSeller;
    private Button btnLogin;
    private HorizontalArrangement hzaBuyerSeller, horiz2;
    private VerticalArrangement Vertical2;
    private String strPass = "",
            strURLlogin = "https://fachtnaroe.net/bananas?cmd=LOGIN&user=",
            strURLpwd = "&pass=",
            weh;
    private Web webLoginConnection;
    private Notifier notifierOK;
    private boolean ResponseContent;
    private static String Snd="BananaDelivery_File",
            FrstUser="",
            SndUser="",
            SessionId="",
            pID="";

    protected void $define() {

        webLoginConnection = new Web(this);
        notifierOK = new Notifier(this);
        notifierOK.BackgroundColor(Component.COLOR_BLUE);
        notifierOK.TextColor(Component.COLOR_WHITE);

        Vertical2 = new VerticalArrangement(this);
        Vertical2.HeightPercent(Component.LENGTH_FILL_PARENT);
        Vertical2.Image("FDS_PossibleLogo_04.png");

        this.BackgroundColor(COLOR_ORANGE);

        lblScreenTitle = new Label(Vertical2);
        lblScreenTitle.Text("Food Delivery Service");
        lblScreenTitle.Width(Component.LENGTH_FILL_PARENT);
        lblScreenTitle.FontSize(36);
        lblScreenTitle.TextAlignment(Component.ALIGNMENT_CENTER);

        lblSpacing01 = new Label(Vertical2);
        lblSpacing01.Text("");
        lblSpacing01.FontSize(20);

        lblUsername = new Label (Vertical2);
        lblUsername.Text("Username");
        lblUsername.Width(Component.LENGTH_FILL_PARENT);
        lblUsername.TextAlignment(Component.ALIGNMENT_CENTER);

        txtUsername = new TextBox(Vertical2);
        txtUsername.Text("blueshop");
        txtUsername.Width(Component.LENGTH_FILL_PARENT);

        lblSpacing01.Text("");
        lblSpacing01.FontSize(20);

        lblPassword = new Label(Vertical2);
        lblPassword.Width(Component.LENGTH_FILL_PARENT);
        lblPassword.Text("Password");
        lblPassword.TextAlignment(Component.ALIGNMENT_CENTER);
        pwdPassword = new PasswordTextBox(Vertical2);
        pwdPassword.Text("tcfetcfe");
        pwdPassword.Width(Component.LENGTH_FILL_PARENT);

        hzaBuyerSeller = new HorizontalArrangement(Vertical2);

        lblSpacing02 = new Label(this);
        lblSpacing02.Text("");
        lblSpacing02.FontSize(20);

        hzaBuyerSeller = new HorizontalArrangement(Vertical2);
        hzaBuyerSeller.WidthPercent(100);
        hzaBuyerSeller.HeightPercent(10);

        chkBuyer = new CheckBox(hzaBuyerSeller);
        chkBuyer.Text("Buyer");
        chkBuyer.WidthPercent(LENGTH_FILL_PARENT);
        chkBuyer.Checked(false);

        chkSeller = new CheckBox(hzaBuyerSeller);
        chkSeller.Text("Seller");
        chkSeller.WidthPercent(LENGTH_FILL_PARENT);
        chkSeller.Checked(true);

        btnLogin = new Button(Vertical2);
        btnLogin.Width(Component.LENGTH_FILL_PARENT);
        btnLogin.Height(100);
        btnLogin.Text("Login");
        btnLogin.BackgroundColor(Color.parseColor("#00520000"));
        btnLogin.FontSize(35);
        btnLogin.FontBold(true);
        btnLogin.TextAlignment(Component.ALIGNMENT_CENTER);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
        EventDispatcher.registerEventForDelegation(this, "ChangedEvent", "Changed");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("Click")) {
            if (component.equals(btnLogin)) {
                loginBtnClick();
                return true;
            }
        }
        else if( eventName.equals("Changed") ) {
            if (component.equals(chkBuyer)) {
                chkSeller.Checked( !chkBuyer.Checked());
                return true;
            }
            else if (component.equals(chkSeller)) {
                chkBuyer.Checked( !chkSeller.Checked());
                return true;
            }
        }
        else if (eventName.equals("GotText")) {
            btnLogin.Text("Login");
            loginResult(params);
            return true;
        }
        return false;
    }

    public void loginBtnClick() {
        SndUser = txtUsername.Text();
        FrstUser = txtUsername.Text();
        strPass = pwdPassword.Text();
        webLoginConnection.Url(strURLlogin + SndUser + strURLpwd + strPass);
        webLoginConnection.Get();
        btnLogin.Text("Pressed");
    }

    public void loginResult(Object[] params) {
        weh = (String) params[3];
        Log.d("TEST ","M");
        Log.d("TEST ",(String)params[3]);
        if (params[1].toString().equals("200") ) try {
            Log.d("TEST ","G");
            JSONObject parser = new JSONObject(weh);
            String result = parser.getString("Status");
            SessionId = parser.getString("sessionID");
            pID = parser.getString("pID");
            Log.d("TESTING", "["+pID+"]["+SessionId+"]");
            if (result.equals("OK")) {
                Log.d("TEST ","H");
                notifierOK.ShowAlert("OK");
                BackgroundColor(Component.COLOR_GREEN);
                NextPlaceGo();
            }
            else {
                notifierOK.ShowAlert("Error");
                BackgroundColor(Component.COLOR_RED);
            }
        }
        catch (JSONException e) {
            Log.d("TEST ","I");
        }
        Log.d("TEST ","L");
    }

    public void NextPlaceGo() {
        if(chkSeller.Checked()){
            switchFormWithStartValue("Sales", pID + "::" + SessionId);
        }
        else {
            switchFormWithStartValue("Order", pID + "::" + SessionId);
        }
    }

    public static String getData(){
        return FrstUser;
    }
    public static String getUsername(){
        return SndUser;
    }
    public static String getSessionID(){
        return SessionId;
    }
    public static String getPID(){
        return pID;
    }
}

