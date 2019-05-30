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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findIds();

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
}
