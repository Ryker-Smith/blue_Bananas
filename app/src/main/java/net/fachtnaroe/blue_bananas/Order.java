package net.fachtnaroe.blue_bananas;
// change for change's sake again!

        import android.content.Intent;
        import com.google.appinventor.components.runtime.HandlesEventDispatching;
        import com.google.appinventor.components.runtime.Label;
        import com.google.appinventor.components.runtime.VerticalArrangement;
        import com.google.appinventor.components.runtime.HorizontalArrangement;
        import com.google.appinventor.components.runtime.Form;
        import com.google.appinventor.components.runtime.Component;
        import com.google.appinventor.components.runtime.ListView;
        import com.google.appinventor.components.runtime.EventDispatcher;
        import com.google.appinventor.components.runtime.Button;

public class Order extends Form implements HandlesEventDispatching {

    private Label lblTitle, lblUsername, lblUser, pID, pID_Label, ThingsOrder, Credit, Amount, ThingsOrdered;
    private VerticalArrangement Vertical1, Vertical2;
  // not used.  private TextBox username;
    private HorizontalArrangement Horizon1, Horizon2;
    private Button btnBuy;
    private ListView lvProducts;
    // not used.     private String FrstUser = MainActivity.getData();

    protected void $define() {

        // if (!this.startupValue.toString().equals("\"blueshop\"")) {
        //}

        // if (!this.startupValue.toString().equals("\"16\"")) {
        //}

        lblTitle = new Label(this);
        lblTitle.Text("Food Delivery Service");
        lblTitle.Width(Component.LENGTH_FILL_PARENT);
        lblTitle.FontSize(36);
        lblTitle.TextAlignment(Component.ALIGNMENT_CENTER);
        lblTitle.BackgroundColor(COLOR_GREEN);
        lblTitle.TextColor(Component.COLOR_WHITE);

        Horizon1 = new HorizontalArrangement(this);
        Horizon1.WidthPercent(Component.LENGTH_FILL_PARENT);
        lblUsername = new Label(Horizon1);
        lblUsername.Text("lblUsername:");
        lblUsername.WidthPercent(30);
        lblUsername.FontSize(14);

        lblUser = new Label(Horizon1);
        lblUser.Text(this.startupValue="blueshop");
        lblUser.TextColor(Component.COLOR_BLUE);
        lblUser.WidthPercent(60);
        lblUser.FontSize(14);

        pID_Label = new Label(Horizon1);
        pID_Label.Text("pID:");
        pID_Label.WidthPercent(10);
        pID_Label.FontSize(14);

        pID = new Label(Horizon1);
        pID.Text(this.startupValue="16");
        pID.WidthPercent(10);
        pID.TextColor(Component.COLOR_BLUE);
        pID.FontSize(14);

        ThingsOrder = new Label(this);
        ThingsOrder.Text("Things available to order");
        ThingsOrder.FontSize(14);
        ThingsOrder.BackgroundColor(COLOR_GREEN);
        ThingsOrder.TextColor(Component.COLOR_WHITE);
        ThingsOrder.Width(Component.LENGTH_FILL_PARENT);


        //items for sale here...
        Vertical1 = new VerticalArrangement(this);
        Vertical1.Height(300);
        lvProducts = new ListView(Vertical1);
        lvProducts.Height(Component.LENGTH_FILL_PARENT);
        lvProducts.TextSize(40);
       //  CANT SEE TEXT. do items exist?
        // lvProducts.TextColor(255255000);
        lvProducts.BackgroundColor(Component.COLOR_DKGRAY);


        Horizon2 = new HorizontalArrangement(this);
        Horizon2.WidthPercent(Component.LENGTH_FILL_PARENT);
        Credit = new Label(Horizon2);
        Credit.Text("Credit €");
        Credit.TextAlignment(Component.ALIGNMENT_NORMAL);
        Credit.FontSize(20);
        Credit.WidthPercent(25);

        Amount = new Label(Horizon2);
        Amount.Text("Amount");
        Amount.TextAlignment(Component.ALIGNMENT_NORMAL);
        Amount.FontSize(20);
        Amount.WidthPercent(25);

        btnBuy = new Button(Horizon2);
        btnBuy.WidthPercent(Component.LENGTH_FILL_PARENT);
        btnBuy.Text("Buy");
        btnBuy.TextAlignment(Component.ALIGNMENT_CENTER);
        btnBuy.FontSize(20);
        btnBuy.WidthPercent(60);

        ThingsOrdered = new Label(this);
        ThingsOrdered.Text("Things I've Ordered");
        ThingsOrdered.FontSize(12);
        ThingsOrdered.Width(Component.LENGTH_FILL_PARENT);
        ThingsOrdered.BackgroundColor(COLOR_GREEN);
        ThingsOrdered.TextColor(COLOR_WHITE);

        Vertical2 = new VerticalArrangement(this);
        Vertical2.Height(Component.LENGTH_FILL_PARENT);
        Vertical2.Width(Component.LENGTH_FILL_PARENT);
        Vertical2.BackgroundColor(Component.COLOR_DKGRAY);

        //add text & size

        EventDispatcher.registerEventForDelegation(this, formName, "Click");
    }

    public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] params) {
        if (eventName.equals("Click")) {
            mainActGo();
            return true;
        } else {
            return false;
        }
    }

    public void mainActGo() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}

