package com.example.careswift.database;

import android.content.Context;

import java.util.List;
import android.content.Context;
import android.os.AsyncTask;

public class DbInitializer {

    public static void initializeServicesDatabase(Context context) {
        new InitializeDatabaseAsyncTask(context).execute();
    }

    private static class InitializeDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private final Context context;

        InitializeDatabaseAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Perform database initialization here
            ServicesDataBase db = ServicesDataBase.getDatabase(context);
            ServiceDao serviceDao = db.serviceDao();

            serviceDao.insert(new ServiceEntity(0, "Household Management", "Our professional household management service ensures a clean, organized and comfortable living space. Our skilled maids are dedicated to maintaining the highest standards of cleanliness in your home", 30.0));
            serviceDao.insert(new ServiceEntity(0, "Childcare Specialist", "Trust your child's well-being to our experienced childcare specialists. Our nannies provide a safe and nurturing environment, fostering positive development and ensuring your child's happiness.", 50.0));
            serviceDao.insert(new ServiceEntity(0, "Landscape Care Expert", "Enhance the beauty of your outdoor space with our landscape care experts. From meticulous lawn maintenance to creative garden design, we bring a touch of nature to your home", 40.0));
            serviceDao.insert(new ServiceEntity(0, "Culinary Artisan", "Indulge in exquisite dining experiences with our culinary artisans. Our chefs are passionate about crafting delicious and personalized meals, catering to your unique tastes and preferences.", 70.0));
            serviceDao.insert(new ServiceEntity(0, "Electrical Solution Provider", "Ensure the safety and functionality of your electrical systems with our solution provider. Our skilled electricians offer reliable services, from installations to repairs, for a secure home environment", 50.0));
            serviceDao.insert(new ServiceEntity(0, "Professional Chauffeur", "Experience hassle-free transportation with our professional chauffeurs. Whether for daily commutes or special occasions, our drivers prioritize safety, punctuality, and a comfortable journey", 50.0));
            serviceDao.insert(new ServiceEntity(0, "Automotive Care Specialist", "Keep your vehicle in top condition with our automotive care specialists. From routine maintenance to repairs, our experts ensure your car performs at its best, enhancing both safety and longevity", 70.0));
            serviceDao.insert(new ServiceEntity(0, "Waterproofing and Insulation Solutions", "Safeguard your home against leaks and temperature extremes with our comprehensive waterproofing and insulation solutions. Our experts provide effective and durable protection for your property", 80.0));
            serviceDao.insert(new ServiceEntity(0, "Beauty Concierge", "Elevate your beauty routine with our mobile beauty concierge service. Our experienced beauticians bring th salon experience to your doorstep, offering personalized and convenient beauty services", 70.0));


            return null;
        }
    }
}



