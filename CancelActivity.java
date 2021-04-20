package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CancelActivity extends AppCompatActivity {
    private TextView a,b,c,d,e,f,g,h,i,j,k,l;
    private DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4;
    private FirebaseAuth firebaseAuth;
    private Button cancelBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        getSupportActionBar().setTitle("Cancel Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cancelBooking=(Button)findViewById(R.id.cancelBooking);

        a=(TextView)findViewById(R.id.busDetailName1);
        b=(TextView)findViewById(R.id.busDetailDate1);
        c=(TextView)findViewById(R.id.busDetailFrom1);
        d=(TextView)findViewById(R.id.busDetailTo1);
        

        f=(TextView)findViewById(R.id.bookingDetailFrom1);
        g=(TextView)findViewById(R.id.bookingDetailTo1);

        h=(TextView)findViewById(R.id.ticketDetailNumber1);
        i=(TextView)findViewById(R.id.ticketDetailPrice1);

        
        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference1= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("BusBookingDetails");
        databaseReference2= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("BookingDetails");
        
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String busDetailName=dataSnapshot.child("Name").getValue().toString();
                String busDetailDate=dataSnapshot.child("date").getValue().toString();
                String busDetailFrom=dataSnapshot.child("from").getValue().toString();
                String busDetailTo=dataSnapshot.child("to").getValue().toString();
                String busDetailCondition=dataSnapshot.child("busCondition").getValue().toString();

                a.setText(""+busDetailName);
                b.setText(" Date              :  "+busDetailDate);
                c.setText(" From             :  "+busDetailFrom);
                d.setText(" To                  :  "+busDetailTo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String bookingDetailFrom=dataSnapshot.child("from").getValue().toString();
                String bookingDetailTo=dataSnapshot.child("to").getValue().toString();

                f.setText(" From            :  "+bookingDetailFrom);
                g.setText(" To                 :  "+bookingDetailTo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ticketDetailNumber=dataSnapshot.child("total_seats").getValue().toString();
                String ticketDetailPrice=dataSnapshot.child("total_cost").getValue().toString();

                h.setText(" Number of Seats    :  "+ticketDetailNumber);
                i.setText(" Total Cost                :  "+ticketDetailPrice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cancelBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                DatabaseReference databaseReferenceA= FirebaseDatabase.getInstance().getReference().child(user1.getUid());
                databaseReferenceA.removeValue();

                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));

            }
        });
    }
}
