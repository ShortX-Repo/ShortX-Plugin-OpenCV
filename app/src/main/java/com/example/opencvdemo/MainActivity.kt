package com.example.opencvdemo

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.opencvdemo.api.Images
import com.example.opencvdemo.api.ScreenMetrics
import com.example.opencvdemo.opencv.OpenCVHelper
import com.example.opencvdemo.ui.theme.OpenCVDemoTheme
import org.autojs.autojs.core.image.ImageWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenCVDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var initStatus by remember {
        mutableStateOf("Init...")
    }
    var pointStatus by remember {
        mutableStateOf("--")
    }

    OpenCVHelper.initIfNeeded(LocalContext.current) {
        initStatus = "Init complete."

        val large = BitmapFactory.decodeResource(context.resources, R.raw.large)
        val small = BitmapFactory.decodeResource(context.resources, R.raw.small)

        val images = Images(ScreenMetrics())
        val point = images.findImage(ImageWrapper.ofBitmap(large), ImageWrapper.ofBitmap(small))
        pointStatus = point?.toString() ?: "NOT FOUND"
    }


    Column {
        Text(
            text = initStatus,
            modifier = modifier
        )
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = pointStatus,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenCVDemoTheme {
        Greeting("Android")
    }
}