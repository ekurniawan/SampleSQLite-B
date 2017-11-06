package actualsolusi.com.samplesqliteb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.DatabaseHandler;
import models.MyWish;

public class AddWishActivity extends AppCompatActivity {
    private EditText etTitle,etContent;
    private Button btnAddWish;
    private DatabaseHandler db;

    public AddWishActivity(){
        db = new DatabaseHandler(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        etTitle = (EditText)findViewById(R.id.etTitle);
        etContent = (EditText)findViewById(R.id.etContent);
        btnAddWish = (Button)findViewById(R.id.btnAddWish);

        btnAddWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyWish wish = new MyWish();
                wish.setTitle(etTitle.getText().toString());
                wish.setContent(etContent.getText().toString());
                try{
                    db.AddWish(wish);
                    Toast.makeText(AddWishActivity.this,"Tambah data berhasil !",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception ex){
                    Toast.makeText(AddWishActivity.this,
                            "Error "+ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
                finally {
                    db.close();
                }
            }
        });
    }
}
