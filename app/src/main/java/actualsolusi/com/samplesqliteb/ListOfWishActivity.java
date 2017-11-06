package actualsolusi.com.samplesqliteb;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import data.DatabaseHandler;
import models.MyWish;

public class ListOfWishActivity extends AppCompatActivity {
    private RecyclerView rvWish;
    private ArrayList<MyWish> arrListWish;
    private DatabaseHandler db;

    public ListOfWishActivity(){
        db = new DatabaseHandler(ListOfWishActivity.this);
    }

    private void RefreshDataWish(){
        arrListWish = db.GetAllWish();
        WishAdapter adapter = new WishAdapter(arrListWish);
        rvWish.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_wish);

        rvWish = (RecyclerView)findViewById(R.id.rvWish);
        rvWish.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(
                getApplicationContext(),R.drawable.item_decorator)));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvWish.setLayoutManager(llm);
    }
}
