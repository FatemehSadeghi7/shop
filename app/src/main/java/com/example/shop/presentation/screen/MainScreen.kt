import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R

@Composable
fun MainScreen(
    onSearchClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onArrowClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.72f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bitmap),
                contentDescription = "Product",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = "V",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(start = 24.dp, top = 20.dp)
            )

            Canvas(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 40.dp)
                    .width(16.dp)
                    .height(200.dp)
            ) {
                val strokeWidth = 1.5.dp.toPx()
                val centerX = size.width / 2
                val tickWidth = size.width * 0.6f

                drawLine(
                    color = Color.White.copy(alpha = 0.85f),
                    start = Offset(centerX - tickWidth / 2, 0f),
                    end = Offset(centerX + tickWidth / 2, 0f),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.85f),
                    start = Offset(centerX, 0f),
                    end = Offset(centerX, size.height),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White.copy(alpha = 0.85f),
                    start = Offset(centerX - tickWidth / 2, size.height),
                    end = Offset(centerX + tickWidth / 2, size.height),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.28f)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(top = 16.dp, end = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onSearchClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(onClick = onCartClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Cart",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(onClick = onMenuClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.twotone_grid_view_24),
                        contentDescription = "Menu",
                        tint = Color.Black,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                "VERTLUNE".forEach { char ->
                    Text(
                        text = char.toString(),
                        color = Color.Black,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        lineHeight = 44.sp,
                        modifier = Modifier.graphicsLayer(rotationZ = -90f)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp)
                    .size(52.dp)
                    .clip(CircleShape)
                    .clickable{onArrowClick()}
                    .background(Color(0xFFF44336)
                        ),

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_line_end_arrow_notch_24),
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}


