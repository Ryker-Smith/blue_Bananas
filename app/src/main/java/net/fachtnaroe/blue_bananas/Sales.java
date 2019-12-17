package net.fachtnaroe.blue_bananas;


import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.ListView;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Notifier;

import java.util.ArrayList;
import java.util.List;


public class Sales extends Form implements HandlesEventDispatching {
    private Button butt1, btnDelete, btnAddNew, btnOrderIsCompleted;
    private VerticalArrangement Vertical, Vertical1, Vertical2;
    private HorizontalArrangement Horizon1, Horizon2btn;
    private Label title, Label_Username, Username, pIDLabel, NumberID, ThingsSale, thingsSold_Label, empty1, empty2, empty3;
    private ListView thingsWeSell, thingsSold;
    private String baseURL = "https://fachtnaroe.net/bananas?", TheUsername = MainActivity.getUsername(), pID = MainActivity.getPID(), SessionID = MainActivity.getSessionID(), URLsesh = "sessionID=", URLthings4sale = "&entity=thing&method=GET", URLThingsSold = "&entity=orders2&method=GET&sellerID=",  webThingDeletURL = "&entity=thing&method=DELETE&tID=", webOrderIsCompleteURL= "entity=orders&method=DELETE&oID=";
    private Web webGetThings4Sale, webThingsSold, webThingDelete, webThingOrderIsComplete;
    private Notifier GotTextNotifier;


