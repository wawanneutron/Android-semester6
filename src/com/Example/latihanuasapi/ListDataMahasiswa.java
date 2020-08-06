package com.Example.latihanuasapi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListDataMahasiswa extends ActionBarActivity {
	
	private Button Keluar;

	private List<StringMahasiswa> listMahasiswa;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_data_mahasiswa);
		
		getSupportActionBar().setTitle("UAS SI6D");
        getSupportActionBar().setSubtitle("IPEM");
		

		Keluar = (Button) findViewById (R.id.btnKeluarData);
		
		
		
		listView = (ListView) findViewById(R.id.listView);
		listMahasiswa = new ArrayList<StringMahasiswa>();
	
		/*
		dbHelper = new DatabaseHandler(this);
		listMahasiswa = dbHelper.getAllRecord();		
		listView.setAdapter(new CustomListView(this, R.layout.custom_list_data_mahasiswa ,
				listMahasiswa));
		*/
		
		
		
		Keluar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				finish();
			}
		});
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				StringMahasiswa mhs = listMahasiswa.get(position);
				
				Intent i = new Intent(ListDataMahasiswa.this, MahasiswaActivity.class);
				i.putExtra("nim", mhs.nim);
				i.putExtra("nama", mhs.nama);
				i.putExtra("kelas", mhs.kelas);
				i.putExtra("jurusan", mhs.jurusan);
				startActivity(i);
				finish();
				
			}
		});
		
		new getData().execute();
		
	}
		
		
		
	
	

	private class getData extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... params) {
			String respon=null;
			try {
				respon=CustomHttpClient.executeHttpget("http://kuliahjava2.mediapay.online/android/mahasiswa/getdata.php");
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Error :"+ e.toString(), Toast.LENGTH_LONG).show();
			}
			String res=respon.toString();
			return res;
		}
		
		@Override
	      protected void onPostExecute(String respon) {
	         super.onPostExecute(respon);
	         
	         try {
				JSONObject result = new JSONObject(respon);
				JSONArray j_arr = result.getJSONArray("data");
				for(int i=0;i<j_arr.length();i++){
		            try {
		                //Getting json object
		                JSONObject jsonOb = j_arr.getJSONObject(i);
		                StringMahasiswa mhsModels = new StringMahasiswa();
			        	mhsModels.setNim(jsonOb.getString("nim"));
			        	mhsModels.setNama(jsonOb.getString("nama"));
			        	mhsModels.setKelas(jsonOb.getString("kelas"));
			        	mhsModels.setJurusan(jsonOb.getString("jurusan"));
			        	listMahasiswa.add(mhsModels);
			        	
		            } catch (JSONException e) {
		            	Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		            }
		        }
				//Toast.makeText(getApplicationContext(), j_arr.toString(), Toast.LENGTH_LONG).show();
				listView.setAdapter(new CustomListView(ListDataMahasiswa.this,R.layout.custom_list_data_mahasiswa,listMahasiswa));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	      }
	}
	
	

}
