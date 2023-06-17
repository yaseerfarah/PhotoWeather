package com.yasser.photoweather.base.presentation.view.customview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.yasser.photoweather.R
import com.yasser.photoweather.core.extensions.getResourcesByLocal
import com.yasser.photoweather.core.extensions.setVectorDrawableEnd
import com.yasser.photoweather.core.extensions.visible

open class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    fun setLoading(
        @ColorRes backgroundColor: Int = android.R.color.transparent,
        blockTouchListener: Boolean = true
    ) {
        showState {
            val stateView = inflate(context, R.layout.layout_state_loading_view, null)
            this.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            return@showState stateView
        }
        setOnTouchListener { _, _ ->
            blockTouchListener
        }
    }

    fun setEmpty(@StringRes emptyMsg: Int, @DrawableRes emptyIcon: Int? = null) {
        showState {
            val stateView = inflate(context, R.layout.layout_state_empty_view, null)
            stateView.findViewById<TextView>(R.id.txtEmptyState)?.text = context.getString(emptyMsg)
            if (emptyIcon != null) stateView.findViewById<ImageView>(R.id.imgEmptyState)
                ?.setImageResource(emptyIcon)
            else stateView.findViewById<ImageView>(R.id.imgEmptyState)?.visible(false)
            return@showState stateView
        }
    }

    fun setError(
        language: String? = null,
        message: Int,
        retryAction: (() -> Unit),
        @ColorRes backgroundColor: Int = R.color.colorWhiteOpacity60,
        @ColorRes retryTextColor: Int = R.color.color_retry_text,
        @DrawableRes retryIcon: Int = R.drawable.ic_retry,
        @DrawableRes retryBackground: Int = R.drawable.shape_btn_retry

    ) {
        if (message == R.string.msgNoInternetConnection) {
            setNetworkError(
                language,
                context.getString(message),
                retryAction = retryAction,
                backgroundColor = backgroundColor,
                retryIcon = retryIcon,
                retryBackground = retryBackground,
                retryTextColor = retryTextColor,
            )
        } else {
            setUnexpectedError(
                language,
                context.getString(message),
                retryAction = retryAction,
                backgroundColor = backgroundColor,
                retryTextColor = retryTextColor,
                retryBackground = retryBackground
            )
        }
    }

    fun setNetworkError(
        language: String? = null,
        message: String = context.getString(R.string.msgNoInternetConnection),
        retryMessage: String = context.getString(R.string.no_internet_screens_retry_btn_title),
        retryAction: (() -> Unit)? = null,
        @DrawableRes networkIcon: Int = R.drawable.ic_connection_failed,
        @ColorRes textColor: Int = R.color.colorRollingStone,
        @DrawableRes retryIcon: Int = R.drawable.ic_retry,
        @DrawableRes retryBackground: Int = R.drawable.shape_btn_retry,
        @ColorRes backgroundColor: Int = R.color.colorWhiteOpacity60,
        @ColorRes retryTextColor: Int = R.color.color_retry_text
    ) {
        showState {
            val stateView = inflate(context, R.layout.layout_state_network_error_view, null)

            stateView.findViewById<ImageView>(R.id.imgNoConnection).setImageResource(networkIcon)

            stateView.findViewById<TextView>(R.id.tvNoConnection)?.text = message
            language?.let {
                stateView.findViewById<TextView>(R.id.tvNoConnection)?.text =
                    context.getResourcesByLocal(language)
                        .getString(R.string.msgNoInternetConnection)
            }

            stateView.findViewById<TextView>(R.id.tvNoConnection)
                ?.setTextColor(ContextCompat.getColor(context, textColor))

            stateView.findViewById<Button>(R.id.btnRetryConnection)?.text = retryMessage
            language?.let {
                stateView.findViewById<Button>(R.id.btnRetryConnection)?.text =
                    context.getResourcesByLocal(language)
                        .getString(R.string.no_internet_screens_retry_btn_title)
            }

            stateView.findViewById<Button>(R.id.btnRetryConnection).setVectorDrawableEnd(retryIcon)
            stateView.findViewById<Button>(R.id.btnRetryConnection)
                ?.setBackgroundResource(retryBackground)
            stateView.findViewById<Button>(R.id.btnRetryConnection)
                ?.setOnClickListener { retryAction?.invoke() }
            stateView.findViewById<Button>(R.id.btnRetryConnection)
                ?.setTextColor(ContextCompat.getColor(context, retryTextColor))
            this.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            return@showState stateView
        }
    }

    fun setUnexpectedError(
        language: String? = null,
        message: String = context.getString(R.string.msgUnexpextedError),
        retryMessage: String = context.getString(R.string.no_internet_screens_retry_btn_title),
        retryAction: (() -> Unit)? = null,
        @DrawableRes retryIcon: Int = R.drawable.ic_retry,
        @DrawableRes retryBackground: Int = R.drawable.shape_btn_retry,
        @ColorRes textColor: Int = R.color.colorRollingStone,
        @ColorRes retryTextColor: Int = R.color.color_retry_text

        ,
        @ColorRes backgroundColor: Int = R.color.transparent

    ) {
        showState {
            val stateView = inflate(context, R.layout.layout_state_unexpected_failure, null)

            stateView.findViewById<TextView>(R.id.tvUnexpected)?.text = message
            language?.let {
                stateView.findViewById<TextView>(R.id.tvUnexpected)?.text =
                    context.getResourcesByLocal(language)
                        .getString(R.string.msgUnexpextedError)
            }

            stateView.findViewById<TextView>(R.id.tvUnexpected)
                ?.setTextColor(ContextCompat.getColor(context, textColor))
            stateView.findViewById<Button>(R.id.btnRetryUnexpected)?.text = retryMessage
            language?.let {
                stateView.findViewById<Button>(R.id.btnRetryUnexpected)?.text =
                    context.getResourcesByLocal(language)
                        .getString(R.string.no_internet_screens_retry_btn_title)
            }
            stateView.findViewById<Button>(R.id.btnRetryUnexpected)?.setVectorDrawableEnd(retryIcon)
            stateView.findViewById<Button>(R.id.btnRetryUnexpected)
                ?.setBackgroundResource(retryBackground)

            stateView.findViewById<Button>(R.id.btnRetryUnexpected)
                ?.setTextColor(ContextCompat.getColor(context, retryTextColor))
            stateView.findViewById<Button>(R.id.btnRetryUnexpected)
                ?.setOnClickListener { retryAction?.invoke() }
            this.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            return@showState stateView
        }
    }

    fun setContent() {
        if (visibility != View.GONE) {
            removeAllViews()
            visibility = View.GONE
        }
        setOnTouchListener(null)
    }

    protected fun showState(inflater: (() -> View)) {
        visibility = View.VISIBLE
        this.post {
            removeAllViews() // (width* SCREEN_PERCENT)
            // todo remove getting width directly and make it attr
            val frameParams = LayoutParams(
                (width * .85f /*context.getFloat(R.dimen.empty_screen_width_ration)*/).toInt(),
                LayoutParams.MATCH_PARENT
            ).apply { gravity = Gravity.CENTER }
            addView(inflater.invoke(), frameParams)
        }
    }
}

fun StateView.showLoading(
    show: Boolean,
    @ColorRes backgroundColor: Int = android.R.color.transparent,
    blockTouchListener: Boolean = true
) {
    if (show) setLoading(backgroundColor, blockTouchListener) else setContent()
}
