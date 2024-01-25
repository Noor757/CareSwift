package com.example.careswift.adapters;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careswift.R;
import com.example.careswift.database.ServiceEntity;

import java.util.List;
import java.util.Locale;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {
    private List<ServiceEntity> services;
    private Context context;
    private static final String CHANNEL_ID = "service_notification_channel";
    private static final int NOTIFICATION_ID = 1;

    public ServiceAdapter(List<ServiceEntity> services, Context context) {
        this.services = services;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceEntity service = services.get(position);
        holder.serviceNameTextView.setText(service.getName());
        holder.serviceDescriptionTextView.setText(service.getDescription());
        holder.servicePriceTextView.setText(String.format(Locale.getDefault(), "$%.2f", service.getPrice()));

        holder.itemView.setOnClickListener(v -> showBookingDialog(service));
    }

    private void showBookingDialog(ServiceEntity service) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Book Service");
        builder.setMessage("Want to book the service?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            // User clicked Yes, you can perform the booking action here
            // Show the notification
            showNotification(context);
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            // User clicked No, do nothing or provide feedback
            dialog.dismiss();
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to show the notification
    private void showNotification(Context context) {
        createNotificationChannel(context);

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Service Booked Successfully")
                    .setContentText("Your service has been booked successfully. Thank you!");
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    // Method to create a notification channel (required for Android 8.0 and above)
    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Service Notification Channel";
            String description = "Channel for service notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
    public void clear() {
        services.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ServiceEntity> newServices) {
        services.addAll(newServices);
        notifyDataSetChanged();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceNameTextView;
        TextView serviceDescriptionTextView;
        TextView servicePriceTextView;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.serviceNameTextView);
            serviceDescriptionTextView = itemView.findViewById(R.id.serviceDescriptionTextView);
            servicePriceTextView = itemView.findViewById(R.id.servicePriceTextView);
        }
    }
}


