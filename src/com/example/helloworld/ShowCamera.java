package com.example.helloworld;
import java.io.IOException;


import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.util.Log;


public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
   
    private Camera theCamera;
    private SurfaceHolder holdMe;
    private Camera.Parameters parameters;
    private Camera.AutoFocusCallback af_cb;
    
    public ShowCamera(Context context,Camera camera) {
        super(context);
        theCamera = camera;
        holdMe = getHolder();
        holdMe.addCallback(this);
        parameters =  theCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        theCamera.setParameters(parameters);
        af_cb=new Camera.AutoFocusCallback(){
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                  Log.d("ShowCamera", "autofocus complete: " + success);
            }
        };

        theCamera.setDisplayOrientation(90);
        Log.i("ShowCamera","Started Callback");

    }

    @Override

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.i("ShowCamera","Surface Create");
        theCamera.autoFocus(af_cb);
    
        try{
            theCamera.setPreviewDisplay(surfaceHolder);
        }
        catch(IOException e){
            Log.i("ShowCamera",e.getMessage());
            return;
        }
        Log.i("ShowCamera","Starting camera preview");
        theCamera.startPreview();

    }


    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
   }
   public void surfaceDestroyed(SurfaceHolder arg0) {
     // theCamera.stopPreview();
     // theCamera.release();   
        Log.i("ShowCamera","surfaceDestroy stopping preview");

    }
}       
