package com.anastaasiasenyshyn.ritrattolinguistico.drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatImageView

data class PathInfo (
    var path : Path? = null,
    var color : Int? = null,
    var stroke : Float? = null
)
class DrawingImageView : AppCompatImageView, OnTouchListener {
    var startx = 50
    var starty = 90
    var endx = 350
    var endy = 90
    private var mCanvas: Canvas
    //var mPaint: Paint? = null
    private var mPath: Path
    private val paths = ArrayList<PathInfo>()
    private val undonePaths = ArrayList<PathInfo>()

    private var currentColor : Int = Color.BLACK
    private var currentStroke : Float = 16f


    constructor(context: Context?) : super(context!!) {
        isFocusable = true
        isFocusableInTouchMode = true
        setOnTouchListener(this)
        mCanvas = Canvas()
        mPath = Path()
    }

    fun getPaint(color : Int,stroke : Float) : Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = stroke
        return paint
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        isFocusable = true
        isFocusableInTouchMode = true
        setOnTouchListener(this)
        mCanvas = Canvas()
        mPath = Path()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        isFocusable = true
        isFocusableInTouchMode = true
        setOnTouchListener(this)
        mCanvas = Canvas()
        mPath = Path()
    }

    fun setStrokeWidth(stroke : Float){
       currentStroke = stroke
    }

    fun setPaintColor(colorResId : Int){
        currentColor = context.getColor(colorResId)
    }

    fun setPaintStroke(stroke : Float){
        currentStroke = stroke
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (p in paths) {
            canvas.drawPath(p.path!!, getPaint(p.color!!,p.stroke!!))
        }
        canvas.drawPath(mPath, getPaint(currentColor,currentStroke))
    }

    private var mX = 0f
    private var mY = 0f
    private fun touch_start(x: Float, y: Float) {
        undonePaths.clear()
        mPath.reset()
        mPath.moveTo(x, y)
        mX = x
        mY = y
    }

    fun onClickUndo() {
        if (paths.size > 0) {
            undonePaths.add(paths.removeAt(paths.size - 1))
            invalidate()
        } else {
        }
        //toast the user
    }

    fun onClickRedo() {
        if (undonePaths.size > 0) {
            paths.add(undonePaths.removeAt(undonePaths.size - 1))
            invalidate()
        } else {
        }
        //toast the user
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
    }

    private fun touchUp() {
        mPath.lineTo(mX, mY)
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, getPaint(currentColor,currentStroke))
        // kill this so we don't double draw
        paths.add(PathInfo(mPath,currentColor,currentStroke))
        mPath = Path()
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touch_start(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }
}