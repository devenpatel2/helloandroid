package com.example.helloworld;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class CamApi extends Activity {
    private Camera cameraObject;
    private ShowCamera showCamera;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_api);
        cameraObject = isCameraAvailable();
    
        showCamera = new ShowCamera(this, cameraObject);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camapi_preview);
        preview.addView(showCamera);
            
    }

	
	
    @Override
    public void onPause(){
        Log.i("MainActivity","On Pause Called");
        super.onPause();
        cameraObject.stopPreview();

        try{
            cameraObject.unlock();
        }
        catch(RuntimeException e){
            Log.i("MainActivity","cannot lock on pause");
        }
    }
	@Override
    public void onResume(){
        Log.i("MainActivity","On Resume Called");
        super.onResume();
        try{ 
            cameraObject.reconnect();
            Log.i("MainActivity","On Resume reconnecting");
			//cameraObject = isCameraAvailable();
        }
        catch(IOException e){
            Log.i("MainActivity","cannot reconnect on resume");
        }
	
    }

    @Override
    public void onDestroy(){
        Log.i("MainActivity","On Destroy Called");
				
        super.onDestroy();
        releaseCamera();	
    }
	 
    
    private void releaseCamera(){
        if (cameraObject != null){
            Log.i("MainActivity", "releasing Camera ");
	            
            cameraObject.release();        // release the camera for other applications
            cameraObject = null;
        }
    }
	
	
    public static Camera isCameraAvailable(){
        Camera object = null;
        int cam_count=Camera.getNumberOfCameras();
        Log.i("MainActivity", "Number of Cameras "+String.valueOf(cam_count));
        
        try {
            object = Camera.open(); 
            Log.i("MainActivity", "Camera opened");
        }
        catch (RuntimeException e){
        	if (object !=null)object.release();
            Log.i("MainActivity", "Camera unavailable  "+ e.getMessage() );

        }
        return object; 
   }

	
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

}
