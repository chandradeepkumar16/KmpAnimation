package  com.chandradeepdev.dhyaan

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.chandradeepdev.dhyaan.theme.AppTheme
import com.example.rotatingshape.model.Spark
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.resource
import kotlin.random.Random

@Composable
internal fun App(
    systemAppearance: (isLight: Boolean) -> Unit = {}
) = AppTheme(systemAppearance) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisibility by remember { mutableStateOf(false) }
//
//    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
//
//        Row(
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Login",
//                style = MaterialTheme.typography.titleMedium,
//                modifier = Modifier.padding(16.dp)
//            )
//
//            Spacer(modifier = Modifier.weight(1.0f))
//
//            var isDark by LocalThemeIsDark.current
//            IconButton(
//                onClick = { isDark = !isDark }
//            ) {
//                Icon(
//                    modifier = Modifier.padding(8.dp).size(20.dp),
//                    imageVector = if (isDark) rememberLightMode() else rememberDarkMode(),
//                    contentDescription = null
//                )
//            }
//        }
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth().padding(16.dp)
//        )
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            singleLine = true,
//            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth().padding(16.dp),
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Password
//            ),
//            trailingIcon = {
//                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
//                    val imageVector = if (passwordVisibility) Icons.Default.Close else Icons.Default.Edit
//                    Icon(imageVector, contentDescription = if (passwordVisibility) "Hide password" else "Show password")
//                }
//            }
//        )
//
//        Button(
//            onClick = { /* Handle login logic here */ },
//            modifier = Modifier.fillMaxWidth().padding(16.dp)
//        ) {
//            Text("Login")
//        }
//
//        TextButton(
//            onClick = { openUrl("https://github.com/terrakok") },
//            modifier = Modifier.fillMaxWidth().padding(16.dp)
//        ) {
//            Text("Open github")
//        }
//
//        TextButton(onClick = {}){
//            Text(text = "hey there chandradeep")
//        }
//    }
    var isAnimating by remember { mutableStateOf(false) }
    var rotationAngle by remember { mutableStateOf(0f) }
    val sparksList = remember { mutableStateOf(emptyList<Spark>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // RevolvingImageScreen content
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .graphicsLayer(
                    rotationZ = rotationAngle
                )
                .background(Color.Blue, shape = CircleShape)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        rotationZ = rotationAngle
                    )
            ) {
                for (spark in sparksList.value) {
                    val startX = spark.position.x
                    val startY = spark.position.y
                    val endX = spark.position.x + spark.size
                    val endY = spark.position.y + spark.size

                    // Draw a straight line for each sparkle
                    drawLine(
                        color = Color.Yellow,
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 7f // Adjust the line width as needed
                    )
                }
            }
            TitleImage()
//            Image(
//                painter = painterResource(""), // Replace with your image resource
//                contentDescription = null, // Provide a content description
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(CircleShape),
//                contentScale = ContentScale.FillBounds // Center-crop the image to fit the circular shape
//            )
        }

        // Button to start/stop rotation and sparkle animation
        Button(
            onClick = {
                isAnimating = !isAnimating
                if (isAnimating) {
                    // Start the sparkle animation
                    startSparkleAnimation(sparksList)
                }
            }
        ) {
            if(isAnimating){
                Text(text = "Stop Animation and Rotation")
            }else{
                Text(text = "Start Animation and Rotation")
                sparksList.value = emptyList()
            }
        }
    }

    // Rotate the image when isAnimating is true
    LaunchedEffect(isAnimating) {
        while (isAnimating) {
            rotationAngle += 70f
            delay(50) // Delay to control the rotation speed
        }
    }
}





// Function to start the sparkle animation
private fun startSparkleAnimation
            (sparksList: MutableState<List<Spark>>)
{

//    val random = Random(System.currentTimeMillis())
    val random = Random
    val newSparkles = mutableListOf<Spark>()

    repeat(2) {
        val sparkle = Spark(
            Offset(random.nextInt(60).toFloat(),random.nextInt(400).toFloat()),500f
        )
        newSparkles.add(sparkle)
    }

    repeat(8) {
        val sparkle = Spark(
            Offset(random.nextInt(50).toFloat(),random.nextInt(70).toFloat()),600f
        )
        newSparkles.add(sparkle)
    }


    repeat(15) {
        val sparkle = Spark(
            Offset(random.nextInt(400).toFloat(),random.nextInt(90).toFloat()),900f
        )
        newSparkles.add(sparkle)
    }


    // Update the sparkles list with the new sparkles
    sparksList.value = sparksList.value + newSparkles

}

//internal expect fun openUrl(url: String?)