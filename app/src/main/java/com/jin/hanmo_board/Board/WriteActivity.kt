package com.jin.hanmo_board.Board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jin.hanmo_board.R
import com.jin.hanmo_board.databinding.ActivityWriteBinding

//게시물 작성 화면 - 로그인 해야 가능
class WriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        binding.upload.setOnClickListener{
            val title = binding.writeTitle.text.toString()
            val content = binding.writeContent.text.toString()

            //사용자 아이디
            //val uid = RBAuth.getUid()

            val time = FBboard.getTime()
            FBboard.boardRef
                .push()
                .setValue(BoardData(title, content, "uid", time))
            finish()
        }

        binding.back.setOnClickListener{
            finish()
        }
    }
}