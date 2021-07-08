package start.android.barcodetestsiri02;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScannerActivity extends Activity implements DecoratedBarcodeView.TorchListener {

    CaptureManager captureManager;
    ImageButton flashButton;
    Boolean flashCheck;
    DecoratedBarcodeView decoratedBarcodeView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);

        flashCheck = false;
        flashButton = findViewById(R.id.switch_flashlight);

        if(!HasFlash())
        {
            flashButton.setVisibility(View.GONE);
        }

        decoratedBarcodeView = findViewById(R.id.zxing_barcode_scanner);
        decoratedBarcodeView.setTorchListener(this);

        captureManager = new CaptureManager(this, decoratedBarcodeView);

        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();




    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    boolean HasFlash()
    {
        return getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    @Override
    public void onTorchOn() {
        flashButton.setImageResource(R.drawable.ic_flash_on_white_36dp);
        flashCheck = true;

    }

    @Override
    public void onTorchOff() {
        flashButton.setImageResource(R.drawable.ic_flash_off_white_36dp);
        flashCheck = false;

    }
}
