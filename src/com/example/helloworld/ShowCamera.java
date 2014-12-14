package com.example.helloworld;
import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.content.res.Configuration;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
   
   private Camera theCamera;
   private SurfaceHolder holdMe;

   public ShowCamera(Context context,Camera camera) {
      super(context);
      theCamera = camera;
      holdMe = getHolder();
      holdMe.addCallback(this);
   }

    @Override
/*    public void surfaceCreated(SurfaceHolder holder) {
        try   {
            theCamera.setPreviewDisplay(holder);
            theCamera.startPreview(); 
        }
            catch (IOException e) {
        }
    
}*/

public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {       
            Camera.Parameters parameters = theCamera.getParameters();
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                 parameters.set("orientation", "portrait");
                 theCamera.setDisplayOrientation(90);
                 parameters.setRotation(90);
            }
                 else {
                      // This is an undocumented although widely known feature
                      parameters.set("orientation", "landscape");
                      // For Android 2.2 and above
                      theCamera.setDisplayOrientation(0);
                      // Uncomment for Android 2.0 and above
                      parameters.setRotation(0);
            }
            theCamera.setPreviewDisplay(surfaceHolder);
            theCamera.startPreview();

        } catch (IOException e) {
            // left blank for now
        }
    }


    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
   }
   public void surfaceDestroyed(SurfaceHolder arg0) {
        theCamera.stopPreview();
        theCamera.release();   
}
}       
