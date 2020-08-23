package com.example.memo

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    lateinit var db: MemoDatabase
    var memoList = listOf<MemoEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        button_add.setOnClickListener {
            val memo = MemoEntity(null, edit_text_memo.text.toString())
            insertMemo(memo)
        }
    }

    fun insertMemo(memo : MemoEntity) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.memoDAO().insert(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemo()
            }
        }

        insertTask.execute()
    }

    fun getAllMemo() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }
        }).execute()
    }

    fun deleteMemo() {

    }

    fun setRecyclerView(memoList: List<MemoEntity>) {

    }
}