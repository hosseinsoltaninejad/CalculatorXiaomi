package ir.hossein.calculatorxiaomi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import ir.hossein.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var operator : Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()
    }

    private fun onOperatorClicked() {

        binding.btnPlus.setOnClickListener {

          if (binding.txtExpression.text.isNotEmpty()){

              val myChar = binding.txtExpression.text.last()

              if ( myChar != '+' &&
                  myChar != '-' &&
                  myChar != '*' &&
                  myChar != '/'){

                  appendText("+")
              }
          }
        }

        binding.btnMines.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()){

                val myChar = binding.txtExpression.text.last()

                if ( myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'){

                    appendText("-")
                }
            }
        }

        binding.btnZarb.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()){

                val myChar = binding.txtExpression.text.last()

                if ( myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'){

                    appendText("*")
                }
            }
        }

        binding.btnTaghsim.setOnClickListener {

            if (binding.txtExpression.text.isNotEmpty()){

                val myChar = binding.txtExpression.text.last()

                if ( myChar != '+' &&
                    myChar != '-' &&
                    myChar != '*' &&
                    myChar != '/'){

                    appendText("/")
                }
            }
        }

        binding.btnPrantezBaz.setOnClickListener {
            appendText("(")
        }

        binding.btnPrantezBaste.setOnClickListener {
            appendText(")")
        }

        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
        }

        binding.btnClear.setOnClickListener {
            val oldText =  binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()){
                binding.txtExpression.text = oldText.substring( 0 , oldText.length - 1)
            }
            binding.txtAnswer.text = ""
         }

        binding.btnMosavi.setOnClickListener {

            try {

                val expression = ExpressionBuilder( binding.txtExpression.text.toString() ).build()
                val  result = expression.evaluate()

                val longResult = result.toLong()

                if ( result == longResult.toDouble() ){
                    binding.txtAnswer.text = longResult.toString()
                }else {
                    binding.txtAnswer.text = result.toString()
                }
            }
            catch ( e : Exception){

                binding.txtExpression.text = ""
                binding.txtAnswer.text = ""
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onNumberClicked() {

        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()){
                appendText("0")
            }
        }

        binding.btn1.setOnClickListener {
            appendText("1")
        }

        binding.btn2.setOnClickListener {
            appendText("2")
        }

        binding.btn3.setOnClickListener {
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            appendText("4")
        }

        binding.btn5.setOnClickListener {
            appendText("5")
        }

        binding.btn6.setOnClickListener {
            appendText("6")
        }

        binding.btn7.setOnClickListener {
            appendText("7")
        }

        binding.btn8.setOnClickListener {
            appendText("8")
        }

        binding.btn9.setOnClickListener {
            appendText("9")
        }

        binding.btnDot.setOnClickListener {
            if (binding.txtExpression.text.isEmpty() || binding.txtAnswer.text.isNotEmpty()){
                appendText("0.")
            }else if( !binding.txtExpression.text.contains(".")){
                appendText(".")
            }
        }
    }

    private fun appendText( newText : String ){

       if ( binding.txtAnswer.text.isNotEmpty() ){
           binding.txtExpression.text = ""
       }

        binding.txtAnswer.text = ""

        binding.txtExpression.append(newText)

        val  viewTree : ViewTreeObserver = binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
            override fun onGlobalLayout() {

                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizontalScrollViewTxtExpression.smoothScrollTo( binding.txtExpression.width , 0 )
            }


        })
    }
}