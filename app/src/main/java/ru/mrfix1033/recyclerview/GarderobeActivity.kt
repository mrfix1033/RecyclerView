package ru.mrfix1033.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GarderobeActivity : AppCompatActivityWithExitMenu() {
    private val garderobe = mutableListOf(
        GarderobeElement("Футболка", "Используется для повседневной носки", R.drawable.t_shirt),
        GarderobeElement("Джинсы", "Используется для повседневной носки, обычно летом", R.drawable.jeans)
        )
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        setContentView(R.layout.activity_garderobe)
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.adapter = CustomAdapter(garderobe)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}