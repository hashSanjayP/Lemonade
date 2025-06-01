package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeImageAndText(Modifier.fillMaxSize().padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun LemonadeImageAndText(modifier: Modifier=Modifier){
    var window by remember { mutableIntStateOf(1) }
    var rand by remember { mutableIntStateOf((2..4).random()) }
    var counter by remember { mutableIntStateOf(0) }


    var result=when(window){
        (1)->{R.drawable.lemon_tree}
        (2)->{R.drawable.lemon_squeeze}
        (3)->{R.drawable.lemon_drink}
        (4)->{R.drawable.lemon_restart}
        else -> {}
    }

    var text=when(window){
        (1)->{R.string.first}
        (2)->{R.string.second}
        (3)->{R.string.third}
        (4)->{R.string.fourth}
        else -> {}
    }


    fun Control(){
        if (window==1){
            window=2
        }else if(window==2){
            if(counter==rand){
                counter=0
                rand=(2..4).random()
                window=3
            }else{
                counter++
            }

        }else if(window==3){
            window=4
        }else{
            window=1
        }
    }

    Column(modifier=modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Button(onClick = {Control()}, shape = RoundedCornerShape(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))) {
            Image(painter = painterResource(result as Int), contentDescription =stringResource(R.string.firstContent))
        }
        Spacer(modifier= Modifier.padding(vertical = 16.dp))
        Text(text = stringResource(text as Int), fontSize = 18.sp)
    }
}



@Preview(showBackground = true)
@Composable
fun LemonadeApp(){
    LemonadeImageAndText(Modifier.fillMaxSize())
}