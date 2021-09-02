package com.example.beepboop

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Date
import java.util.*
import android.view.animation.AlphaAnimation
import androidx.core.graphics.toColor
import android.widget.TextView;
import android.view.View
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mp : MediaPlayer= MediaPlayer.create(this,R.raw.sample)
        warning.visibility=View.INVISIBLE


        submit.setOnClickListener {
            mp.start()

            var time: Calendar = Calendar.getInstance()
            text1.setText("${time.get(Calendar.HOUR)}" + ":${time.get(Calendar.MINUTE)}")
            val sharedPreferences = getPreferences(MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            var point = sharedPreferences.getString("points", "0")
            var timecheck = sharedPreferences.getString("prevtime", "True")

            var count = sharedPreferences.getInt("counter", 0)
            if (point.isNullOrEmpty()) {
                point = "0"
            }
            points.setText(point)






                if (timecheck != (time.get(Calendar.MINUTE)).toString()) {
                    if (count < 11) {


                        var time: Calendar = Calendar.getInstance()
                        text1.setText("${time.get(Calendar.HOUR)}" + ":${time.get(Calendar.MINUTE)}")


                        point = ((point!!.toInt()) + 100).toString()
                        count = (count + 1)
                        editor.putInt("counter", count)
                        editor.commit()
                        editor.putString("points", point);
                        editor.commit();
                        points.setText("${point}")
                        count++
                        editor.putString("prevtime", (time.get(Calendar.MINUTE)).toString())
                        editor.commit()


                    } else {

                        warning.visibility=View.VISIBLE
                        warning.setText("Sorry Rolls limited to 10 times per day. ")
                        val fadeIn = AlphaAnimation(0.0f, 1.0f)
                        val fadeOut = AlphaAnimation(1.0f, 0.0f)
                        warning.startAnimation(fadeIn)
                        warning.startAnimation(fadeOut)
                        fadeIn.duration = 5000
                        fadeIn.fillAfter = true
                        fadeOut.duration = 2500
                        fadeOut.fillAfter = true
                        fadeOut.startOffset = 1000 + fadeIn.startOffset



                    }

                }
                else{
                    warning.visibility=View.VISIBLE
                    warning.setText("Cannot use the button twice for the same minute ")

                    val fadeIn = AlphaAnimation(0.0f, 1.0f)
                    val fadeOut = AlphaAnimation(1.0f, 0.0f)
                    warning.startAnimation(fadeIn)
                    warning.startAnimation(fadeOut)
                    fadeIn.duration = 5000
                    fadeIn.fillAfter = true
                    fadeOut.duration = 2500
                    fadeOut.fillAfter = true
                    fadeOut.startOffset = 1000 + fadeIn.startOffset




                    }
                }
            }

    }


