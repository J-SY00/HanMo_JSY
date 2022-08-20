package com.jin.hanmo_board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jin.hanmo_board.Board.SocialActivity
import com.jin.hanmo_board.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.social.setOnClickListener{
            val intent = Intent(this, SocialActivity::class.java)
            startActivity(intent)
        }
    }
}