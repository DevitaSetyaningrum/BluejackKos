package com.project.bluejackkos;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class KosDetail extends AppCompatActivity implements OnMapReadyCallback{

    ImageView gambarKos;
    TextView namaKos, fasilitasKos, hargaKos, alamatKos, latitudeKos, longitudeKos;
    Button booking, location;
    String currId, dateStr;
    String bookId = "BK000";
    SimpleDateFormat sdf;

    Integer kosThumbnail, harga;
    String nama, fasilitas, alamat;
    Float longitude, latitude;

    GoogleMap gMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Kost Details");
        setContentView(R.layout.detail_kost);

        Intent retrieveData = getIntent();
        Kos kos = Helper.dataKos.get(retrieveData.getIntExtra("name", -1));

        currId = retrieveData.getStringExtra("currentuserid");

        gambarKos = findViewById(R.id.kosThumbnailDetail);
        namaKos = findViewById(R.id.kosNameDetail);
        fasilitasKos = findViewById(R.id.kosFacilityDetail);
        hargaKos = findViewById(R.id.kosPriceDetail);
        alamatKos = findViewById(R.id.kosDescription);
        latitudeKos = findViewById(R.id.kosLatitude);
        longitudeKos = findViewById(R.id.kosLongitude);

        booking = findViewById(R.id.bookingDate);
        location = findViewById(R.id.viewLocation);

        gambarKos.setTag(kos.getKosThumbnail());
        namaKos.setText(kos.getKosName());
        fasilitasKos.setText(kos.getKosFacility());
        setQuantityCounter("Rp. " + (kos.getKosPrice()) + ",-");
        alamatKos.setText(kos.getKosAddress());
        latitudeKos.setText(String.valueOf(kos.getKosLatitude()));
        longitudeKos.setText(String.valueOf(kos.getKosLongitude()));

        Glide.with(this).load(kos.getKosThumbnail()).into(gambarKos);

        sdf = new SimpleDateFormat("dd-MM-yyy", Locale.US);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
                finish();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.kost_map);
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.kosMap);
                supportMapFragment.getMapAsync(KosDetail.this);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng locat = new LatLng(Double.valueOf(latitudeKos.getText().toString()), Double.valueOf(longitudeKos.getText().toString()));
        gMap.addMarker(new MarkerOptions().position(locat).title(nama));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locat, 16));
    }

    public void doneBooking() {
        int idx = Helper.transaction.size() + 1;
        if (idx > 99) {
            bookId = "BK" + idx;
        } else if (idx > 9) {
            bookId = "BK0" + idx;
        } else {
            bookId = "BK00" + idx;
        }

        kosThumbnail = Integer.parseInt(gambarKos.getTag().toString());
        String userId = currId;
        nama = namaKos.getText().toString();
        fasilitas = fasilitasKos.getText().toString();
        harga = Integer.parseInt(hargaKos.getText().toString());
        alamat = alamatKos.getText().toString();
        longitude = Float.valueOf(longitudeKos.getText().toString());
        latitude = Float.valueOf(latitudeKos.getText().toString());
        String tanggal = dateStr;

        Transaction transaction = new Transaction(kosThumbnail, bookId, userId, nama, fasilitas, harga, alamat, longitude, latitude, tanggal);
        Helper.transaction.add(transaction);
    }

    public void getLocation(){
//        Double lat = Double.valueOf(latitude);
//        Double longg = Double.valueOf(longitude);
//        LatLng kosLocation = new LatLng(lat, longg);
//
//        Bundle data = new Bundle();
//        data.putParcelable("lalo", kosLocation);
//        data.putString("name", nama);
//
//        Intent located = new Intent(this, KosLocation.class);
//        located.putExtras(data);
//        startActivity(located);
    }

    private void setQuantityCounter(String count) {
        hargaKos.setText("" + count);
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateStr = sdf.format(newDate.getTime());
                doneBooking();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        datePickerDialog.show();
    }


}