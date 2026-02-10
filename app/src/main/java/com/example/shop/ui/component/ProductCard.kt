package com.example.shop.ui.component

import com.example.shop.data.model.Product
import com.example.shop.ui.theme.Dimens
import com.example.shop.ui.theme.VertluneColors


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .clip(RoundedCornerShape(Dimens.RadiusMd))
                .background(VertluneColors.CardBackground)
        )

        Spacer(modifier = Modifier.height(Dimens.PaddingSm))

        Text(
            text = product.name,
            color = VertluneColors.Black,
            fontSize = Dimens.FontLg,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )

        Text(
            text = product.formattedPrice,
            color = VertluneColors.Gray,
            fontSize = Dimens.FontSm
        )
    }
}