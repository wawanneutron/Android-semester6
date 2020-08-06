package com.Example.latihanuasapi;

public class StringMahasiswa {
	
	String nim;
	String nama;
	String kelas;
	String jurusan;
	
	public StringMahasiswa () {
		
	}
	
	public StringMahasiswa (String nim, String nama, String kelas, String jurusan) {
		this.nim = nim;
		this.nama = nama;
		this.kelas = kelas;
		this.jurusan = jurusan;
	}

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}

	public String getJurusan() {
		return jurusan;
	}

	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}
	
	

}
