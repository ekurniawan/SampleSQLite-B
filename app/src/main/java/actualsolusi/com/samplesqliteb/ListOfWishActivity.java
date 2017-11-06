package actualsolusi.com.samplesqliteb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class ListOfWishActivity extends AppCompatActivity {
    private RecyclerView rvWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_wish);

        rvWish = (RecyclerView)findViewById(R.id.rvWish);
    }
}
