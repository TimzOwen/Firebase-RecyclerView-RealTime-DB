package com.owen.realtimedatabas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //decalre the constant values
    private static final int SELECT_IMAGE = 1;

    private Button btnUplaod, btnChooseFile;
    private TextView tvUpshowUpLoads;
    private ImageView imageView;
    private ProgressBar mProgressBar;
    private EditText mEditText;
    
     private DatabaseReference mDataRef;
    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findIds();
        
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDataRef = FirebaseDatabase.getInstance().getReference("uploads");

        btnChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openFileChooser();
            }
        });
        btnUplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvUpshowUpLoads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void openFileChooser()
    {
        //this method to open implicit intents
        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE);

    }

    private void findIds()
    {
        //gets relevant view from the UI
        btnChooseFile = (Button) findViewById(R.id.btn_choose_file);
        btnUplaod = (Button) findViewById(R.id.btn_upload);
        tvUpshowUpLoads = (TextView)findViewById(R.id.tv_show_uploads);
        mEditText = (EditText)findViewById(R.id.et_file_Name);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        imageView = (ImageView)findViewById(R.id.iv_imageView);
    }
    }
 //create a function to check the file type upload
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cR.getType(uri));
    }

    public void uploadFile()
    {
        if (mImageUri != null)
        {
             StorageReference fileRef = mStorageRef.child(System.currentTimeMillis() + "." +getFileExtension(mImageUri));
            fileRef.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            //set a delay to as the document uploads
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            },5000);
                            Toast.makeText(MainActivity.this, "Upload Success", Toast.LENGTH_LONG).show();

                            //check //TODO
                            Uploads uploads = new Uploads(mEditText.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            //get the time for progress while uploading
                                 double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                 mProgressBar.setProgress((int)progress);
                        }
                    });


        }
        else
        {
            Toast.makeText(MainActivity.this, "Please select Image", Toast.LENGTH_LONG).show();
        }

    }
}
