package com.azhar.images_jetpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import coil.compose.rememberAsyncImagePainter
import com.azhar.images_jetpackcompose.ui.theme.ImagesJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagesJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildImage()
                }
            }
        }
    }
}

@Composable
fun BuildImage() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleImageView()
        LineSpacer()
        SimpleImageViewWithSize()
        LineSpacer()
        ImageButton()
        LineSpacer()
        CircularImageView()
        LineSpacer()
        RoundedCornerShapeImageView()
        LineSpacer()
        RoundedCornerShapeImageView2()
        LineSpacer()
        CutCornerShapeImageView()
        LineSpacer()
        ImageWithCustomAspectRatio()
        LineSpacer()
        BlurredEdgeTreatment()
        LineSpacer()
        BlurredEdgeTreatmentUnbounded()
        LineSpacer()
        ImageFromRemoteUsingCoil()
        LineSpacer()
        SettingImageAsBackgroundForBox()
        LineSpacer()
        SettingImageAsBackgroundForCard()
        LineSpacer()
        VectorImageView()
        LineSpacer()
        BitmapImageView()
        LineSpacer()
        MirrorImage(LocalContext.current)
        LineSpacer()
    }
}

@Composable
fun SimpleImageView() {
    Image(
        painter = painterResource(id = R.drawable.cat),
        contentDescription = "Cat Image"
    )
}

@Composable
fun SimpleImageViewWithSize() {
    Image(
        modifier = Modifier.size(size = 200.dp),
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog Image"
    )
}

@Composable
fun ImageButton() {
    val contextForToast = LocalContext.current.applicationContext

    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = "Dog Image",
        modifier = Modifier.clickable {
            Toast.makeText(contextForToast, "Click!", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun CircularImageView() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Color.Red, shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RoundedCornerShapeImageView() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(size = 12.dp))
            .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(size = 12.dp)),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun RoundedCornerShapeImageView2() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(160.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 20.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 40.dp
                )
            )
            .border(
                width = 2.dp, color = Color.Red, shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 20.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 40.dp
                )
            ),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun CutCornerShapeImageView() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(160.dp)
            .clip(CutCornerShape(size = 20.dp))
            .border(width = 2.dp, color = Color.Red, shape = CutCornerShape(size = 20.dp)),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun ImageWithCustomAspectRatio() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier.aspectRatio(16f / 7f)
    )
}

@Composable
fun BlurredEdgeTreatment() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(size = 160.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment(shape = RoundedCornerShape(percent = 5))
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun BlurredEdgeTreatmentUnbounded() {
    Image(
        painter = painterResource(id = R.drawable.dog),
        contentDescription = null,
        modifier = Modifier
            .size(size = 160.dp)
            .blur(
                radiusX = 10.dp,
                radiusY = 10.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ImageFromRemoteUsingCoil() {
    val asyncPainter =
        rememberAsyncImagePainter("https://semicolonspace.com/wp-content/uploads/2023/02/forest.jpg")

    Image(
        painter = asyncPainter,
        contentDescription = null
    )
}

@Composable
fun SettingImageAsBackgroundForBox() {
    Box(modifier = Modifier.size(size = 200.dp)) {
        // add the image
        Image(
            modifier = Modifier.size(size = 200.dp),
            painter = painterResource(id = R.drawable.dog),
            contentDescription = "Dog Image"
        )
        // add the content on the image here
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SettingImageAsBackgroundForCard() {
    Card(modifier = Modifier.size(size = 200.dp)) {
        // add the image
        Image(
            modifier = Modifier.size(size = 200.dp),
            painter = painterResource(id = R.drawable.dog),
            contentDescription = "Dog Image"
        )
        // add the content on the image here
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun VectorImageView() {
    Image(
        imageVector = Icons.Default.Search,
        contentDescription = null,
        modifier = Modifier.size(size = 100.dp)
    )
}

@Composable
fun BitmapImageView() {
    val context = LocalContext.current
    val bitmap = ContextCompat.getDrawable(context, R.drawable.dog)?.toBitmap()
        ?.asImageBitmap()!!

    Image(
        bitmap = bitmap,
        contentDescription = null,
        modifier = Modifier.size(size = 100.dp)
    )
}

@Composable
fun MirrorImage(context: Context) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(40.dp))

        // on below line we are adding mirror
        // image for our image view.
        MirrorImage {
            // on below line we are
            // creating a simple image.
            Image(
                // on below line specifying image id
                painterResource(id = R.drawable.dog),
                // on below line specifying image description.
                contentDescription = "Android",
                // on below line specifying content scale.
                contentScale = ContentScale.Crop,
                // on below line adding modifier for image.
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    // on below line adding
                    // rounded radius for image.
                    .clip(
                        RoundedCornerShape(24.dp)
                    )
            )
        }

    }
}

// on below line creating a
// widget to mirror our image.
@Composable
fun MirrorImage(image: @Composable () -> Unit) {

    // on below line creating a column.
    Column {
        // on below line displaying
        // original image.
        image()
        // on below line creating  a box.
        Box(modifier = Modifier
            // on below line adding a
            // graphic layer to it
            // to display mirror image.
            .graphicsLayer {
                // on below line adding
                // alpha and rotation for image.
                alpha = 0.70f
                rotationZ = 180f
            }
            // on below line drawing content
            .drawWithContent {
                // on below line adding colors for image.
                val colors = listOf(Color.White, Color.Transparent)
                // on below line drawing content.
                drawContent()
                // on below line drawing rectangle.
                drawRect(
                    // on below line specifying
                    // brush and blend mode.
                    brush = Brush.verticalGradient(colors),
                    blendMode = BlendMode.DstIn
                )
            }
            // on below line adding blur effect for image.
            .blur(radiusX = 1.dp, radiusY = 3.dp, BlurredEdgeTreatment.Unbounded)

        ) {
            // on below line displaying
            // our mirror image effect.
            image()
        }
    }
}

@Composable
fun LineSpacer(){
    Spacer(modifier = Modifier.height(40.dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagesJetpackComposeTheme {
        BuildImage()
    }
}