# HealthSync: Inventory / E‑commerce App – Android Final Project

## Overview
This Android application simulates a small inventory or e‑commerce system where users can **view, add, edit, and delete products**. The app reinforces core Android concepts such as:

- `RecyclerView` with `CardView` items  
- `ConstraintLayout`‑based UI  
- `Intent`‑based data passing between screens  
- Form validation and user feedback  
- Proper navigation and document‑style code comments  

---

## Features

### 1. Product List Screen
- Displays a scrollable list of products using `RecyclerView`.
- Each item is a `CardView` showing:
  - Product name  
  - Price  
  - Description  
- Users can:
  - Tap an item to view **Product Detail**  
  - Click **Edit** to modify a product  
  - Click **Delete** to remove a product (with confirmation dialog)  

### 2. Product Detail Screen
- Shows full details of a selected product.
- Data is passed from the list using `Intent` extras.

### 3. Add Product Screen
- Form with fields:
  - Product name  
  - Price (validates as a positive `Double`)  
  - Description  
- After saving, the new product appears in the list.

### 4. Edit Product Screen
- Pre‑fills the form with existing product data.
- Updates the product in the list when saved.

### 5. Delete Product with Confirmation
- Shows an `AlertDialog` before deleting a product.
- Removes the item from the list and notifies the adapter.

### 6. Navigation
- Main screen: `ProductListActivity` (launches when the app starts).  
- No “Hello World” launcher; the project starts directly on the product list.  

---

## Technical Details

### Data Model
A simple `Product` data class:
```kotlin
Product(id: Int, name: String, price: Double, description: String)
```
- Stored in a `mutableList` in `ProductListActivity` (in a real app, this would be replaced with Room or an API).

### UI Components
- `RecyclerView` + `CardView` for product cards  
- `ConstraintLayout` for screen layouts  
- `EditText`, `Button`, `FloatingActionButton`, and `AlertDialog`  

### Patterns Used
- `Intent`‑based communication between activities  
- `onActivityResult` pattern to handle Add/Edit results  
- Validation in form screens  
- Clean, commented Kotlin code

---

## How to Run

1. Open the project in **Android Studio**.
2. Ensure `ProductListActivity` is set as the launcher in `AndroidManifest.xml`:
   ```xml
   <activity
       android:name=".ProductListActivity"
       android:exported="true">
       <intent-filter>
           <action android:name="android.intent.action.MAIN" />
           <category android:name="android.intent.category.LAUNCHER" />
       </intent-filter>
   </activity>
   ```
3. Select a device / emulator and run the app.

---

## Submission Notes

This project was created for:

- **Course code and lab number:** MAD411 – Final Project  
- **Student name:** Ramandeep Singh  
- **Student ID:** A00194321  
- **Date of submission:** 2026/04/20  

All `.java` (`.kt`) files include:

- File headers with course, name, ID, date, and description  
- `/** ... */` class and method comments  
- `//` inline comments to explain important logic  

---

## Screens You Can See

- **Product List**  
- **Product Detail**  
- **Add Product Form**  
- **Edit Product Form**  
- Confirmation dialog for **Delete Product**

---

## How to Extend (Optional)

This project can be extended by:

- Replacing the in‑memory list with **Room Database**  
- Adding **image support** via `ImageView` in the `CardView`  
- Using **Material Design** components (e.g., `TopAppBar`, `BottomNavigationView`)  