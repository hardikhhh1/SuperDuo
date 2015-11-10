package it.jaschke.alexandria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by harora on 11/10/15.
 */
public class ScannerActivity extends Activity implements ZXingScannerView.ResultHandler{

    public static final String SCANNER_TAG = ScannerActivity.class.getSimpleName();
    private ZXingScannerView zXingScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        zXingScannerView.setResultHandler(this);
        setContentView(zXingScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Intent intent = new Intent();
        intent.putExtra(SCANNER_TAG, result.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}
