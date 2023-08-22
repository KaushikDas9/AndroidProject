package com.example.udemy_practice

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RememberObserver
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.udemy_practice.ui.theme.Udemy_practiceTheme

//import androidx.compose.material.Text
//import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Udemy_practiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Yellow),

                    color = MaterialTheme.colorScheme.background
                ) {
                    tapForMoney2();
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {

        Text( text = "Second")
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Udemy_practiceTheme {
//        Greeting("Android")
        tapForMoney2();
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tapForMoney1() {

    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var text by rememberSaveable { mutableStateOf("Hello") }

        TextField(
            modifier = Modifier.background(color = Color.White),
            value = text,
            onValueChange = {text=it},
            label = { Text("Label") },
            singleLine = false
        )

        Button(onClick = { /*TODO*/ },

            elevation = ButtonDefaults.buttonElevation(1.dp)) {
            Text(text = "OK")
        }

    }
}

var preBestScore:Int =-1;
@Composable
fun tapForMoney2() {

    var counter = remember {
        mutableStateOf(-1)
    };
    var timeLeft = remember { mutableStateOf(10) }

    Surface (color = Color(0xFF3C1A5B))
    {

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Text(text = "${counter.value}", color = Color(0xFFFFF748),
                        fontSize = 45.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                         );
            Spacer(modifier = Modifier.size(75.dp))
            circle(counter = counter.value) {
               counter.value = it;
            };
            val context= LocalContext.current;

            Spacer(modifier = Modifier.size(75.dp))
           if(timeLeft.value>0 && counter.value>=0) {

               // timer Code

               LaunchedEffect(key1 = timeLeft) {
                   while (timeLeft.value > 0) {
                       delay(1000L)
                       timeLeft.value--
                   }
               }

               Text(
                   text = "Time left: ${timeLeft.value}", color = Color(0xFFFFF748),
                   fontSize = 45.sp,
                   fontStyle = FontStyle.Normal,
                   fontWeight = FontWeight.Bold
               )

           }

            if(timeLeft.value==0 ) {
                preBestScore = maxOf(counter.value, preBestScore);
                timeLeft.value =10
                counter.value = -1

            }

            Spacer(modifier = Modifier.size(75.dp))
            Text(
                text = " Your best Score:$preBestScore", color = Color(0xFFFFF748),
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )


        }
    }
}


//@Preview
@Composable
fun circle(counter: Int=0, moneyincrease:(Int) -> Unit  ) {

    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(65.dp)
            .clickable(enabled = true) {
                moneyincrease(counter + 1);
            },
        elevation = CardDefaults.cardElevation(focusedElevation = 10.dp),

        ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF748)),
            contentAlignment = Alignment.Center) {
            Text(text = "TAP ",color =Color.Blue, fontSize = 15.sp)
        }
    }

}



