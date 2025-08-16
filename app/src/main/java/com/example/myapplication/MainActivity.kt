package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    private var isFirstClick = true


    var currentNumber: String = "0"
    var firstNumber: Double = 0.0
    var secondNumber: String = "0"
    var isThereAnyOperations: Boolean = false
    var currentOperator: Char? = null
    var result: Double = 0.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numbers: TextView = findViewById(R.id.field)


        val zero: AppCompatButton = findViewById(R.id.zero)
        val one: AppCompatButton = findViewById(R.id.one)
        val two: AppCompatButton = findViewById(R.id.two)
        val three: AppCompatButton = findViewById(R.id.three)
        val four: AppCompatButton = findViewById(R.id.four)
        val five: AppCompatButton = findViewById(R.id.five)
        val six: AppCompatButton = findViewById(R.id.six)
        val seven: AppCompatButton = findViewById(R.id.seven)
        val eight: AppCompatButton = findViewById(R.id.eight)
        val nine: AppCompatButton = findViewById(R.id.nine)

        val plus: AppCompatButton = findViewById(R.id.plus)
        val minus: AppCompatButton = findViewById(R.id.minus)
        val divide: AppCompatButton = findViewById(R.id.divide)
        val multiply: AppCompatButton = findViewById(R.id.multiply)
        val percentage: AppCompatButton = findViewById(R.id.percentage)


        val clearAll: AppCompatButton = findViewById(R.id.AC)
        val clear: AppCompatButton = findViewById(R.id.C)


        val equals: AppCompatButton = findViewById(R.id.equals)



        zero.setOnClickListener {
            if(!isThereAnyOperations){
                if(currentNumber != "0"){
                    currentNumber = currentNumber + "0"
                }
                else{
                    currentNumber = "0"
                }
            }
            else{
                Toast.makeText(applicationContext, "You cannot do that", Toast.LENGTH_SHORT).show()
            }
            numbers.text = currentNumber
        }

        one.setOnClickListener { putANumber('1'); numbers.text = currentNumber }
        two.setOnClickListener { putANumber('2'); numbers.text = currentNumber }
        three.setOnClickListener { putANumber('3'); numbers.text = currentNumber }
        four.setOnClickListener { putANumber('4'); numbers.text = currentNumber }
        five.setOnClickListener { putANumber('5'); numbers.text = currentNumber }
        six.setOnClickListener { putANumber('6'); numbers.text = currentNumber }
        seven.setOnClickListener { putANumber('7'); numbers.text = currentNumber }
        eight.setOnClickListener { putANumber('8'); numbers.text = currentNumber }
        nine.setOnClickListener { putANumber('9'); numbers.text = currentNumber }




        plus.setOnClickListener { putAnOperation('+');numbers.text = currentNumber }
        minus.setOnClickListener { putAnOperation('-');numbers.text = currentNumber }
        divide.setOnClickListener { putAnOperation('/');numbers.text = currentNumber }
        multiply.setOnClickListener { putAnOperation('*');numbers.text = currentNumber }

        percentage.setOnClickListener {

            if(!isThereAnyOperations){ currentOperator = '%'; onEqualsClick() ; numbers.text = currentNumber}


            else{ Toast.makeText(applicationContext, "You already have an operator!!!", Toast.LENGTH_SHORT).show() }

        }

        equals.setOnClickListener {

            onEqualsClick() ; numbers.text = currentNumber

        }


        clearAll.setOnClickListener { onClearClick(); numbers.text = currentNumber}

        clear.setOnClickListener { onCClick();  numbers.text = currentNumber}

    }

    fun onEqualsClick() {
        val num2: Double = secondNumber.toDouble()
        when (currentOperator) {
            '+' -> result = firstNumber + num2
            '-' -> result = firstNumber - num2
            '*' -> result = firstNumber * num2
            '/' -> {
                if (num2 != 0.0) {
                    result = firstNumber / num2
                } else {
                    // Handle division by zero error
                    currentNumber = "Error"
                    return
                }
            }
            '%' -> {
                if(!currentNumber.contains("+") && !currentNumber.contains("-") && !currentNumber.contains("/") && !currentNumber.contains("*")){
                    result = currentNumber.toDouble() / 100
                }
                else{
                    Toast.makeText(applicationContext, "Wrong, press =", Toast.LENGTH_SHORT).show()
                }
            }
        }
        currentNumber = result.toString()
        currentOperator = null
        firstNumber = 0.0
        secondNumber = "0"
        isThereAnyOperations = false
        result = 0.0// Reset operator after calculation

    }


    fun putANumber(num: Char){
        if(currentNumber != "0"){
            if(isThereAnyOperations){
                secondNumber = secondNumber + num
            }

            currentNumber = currentNumber + num
        }
        else{
            currentNumber = num.toString()
        }
    }

    fun putAnOperation(operation: Char){
        if(!isThereAnyOperations){
            firstNumber = currentNumber.toDouble()
            currentNumber = currentNumber + operation
            currentOperator = operation
            isThereAnyOperations = true
        }
        else{
            Toast.makeText(applicationContext, "You already have an operator!!!", Toast.LENGTH_SHORT).show()
        }

    }


    fun onClearClick() {
        currentNumber = "0"
        firstNumber = 0.0
        secondNumber = "0"
        isThereAnyOperations = false
        currentOperator = null
        result = 0.0
    }


    fun onCClick() {
        if(currentNumber.length == 1){
            currentNumber = "0"
        }
        else{
            if (currentNumber[currentNumber.length-1] == '+' || currentNumber[currentNumber.length-1] == '-' || currentNumber[currentNumber.length-1] == '/' || currentNumber[currentNumber.length-1] == '*'){
                currentNumber = currentNumber.substring(0, currentNumber.length-2)
                isThereAnyOperations = false
            }
            else{
                currentNumber = currentNumber.substring(0, currentNumber.length-2)
            }
        }
    }

}