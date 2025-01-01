package ru.mrfix1033.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {
    private lateinit var garderobeElement: GarderobeElement

    private lateinit var textViewTitle: TextView
    private lateinit var textViewDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDescription = findViewById(R.id.textViewDescription)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()  // почему бы не использовать finish() вместо deprecated onBackPressed()?
        }

        garderobeElement =
            intent.getParcelableExtra(RequestCode.GARDEROBE_ELEMENT, GarderobeElement::class.java)!!

        findViewById<ImageView>(R.id.imageView).setImageResource(garderobeElement.image)
        textViewTitle.setText(garderobeElement.title)
        textViewDescription.setText(garderobeElement.description)

        findViewById<LinearLayout>(R.id.linearLayoutData).setOnClickListener {
//            AlertDialog.Builder(this)
//                .setMessage("Выберите действие")
//                .setPositiveButton("Обновить") { _, _ ->
            val view =
                LayoutInflater.from(this).inflate(R.layout.update_dialog, null, false)
            val editTextTitle = view.findViewById<TextView>(R.id.editTextTitle)
            val editTextDescription = view.findViewById<TextView>(R.id.editTextDescription)
            editTextTitle.setText(garderobeElement.title)
            editTextDescription.setText(garderobeElement.description)
            AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("Сохранить") { _, _ ->
                    garderobeElement.title = editTextTitle.text.toString()
                    garderobeElement.description = editTextDescription.text.toString()
                    textViewTitle.setText(garderobeElement.title)
                    textViewDescription.setText(garderobeElement.description)
                }
                .setNegativeButton("Отмена") { _, _ -> }
                .show()
        }
//                .setNegativeButton("Отмена") { _, _ -> }
//                .show()
//        }
    }

    override fun finish() {
        intent.putExtra(RequestCode.GARDEROBE_ELEMENT, garderobeElement)
        setResult(RESULT_OK, intent)
        super.finish()
    }
}