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
        Intent intent = new Intent();
        intent.setType("images/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE);

    }

    private void findIds()
    {
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

        }
        else
        {
            Toast.makeText(MainActivity.this, "Please select Image", Toast.LENGTH_LONG).show();
        }

    }
}
