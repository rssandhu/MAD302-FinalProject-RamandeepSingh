package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private var nextId = 1000  // simple ID generator; in real app, use DB auto-ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPrice = findViewById<EditText>(R.id.inputPrice)
        val inputDescription = findViewById<EditText>(R.id.inputDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = inputName.text.toString().trim()
            val priceText = inputPrice.text.toString()

            if (name.isEmpty()) {
                inputName.error = "Product name is required"
                return@setOnClickListener
            }
            if (priceText.isEmpty()) {
                inputPrice.error = "Enter a price"
                return@setOnClickListener
            }

            val price = priceText.toDoubleOrNull()
            if (price == null || price <= 0) {
                inputPrice.error = "Enter a valid price greater than 0"
                return@setOnClickListener
            }

            val description = inputDescription.text.toString().trim()

            // Create new product
            val product = Product(nextId++, name, price, description)

            // Put data into Intent and return to ProductListActivity
            val intent = Intent()
            intent.putExtra("EXTRA_PRODUCT_ID", product.id)
            intent.putExtra("EXTRA_PRODUCT_NAME", product.name)
            intent.putExtra("EXTRA_PRODUCT_PRICE", product.price)
            intent.putExtra("EXTRA_PRODUCT_DESCRIPTION", product.description)

            setResult(RESULT_OK, intent)
            finish()
            Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()
        }
    }
}