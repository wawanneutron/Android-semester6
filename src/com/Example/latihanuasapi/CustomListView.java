package com.Example.latihanuasapi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListView extends ArrayAdapter<StringMahasiswa> {
	
	private TextView Nim, Nama, Kelas, Jurusan;
	
	private List <StringMahasiswa> listMahasiswa;
	private Activity context;
	int layout;
	

	public CustomListView(Activity context, int resource, List <StringMahasiswa> mahasiswa) {
		
		super(context, resource, mahasiswa);
		this.context = context;
		this.layout = resource;
		this.listMahasiswa = mahasiswa;
		
	}
	
	public View getView ( int position, View view, ViewGroup parent ){
		LayoutInflater inflater = context.getLayoutInflater();
		
		View rowView = inflater.inflate(R.layout.custom_list_data_mahasiswa , null,true);
		
		Nim = (TextView) rowView.findViewById(R.id.txtNim   );
		Nama = (TextView) rowView.findViewById(R.id.txtNama  );
		Kelas = (TextView) rowView.findViewById(R.id.txtKelas   );
		Jurusan = (TextView) rowView.findViewById(R.id.txtJurusan   );

		



		
		StringMahasiswa mhs = listMahasiswa.get(position);
		Nim.setText(mhs.nim);
		Nama.setText(mhs.nama);
		Kelas.setText(mhs.kelas);
		Jurusan.setText(mhs.jurusan);

	

		return rowView;
		
		
		
	};
	
	

}
