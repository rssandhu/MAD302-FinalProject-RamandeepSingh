/**
 * File Header
 * Course code and lab number: MAD411 – Final Project
 * Student name: Ramandeep Singh
 * Student ID: A00194321
 * Date of submission: 2026/04/20
 * Short description: Shows detailed information for a single product.
 * Receives Product data via Intent extras from ProductListActivity.
 */

package com.example.finalproject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity that displays detailed information about a product.
 * Data is received from ProductListActivity via Intent extras.
 */
class ProductDetailActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Read product data from Intent extras
        val id = intent.getIntExtra("EXTRA_PRODUCT_ID", 0)
        val name = intent.getStringExtra("EXTRA_PRODUCT_NAME") ?: "Unknown"
        val price = intent.getDoubleExtra("EXTRA_PRODUCT_PRICE", 0.0)
        val description = intent.getStringExtra("EXTRA_PRODUCT_DESCRIPTION") ?: ""

        // Update UI
        findViewById<TextView>(R.id.textDetailName).text = name
        findViewById<TextView>(R.id.textDetailPrice).text = "Price: \$%.2f".format(price)
        findViewById<TextView>(R.id.textDetailDescription).text = description
    }
}