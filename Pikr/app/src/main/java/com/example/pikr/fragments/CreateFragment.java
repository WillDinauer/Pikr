package com.example.pikr.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pikr.BuildConfig;
import com.example.pikr.R;
import com.example.pikr.activities.MainActivity;
import com.example.pikr.activities.RegisterActivity;
import com.soundcloud.android.crop.Crop;
import com.example.pikr.models.Login;
import com.example.pikr.models.Picture;
import com.example.pikr.models.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CreateFragment extends Fragment {
    private static final int RESULT_OK = -1;
    private static final CharSequence PERIOD_REPLACEMENT_KEY = "hgiasdvohekljh91-76";
    private static final int PHOTO_FROM_CAMERA_CODE = 0;
    private static final int PHOTO_FROM_GALLERY_CODE = 1;
    private Login currLogin;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 2;
    private static final int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 3;
    private TextView mTitle, mDescription;
    private Button mPostButton, mPhotoButton;
    private Bitmap correctedBitmap;
    private ImageView mImage;
    private File mFile;
    private Uri mUri;
    private LinearLayout mLinearLayout;
    private String mPath;
    private Uri uri;
    private static final int MAX_PHOTOS = 5;
    private Post newPost;
    private DatabaseReference mRef;
    private ValueEventListener postListener;
    private int postIndex;

    public CreateFragment() {
        // Required empty public constructor
    }

    public CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        checkPermissions();
        postIndex = 0;
        currLogin = new Login(getContext().getApplicationContext());
        newPost = new Post();
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(requireContext());
            alert.setTitle("Important Permissions");
            alert.setMessage("These permissions are required for certain functions of the app");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkPermissions();
                }
            });
            alert.show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = view.findViewById(R.id.title_text);
        mDescription = view.findViewById(R.id.description_text);
        mPhotoButton = view.findViewById(R.id.add_photo_button);
        mPostButton = view.findViewById(R.id.save_post_button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String emailKey = currLogin.getEmail().replace(".", PERIOD_REPLACEMENT_KEY);
        mRef = database.getReference(emailKey);

        setupDatabaseListener();
        createPhotoButtonListener();
        createPublishPostListener();
        createPhotoButtonListener();
        createPublishPostListener();

        mLinearLayout = view.findViewById(R.id.photo_scroll);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View scrollView = inflater.inflate(R.layout.photo_item, mLinearLayout, false);
        mImage = scrollView.findViewById(R.id.imageView);
        mLinearLayout.addView(scrollView);
    }

    private void createPhotoButtonListener() {
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissions();
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Select Profile Picture");
                builder.setNeutralButton("Take from camera", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onItemSelected(PHOTO_FROM_CAMERA_CODE);
                    }
                });
                builder.setPositiveButton("Take from gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onItemSelected(PHOTO_FROM_GALLERY_CODE);
                    }
                });
                builder.show();
            }
        });
    }

    private void createPublishPostListener(){
        mPostButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean complete = fillPostValues();
            if(complete) {
                Log.d("TEST", "postIndex()" + postIndex);
                mRef.child(String.valueOf(postIndex)).setValue(newPost);
                clearFragment();
            }
            else{
                Toast.makeText(getContext(), "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
            }
        }
        });
    }

    private void clearFragment(){
        Toast.makeText(getContext(), "Post Uploading!",
                Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyActivityFragment()).commit();
    }

    private void setupDatabaseListener(){
        final int[] size = new int[1];
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                size[0] = (int) dataSnapshot.getChildrenCount();
                Log.d("TEST", "setupDatabaseListener()" + size[0]);
                postIndex = size[0];
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean fillPostValues() {
        boolean complete = allValuesFilledOut();

        newPost.setTitle(mTitle.getText().toString());
        newPost.setDescription(mDescription.getText().toString());
        newPost.setPictures(new ArrayList<Picture>());
        newPost.setDatetime(Calendar.getInstance().getTime().toString());

        return complete;
    }

    private boolean allValuesFilledOut() {
        boolean complete = false;
        if (getView() != null) {
            if (!mTitle.getText().toString().equals("") && !mDescription.getText().toString().equals("")) {
                complete = true;
            }
        }
        return complete;
    }

    private void onItemSelected(int code) {
        Intent intent;
        switch (code) {
            case PHOTO_FROM_CAMERA_CODE:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    mFile = createImageFile();
                    mPath = mFile.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (mFile != null) {
                    Uri uri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID, mFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
            case PHOTO_FROM_GALLERY_CODE:
                try {
                    mFile = createImageFile();
                    mPath = mFile.getAbsolutePath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_IMAGE_ACTIVITY_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                    Bitmap cameraBitmap = imageOrientationValidator(mFile);
                    setImageDetails(cameraBitmap);
                    break;
                case GALLERY_IMAGE_ACTIVITY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap galleryBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        setImageDetails(galleryBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Crop.REQUEST_CROP:
                    handleCrop(resultCode, data);
                    break;
            }
        }
    }

    private Bitmap imageOrientationValidator(File photoFile) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), FileProvider.getUriForFile(requireActivity(),
                    BuildConfig.APPLICATION_ID,
                    photoFile));
            ExifInterface control = new ExifInterface(photoFile.getAbsolutePath());
            int orientation = control.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            correctedBitmap = bitmap;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    correctedBitmap = rotate(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    correctedBitmap = rotate(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    correctedBitmap = rotate(bitmap, 270);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return correctedBitmap;
    }

    private Bitmap rotate(Bitmap bm, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
    }

    private void setImageDetails(Bitmap bm) {
        try {
            FileOutputStream fOut = new FileOutputStream(mFile);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mUri = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID, mFile);
        beginCrop(mUri);
    }

    private void beginCrop(Uri source) {
        if (mFile != null) {
            Uri destination = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID, mFile);
            Crop.of(source, destination).asSquare().start(requireActivity());
        }
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode != RESULT_OK)
            return;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Crop.getOutput(result));
            int scale = (int) getResources().getDimension(R.dimen.image_length);
            mImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, scale, scale, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        File storageDirectory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(fileName, ".jpg", storageDirectory);
    }
}

