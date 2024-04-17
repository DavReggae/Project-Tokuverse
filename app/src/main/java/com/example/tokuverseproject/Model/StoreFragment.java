package com.example.tokuverseproject.Model;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tokuverseproject.Activity.CheckOutActivity;
import com.example.tokuverseproject.Activity.CreatePostActivity;
import com.example.tokuverseproject.Activity.HomeActivity;
import com.example.tokuverseproject.Activity.UserPageActivity;
import com.example.tokuverseproject.R;
import com.example.tokuverseproject.ServerAPI.ServerHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StoreFragment extends Fragment {

    Bundle bundle = new Bundle();
    User user;
    ImageView img_StoreUserAvatar;
    TextView lbl_StoreUserName, lbl_StoreUserCoin;
    Button btn_StoreCheckout;
    ListView listView_Store;
    ProgressBar loadingBar_Store;
    ServerHandler serverHandler = new ServerHandler();

    ArrayList<Product> cart = new ArrayList<Product>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        if(bundle != null)
        {
            try
            {
                user = (User) bundle.getSerializable("user");
            }
            catch (Exception e)
            {
                Log.d("Error", e.getMessage());
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        img_StoreUserAvatar = view.findViewById(R.id.img_StoreUserAvatar);
        lbl_StoreUserName = view.findViewById(R.id.lbl_StoreUserName);
        lbl_StoreUserCoin = view.findViewById(R.id.lbl_StoreUserCoin);
        btn_StoreCheckout = view.findViewById(R.id.btn_StoreCheckout);
        loadingBar_Store = view.findViewById(R.id.loadingBar_Store);
        lbl_StoreUserCoin.setText("  " + user.getCoins());
        lbl_StoreUserName.setText(user.getUsername());
        listView_Store = view.findViewById(R.id.listView_Store);
        Picasso.get().load(user.getAvatar()).into(img_StoreUserAvatar);

        showLoading();
        try {
            serverHandler.getProduct_Action(new ServerHandler.getProduct_CallBack() {
                @Override
                public void onSuccess(List<Product> productList) {
                    StoreCustomBase storeCustomBase = new StoreCustomBase(inflater.getContext(), productList, StoreFragment.this);
                    listView_Store.setAdapter(storeCustomBase);
                    dismissLoading();
                }

                @Override
                public void onFailed(String message) {

                }
            });
            btn_StoreCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CheckOutActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("cart", cart);

                    // Debugging: Print the cart contents
                    Log.d("StoreFragment", "Cart contents: " + cart.toString());

                    getActivity().startActivity(intent);
                }
            });
        }
        catch(Exception e)
        {
            Log.d("ERRRROR", e.getMessage());
        }

        return view;
    }

    public void addToCart(Product product) {
        cart.add(product); // You can adjust this to add the product ID or any other identifier
        btn_StoreCheckout.setText("x " + cart.size());
    }

    public void removeFromCart(Product product){
        cart.add(product);
    }


    private void showLoading() {
        loadingBar_Store.setVisibility(View.VISIBLE);
    }

    // Method to dismiss loading screen
    private void dismissLoading() {
        loadingBar_Store.setVisibility(View.GONE);
    }
}