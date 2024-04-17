package com.example.tokuverseproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tokuverseproject.Model.Product;
import com.example.tokuverseproject.Model.User;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {
    User user;
    ArrayList<Product> cart;
    ImageView btn_CheckOutBack, img_CheckOut_UserAvatar;
    TextView lbl_CheckOut_UserName, lbl_CheckOut_UserPhoneNumber, lbl_CheckOut_UserCoins, lbl_CheckOut_TotalPrice;
    Button btn_CheckOut;
    Integer totalPrice = 0;
    ProgressBar loadingBar_CheckOut;
    ListView listView_Cart;
    ServerHandler serverHandler = new ServerHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        user = (User) getIntent().getSerializableExtra("user");
        try {
            cart = (ArrayList<Product>) getIntent().getSerializableExtra("cart");
        }
        catch(Exception e)
        {
            Log.d("ERRRROR", e.getMessage());
        }
        btn_CheckOutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_CheckOutBack = findViewById(R.id.btn_CheckOutBack);
        img_CheckOut_UserAvatar = findViewById(R.id.img_CheckOut_UserAvatar);
        lbl_CheckOut_UserName = findViewById(R.id.lbl_CheckOut_UserName);
        lbl_CheckOut_UserCoins = findViewById(R.id.lbl_CheckOut_UserCoins);
        lbl_CheckOut_UserPhoneNumber = findViewById(R.id.lbl_CheckOut_UserPhoneNumber);
        lbl_CheckOut_TotalPrice = findViewById(R.id.lbl_CheckOut_TotalPrice);
        btn_CheckOut = findViewById(R.id.btn_CheckOut);
        loadingBar_CheckOut = findViewById(R.id.loadingBar_CheckOut);
        listView_Cart = findViewById(R.id.listView_Cart);

        serverHandler.LoadImageFromURL(user.getAvatar(), img_CheckOut_UserAvatar);
        lbl_CheckOut_UserName.setText(user.getUsername());
        lbl_CheckOut_UserPhoneNumber.setText(user.getPhone_number());
        lbl_CheckOut_UserCoins.setText(user.getCoins() + " ");


        for(Product product : cart)
        {
            Integer price = Integer.parseInt(product.getPrice());
            totalPrice += price;
        }
        lbl_CheckOut_TotalPrice.setText(totalPrice.toString());
    }

    private void showLoading() {
        loadingBar_CheckOut.setVisibility(View.VISIBLE);
    }

    // Method to dismiss loading screen
    private void dismissLoading() {
        loadingBar_CheckOut.setVisibility(View.GONE);
    }
}