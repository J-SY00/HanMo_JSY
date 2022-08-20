package com.jin.hanmo_board.Board

//게시물이 서버에 저장되는 형태
data class BoardData(
    val title : String?=null,
    val content : String?=null,
    val uid : String?=null,
    val time : String?=null
)