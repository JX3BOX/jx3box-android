package com.carey.module_glidelib.config

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestListener
import com.carey.module_glidelib.progress.OnProgressListener

/**
 * Glide配置类
 */
class GlideConfigImpl private constructor(builder: Builder) : ImageConfig() {
    /**
     * 0 对应DiskCacheStrategy.all
     * 1 对应DiskCacheStrategy.NONE
     * 2 对应DiskCacheStrategy.SOURCE
     * 3 对应DiskCacheStrategy.RESULT
     */
    val cacheStrategy: Int
    val fallback: Int
    val transformation: Array<out BitmapTransformation>?
    val imageViews: Array<out ImageView>?
    val isClearMemory: Boolean
    val isClearDiskCache: Boolean
    val placeHolderDrawable: Drawable?
    val resizeX: Int
    val isCropCenter: Boolean
    val isCropCircle: Boolean
    val isFitCenter: Boolean
    val formatType: DecodeFormat?
    val resizeY: Int
    val imageRadius: Int
    val blurValue: Int
    val isCrossFade: Boolean
    var onProgressListener: OnProgressListener?
    var requestListener: RequestListener<Drawable?>?


    val isBlurImage: Boolean
        get() = blurValue > 0

    fun isImageRadius(): Boolean {
        return imageRadius > 0
    }

    open class Builder {
        internal var resizeX = 0
        internal var url: String? = null
        internal var drawableId = 0
        internal var imageView: ImageView? = null
        internal var placeholder = 0
        internal var placeholderDrawable: Drawable? = null
        internal var errorPic = 0
        internal var fallback = 0
        internal var cacheStrategy = 0
        internal var imageRadius = 0
        internal var blurValue = 0
        internal var transformation: Array<out BitmapTransformation>? = null
        internal var imageViews: Array<out ImageView>? = null
        internal var isClearMemory = false
        internal var isClearDiskCache = false
        internal var isCropCenter = false
        internal var isCropCircle = false
        internal var isCrossFade = false
        internal var formatType: DecodeFormat? = null
        internal var isFitCenter = false
        internal var resizeY = 0
        internal var onProgressListener: OnProgressListener? = null
        internal var requestListener: RequestListener<Drawable?>? = null
        open fun url(url: String?) = apply {
            this.url = url
        }

        open fun drawableId(drawableId: Int) = apply {
            this.drawableId = drawableId
        }

        open fun placeholder(placeholder: Int) = apply {
            this.placeholder = placeholder
        }

        open fun errorPic(errorPic: Int) = apply {
            this.errorPic = errorPic
        }

        open fun fallback(fallback: Int) = apply {
            this.fallback = fallback
        }

        open fun imageView(imageView: ImageView?) = apply {
            this.imageView = imageView
        }

        open fun cacheStrategy(cacheStrategy: Int) = apply {
            this.cacheStrategy = cacheStrategy
        }

        open fun imageRadius(imageRadius: Int) = apply {
            this.imageRadius = imageRadius
        }

        open fun blurValue(blurValue: Int) = apply { //blurValue 建议设置为 15
            this.blurValue = blurValue
        }

        open fun isCrossFade(isCrossFade: Boolean) = apply {
            this.isCrossFade = isCrossFade
        }

        open fun transformation(vararg transformation: BitmapTransformation) = apply {
            this.transformation = transformation
        }

        open fun imageViews(vararg imageViews: ImageView) = apply {
            this.imageViews = imageViews
        }

        open fun isClearMemory(isClearMemory: Boolean) = apply {
            this.isClearMemory = isClearMemory
        }

        open fun isClearDiskCache(isClearDiskCache: Boolean) = apply {
            this.isClearDiskCache = isClearDiskCache
        }

        open fun placeholderDrawble(placeholderDrawble: Drawable?) = apply {
            placeholderDrawable = placeholderDrawble
        }

        open fun resize(resizeX: Int, resizeY: Int) = apply {
            this.resizeX = resizeX
            this.resizeY = resizeY
        }

        open fun isCropCenter(isCropCenter: Boolean) = apply {
            this.isCropCenter = isCropCenter
        }

        open fun isCropCircle(isCropCircle: Boolean) = apply {
            this.isCropCircle = isCropCircle
        }

        open fun setDecodeFormate(decodeFormat: DecodeFormat?) = apply {
            formatType = decodeFormat
        }

        open fun isFitCenter(isFitCenter: Boolean) = apply {
            this.isFitCenter = isFitCenter
        }

        open fun progressListener(onProgressListener: OnProgressListener?) = apply {
            this.onProgressListener = onProgressListener
        }

        open fun requestListener(requestListener: RequestListener<Drawable?>?) = apply {
            this.requestListener = requestListener
        }

        open fun build(): GlideConfigImpl {
            return GlideConfigImpl(this)
        }
    }

    init {
        url = builder.url
        drawableId = builder.drawableId
        imageView = builder.imageView
        placeholder = builder.placeholder
        placeHolderDrawable = builder.placeholderDrawable
        errorPic = builder.errorPic
        fallback = builder.fallback
        cacheStrategy = builder.cacheStrategy
        transformation = builder.transformation
        imageViews = builder.imageViews
        isClearMemory = builder.isClearMemory
        isClearDiskCache = builder.isClearDiskCache
        resizeX = builder.resizeX
        resizeY = builder.resizeY
        isCropCenter = builder.isCropCenter
        isCropCircle = builder.isCropCircle
        formatType = builder.formatType
        isFitCenter = builder.isFitCenter
        isCrossFade = builder.isCrossFade
        imageRadius = builder.imageRadius
        blurValue = builder.blurValue
        onProgressListener = builder.onProgressListener
        requestListener = builder.requestListener
    }
}