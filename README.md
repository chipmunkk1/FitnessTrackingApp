# Fitness Tracker App (Android)

A smart and user-friendly Android application designed to help users track daily fitness activity, monitor calories, and stay motivated throughout the day.  
Built with Android Studio using Java, this app is suitable for everyone from kids to the elderly.

---

## 📱 Features
### 🔐 User Authentication
- Secure email + password login system.
- New users can create an account, which is safely stored in a local database.
<img width="200" height="600" alt="Login" src="https://github.com/user-attachments/assets/73638510-d3af-44e1-a645-fc00ed3dfbd1" />






### 🧍 Personalized Profile Setup
Users can input:
- Height  
- Weight  
- Age  
- Daily activity level  
- Goal: **Bulk** or **Cut**
  <img width="200" height="600" alt="Details" src="https://github.com/user-attachments/assets/89e3feeb-5733-4808-801e-1074960273df" />



The app uses these details to calculate the **recommended daily calorie intake** tailored to the user's fitness goal.

---

## 🏃 Real-Time Activity Tracking

### **Step Counter**
- Tracks your daily steps in real time using Android’s built-in sensors.

### **Distance Tracking**
- Automatically calculates the **distance walked** based on your steps and stride length estimation.

### **Calories Burned While Walking**
- Converts your walking activity into **calories burned**.
- These burned calories are then added into the main calorie system so the user always knows the **exact remaining calories** for the day.

---

## 🌍 Location Tracking
When the user taps "Get Location":
- Displays **latitude** and **longitude**
- Retrieves the **exact address**
- Shows the **city name**
Powered by Google’s Location Services.
<img width="200" height="600" alt="MainActivity" src="https://github.com/user-attachments/assets/d790f335-3c37-4167-aa67-ee1a3501785e" />




---

## 💧 Smart Hydration Reminders
- Sends scheduled notifications reminding the user to drink water throughout the day.

---

## 🔧 Technologies Used

- **Android Studio**
- **Java**
- **SQLite / Room Database** 
- **Google Location Services API**
- **Android Sensor Framework** (for step counting)
- **Notification Manager** 
- **GPS & Location Manager**
- **Material Design UI**
