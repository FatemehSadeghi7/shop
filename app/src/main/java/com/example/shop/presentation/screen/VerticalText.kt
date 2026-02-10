import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalBottomUpText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle,
    color: Color = Color.Black,
    gap: Dp = 2.dp,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        text.reversed().forEachIndexed { i, ch ->
            Text(
                text = ch.toString(),
                style = style,
                color = color
            )
            if (i != text.lastIndex) Spacer(Modifier.height(gap))
        }
    }
}