    protected void $define() {

        this.BackgroundColor(COLOR_ORANGE);
        String startupValue=this.startupValue;
        String[] temp=startupValue.split("::");
        pID=temp[0];
        pID=pID.replaceAll("\"","");
        SessionID=temp[1];
        GotTextNotifier = new Notifier(this);
        Vertical = new VerticalArrangement(this);
        Vertical.HeightPercent(Component.LENGTH_FILL_PARENT);
        Vertical.Image("FDS_PossibleLogo_04.png");

        title = new Label(Vertical);
        title.Text("Food Delivery Service");
        title.FontSize(20);
        title.Width(Component.LENGTH_FILL_PARENT);
        title.TextAlignment(Component.ALIGNMENT_CENTER);
        title.BackgroundColor(Color.parseColor("#005200"));
        title.TextColor(COLOR_WHITE);

        Horizon1 = new HorizontalArrangement(Vertical);
        Horizon1.WidthPercent(Component.LENGTH_FILL_PARENT);
        Label_Username = new Label(Horizon1);
        Label_Username.Text("Username:");
        Label_Username.WidthPercent(35);
        Label_Username.FontSize(14);

        Username = new Label(Horizon1);
        Username.WidthPercent(45);
        Username.FontSize(14);
        Username.TextColor(COLOR_BLUE);
        Username.Text(TheUsername);

        pIDLabel = new Label(Horizon1);
        pIDLabel.Text("pID:");
        pIDLabel.FontSize(14);
        pIDLabel.WidthPercent(10);

        NumberID = new Label(Horizon1);
        NumberID.Text(pID);
        NumberID.TextColor(Component.COLOR_BLUE);
        NumberID.WidthPercent(5);
        NumberID.FontSize(14);

        ThingsSale = new Label(Vertical);
        ThingsSale.Text("My things for sale");
        ThingsSale.FontSize(14);
        ThingsSale.BackgroundColor(Color.parseColor("#005200"));
        ThingsSale.TextColor(Component.COLOR_WHITE);
        ThingsSale.Width(Component.LENGTH_FILL_PARENT);

        Vertical1 = new VerticalArrangement(Vertical);
        Vertical1.HeightPercent(25);
        thingsWeSell = new ListView(Vertical1);
        thingsWeSell.Height(Component.LENGTH_FILL_PARENT);
        thingsWeSell.TextSize(40);
        thingsWeSell.TextColor(COLOR_BLACK);
        thingsWeSell.BackgroundColor(Color.parseColor("#00000000"));

        Horizon2btn = new HorizontalArrangement(Vertical);
        Horizon2btn.WidthPercent(Component.LENGTH_FILL_PARENT);

        btnDelete = new Button(Horizon2btn);
        btnDelete.Text("Delete");
        btnDelete.WidthPercent(50);
        btnDelete.FontSize(12);

        btnAddNew = new Button(Horizon2btn);
        btnAddNew.Text("Add New");
        btnAddNew.WidthPercent(50);
        btnAddNew.FontSize(12);

        thingsSold_Label = new Label(Vertical);
        thingsSold_Label.Text("My things sold");
        thingsSold_Label.Width(Component.LENGTH_FILL_PARENT);
        thingsSold_Label.BackgroundColor(Color.parseColor("#005200"));
        thingsSold_Label.FontSize(12);
        thingsSold_Label.TextColor(COLOR_WHITE);

        Vertical2 = new VerticalArrangement(Vertical);
        Vertical2.HeightPercent(20);
        thingsSold = new ListView(Vertical2);
        thingsSold.Height(Component.LENGTH_FILL_PARENT);
        thingsSold.TextSize(40);
        thingsSold.TextColor(Component.COLOR_BLACK);
        thingsSold.BackgroundColor(Color.parseColor("#00000000"));

        btnOrderIsCompleted = new Button(Vertical);
        btnOrderIsCompleted.Width(Component.LENGTH_FILL_PARENT);
        btnOrderIsCompleted.Text("Order is completed");
        btnOrderIsCompleted.FontSize(12);

        webGetThings4Sale = new Web(this);
        webGetThings4Sale.Url(baseURL + URLsesh + "a1b2c3d4" + URLthings4sale);
        webGetThings4Sale.Get();

        webThingsSold = new Web(this);
        webThingsSold.Url(baseURL + URLsesh + "a1b2c3d4" + URLThingsSold);
        webThingsSold.Get();

        webThingDelete = new Web(this);
        webThingDelete.Url(baseURL + URLsesh + "a1b2c3d4" + URLthings4sale + webThingDeletURL);
        webThingDelete.Get();

        webThingOrderIsComplete  = new Web(this);
        webThingOrderIsComplete.Url(baseURL + URLsesh + "a1b2c3d4" + webOrderIsCompleteURL);
        webThingOrderIsComplete.Get();

        butt1 = new Button(Vertical);
        butt1.Text("Go Back");
        butt1.FontSize(12);
        butt1.Width(Component.LENGTH_FILL_PARENT);
        butt1.Height(Component.LENGTH_FILL_PARENT);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("Click")) {
            if (component.equals(butt1)) {
                mainActGo();
                return true;
            }
            else if (component.equals(btnAddNew)) {
                switchForm("AddNamesOfItems");
            }
            else if (component.equals(btnDelete)) {
                ThingsSale.Text(ThingsSale.Text() + " >");
                deletThis(thingsWeSell.Selection());
                return true;
            }
            else if (component.equals(btnOrderIsCompleted)) {
                Log.d("INFO:~~>", "A");
                thingsSold_Label.Text(thingsSold_Label.Text() + " >");
                removeCompletedOrder(thingsSold.Selection());
                Log.d("INFO:~~>", "B");
                return true;
            }
        }
        else if (eventName.equals("GotText")) {
            if (component.equals(webGetThings4Sale)) {
                sortJson4GetThings4Sale((String) params[3], "16");
            }
            else if (component.equals(webThingsSold)) {
                sortJson4GetThingsSold((String) params[3]);
            }
        }
        return false;
    }

    public void mainActGo() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
    public void deletThis(String selection){
        if((thingsWeSell.Selection().isEmpty())) {
            GotTextNotifier.ShowAlert("No Item Selected");
        }
        else {
            String tIDForURL = selection.substring(1, 3);
            webThingDelete.Url(webThingDeletURL+tIDForURL);
            webThingDelete.Get();
        }
    }
    public void removeCompletedOrder(String selection) {
        Log.d("INFO:~~>", "C");
        if ((thingsSold.Selection().isEmpty())) {
            GotTextNotifier.ShowAlert("No Order Selected");
            Log.d("INFO:~~>", "D");
        }
        else {
            Log.d("INFO:~~>", "E");
            String[] bits=selection.split("]");
            String orderNum=bits[0].replace("[","");
            String oIDForURL = selection.substring(1, 4);
            webThingOrderIsComplete.Url(baseURL + webOrderIsCompleteURL + orderNum);
            webThingOrderIsComplete.Get();
            btnOrderIsCompleted.Text(webThingOrderIsComplete.Url());
            GotTextNotifier.ShowAlert("Order " + orderNum + " removed");
            Log.d("INFO:~~>", "F");
            //webGetThingsSold.Get();
            //https://stackoverflow.com/questions/3053761/reload-activity-in-androids
        }
    }

    public void sortJson4GetThings4Sale(String jsonString, String pID) {
        // for loop to sort by pID
        String Temp1 = "";
        //Used https://stackoverflow.com/questions/48449004/java-storing-the-output-of-a-for-loop-into-an-array/48449039 and https://www.w3schools.com/java/java_ref_string.asp
        List<String> jsonIsMySon = new ArrayList<String>();
        char start = '{';
        char finish = '}';
        int e = 0;
        for (int i = 0; i < jsonString.length(); i++) {
            char thisChar = jsonString.charAt(i);
            if (thisChar == start) {

                e = i + 1;
            } else if ((thisChar == finish)) {
                String Temp2 = jsonString.substring(e, i);
                ;
                if (!(Temp2.contains("]"))) {
                    if (Temp2.contains("tSoldBy\":\"" + pID)) {
                        jsonIsMySon.add(Temp2);
                    }
                }
            }

        }
        //For Loop to Rearrange Data To How I want
        String Temp3 = "";
        for (int a = 0; a < jsonIsMySon.size(); a++) {
            String r1 = jsonIsMySon.get(a).replace("\",\"", ",");

            //Rearrange Json data [0]=tDescription,[1]=tID,[2]=tName,[3]=tPicture,[4]=tPrice,[5]=tSoldBy
            String[] keyValueArray = r1.split(",");
            jsonIsMySon.set(a, "[" + keyValueArray[1] + "]" + keyValueArray[2] + "(" + keyValueArray[0] + ")€" + keyValueArray[4]);
            if (a == 0) {
                Temp3 += jsonIsMySon.get(a);
            } else {
                Temp3 += "," + jsonIsMySon.get(a);
            }
        }

        //Format for use in listView-Remove KeyNames
        String r2 = Temp3.replace("\":\"", "");
        String r3 = r2.replace("\"tDescription", "");
        String r4 = r3.replace("tID", "");
        String r5 = r4.replace("tName", "");
        String r6 = r5.replace("tPrice", "");
        thingsWeSell.ElementsFromString(r6);
    }

    public void sortJson4GetThingsSold(String jsonString) {

        // for loop to sort by sellerID with pID
        String Temp1 = "";
        //Used https://stackoverflow.com/questions/48449004/java-storing-the-output-of-a-for-loop-into-an-array/48449039 and https://www.w3schools.com/java/java_ref_string.asp
        List<String> jsonIsMySon = new ArrayList<String>();
        char start = '{';
        char finish = '}';
        int e = 0;
        for (int i = 0; i < jsonString.length(); i++) {
            char thisChar = jsonString.charAt(i);
            if (thisChar == start) {
                e = i;
            } else if ((thisChar == finish)) {
                String Temp2 = jsonString.substring(e, i);
                if (!(Temp2.contains("]"))) {
                    if (Temp2.contains("sellerID\":\"")) {
                        jsonIsMySon.add(Temp2);
                    }
                }
            }

        }
        //For Loop to Rearrange Data To How I want
        String Temp3 = "";
        for (int a = 0; a < jsonIsMySon.size(); a++) {
            String r1 = jsonIsMySon.get(a).replace("\",\"", ",");

            //Rearrange Json data [0]=Name,[1]=oID,[2]=sellerID,[3]=slotNum,[4]=tName
            String[] keyValueArray = r1.split(",");
            jsonIsMySon.set(a, "[" + keyValueArray[1] + "]" + keyValueArray[4] + " for " + keyValueArray[0] + keyValueArray[3]);
            if (a == 0) {
                Temp3 += jsonIsMySon.get(a);
            } else {
                Temp3 += "," + jsonIsMySon.get(a);
            }
        }
        //Format for use in listView-Remove KeyNames
        String r2 = Temp3.replace("\":\"", "");
        String r3 = r2.replace("{\"Name", "");
        String r4 = r3.replace("oID", "");
        String r5 = r4.replace("sellerID", "");
        String r6 = r5.replace(pID, "");
        String r7 = r6.replace("slotNum", " Slot:");
        String r8 = r7.replace("tName", "");
        String r9 = r8.replace("\"", "");
        thingsSold.ElementsFromString(r9);
    }
}
