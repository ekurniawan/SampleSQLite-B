package actualsolusi.com.samplesqliteb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import models.Kategori;
import services.KategoriServices;

public class SampleServicesActivity extends AppCompatActivity {
    private Button btnGetData;
    private List<Kategori> listKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_services);

        btnGetData = (Button) findViewById(R.id.btnGetData);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadContent();

            }
        });
    }

    private void LoadContent(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    listKategori = KategoriServices.GetAllKategori();

                } catch (IOException e) {
                    Log.d("Kesalahan",e.getLocalizedMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                String result = "";
                for(int i=0;i<listKategori.size();i++){
                    result += listKategori.get(i).getKategoriID() + " - " +
                            listKategori.get(i).getNamaKategori()+"\n";
                }
                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
}
