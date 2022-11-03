package com.example.firebase_login_signup_form

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.firebase_login_signup_form.databinding.FragmentPictureBinding
import kotlin.math.atan2
import kotlin.math.sqrt

class PictureFragment : Fragment(), View.OnTouchListener {

    lateinit var pictureBinding: FragmentPictureBinding
    private val matrix = Matrix()
    private val savedMatrix = Matrix()

    // we can be in one of these 3 states
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2
    private var mode = NONE

    // remember some things for zooming
    private val start = PointF()
    private val mid = PointF()
    private var oldDist = 1f
    private var d = 0f
    private var newRot = 0f
    private var lastEvent: FloatArray? = null
    private var bmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        pictureBinding = FragmentPictureBinding.inflate(inflater, container, false)
        return pictureBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pictureBinding.fullPicture.scaleType = ImageView.ScaleType.MATRIX

        pictureBinding.textview.text = requireArguments().getString("ImageName").toString()
        pictureBinding.fullPicture.setImageResource(requireArguments().getInt("ImageView"))

        pictureBinding.fullPicture.setOnTouchListener(this)
    }

    /**
     * Determine the space between the first two fingers
     */
    fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        val s = x * x + y * y
        return sqrt(s.toDouble()).toFloat()
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    open fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point[x / 2] = y / 2
    }

    /**
     * Calculate the degree to be rotated by.
     *
     * @param event
     * @return Degrees
     */
    open fun rotation(event: MotionEvent): Float {
        val delta_x = (event.getX(0) - event.getX(1)).toDouble()
        val delta_y = (event.getY(0) - event.getY(1)).toDouble()
        val radians = atan2(delta_y, delta_x)
        return Math.toDegrees(radians).toFloat()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        // handle touch events here

        // handle touch events here
        // view = v as ImageView
        when (event!!.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                savedMatrix.set(matrix)
                start[event.x] = event.y
                mode = DRAG
                lastEvent = null
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                if (oldDist > 10f) {
                    savedMatrix.set(matrix)
                    midPoint(mid, event)
                    mode = ZOOM
                }
                lastEvent = FloatArray(4)
                lastEvent!![0] = event.getX(0)
                lastEvent!![1] = event.getX(1)
                lastEvent!![2] = event.getY(0)
                lastEvent!![3] = event.getY(1)
                d = rotation(event)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                mode = NONE
                lastEvent = null
            }
            MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                matrix.set(savedMatrix)
                val dx = event.x - start.x
                val dy = event.y - start.y
                matrix.postTranslate(dx, dy)
            } else if (mode == ZOOM) {
                val newDist: Float = spacing(event)
                if (newDist > 10f) {
                    matrix.set(savedMatrix)
                    val scale = newDist / oldDist
                    matrix.postScale(scale, scale, mid.x, mid.y)
                }
                if (lastEvent != null && event.pointerCount == 2 || event.pointerCount == 3) {
                    newRot = rotation(event)
                    val r = newRot - d
                    val values = FloatArray(9)
                    matrix.getValues(values)
                    val tx = values[2]
                    val ty = values[5]
                    val sx = values[0]
                    val xc = pictureBinding.fullPicture.width / 2 * sx
                    val yc = pictureBinding.fullPicture.height / 2 * sx
                    matrix.postRotate(r, tx + xc, ty + yc)
                }
            }
        }
        pictureBinding.fullPicture.imageMatrix = matrix
        bmap = Bitmap.createBitmap(pictureBinding.fullPicture.width,
            pictureBinding.fullPicture.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bmap!!)
        pictureBinding.fullPicture.draw(canvas)
        //fin.setImageBitmap(bmap);
        return true
    }

}

