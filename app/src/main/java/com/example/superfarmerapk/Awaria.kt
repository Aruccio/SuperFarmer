package com.example.superfarmerapk

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Awaria: AppCompatActivity()  {

    lateinit var button: Button
    lateinit var zwierzatka: EditText
    lateinit var wynik : TextView
    lateinit var str: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.awaria)
        button = findViewById(R.id.button)
        zwierzatka = findViewById(R.id.wpisztutaj)
        wynik = findViewById(R.id.wynik)
        println("To jest button: " +button)
        button.setOnClickListener()
        {
            println(zwierzatka.toString())
            wynik.text = zwierzatka.toString()
            //wynik.text = str
        }
    }
}

