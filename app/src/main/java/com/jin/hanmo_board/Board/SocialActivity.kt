package com.jin.hanmo_board.Board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.jin.hanmo_board.R
import com.jin.hanmo_board.databinding.ActivitySocialBinding

//게시물 목록 확인 화면
class SocialActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySocialBinding
    private lateinit var BoardRecyclerView : RecyclerView
    private lateinit var BoardList:ArrayList<BoardData>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_social)

        //글쓰기 아이콘 클릭 -> 쓰기 화면으로 이동
        binding.write.setOnClickListener{
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }


        //게시물 아이디 가져와서 리사이클러뷰로 나타나게 하기..?
        BoardRecyclerView = findViewById(R.id.ContentView)
        BoardRecyclerView.layoutManager = LinearLayoutManager(this)
        BoardRecyclerView.setHasFixedSize(true)


        //BoardList = { title, content, uid, time}
        BoardList = arrayListOf<BoardData>()
        getData()
    }

    private fun getData(){
        BoardRecyclerView.visibility= View.GONE

        //파이어베이스의 board 내용 가져오기
        dbRef = FirebaseDatabase.getInstance().getReference("board")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                BoardList.clear()
                if(snapshot.exists()){
                    for(ampSnap in snapshot.children){
                        val BoardData = ampSnap.getValue(BoardData::class.java)
                        BoardList.add(BoardData!!)
                    }

                    val mAdapter = BoardAdapter(BoardList)
                    BoardRecyclerView.adapter = mAdapter

                    //목록 클릭하면 해당 목록 List에서 title,content 찾아와서 확대 페이지(SocialInsideActivity)에서 보이게 하기
                    mAdapter.setOnItemClickListener(object : BoardAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@SocialActivity, BoardInsideActivity::class.java)
                            intent.putExtra("title",BoardList[position].title)
                            intent.putExtra("content",BoardList[position].content)
                            intent.putExtra("time",BoardList[position].time)
                            //intent.putExtra("uid",BoardList[position].uid)
                            startActivity(intent)
                        }
                    })

                    //최근 생성 게시물부터 보이게
                    BoardList.reverse()
                    BoardRecyclerView.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
