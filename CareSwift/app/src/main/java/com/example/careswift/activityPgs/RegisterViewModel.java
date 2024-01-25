package com.example.careswift.activityPgs;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private final MutableLiveData<Boolean> registrationStatus = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public LiveData<Boolean> getRegistrationStatus() {
        return registrationStatus;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void onSignUpClick(String name, String email, String phone, String password, String confirmPassword) {
        // Perform input validation
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            showToast("Please fill in all fields");
        } else if (!isEmailValid(email)) {
            showToast("Invalid email format");
        } else if (!password.equals(confirmPassword)) {
            showToast("Passwords do not match");
        } else if (!isPhoneNumberValid(phone)) {
            showToast("Phone number must be 11 digits");
        } else {
            // Registration successful
            registrationStatus.setValue(true);

            // You can add more logic here if needed
            // For example, you can provide additional data to the UI using LiveData
            toastMessage.setValue("Registration successful");
        }
    }

    // Rest of the ViewModel code...

    private boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.length() == 11;
    }

    private void showToast(String message) {
        // Provide a toast message through LiveData
        toastMessage.setValue(message);
    }
}
