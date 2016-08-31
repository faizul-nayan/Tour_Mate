package hunter.black.tourmateblackhunter;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import android.view.View.OnClickListener;

public class CreateNewEventActivity extends AppCompatActivity {

    AutoCompleteTextView placeNameCNEventET;
    TextView budgetAmountCNEventET;
    TextView fromDateCNEventET;
    TextView toDateCNEventET;
    public static HashMap<String, String> listOfCities;
    private static ArrayList<String> eventKeyList;

    DataBaseOperations dataBaseOperations;

    NewEventObject newEventObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);

        placeNameCNEventET = (AutoCompleteTextView) findViewById(R.id.placeNameCNEventET);
        budgetAmountCNEventET = (TextView) findViewById(R.id.budgetAmountCNEventET);
        fromDateCNEventET = (TextView) findViewById(R.id.fromDateCNEventET);
        toDateCNEventET = (TextView) findViewById(R.id.toDateCNEventET);
        listOfCities = new HashMap<String, String>();
        eventKeyList = new ArrayList<String>();
        dataBaseOperations = new DataBaseOperations(this);
        loadData();
        getAllCity();

        fromDateCNEventET.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateNewEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel((EditText) fromDateCNEventET);
            }
        });

        toDateCNEventET.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateNewEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel((EditText) toDateCNEventET);
            }
        });
    }

    public void createNewEvent(View view) {
        newEventObject = new NewEventObject(placeNameCNEventET.getText().toString(),
                budgetAmountCNEventET.getText().toString(),
                fromDateCNEventET.getText().toString(),
                toDateCNEventET.getText().toString());

        boolean check = dataBaseOperations.newEvent(newEventObject);
        if(check)
            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();

    }

    private void getAllCity() {
        int i = 0;
        ArrayList<String> test = new ArrayList<>();
        for (String key:
                listOfCities.keySet() ) {
            test.add(key);
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,test);
        placeNameCNEventET.setAdapter(adapter);
    }

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        }

    };


    private void updateLabel(EditText dateEt) {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEt.setText(sdf.format(myCalendar.getTime()));
    }

    private void loadData(){

        listOfCities.put("Dhaka", "1185241");
        listOfCities.put("Faridpur", "1203344");
        listOfCities.put("Tungi", "1185098");
        listOfCities.put("Tungipara", "1185920");
        listOfCities.put("Jamalpur", "1185106");
        listOfCities.put("Kishorganj", "1337249");
        listOfCities.put("Madaripur", "1337245");
        listOfCities.put("Manikganj", "1348441");
        listOfCities.put("Mymensingh", "1185162");
        listOfCities.put("Narayanganj", "1185155");

        listOfCities.put("Narsingdi", "1185117");
        listOfCities.put("Netrakona", "1185116");
        listOfCities.put("Sripur", "1187197");
        listOfCities.put("Tangail", "1336144");
        listOfCities.put("Bandarban", "1185270");
        listOfCities.put("Chandpur", "1207339");
        listOfCities.put("Chittagong", "1337200");
        listOfCities.put("Comilla", ":1185186");
        listOfCities.put("Cox%27sbazaar", "1336134");
        listOfCities.put("Feni", "1185224");

        listOfCities.put("Khagrachhari", "1185252");
        listOfCities.put("Lakshmipur", "1196292");
        listOfCities.put("Bogra", "1337233");
        listOfCities.put("Nawabganj", "1337240");
        listOfCities.put("Joypurhat", "1185206");
        listOfCities.put("Pabna", "1336143");
        listOfCities.put("Rajshahi", "1185128");
        listOfCities.put("Sirajganj", "1185115");
        listOfCities.put("Jessore", "1336140");
        listOfCities.put("Khulna", "1336135");

        listOfCities.put("Kushtia", "1185191");
        listOfCities.put("Narail", "1185293");
        listOfCities.put("Satkhira", "1185111");
        listOfCities.put("Barisal", "1336137");
        listOfCities.put("Bhola", "1336136");
        listOfCities.put("Pirojpur", "1185138");
        listOfCities.put("Habiganj", "1185209");
        listOfCities.put("Maulvibazar", "1185166");
        listOfCities.put("Chhatak", "1185254");
        listOfCities.put("Sylhet", "1185099");

        listOfCities.put("Dinajpur", "1203891");
        listOfCities.put("Gaibandha", "7921384");
        listOfCities.put("Lalmanirhat", "1185181");
        listOfCities.put("Panchagarh", "1185141");
        listOfCities.put("Rangpur", "1185188");
        listOfCities.put("Thakurgaon", "1185092");
    }
}
