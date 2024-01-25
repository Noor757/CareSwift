package com.example.careswift.activityPgs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careswift.R;
import com.example.careswift.adapters.ServiceAdapter;
import com.example.careswift.database.ServiceDao;
import com.example.careswift.database.ServiceEntity;
import com.example.careswift.database.ServicesDataBase;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button exitButton = findViewById(R.id.buttonE);
        Button optionButton = findViewById(R.id.button_moreOptions);
        exitButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        optionButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitConfirmationDialog();
            }
        });
        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to RegisterActivity
                Intent intent = new Intent(HomePageActivity.this, OptionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Spinner citySpinner = findViewById(R.id.citySpinner);
        String[] cities = {"Select a city", "Lahore", "Karachi", "Islamabad", "Multan"};

        // Create a custom adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                if (position == 0) {
                    // Customize the appearance of the hint/prompt item
                    ((TextView) view).setTextColor(Color.GRAY); // Change text color
                } else {
                    // Customize the appearance of other items
                    ((TextView) view).setTextColor(Color.WHITE); // Change text color
                }
                return view;
            }
        };
        citySpinner.setAdapter(adapter);

        new FetchServicesAsyncTask(getApplicationContext()).execute();
    }

    private class FetchServicesAsyncTask extends AsyncTask<Void, Void, List<ServiceEntity>> {
        private final Context context;
        FetchServicesAsyncTask(Context context) {
            this.context = context;
        }
        @Override
        protected List<ServiceEntity> doInBackground(Void... voids) {
            ServicesDataBase db = ServicesDataBase.getDatabase(context);
            ServiceDao serviceDao = db.serviceDao();
            return serviceDao.getAllServices();
        }

        @Override
        protected void onPostExecute(List<ServiceEntity> services) {
            updateUIWithServices(services);
        }
    }
    private void updateUIWithServices(List<ServiceEntity> services) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ServiceAdapter serviceAdapter = (ServiceAdapter) recyclerView.getAdapter();

        // If adapter is null, create a new one
        if (serviceAdapter == null) {
            serviceAdapter = new ServiceAdapter(services, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(serviceAdapter);
        } else {
            // Adapter already exists, update the data
            Log.d("Adapter", "Existing Adapter Size: " + serviceAdapter.getItemCount());

            serviceAdapter.clear();
            serviceAdapter.addAll(services);
            serviceAdapter.notifyDataSetChanged();

            Log.d("Adapter", "Updated Adapter Size: " + serviceAdapter.getItemCount());
        }
    }
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Confirmation");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked Yes, exit the app
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // User clicked No, do nothing
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
