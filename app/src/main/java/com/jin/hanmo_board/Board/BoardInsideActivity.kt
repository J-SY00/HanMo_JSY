package com.jin.hanmo_board.Board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jin.hanmo_board.R
import com.jin.hanmo_board.databinding.ActivityBoardInsideBinding

//게시물 확대 화면 (댓글, 좋아요 기능 - 로그인해야 가능)
class BoardInsideActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        //SocialActivity에서 받은 title,content 문자열로 바꿔서 화면에 보이기
        val title = intent.getStringExtra("title").toString()
        val content = intent.getStringExtra("content").toString()
        val time = intent.getStringExtra("time").toString()
        //val uid = intent.getStingExtra("uid").toString()

        //activity_social_inside에서의 배치에 맞게 설정
        binding.insideTitle.text=title
        binding.insideContext.text=content
        binding.insideTime.text=time
        //binding.insideId.text = uid

        //로그인 해야 하는 기능
        //좋아요 기능
        binding.likeBtn.setOnClickListener{}
        //댓글 작성

    }
}