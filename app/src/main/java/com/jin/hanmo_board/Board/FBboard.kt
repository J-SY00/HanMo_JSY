package com.jin.hanmo_board.Board

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

//게시판에서 작성자 정보, 작성 시간 가져오는 함수
class FBboard {
    companion object{
        private val database = Firebase.database

        val boardRef = database.getReference("board")

        //사용자 아이디 가져오는 작업
//        fun getUid():String{
//            auth = FirebaseAuth.getInstance()
//            return auth.currentUser?.uid.toString()
//        }

        //작성 시간 가져오는 작업
        fun getTime() : String{
            val CurrentDateTime = Calendar.getInstance().time
            val dataFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA).format(CurrentDateTime)

            return dataFormat
        }
    }
}