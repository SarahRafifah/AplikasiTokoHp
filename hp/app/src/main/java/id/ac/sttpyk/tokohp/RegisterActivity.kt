package id.ac.sttpyk.tokohp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtLoginListener()
    }
    private fun txtLoginListener(){
        txtlog.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}