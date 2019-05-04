package com.google.firebase.codelab.mlkit;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener
    {
        private Button buttonNotiSet;
        private Button submit;
        private NotificationHelper mNotificationHelper;
        private DatePickerDialog.OnDateSetListener mDateSetListener;
        private Calendar c = Calendar.getInstance();
        TextView timeText;
        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_notification);
                mNotificationHelper = new NotificationHelper(this);
                timeText = findViewById(R.id.textViewTime);
                submit = findViewById(R.id.buttonSubmi);
                buttonNotiSet = findViewById(R.id.buttonSetNotification);
                buttonNotiSet.setOnClickListener(new View.OnClickListener(){@Override public void onClick(View v){DialogFragment timePicker = new TimePickerFragment();
                                timePicker.show(getSupportFragmentManager(), "time picker");}});
                submit.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v){startAlarm();}});

            }

        public void sendOnChannel1( String message)
            {
                NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification("Time to check receipts", message);
                mNotificationHelper.getManager().notify(1,nb.build());
            }

        public void Return(View v)
            {
                finish();
                startActivity(new Intent(this, MainActivity.class));
            }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                TextView time = findViewById(R.id.textViewTime);
                DialogFragment datePicker = new DatePickerFragment();
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.SECOND, 0);
                String text = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
                time.setText(text);
//                startAlarm();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
//                c = Calendar.getInstance();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");

                String currentDateString = myFormat.format(c.getTime());
                TextView date = findViewById(R.id.textViewDate);
                date.setText(currentDateString);

            }

        private void startAlarm()
            {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(this, AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_LONG).show();

            }

        private void cancelAlarm()
            {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(this, AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
                alarmManager.cancel(pendingIntent);
                timeText.setText("Alarm Canceled");
            }
    }
