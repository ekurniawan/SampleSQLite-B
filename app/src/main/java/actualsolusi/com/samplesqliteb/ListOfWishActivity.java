package actualsolusi.com.samplesqliteb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    @Override
    protected void onResume() {
        super.onResume();
        RefreshDataWish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wish,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                Intent intentAdd = new Intent(ListOfWishActivity.this,AddWishActivity.class);
                startActivity(intentAdd);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
