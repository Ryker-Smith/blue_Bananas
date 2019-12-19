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
        import com.google.appinventor.components.runtime.util.YailList;


        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;


public class Sales extends Form implements HandlesEventDispatching {
    private Button butt1, btnDelete, btnAddNew, btnOrderIsCompleted;
    private VerticalArrangement Vertical1, Vertical2;
    private HorizontalArrangement Horizon1, Horizon2btn;
    private Label title, Label_Username, Username, pIDLabel, NumberID, ThingsSale, thingsSold_Label, empty1, empty2, empty3;
    private ListView thingsWeSell, thingsSold;
    private String baseURL = "https://fachtnaroe.net/bananas?", TheUsername = MainActivity.getUsername(), pID = MainActivity.getPID(), SessionID = MainActivity.getSessionID(), URLsesh = "sessionID=", URLthings4sale = "&entity=thing&method=GET", URLThingsSold = "&entity=orders&method=GET", webThingDeletURL = "&entity=thing&method=DELETE&tID=", webOrderIsCompleteURL = "&entity=orders&method=DELETE&oID=";
    private Web webGetThings4Sale, webThingsSold, webThingDelete, webThingOrderIsComplete;
    private Notifier GotTextNotifier;


    protected void $define() {

        BackgroundImage("FDS_PossibleLogo_03.png");
        this.BackgroundColor(COLOR_ORANGE);

        GotTextNotifier = new Notifier(this);
        GotTextNotifier.BackgroundColor(Component.COLOR_RED);
        GotTextNotifier.TextColor(Component.COLOR_WHITE);

        title = new Label(this);
        title.Text("Food Delivery Service");
        title.FontSize(36);
        title.Width(Component.LENGTH_FILL_PARENT);
        title.TextAlignment(Component.ALIGNMENT_CENTER);
        title.BackgroundColor(Color.parseColor("#005200"));
        title.TextColor(COLOR_WHITE);

        Horizon1 = new HorizontalArrangement(this);
        Horizon1.WidthPercent(Component.LENGTH_FILL_PARENT);
        Label_Username = new Label(Horizon1);
        Label_Username.Text("Username:");
        Label_Username.WidthPercent(30);
        Label_Username.FontSize(14);


        Username = new Label(Horizon1);
        Username.WidthPercent(30);
        Username.FontSize(14);
        Username.TextColor(COLOR_BLUE);
        Username.Text(TheUsername);

        empty1 = new Label(Horizon1);
        empty1.Text("Helloo");
        empty1.TextAlignment(Component.ALIGNMENT_NORMAL);
        empty1.FontSize(20);
        empty1.TextColor(COLOR_NONE);
        empty1.WidthPercent(30);

        pIDLabel = new Label(Horizon1);
        pIDLabel.Text("pID:");
        pIDLabel.WidthPercent(10);
        pIDLabel.FontSize(14);

        NumberID = new Label(Horizon1);
        NumberID.Text("16");
        NumberID.TextColor(Component.COLOR_BLUE);
        NumberID.WidthPercent(10);
        NumberID.FontSize(14);

        ThingsSale = new Label(this);
        ThingsSale.Text("Things available to order");
        ThingsSale.FontSize(14);
        ThingsSale.BackgroundColor(Color.parseColor("#005200"));
        ThingsSale.TextColor(Component.COLOR_WHITE);
        ThingsSale.Width(Component.LENGTH_FILL_PARENT);

        Vertical1 = new VerticalArrangement(this);
        Vertical1.Height(200);
        thingsWeSell = new ListView(Vertical1);
        thingsWeSell.Height(Component.LENGTH_FILL_PARENT);
        thingsWeSell.TextSize(18);
        thingsWeSell.BackgroundColor(Color.parseColor("#00000000"));

        Horizon2btn = new HorizontalArrangement(this);
        Horizon2btn.WidthPercent(Component.LENGTH_FILL_PARENT);
        btnDelete = new Button(Horizon2btn);
        btnDelete.Text("Delete");
        btnDelete.BackgroundColor(Color.parseColor("#00000000"));
        btnDelete.Width(Component.LENGTH_FILL_PARENT);
        btnDelete.FontSize(12);

        empty2 = new Label(Horizon2btn);
        empty2.Text("Amount");
        empty2.TextAlignment(Component.ALIGNMENT_NORMAL);
        empty2.FontSize(20);
        empty2.TextColor(COLOR_WHITE);
        empty2.WidthPercent(60);

        btnAddNew = new Button(Horizon2btn);
        btnAddNew.Text("Add New");
        btnAddNew.Width(Component.LENGTH_FILL_PARENT);
        btnAddNew.BackgroundColor(Color.parseColor("#00000000"));
        btnAddNew.FontSize(12);
        btnAddNew.Width(Component.LENGTH_FILL_PARENT);

        thingsSold_Label = new Label(this);
        thingsSold_Label.Text("My things Sold");
        thingsSold_Label.Width(Component.LENGTH_FILL_PARENT);
        thingsSold_Label.BackgroundColor(Color.parseColor("#005200"));
        thingsSold_Label.FontSize(12);
        thingsSold_Label.TextColor(COLOR_WHITE);

        Vertical2 = new VerticalArrangement(this);
        Vertical2.Height(150);
        thingsSold = new ListView(Vertical2);
        thingsSold.Height(Component.LENGTH_FILL_PARENT);
        thingsSold.TextSize(18);
        thingsSold.BackgroundColor(Color.parseColor("#00000000"));

        btnOrderIsCompleted = new Button(this);
        btnOrderIsCompleted.Width(Component.LENGTH_FILL_PARENT);
        btnOrderIsCompleted.BackgroundColor(Color.parseColor("#00000000"));
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

        webThingOrderIsComplete = new Web(this);
        webThingOrderIsComplete.Url(baseURL + URLsesh + "a1b2c3d4" + URLThingsSold + webOrderIsCompleteURL);
        webThingOrderIsComplete.Get();

        butt1 = new Button(this);
        butt1.Text("Go Back");
        butt1.FontSize(12);
        butt1.BackgroundColor(Color.parseColor("#00000000"));
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
            if (component.equals(btnAddNew)) {
                switchForm("AddNamesOfItems");
            }
            if (component.equals(btnDelete)) {
                ThingsSale.Text(ThingsSale.Text() + " >");
                deletThis(thingsWeSell.Selection());
                return true;
            }
            if (component.equals(btnOrderIsCompleted)) {
                thingsSold_Label.Text(thingsSold_Label.Text() + " >");
                removeCompletedOrder(thingsSold.Selection());
                return true;
            }
        }
        if (eventName.equals("GotText")) {
            if (component.equals(webGetThings4Sale)) {
                jsonSortAndListViewForSellerScreen(params[1].toString(), (String) params[3], "thing", "tSoldBy");
                return true;
            }

            if (component.equals(webThingsSold)) {
                jsonSortAndListViewForSellerScreen(params[1].toString(), (String) params[3], "orders", "sellerID");
                return true;
            }
            if (component.equals(webThingDelete)) {
                webGetThings4Sale.Get();
            }
            if (component.equals(webThingOrderIsComplete)) {
                webThingsSold.Get();
            }
        }
        return false;
    }

    public void mainActGo() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void deletThis(String selection) {
        if ((thingsWeSell.Selection().isEmpty())) {
            GotTextNotifier.ShowAlert("No Item Selected");
        } else {
            String tIDForURL = selection.substring(1, 3);
            webThingDelete.Url( baseURL + URLsesh + "a1b2c3d4" + webThingDeletURL + tIDForURL);
            webThingDelete.Get();
            finish();
            startActivity(getIntent());
        }
    }

    public void removeCompletedOrder(String selection) {
        Log.d("INFO:~~>", "C");
        if ((thingsSold.Selection().isEmpty())) {
            GotTextNotifier.ShowAlert("No Order Selected");
            Log.d("INFO:~~>", "D");
        } else {
            Log.d("INFO:~~>", "E");
            String[] bits = selection.split("]");
            String orderNum = bits[0].replace("[", "");
            webThingOrderIsComplete.Url(baseURL + URLsesh + "a1b2c3d4" + webOrderIsCompleteURL + orderNum);
            webThingOrderIsComplete.Get();
            GotTextNotifier.ShowAlert("Order " + orderNum + " removed");
            Log.d("INFO:~~>", "F");
            //webGetThingsSold.Get();
            //https://stackoverflow.com/questions/3053761/reload-activity-in-androids
        }
    }

    public void jsonSortAndListViewForSellerScreen(String status, String textOfResponse, String tableName, String fieldName) {
        List<String> ListViewItemArray;
        if (status.equals("200")) try {
            ListViewItemArray = new ArrayList<String>();
            // See:  https://stackoverflow.com/questions/5015844/parsing-json-object-in-java
            JSONObject parser = new JSONObject(textOfResponse);
            if (!parser.getString(tableName).equals("")) {
                JSONArray jsonIsMySon = parser.getJSONArray(tableName);
                for (int i = 0; i < jsonIsMySon.length(); i++) {
                    if (Integer.valueOf(jsonIsMySon.getJSONObject(i).getString(fieldName)).equals(Integer.valueOf(pID))) {
                        String oneEntryInTheListView = "";
                        //add data from table to the sting above by getting the field name you want from the brief ( example where field name is "sellerID": oneEntryInTheListView = jsonIsMySon.getJSONObject(i).getString("sellerID"); )
                        //formats entries the ListView containing the orders
                        if (tableName.equals("orders") && fieldName.equals("sellerID")) {
                            oneEntryInTheListView = "[" + jsonIsMySon.getJSONObject(i).getString("oID")
                                    + "] buyer: " + jsonIsMySon.getJSONObject(i).getString("buyerID")
                                    + " (slotNum: " + jsonIsMySon.getJSONObject(i).getString("slotNum")
                                    + ") [" + jsonIsMySon.getJSONObject(i).getString("tID") + "]";
                        }
                        //formats entries the ListView containing the items sold by seller
                        if (tableName.equals("thing") && fieldName.equals("tSoldBy")) {
                            oneEntryInTheListView = "[" + jsonIsMySon.getJSONObject(i).getString("tID")
                                    + "] " + jsonIsMySon.getJSONObject(i).getString("tName")
                                    + " (" + jsonIsMySon.getJSONObject(i).getString("tDescription")
                                    + ") €" + jsonIsMySon.getJSONObject(i).getString("tPrice");
                        }
                        ListViewItemArray.add(oneEntryInTheListView);
                    }
                }
                YailList tempData = YailList.makeList(ListViewItemArray);
                if (tableName.equals("orders") && fieldName.equals("sellerID")) {
                    thingsSold.Elements(tempData);
                }
                if (tableName.equals("thing") && fieldName.equals("tSoldBy")) {
                    thingsWeSell.Elements(tempData);
                }
            }
        } catch (JSONException e) {
            // if an exception occurs, code for it in here
            GotTextNotifier.ShowMessageDialog("Error 3.353; JSON Exception (check password) ", "Information", "OK");
        }
        else {
            GotTextNotifier.ShowMessageDialog("Error 3.356; Problem connecting with server", "Information", "OK");
        }
    }
}