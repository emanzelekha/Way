package com.appytech.businessway.dialogs;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.system.ErrnoException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.appytech.businessway.R;
import com.appytech.businessway.tools.Base64Manager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eng_m on 8/12/2016.
 */
public class SelectImageDialog extends DialogFragment {

    /* Add to Activity in Manifest
        android:configChanges="orientation|screenSize"

        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     */

    private static int MAX_LENGTH = 500;

    private static String TAG_Image_URL = "imageURL";
    private static String TAG_FIXED_CROP = "fixedCrop";
    private static String TAG_FIXED_CROP_RATIO_X = "fixedCropRatioX";
    private static String TAG_FIXED_CROP_RATIO_Y = "fixedCropRatioY";

    private CropImageView cropImageView;
    private Uri mCropImageUri;
    private static SelectImageListener selectImageListener;
    private Bitmap finalBitmap;

    public SelectImageDialog() {
    }

    public static SelectImageDialog newInstance(String imageURL) {
        return newInstance(imageURL, false);
    }

    public static SelectImageDialog newInstance(String imageURL, boolean isFixedCrop) {
        return newInstance(imageURL, isFixedCrop, 0, 0);
    }

    public static SelectImageDialog newInstance(String imageURL, boolean isFixedCrop, int fixedRatioX, int fixedRatioY) {
        SelectImageDialog fragment = new SelectImageDialog();
        Bundle args = new Bundle();
        args.putString(TAG_Image_URL, imageURL);
        args.putBoolean(TAG_FIXED_CROP, isFixedCrop);
        args.putInt(TAG_FIXED_CROP_RATIO_X, fixedRatioX);
        args.putInt(TAG_FIXED_CROP_RATIO_Y, fixedRatioY);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_select_image, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = (ImageView) view.findViewById(R.id.select_image_logo_imageView);
        cropImageView = (CropImageView) view.findViewById(R.id.select_image_cropImageView);

        Log.e("LoadImageDialog", "start");
        if (getArguments() != null) {
            Log.e("LoadImageDialog", getArguments().toString());
            if (getArguments().containsKey(TAG_Image_URL)) {
                String logoUrl = getArguments().getString(TAG_Image_URL);
                Log.e("LoadImageDialog", logoUrl+"");

                Glide.with(this).
                        load(logoUrl).
                        asBitmap().
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.mipmap.ic_launcher).
                        error(R.mipmap.ic_launcher).
                        into(new BitmapImageViewTarget(imageView) {
                            @Override
                            public void onResourceReady(Bitmap drawable, GlideAnimation anim) {
                                super.onResourceReady(drawable, anim);
                                Log.e("onResourceReady", drawable + "");
                                cropImageView.setImageBitmap(drawable);
                            }
                        });
            }
            if (getArguments().containsKey(TAG_FIXED_CROP)) {
                cropImageView.setFixedAspectRatio(getArguments().getBoolean(TAG_FIXED_CROP));
            }
            if (getArguments().containsKey(TAG_FIXED_CROP_RATIO_X) && getArguments().containsKey(TAG_FIXED_CROP_RATIO_Y) &&
                    getArguments().getInt(TAG_FIXED_CROP_RATIO_X) > 0 && getArguments().getInt(TAG_FIXED_CROP_RATIO_Y) > 0) {
                cropImageView.setFixedAspectRatio(getArguments().getBoolean(TAG_FIXED_CROP));
                cropImageView.setAspectRatio(getArguments().getInt(TAG_FIXED_CROP_RATIO_X), getArguments().getInt(TAG_FIXED_CROP_RATIO_Y));
            }
        }

        view.findViewById(R.id.select_image_change_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoadImageClick();
            }
        });

        view.findViewById(R.id.select_image_crop_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCropImageClick();
            }
        });

        view.findViewById(R.id.select_image_ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               final ProgressDialog progressDialog = DialogManager.showLoadingDialog(getActivity());
//                cropImageView.setFixedAspectRatio(true);
                new AsyncTask<Void, Void, Void>(){
                    @Override
                    protected void onPreExecute() {
                        onCropImageClick();
                    }
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try{
                            Thread.sleep(200);}catch (Exception e){}
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        cropImageView.setOnGetCroppedImageCompleteListener(new CropImageView.OnGetCroppedImageCompleteListener() {
                            @Override
                            public void onGetCroppedImageComplete(CropImageView view, Bitmap finalBitmap, Exception error) {
//                        finalBitmap=
                                Log.e("croped", cropImageView.getCroppedImage() + " - " + cropImageView.getCroppedImage().getHeight()+" - "+(selectImageListener != null));
                                String imageBase64 = Base64Manager.getString(finalBitmap);
//                        Log.e("croped", imageBase64);
                                if (selectImageListener != null)
                                    selectImageListener.onSelectImage(finalBitmap, imageBase64);
                                selectImageListener=null;
                                dismiss();
                            }
                        });
                        cropImageView.getCroppedImageAsync();
                    }
                }.execute();



//                finalBitmap = Bitmap.createBitmap(cropImageView.getCroppedImage());
//                finalBitmap = scaleBitmap(cropImageView.getCroppedImage(), MAX_LENGTH);
//                new AsyncTask<Void, Void, String>() {
//                    @Override
//                    protected String doInBackground(Void... voids) {
//                        String imageBase64 = Base64Manager.getString(finalBitmap);
//                        return imageBase64;
//                    }
//
//                    @Override
//                    protected void onPostExecute(String imageBase64) {
////                                progressDialog.dismiss();
//                    }
//                }.execute();
            }
        });

//        view.findViewById(R.id.select_image_close_imageView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
    }

    /**
     * On load image button click, start pick image chooser activity.
     */
    public void onLoadImageClick() {
        startActivityForResult(getPickImageChooserIntent(), 200);
    }

    /**
     * Crop the image and set it back to the cropping view.
     */
    public void onCropImageClick() {
        Bitmap cropped = cropImageView.getCroppedImage(500, 500);
        if (cropped != null)
            cropImageView.setImageBitmap(cropped);
    }

    public void setImage(final Bitmap bitmap, final int defaultImage) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(bitmap!=null)cropImageView.setImageBitmap(bitmap);
                else cropImageView.setImageResource(defaultImage);
            }
        },500);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = getPickImageResultUri(data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    isUriRequiresPermissions(imageUri)) {

                // request permissions and handle the result in onRequestPermissionsResult()
                requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                cropImageView.setImageUriAsync(imageUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            cropImageView.setImageUriAsync(mCropImageUri);
        } else {
            Toast.makeText(getContext(), "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getContext().getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getContext().getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    /**
     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera and gallery image.
     *
     * @param data the returned data of the activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getActivity().getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    private Bitmap scaleBitmap(Bitmap mBitmap, int maxLength) {
        int ScaleSize = maxLength;//max Height or width to Scale
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float excessSizeRatio = width > height ? width / ScaleSize : height / ScaleSize;
        if (excessSizeRatio > 0) {
            Bitmap bitmap = Bitmap.createBitmap(mBitmap, 0, 0, (int) (width / excessSizeRatio), (int) (height / excessSizeRatio));
            mBitmap.recycle(); //if you are not using mBitmap Obj
            return bitmap;
        } else return mBitmap;
    }

    public void setSelectListener(SelectImageListener selectImageListener) {
        this.selectImageListener = selectImageListener;
    }

    public interface SelectImageListener {
        void onSelectImage(Bitmap bitmap, String base64);
    }
}
