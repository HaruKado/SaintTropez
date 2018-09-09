package com.example.kadohiraharuki.sainttropez

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.kadohiraharuki.sainttropez.R.id.imageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //ここでは画像を表示しているImageViewを登録
        registerForContextMenu(imageView)
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

    //コンテキストメニューとして登録されたビューを長押しすると、onCreateContextMenuメソッドが呼ばれるので、それをオーバーライド
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //menuInflaterプロパティでMenuInflaterクラスのインスタンスを取得、infrateメソッドによりメニューXMLファイルをコンテキストに設定
        menuInflater.inflate(R.menu.context, menu)
    }

    //onContextItemSelectedメソッドでオプションメニューのメニュー項目がタップされた時に呼ばれる
    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            //メニュー処理を続行するにはfalse、終了するにはtrueを返す
            R.id.sms -> {
                //データを渡すために、電話番号からUriオブジェクトを生成
                val number = "999-9999-9999"
                //parseメソッドでuriオブジェクト生成
                val uri = Uri.parse("sms:$number")
                //アクションとしてIntent.ACTION_VIEWを指定してインテントを作成
                var intent = Intent(Intent.ACTION_VIEW)
                //作成したURIオブジェクトをデータとして設定
                intent.data = uri
                //startActivityにintentを渡して起動
                startActivity(intent)
                return true
            }
            R.id.mail -> {
                //メールアプリに渡すアドレス、件名、本文を変数に代入
                val email: String = "nobady@example.com"
                val subject: String = "予約問い合わせ"
                val text: String = "以下の通り予約希望します"
                //データを渡すuriオブジェクト作成
                val uri = Uri.parse("maiito")
                //インテントアクションとしてIntent.ACTION_SENDTO（メッセージ配信機能）を指定して作成
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.apply {
                    data = uri
                    //arrayof関数により配列にしておく
                    putExtra(Intent.EXTRA_EMAIL, arrayListOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                //暗黙インテントを処理できるアプリをユーザがインストールしていない場合
                //packageManagerはインストールされているアプリに関する情報を保持しているクラス
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.share -> {
                val text: String = "美味しいレストランを紹介します"
                val intent = Intent(Intent.ACTION_SEND)
                intent. apply {
                    //typeプロパティで、インテントのタイプを設定
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, text)
                }
                //毎回アプリの選択画面を表示させたいときはIntentクラスのクラスメソッドであるcreateChooserを使う
                val chooser = Intent.createChooser(intent, null)
                if(intent.resolveActivity(packageManager) != null)
                    startActivity(chooser)

                return true
            }
            R.id.browse -> {
                val url: String = "http://www.yahoo.co.jp/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}
