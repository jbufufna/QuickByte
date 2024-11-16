package com.example.quickbyte;

import android.os.Bundle;

import com.example.quickbyte.apiEndpoints.userEndpoints.UserService;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserCreationRequestDTO;
import com.example.quickbyte.apiEndpoints.userEndpoints.useDTO.UserDTO;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quickbyte.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Example of using the endpoint calls
     /*   performLogin("DT", "hashed_password_1");*/ // -------------------------------------------------------



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    private void performLogin(String username, String passwordHash) {
        UserService.getInstance().loginUser(username, passwordHash, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Login successful! Welcome " + result.getUsername(), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void getAllUsers() {
        UserService.getInstance().getAllUsers(new UserService.ApiCallback<List<UserDTO>>() {
            @Override
            public void onSuccess(List<UserDTO> result) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Retrieved " + result.size() + " users", Toast.LENGTH_SHORT).show();
                    // Here you can process the list of users, e.g., display them in a RecyclerView
                });
            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Failed to get users: " + errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void createUser(UserCreationRequestDTO userCreationRequest) {
        UserService.getInstance().createUser(userCreationRequest, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "User created successfully: " + result.getUsername(), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Failed to create user: " + errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void getUserById(int userId) {
        UserService.getInstance().getUserById(userId, new UserService.ApiCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Retrieved user: " + result.getUsername(), Toast.LENGTH_SHORT).show();
                    // Here you can process the user data, e.g., display it in the UI
                });
            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Failed to get user: " + errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void performGetAllUsers() {
        UserService.getInstance().getAllUsers(new UserService.ApiCallback<List<UserDTO>>() {
            @Override
            public void onSuccess(List<UserDTO> result) {
                runOnUiThread(() -> {
                    // Handle success - you can update UI, for example, show the list of users in a RecyclerView
                    Toast.makeText(MainActivity.this, "Successfully fetched " + result.size() + " users.", Toast.LENGTH_SHORT).show();
                    // You can pass the result to your RecyclerView adapter or whatever UI element you're using.
                });
            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> {
                    // Handle error - for example, show an error message to the user
                    Toast.makeText(MainActivity.this, "Failed to fetch users: " + errorMessage, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}