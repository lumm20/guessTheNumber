package alcantar.diego.theguessnumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import org.w3c.dom.Text
import kotlin.math.min
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var minValue=0
    var maxValue=100
    var num:Int=0
    var won=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val guessings: TextView =findViewById(R.id.guessings)
        val down: Button =findViewById(R.id.down)
        val up: Button =findViewById(R.id.up)
        val generate: Button =findViewById(R.id.generate)
        val guessed: Button =findViewById(R.id.guessed)

        fun resetValues(){
            minValue=0
            maxValue=100
            num=0
            won=false
        }

        fun checkingLimits():Boolean{
            return minValue!=maxValue
        }

        generate.setOnClickListener{
            num= Random.nextInt(minValue,maxValue)
            guessings.setText(num.toString())
            generate.visibility= View.INVISIBLE
            guessed.visibility= View.VISIBLE
        }

        up.setOnClickListener{
            minValue=num
            if(checkingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Me ganaste :p")
            }
        }

        down.setOnClickListener{
            maxValue=num
            if(checkingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText(num.toString())
            }
        }

        guessed.setOnClickListener{
            if(!won){
                guessings.setText("Adivine, tu numero es el "+num)
                guessed.setText("Volver a jugar")
                won=true
            }else{
                generate.visibility= View.VISIBLE
                generate.setText("Tap to generate to start")
                guessed.visibility= View.GONE
                resetValues()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}