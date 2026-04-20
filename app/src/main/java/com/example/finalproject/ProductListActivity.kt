/**
 * File Header
 * Course code and lab number: MAD411 – Final Project
 * Student name: Ramandeep Singh
 * Student ID: A00194321
 * Date of submission: 2026/04/20
 * Short description: Main screen showing a list of products using
 * RecyclerView and CardView. Users can view details, edit, and delete.
 */

package com.example.finalproject

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup


/**
 * Main activity displaying all products in a RecyclerView.
 * Handles adding new products, editing existing ones, and deleting with confirmation.
 */

class ProductListActivity : AppCompatActivity() {

    // Data source: list of products (in real app, use Room/SQLite or API)
    private val products = mutableListOf<Product>()

    // Adapter connects data to RecyclerView
    private val adapter = ProductAdapter(products) { product ->
        // Callback when user clicks on a product item
        showProductDetail(product)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add some sample products for demo
        addSampleProducts()

        // Float button to add new product
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
            R.id.fabAddProduct
        ).setOnClickListener {
            // Start AddProductActivity
            startActivityForResult(Intent(this, AddProductActivity::class.java), REQUEST_ADD_PRODUCT)        }
    }

    /**
     * Shows detail screen for a given product.
     *
     * @param product the product to display
     */
    private fun showProductDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        // Pass product data via Intent extras
        intent.putExtra("EXTRA_PRODUCT_ID", product.id)
        intent.putExtra("EXTRA_PRODUCT_NAME", product.name)
        intent.putExtra("EXTRA_PRODUCT_PRICE", product.price)
        intent.putExtra("EXTRA_PRODUCT_DESCRIPTION", product.description)
        startActivity(intent)
    }

    /**
     * Launches EditProductActivity to modify an existing product.
     *
     * @param product the product to edit
     */
    private fun editProduct(product: Product) {
        val intent = Intent(this, EditProductActivity::class.java)
        intent.putExtra("EXTRA_PRODUCT_ID", product.id)
        intent.putExtra("EXTRA_PRODUCT_NAME", product.name)
        intent.putExtra("EXTRA_PRODUCT_PRICE", product.price)
        intent.putExtra("EXTRA_PRODUCT_DESCRIPTION", product.description)
        startActivityForResult(intent, REQUEST_EDIT_PRODUCT)  // ← use this

    }

    /**
     * Shows a confirmation dialog before deleting a product.
     *
     * @param product product to delete
     * @param position index in the list
     */
    private fun deleteProduct(product: Product, position: Int) {
        val builder = AlertDialog.Builder(this)
            .setTitle("Delete Product")
            .setMessage("Are you sure you want to delete \"${product.name}\"?")
            .setPositiveButton("Delete") { _, _ ->
                // Remove product from list
                products.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
        builder.show()
    }

    /**
     * Called from AddProductActivity when user adds a new product.
     * This method is used via onActivityResult‑style pattern.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null) return

        val id = data.getIntExtra("EXTRA_PRODUCT_ID", 0)
        val name = data.getStringExtra("EXTRA_PRODUCT_NAME") ?: return
        val price = data.getDoubleExtra("EXTRA_PRODUCT_PRICE", 0.0)
        val description = data.getStringExtra("EXTRA_PRODUCT_DESCRIPTION") ?: ""

        val newProduct = Product(id, name, price, description)

        if (requestCode == REQUEST_ADD_PRODUCT) {
            // Add new product
            products.add(newProduct)
            adapter.notifyItemInserted(products.size - 1)
            Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()
        } else if (requestCode == REQUEST_EDIT_PRODUCT) {
            // Update existing product at correct index
            val index = products.indexOfFirst { it.id == id }
            if (index != -1) {
                products[index] = newProduct
                adapter.notifyItemChanged(index)
                Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Internal adapter class for displaying products in RecyclerView.
     * Handles click, edit, and delete actions.
     */
    private inner class ProductAdapter(
        private val items: List<Product>,
        private val onItemClicked: (Product) -> Unit
    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = layoutInflater.inflate(R.layout.item_product_card, parent, false)
            return ProductViewHolder(view, this)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount() = items.size

        inner class ProductViewHolder(
            itemView: android.view.View,
            private val adapter: ProductAdapter
        ) : RecyclerView.ViewHolder(itemView) {

            private val context: Context = itemView.context

            fun bind(product: Product) {
                itemView.findViewById<android.widget.TextView>(R.id.textProductName).text = product.name
                itemView.findViewById<android.widget.TextView>(R.id.textProductPrice).text =
                    "Price: \$%.2f".format(product.price)
                itemView.findViewById<android.widget.TextView>(R.id.textProductDescription).text =
                    product.description

                // Click on card opens detail screen
                itemView.setOnClickListener {
                    if (context is ProductListActivity) {
                        context.showProductDetail(product)
                    }
                }

                // Edit button
                itemView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnEdit)
                    .setOnClickListener {
                        if (context is ProductListActivity) {
                            context.editProduct(product)
                        }
                    }

                // Delete button with confirmation
                itemView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnDelete)
                    .setOnClickListener {
                        if (context is ProductListActivity) {
                            context.deleteProduct(product, adapterPosition)
                        }
                    }
            }
        }
    }

    /**
     * Adds a few sample products to the list for demo.
     */
    private fun addSampleProducts() {
        if (products.isEmpty()) {
            products.addAll(
                listOf(
                    Product(1, "Laptop", 1299.99, "Powerful laptop for students and developers."),
                    Product(2, "Smartphone", 599.49, "Flagship smartphone with great camera."),
                    Product(3, "Headphones", 149.99, "Wireless noise‑canceling headphones.")
                )
            )
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val REQUEST_ADD_PRODUCT = 100
        const val REQUEST_EDIT_PRODUCT = 101
    }
}