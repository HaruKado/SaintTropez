package com.example.kadohiraharuki.sainttropez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kadohiraharuki.sainttropez.R.id.imageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //onCreateOptionsMenuをオーバーライドしてメニューの初期化
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //MenuInflaterクラスでXMLファイルのインスタンス設定および取得
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //オプションメニューをタップすると、onOptionsItemSelectedメソッドが呼び出される
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //itemIdプロパティから選択されたメニューの項目のIDを取得
        when(item?.itemId){
            R.id.top -> {
                imageView.setImageResource(R.drawable.toppage)
                return true
            }

            R.id.lunch01 -> {
                imageView.setImageResource(R.drawable.lunch01)
                return true
            }

            R.id.lunch02 -> {
                imageView.setImageResource(R.drawable.lunch02)
                return true
            }

            R.id.dinner01 -> {
                imageView.setImageResource(R.drawable.dinner01)
                return true
            }

            R.id.dinner02 -> {
                imageView.setImageResource(R.drawable.dinner02)
                return true
            }

        }
        //親クラスの同名メソッドを実行して値を返しているが、これはAPIの決まり
        //when式で指定している以外のIDが取得された場合に何も返されなくなるのを避けるための記述
        return super.onOptionsItemSelected(item)
    }
}
