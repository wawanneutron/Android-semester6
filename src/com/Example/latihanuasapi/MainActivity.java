package com.Example.latihanuasapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private EditText Nim, Nama;
	private RadioButton TI, SI, SIA;
	private Spinner Kelas;
	private Button Simpan, View;
	
	

	private String[] MhsKelas = { "Sistem Informasi 6B",
							  "Sistem Informasi 6C",
							  "Sistem Informasi 6D"
						 };
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getSupportActionBar().setTitle("UAS SI6D");
        getSupportActionBar().setSubtitle("IPEM");
        
        Nim = (EditText) findViewById(R.id.inputNimApi);
        Nama = (EditText) findViewById (R.id.inputNamaApi);
        
     
        TI = (RadioButton) findViewById (R.id.radioPil1 );
        SI = (RadioButton) findViewById (R.id.radioPil2 );
        SIA = (RadioButton) findViewById (R.id.radioPil3 );
        
        Kelas = (Spinner) findViewById (R.id.spinnerKelasApi );
        
        Simpan = (Button) findViewById (R.id.btnSimpanApi);
        View = (Button) findViewById (R.id.btnViewApi );
        
        
        
        
        // meng set array ke spinner
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, MhsKelas);

		Kelas.setAdapter(adapter);
        		

//		ketika tombol simpan di klik
		
        Simpan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				new SimpanData().execute();
				
				
				/*
				String sOrganisasi = Organisasi.getSelectedItem().toString();

				StringMahasiswa mhs = new StringMahasiswa();
				mhs.setNim (Nim.getText().toString());
				mhs.setNama(Nama.getText().toString());
				
				if (TI.isChecked()){
					mhs.setProdi(TI.getText().toString());
				}else if (SI.isChecked()) {
					mhs.setProdi(SI.getText().toString());
				}else if (SIA.isChecked()) {
					mhs.setProdi(SIA.getText().toString());
				}
				mhs.setOrganisasi(sOrganisasi);
				
				
				dbHelper.tambahMahasiswa(mhs);
				
				Toast.makeText(
						getBaseContext(),
						"Tambah Data : " + Nim.getText().toString()
								+ "\n" + Nama.getText().toString()
								+ "\n", Toast.LENGTH_LONG).show();
								
								*/
								

				clearText();
				
				
				
				
			}

			
			private void clearText() {
				Nim.requestFocus();
				Nim.setText("");
				Nama.setText("");
				Kelas.setSelection(0);
				
			}
			
			
		});
        
        
        
        View.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent LihatData = new Intent (MainActivity.this, ListDataMahasiswa.class);
				
				startActivity(LihatData);
			}
		});
          
    }
    
    
    private class SimpanData extends AsyncTask<String, String, String> {
    	


    	@Override
		protected String doInBackground(String... params) {
    		
    		//atur variabel utk menampung pilihan
    		
    		/*
    		
            String type = null;
            switch (prodi.getCheckedRadioButtonId()) {
            
            case R.id.rdpil1:
            	type = "TI";
            	break;
            case R.id.rdpil2:
            	type = "SI";
            	break;
            case R.id.rdpil3:
            	type = "SIA";
            	break;
            }
            
            */
            
            
            
			String sKelas = Kelas.getSelectedItem().toString();
			
			ArrayList<NameValuePair> postparameter = new ArrayList<NameValuePair>();
			
			
			
			postparameter.add(new BasicNameValuePair("nim", Nim.getText().toString()));
			postparameter.add(new BasicNameValuePair("nama",Nama.getText().toString()));
			
			postparameter.add(new BasicNameValuePair("kelas", sKelas ));
			
			
			if (TI.isChecked()){
				postparameter.add(new BasicNameValuePair("jurusan", TI.getText().toString()));
			}else if (SI.isChecked()){
				postparameter.add(new BasicNameValuePair("jurusan", SI.getText().toString()));
			}else if (SIA.isChecked()) {
				postparameter.add(new BasicNameValuePair("jurusan", SIA.getText().toString()));
			}
		
			
			
			
			String respon = null;
			try {
				respon = CustomHttpClient.executeHttpPost(
						"http://kuliahjava2.mediapay.online/android/mahasiswa/insert.php", postparameter);
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"Error :" + e.toString(), Toast.LENGTH_LONG).show();
			}
			String res = respon.toString();
			return res;
		}

		@Override
		protected void onPostExecute(String respon) {
			super.onPostExecute(respon);
			Toast.makeText(getApplicationContext(), respon, Toast.LENGTH_LONG)
					.show();
		}

	}



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
