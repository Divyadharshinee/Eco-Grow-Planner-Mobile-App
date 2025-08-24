# Eco_Grow_Planner
Initial Update on Eco Grow Planner
Here’s a **single `README.md`** draft for your project based on your **Eco Grow Planner** report and your requirement that **users must manually add API keys** before running the app.

---

# 🌱 EcoGrow Planner

EcoGrow Planner is an Android mobile application that empowers gardeners, farmers, and plant enthusiasts with AI-powered tools for **plant identification**, **disease detection**, **weather-based insights**, and **crop planning**. It integrates **AI advice, weather data, and crop health APIs** to improve plant care and sustainable gardening practices.

---

## 🚀 Features

* 📷 **Plant Identification** – Identify plants instantly using AI-powered recognition.
* 🦠 **Crop Health Diagnosis** – Detect plant diseases using the Crop Health API and receive treatment suggestions.
* 🌦 **Weather Forecast Integration** – Get real-time and forecast weather data to plan irrigation and crop activities.
* 💧 **Smart Reminders & Calculators** – Watering and fertilizer reminders tailored to your plants.
* ☀️ **Light Meter** – Use phone sensors to measure light intensity for better plant placement.
* ✂️ **Pruning & Plant Care Guides** – Access expert-backed cultivation and pruning techniques.
* 👩‍🌾 **Community & AI Expert Advice** – Engage in discussions and get AI-powered guidance.

---

## 🔑 API Keys Required

To run the app, you must provide **your own API keys**. These keys are not included in the repository.

The following 3 API keys are required:

1. **Weather API Key** – used to fetch real-time and forecast weather data.
2. **OpenAI API Key** – used for AI-powered expert gardening advice.
3. **Plant.id Crop Health API Key** – used for plant/crop disease detection.

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ecogrow-planner.git
cd ecogrow-planner
```

### 2. Add your API Keys

After downloading, **manually add your keys** before installing the app.

* Open the following files in your project:

```
Weather API Key  →  WeatherApiFile (where weather API is integrated)  
OpenAI API Key   →  OpenAiHelper / Expert Advice module  
Crop Health API  →  CropHealth (plant disease detection module)  
```

👉 Replace the placeholder value:


 "your_api_key_here"


with your actual API key.

⚠️ Do not share your keys publicly. Each user must generate their own from:

* [OpenWeather](https://openweathermap.org/api)
* [OpenAI](https://platform.openai.com/)
* [Plant.id](https://web.plant.id/plant-identification-api/)

### 3. Build & Run

* Open the project in **Android Studio**
* Sync Gradle and install dependencies
* Run on an Android emulator or device (Android 8.0+)

---

## 📌 Tech Stack

* ** Java** – App development
* **Firebase** – Database, authentication, and community forum
* **OpenAI API** – AI chatbot & expert advice
* **Weather API** – Real-time weather and climate insights
* **Plant.id Crop Health API** – Plant identification and disease detection

---

## 🛡️ Security Notes

* API keys are **not committed** to this repo.
* Users must add their own API keys manually in the respective files.
* For production releases, consider using a **backend server** to protect keys.

---

## 👩‍💻 Contributing

Contributions are welcome! Fork this repo, make changes, and submit a pull request.

---

## 📜 License

This project is licensed under the MIT License – see the LICENSE file for details.
