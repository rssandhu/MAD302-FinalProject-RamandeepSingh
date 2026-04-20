/**
 * File Header
 * Course code and lab number: MAD411 – Final Project
 * Student name: Ramandeep Singh
 * Student ID: A00194321
 * Date of submission: 2026/04/20
 * Short description: Represents a single product in the inventory system
 * including ID, name, price, and description.
 */

/**
 * Data class representing a product in the app.
 * Used in the RecyclerView adapter and passed between activities via Intent.
 *
 * @property id product ID (unique integer)
 * @property name product name (String)
 * @property price product price in dollars (Double)
 * @property description short product description (String)
 */

package com.example.finalproject

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String
)