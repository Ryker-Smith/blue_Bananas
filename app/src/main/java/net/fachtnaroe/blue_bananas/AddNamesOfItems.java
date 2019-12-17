package net.fachtnaroe.blue_bananas;

import android.content.ClipData;
import android.content.Intent;

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
import com.google.appinventor.components.runtime.TextBox;

import org.w3c.dom.Text;


public class AddNamesOfItems extends Form implements HandlesEventDispatching {
    private Label title, ItemNameLabel, ItemDescriptionLabel, ItemPriceLabel;
    private HorizontalArrangement horiz1, horiz2, horiz3, horiz4, horiz5;
    private TextBox ItemName, ItemDescription, ItemPrice;
    private Button ItemSave, BtnGoBack;
    private Web webItemSave;
    private String baseURL = "https://fachtnaroe.net/bananas?", SessionID = MainActivity.getSessionID(), URLsesh = "sessionID=";


    protected void $define() {

        this.BackgroundColor(COLOR_ORANGE);
        this.BackgroundImage("FDS_PossibleLogo_04.png");

        title = new Label(this);
        title.Text("Food Delivery Service");
        title.FontSize(20);
        title.Width(Component.LENGTH_FILL_PARENT);
        title.TextAlignment(Component.ALIGNMENT_CENTER);
        title.TextColor(Component.COLOR_BLACK);

        horiz1 = new HorizontalArrangement(this);
        horiz1.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemNameLabel = new Label(this);
        ItemNameLabel.Text("Item Name:");
        ItemNameLabel.WidthPercent(30);
        ItemNameLabel.FontSize(14);
        ItemName = new TextBox(this);
        ItemName.Text("");
        ItemName.WidthPercent(100);
        ItemName.FontSize(14);
        ItemName.BackgroundColor(COLOR_WHITE);

        horiz2 = new HorizontalArrangement(this);
        horiz2.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemDescriptionLabel = new Label(this);
        ItemDescriptionLabel.Text("Item Description:");
        ItemDescriptionLabel.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemDescriptionLabel.FontSize(14);
        ItemDescription = new TextBox(this);
        ItemDescription.Text("");
        ItemDescription.WidthPercent(100);
        ItemDescription.FontSize(14);
        ItemDescription.BackgroundColor(COLOR_WHITE);

        horiz3 = new HorizontalArrangement(this);
        horiz3.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemPriceLabel = new Label(this);
        ItemPriceLabel.Text("Item Price:");
        ItemPriceLabel.WidthPercent(30);
        ItemPriceLabel.FontSize(14);
        ItemPrice = new TextBox(this);
        ItemPrice.Text("");
        ItemPrice.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemPrice.FontSize(14);
        ItemPrice.BackgroundColor(COLOR_WHITE);

        horiz4 = new HorizontalArrangement(this);
        horiz4.WidthPercent(Component.LENGTH_FILL_PARENT);
        ItemSave = new Button(this);
        ItemSave.Text("Save this");
        ItemSave.WidthPercent(100);
        ItemSave.FontSize(14);

        horiz5 = new HorizontalArrangement(this);
        horiz5.WidthPercent(Component.LENGTH_FILL_PARENT);
        BtnGoBack = new Button(this);
        BtnGoBack.Text("Go Back");
        BtnGoBack.WidthPercent(30);
        BtnGoBack.FontSize(14);


        webItemSave = new Web(this);

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
        EventDispatcher.registerEventForDelegation(this, formName, "GotText");
    }
    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("Click")) {
            if (component.equals(ItemSave)) {
                SaveItem();
                BackgroundColor(COLOR_GREEN);
            }
            if (eventName.equals("Click")) {
                if (component.equals(BtnGoBack))
                    salesActGo();
                return true;
            }
        }
        if (eventName.equals("GotText")){
            title.Text((String) params[3]);
        }
        return false;
    }
    public void SaveItem(){
        webItemSave.Url(baseURL+"sessionID=a1b2c3d4&method=POST&entity=thing&tName="+ItemName.Text()+"&tDescription="+ItemDescription.Text()+"&tPrice="+ItemPrice.Text()+"&tSoldBy="+"16");
        webItemSave.Get();
    }
    public void salesActGo() {
        Intent i = new Intent(getApplicationContext(), Sales.class);
        startActivity(i);
    }
}
