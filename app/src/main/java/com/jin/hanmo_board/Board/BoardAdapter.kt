package com.jin.hanmo_board.Board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jin.hanmo_board.R

class BoardAdapter(private val boardList:ArrayList<BoardData>): RecyclerView.Adapter<BoardAdapter.Viewholder>() {

    private lateinit var mListener: onItemClickListener

    //리스트 클릭 시 배열 숫자로 전달
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    //onItemClickListener 객체 전달...????
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.board_recycler_model, parent, false)
        return Viewholder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val boardList = boardList[position]
        holder.title.text = boardList.title
        holder.context.text = boardList.content
        holder.time.text = boardList.time
        //holder.uid.text = boardList.uid
    }

    override fun getItemCount(): Int {
        return boardList.size
    }

    //리사이클러 뷰 안 내용 배치와 데이터 연결 (제목,내용,시간)
    class Viewholder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.bm_title)
        val context: TextView = itemView.findViewById(R.id.bm_context)
        val time: TextView = itemView.findViewById(R.id.bm_time)
        //val uid: TextView = itemView.findViewById(R.id.bm_id)

        //클릭 이벤트
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}