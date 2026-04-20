package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProductActivity : AppCompatActivity() {

    private var productId = 0
    private lateinit var initialName: String
    private var initialPrice = 0.0
    private lateinit var initialDescription: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val inputName = findViewById<EditText>(R.id.inputName)
        val inputPrice = findViewById<EditText>(R.id.inputPrice)
        val inputDescription = findViewById<EditText>(R.id.inputDescription)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Read passed data from ProductListActivity
        productId = intent.getIntExtra("EXTRA_PRODUCT_ID", 0)
        initialName = intent.getStringExtra("EXTRA_PRODUCT_NAME") ?: ""
        initialPrice = intent.getDoubleExtra("EXTRA_PRODUCT_PRICE", 0.0)
        initialDescription = intent.getStringExtra("EXTRA_PRODUCT_DESCRIPTION") ?: ""

        inputName.setText(initialName)
        inputPrice.setText(initialPrice.toString())
        inputDescription.setText(initialDescription)

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

            // Create updated product using the same ID
            val product = Product(productId, name, price, description)

            val intent = Intent()
            intent.putExtra("EXTRA_PRODUCT_ID", product.id)
            intent.putExtra("EXTRA_PRODUCT_NAME", product.name)
            intent.putExtra("EXTRA_PRODUCT_PRICE", product.price)
            intent.putExtra("EXTRA_PRODUCT_DESCRIPTION", product.description)

            setResult(RESULT_OK, intent)
            finish()
            Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show()
        }
    }
}