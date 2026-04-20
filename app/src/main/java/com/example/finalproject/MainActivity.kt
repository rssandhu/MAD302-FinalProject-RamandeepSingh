/**
 * File Header
 * Course code and lab number: MAD411 – Final Project
 * Student name: Ramandeep Singh
 * Student ID: A00194321
 * Date of submission: 2026/04/20
 * Short description: Simple starter activity that immediately
 * opens ProductListActivity. No UI layout is used.
 */

package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Minimal starter activity that opens ProductListActivity immediately.
 * This keeps MainActivity as the launcher but does not show a UI.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start the main app screen and finish this activity
        startActivity(Intent(this, ProductListActivity::class.java))
        finish()
    }
}