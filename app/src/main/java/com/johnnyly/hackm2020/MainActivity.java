package com.johnnyly.hackm2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Button button;
    ImageView imageView;
    ImageButton imageButton;
    TextView text_display;
    static final int REQUEST_IMAGE_CAMERA=1000;
    BitmapDrawable bitmapDrawable;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button =findViewById(R.id.button);


        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageView = (ImageView) findViewById(R.id.imageView);
        text_display = (TextView) findViewById(R.id.text_display);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakeImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTextFromImageFunction();
            }
        });

    }



    private void TakeImage() {
        Intent takeImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeImageIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(takeImageIntent,REQUEST_IMAGE_CAMERA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_IMAGE_CAMERA && resultCode == RESULT_OK && data!=null)
        {
            CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK )
            {
                Uri resultURI=result.getUri();

                imageView.setImageURI(resultURI);//IT show image to image view
                bitmapDrawable=(BitmapDrawable)imageView.getDrawable();
                bitmap=bitmapDrawable.getBitmap();
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception e=result.getError();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void GetTextFromImageFunction() {
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational())
        {
            Toast.makeText(this, "Error occur", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray=textRecognizer.detect(frame);
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0; i< ((SparseArray) textBlockSparseArray).size(); i++)
            {
                TextBlock textBlock=textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }
            text_display.setText(stringBuilder.toString());

        }
    }
}












