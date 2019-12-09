package com.examp.bienajuste;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetImageActivity extends AppCompatActivity {
    private static final String TAG = "TEjava";

    private static final int PICK_FROM_ALBUM = 1;
    private static final int PICK_FROM_CAMERA = 2;

    private Boolean isCamera = false;
    private File tempFile, compFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getimage);

        final Button galleryButton = (Button) findViewById(R.id.galleryButton);
        final Button cameraButton = (Button) findViewById(R.id.cameraButton);
        final Button okButton = (Button) findViewById(R.id.okButton);

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAlbum();
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이미 cameraButton이나 galleryButton을 이용해 ImageView에
                // 사진 띄우기 성공했다고 가정
                // 이제 민규찡의 코드로 넘어가자(compFile에는 ImageView에 띄워진 사진의 정보가 들어있음
                // .getAbsolutePath과 Intent를 이용해 사진의 경로를 민규쪽 코드로 넘길것임)

                Intent RegisterIntent = new Intent(GetImageActivity.this, MeasureActivity.class);
                RegisterIntent.putExtra("sizeloc",compFile.getAbsolutePath());

                //GetImageActivity.this.startActivity(RegisterIntent); //일단 RegisterActivity로 바로 넘어가게 하였음 추후 수정해야함
                //finish();
                GetImageActivity.this.startActivity(RegisterIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();

            if(tempFile != null) {
                if(tempFile.exists()) {
                    if(tempFile.delete()) {
                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
                        tempFile = null;
                    } else {
                        Log.e(TAG, "tempFile 삭제 실패");
                    }

                } else {
                    Log.e(TAG, "tempFile 존재하지 않음");
                }
            } else {
                Log.e(TAG, "tempFile is null");
            }

            return;
        }

        if (requestCode == PICK_FROM_ALBUM) {

            Uri photoUri = data.getData();
            Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + photoUri);

            Cursor cursor = null;

            try {
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

                Log.d(TAG, "tempFile Uri : " + Uri.fromFile(tempFile));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
        else if (requestCode == PICK_FROM_CAMERA)
            setImage();
    }

    /**
     *  앨범에서 이미지 가져오기
     */
    private void goToAlbum() {
        isCamera = false;

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    /**
     *  카메라에서 이미지 가져오기
     */
    private void takePhoto() {
        isCamera = true;

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int PERMISSIONS_REQUEST_CODE = 100;
        String[] REQUIRED_PERMISSIONS  = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (cameraPermission == PackageManager.PERMISSION_GRANTED && readExternalStoragePermission == PackageManager.PERMISSION_GRANTED && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED) {
            try {
                tempFile = createImageFile();
            } catch (IOException e) {
                Toast.makeText(this, "이미지 처리 오류! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                finish();
                e.printStackTrace();
            }
            if (tempFile != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                    Uri photoUri = FileProvider.getUriForFile(this,
                            "com.examp.bienajuste.provider", tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, PICK_FROM_CAMERA);

                } else {

                    Uri photoUri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, PICK_FROM_CAMERA);

                }
            }
        }
        else{
            ActivityCompat.requestPermissions( this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     *  폴더 및 파일 만들기
     */
    private File createImageFile() throws IOException {

        // 이미지 파일 이름 ( bienajuste_{시간}_ )
        String timeStamp = new SimpleDateFormat("HHmmss").format(new Date());
        String imageFileName = "bienajuste_" + timeStamp + "_";

        // 이미지가 저장될 파일 주소 ( bienajuste )
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/bienajuste/");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        // 빈 파일 생성
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        Log.d(TAG, "createImageFile : " + image.getAbsolutePath());

        return image;
    }

    private void setImage() {

        ImageView imageView = findViewById(R.id.imageView);

        ImageResizeUtils.resizeFile(tempFile, tempFile, 1280, isCamera);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d(TAG, "setImage : " + tempFile.getAbsolutePath());

        imageView.setImageBitmap(originalBm);
        compFile = tempFile;
        tempFile = null;

    }
}
