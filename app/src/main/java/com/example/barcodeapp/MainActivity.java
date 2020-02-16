package com.example.barcodeapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    private ImageView qrImageview;
    private ImageView barcodeImageview;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.editext);
        Button qrButton = findViewById(R.id.convertQRCodeButton);
        Button barcodeButton = findViewById(R.id.convertBarcodeButton);
        qrImageview = findViewById(R.id.qrImageview);
        barcodeImageview = findViewById(R.id.barcodeImageview);

        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputText.getText().toString().trim().isEmpty()) {
                    try {
                        convertToQRcode();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Field is required", Toast.LENGTH_SHORT).show();
                }

            }
        });
        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputText.getText().toString().trim().isEmpty()) {
                    try {
                        convertToBarcode();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Field is required", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void convertToQRcode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(inputText.getText().toString().trim(), BarcodeFormat.QR_CODE, 350, 300);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        qrImageview.setImageBitmap(bitmap);
    }

    private void convertToBarcode() throws WriterException {
        BitMatrix bitMatrix = multiFormatWriter.encode(inputText.getText().toString().trim(), BarcodeFormat.CODE_128, 400, 170);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        barcodeImageview.setImageBitmap(bitmap);
    }
}
