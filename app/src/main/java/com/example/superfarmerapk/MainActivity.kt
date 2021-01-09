package com.example.superfarmerapk

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    lateinit var aparatButton: Button
    lateinit var awariaButton: Button
    lateinit var imageView: ImageView
    private val REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aparatButton = findViewById(R.id.aparatButton)
        awariaButton = findViewById(R.id.awariaButton)
        imageView = findViewById(R.id.imageView)
        aparatButton.setOnClickListener()
        {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity((this.packageManager)) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Nie moge otworzyc kamery", Toast.LENGTH_SHORT).show()
            }

        }

        awariaButton.setOnClickListener()
        {
            val intent = Intent(this, Awaria::class.java)
            startActivity(intent)

        }
    }
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if(requestCode==REQUEST_CODE && resultCode== Activity.RESULT_OK)
            {
                val takenImage = data?.extras?.get("data") as Bitmap
                var cyaned = ImageManagement.ImageToCyan(takenImage)
                var cutted = ImageManagement.FindOneSector(cyaned)
                imageView.setImageBitmap(cutted)

            }
            else
            {
                super.onActivityResult(requestCode, resultCode, data)
            }

        }
}

