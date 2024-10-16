package com.example.semafor

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {

    var imSemafor: ImageView? = null
    var counter: Int = 0
    var timer: Timer? = null
    var is_run: Boolean = false
    var imageArray: IntArray = intArrayOf(R.drawable.red, R.drawable.yellow, R.drawable.green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imSemafor = findViewById(R.id.imageViewSemafor)
        //imSemafor?.setImageResource(imageArray[2])
    }

    fun onClickStartStop(view: View)
    {
        view as ImageButton
        if(!is_run)
        {
            startStop()
            view.setImageResource(R.drawable.red)
            is_run = true

        }
        else
        {
            imSemafor?.setImageResource(R.drawable.gray)
            view.setImageResource(R.drawable.green)
            timer?.cancel()
            is_run = false
        }

    }

    fun startStop()
    {
        timer = Timer()
        timer?.schedule(object :TimerTask(){
            override fun run() {
                imSemafor?.setImageResource(imageArray[counter])
                counter++
                if (counter == 3) counter = 0
            }

        }, 1000)
    }
}