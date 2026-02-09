# 🛒 Shopping App

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Hilt](https://img.shields.io/badge/Dagger%20Hilt-00BFA5?style=for-the-badge&logo=dagger&logoColor=white)

A modern, high-performance **eCommerce Android application** built using **Jetpack Compose** and **Kotlin**. This project demonstrates a **production-ready, scalable architecture** leveraging **Clean Architecture**, **MVVM**, **Dagger Hilt**, and **Firebase**.

---

## 📱 Features

* **Authentication**
    * Secure login and signup using Firebase Authentication.
* **Product Discovery**
    * Browse products and categories with optimized image loading.
* **Shopping Cart**
    * Real-time cart updates synced with Cloud Firestore.
* **Wishlist**
    * Personalized favorites with instant state updates.
* **Address Management**
    * Full CRUD operations for shipping addresses.
* **Profile Management**
    * Editable user profiles with secure data persistence.
* **Search**
    * Intelligent search powered by Gemini / AI components.

---

## 🛠 Tech Stack & Tools

* **Language**: Kotlin
* **UI Framework**: Jetpack Compose
* **Architecture**: MVVM + Clean Architecture
* **Dependency Injection**: Dagger Hilt (with Hilt Navigation Compose)
* **Backend Services**:
    * Firebase Authentication
    * Cloud Firestore
* **Async & State Handling**:
    * Kotlin Coroutines
    * Kotlin Flow
* **Navigation**: Type-safe Jetpack Compose Navigation
* **Local Storage**: DataStore Preferences

---

## 🏗 Project Structure

The project follows a **modular, layered architecture** to ensure scalability, maintainability, and testability.

```text
com.pks.shoppingapp
├── 📁 core                  # Shared components, themes, utilities
├── 📁 authentication        # Login, signup, and user profile
├── 📁 home                  # Dashboard, banners, and feeds
├── 📁 category              # Category browsing and filtering
├── 📁 products              # Product details and specifications
├── 📁 cart                  # Cart and checkout logic
├── 📁 personalization       # Address, settings, user preferences
├── 📁 wishlist              # Favorite items management
├── 📁 paymentgateway        # Payment integration
└── 📁 gemini                # AI-powered features
```

## 🧱 Module Internal Architecture

Each feature module is divided into three distinct layers to maintain separation of concerns:

```bash
module/
├── 📂 data/
│   ├── 📄 repositories      # Data layer implementations
│   ├── 📄 data-sources      # Remote / Local sources (Firebase/Firestore)
│   └── 📄 mappers           # DTO ↔ Domain mapping
├── 📂 domain/
│   ├── 📄 use-cases         # Pure business logic
│   └── 📄 models            # Pure Kotlin models
└── 📂 presentation/
    ├── 📄 viewmodels        # UI logic & State management
    ├── 📄 ui-state          # UI State holders
    └── 📄 screens           # Jetpack Compose UI components

```

### **🚀 Getting Started**

#### **Clone the Repository**
```bash
git clone https://github.com/nasir82/shopping-app.git
```

### **👨‍💻 Author**

**Md. Nasir Uddin** *Mobile Developer* > **Skills:**  • Flutter •  Jetpack Compose • Dart • Kotlin • Clean Architecture • Firebase
