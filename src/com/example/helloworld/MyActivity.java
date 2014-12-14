package com.example.helloworld;

//import android.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.provider.MediaStore;
import android.hardware.Camera;
import android.widget.FrameLayout;


public class MyActivity extends Activity
{
    
    /** Called when the activity is first created. */
    private Camera cameraObject;
   private ShowCamera showCamera;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_main);
    }

    public void camIntent(View view){

         Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         startActivity(intent);           
   }
    
    public void camAPI(View view){
        cameraObject = isCameraAvailiable();
        showCamera = new ShowCamera(this, cameraObject);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(showCamera);
         
    }
 
    public static Camera isCameraAvailiable(){
        Camera object = null;
        try {
            object = Camera.open(); 
        }
        catch (Exception e){
        }
        return object; 
   }
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
