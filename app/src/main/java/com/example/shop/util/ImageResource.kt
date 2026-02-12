package com.example.shop.util

import com.example.shop.R

/**
 * مپ کردن نام تصویر به ریسورس drawable
 * شما عکس‌های واقعی رو در res/drawable قرار بدید
 * و اسم فایل‌ها رو مطابق کلیدها بذارید
 */
object ImageResource {

    private val productImages = mapOf(
        "surge_short" to R.drawable.surge_short,
        "sweat_jogger" to R.drawable.jogger,
        "align_biker" to R.drawable.align_biker,
        "camo_legging" to R.drawable.leg,
        "metal_vent_sleeve" to R.drawable.metal_vent_sleeve,
        "at_ease_hoodie" to R.drawable.hoodi,
        "align_tank" to R.drawable.leg,
        "pace_breaker" to R.drawable.leg,
        "scuba_hoodie" to R.drawable.hoodi,
        "abc_pant" to R.drawable.leg,
    )

    private val categoryImages = mapOf(
        "shorts" to R.drawable.surge_short,
        "pants" to R.drawable.leg,
        "leggings" to R.drawable.leg,
        "sleeve" to R.drawable.metal_vent_sleeve,
        "long_sleeve" to R.drawable.metal_vent_sleeve,
        "align_tank" to R.drawable.align_biker,
    )

    private val bannerImages = mapOf(
        "sale_banner" to R.drawable.banner,
        "new_arrivals" to R.drawable.metal_vent_sleeve,
    )

    // تصویر هیروی صفحه Home
    val heroImage = R.drawable.bitmap

    fun getProductImage(key: String): Int {
        return productImages[key] ?: R.drawable.leg
    }

    fun getCategoryImage(key: String): Int {
        return categoryImages[key] ?: R.drawable.bitmap
    }

    fun getBannerImage(key: String): Int {
        return bannerImages[key] ?: R.drawable.bitmap
    }
}
