package com.example.artemplateproject

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.arcorelab.common.helpers.CameraPermissionHelper
import com.example.arcorelab.common.helpers.SessionManagerHelper
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainActivity : AppCompatActivity(), GLSurfaceView.Renderer {

    var userARSetupRequest: Boolean = true
    var mSession: Session? = null
    var mConfig: Config? = null
    val TAG: String = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    override fun onResume() {
        super.onResume()
        //

       SessionCameraChecker()
    }

    override fun onPause() {
        super.onPause()
        SessionOnPause()


    }

    fun onRadioButtonClicked(view: View)
    {

    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun onDrawFrame(gl: GL10?) {
        TODO("Not yet implemented")
    }

    fun SessionCameraChecker()
    {
        if(!CameraPermissionHelper.hasCameraPermission(this))
        {
            CameraPermissionHelper.requestCameraPermission(this)
            return
        }
        //
        try
        {
            if(mSession == null)
            {
                when(ArCoreApk.getInstance().requestInstall(this,userARSetupRequest))
                {
                    ArCoreApk.InstallStatus.INSTALLED ->
                    {
                        mSession= SessionManagerHelper.createSession(this)
                    }
                    ArCoreApk.InstallStatus.INSTALL_REQUESTED ->
                    {
                        userARSetupRequest= false
                        return
                    }
                }
            }
        }
        catch(e: UnavailableUserDeclinedInstallationException)
        {
            Toast.makeText(this,"TODO: handle exception" + e, Toast.LENGTH_LONG).show()
            return
        }
    }

    fun SessionOnPause()
    {
        if (mSession != null) {
            /*
            displayRotationHelper.onPause()
            surfaceView.onPause()
             */
            mSession!!.pause()
        }
    }


}