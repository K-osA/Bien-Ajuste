package com.examp.bienajuste;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import android.content.Intent;

public class Show3DModelActivity extends AppCompatActivity {
    private static final String TAG = Show3DModelActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private ArFragment arFragment;
    private ModelRenderable andyRenderable;
    private ModelRenderable modelOneRenderable;
    private ModelRenderable modelTwoRenderable;
    private ModelRenderable modelThreeRenderable;

    Integer shoesId;
    boolean modelOne,modelTwo,modelThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        shoesId = intent.getIntExtra("shoesId",0);
        modelOne=modelTwo=modelThree=false;
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }


        setContentView(R.layout.activity_ux);
        final Button exitButton = (Button) findViewById(R.id.exitButton);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        if (shoesId == 1) {
            modelOne=true;
            ModelRenderable.builder()
                    .setSource(this, R.raw.converse_3d)
                    .build()
                    .thenAccept(renderable -> modelOneRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load model one renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });
        }

        else if (shoesId == 2) {
            modelTwo=true;
            ModelRenderable.builder()
                    .setSource(this, R.raw.converse_obj)
                    .build()
                    .thenAccept(renderable -> modelTwoRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load model two renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });
        }

        else if (shoesId == 3) {
            modelThree=true;
            ModelRenderable.builder()
                    .setSource(this, R.raw.obj)
                    .build()
                    .thenAccept(renderable -> modelThreeRenderable = renderable)
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load modelThree renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });

        }

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (modelOne && modelOneRenderable == null || modelTwo && modelTwoRenderable == null || modelThree && modelThreeRenderable == null) {
                        return;
                    }

                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());
                    if(modelOne){
                        TransformableNode modelone = new TransformableNode(arFragment.getTransformationSystem());
                        modelone.getScaleController().setMinScale(0.00001f);
                        modelone.getScaleController().setMaxScale(0.00003f);
                        modelone.setParent(anchorNode);
                        modelone.setRenderable(modelOneRenderable);
                        modelone.select();
                    }
                    else if(modelTwo){
                        TransformableNode modeltwo = new TransformableNode(arFragment.getTransformationSystem());
                        modeltwo.getScaleController().setMinScale(0.00001f);
                        modeltwo.getScaleController().setMaxScale(0.00003f);
                        modeltwo.setParent(anchorNode);
                        modeltwo.setRenderable(modelTwoRenderable);
                        modeltwo.select();
                    }
                    else if(modelThree) {
                        TransformableNode modelThree = new TransformableNode(arFragment.getTransformationSystem());
                        modelThree.getScaleController().setMinScale(0.00001f);
                        modelThree.getScaleController().setMaxScale(0.00003f);
                        modelThree.setParent(anchorNode);
                        modelThree.setRenderable(modelThreeRenderable);
                        modelThree.select();
                    }
                });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelOne=modelTwo=modelThree=false;
                finish();
            }
        });
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}
