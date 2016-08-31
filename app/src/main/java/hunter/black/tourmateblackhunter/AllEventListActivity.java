package hunter.black.tourmateblackhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class AllEventListActivity extends AppCompatActivity {

    ListView listView;
    DataBaseOperations dataBaseOperations;
    ArrayList<NewEventObject> eventData;
    CurrentWeatherURL currentWeatherURL;
    CurrentWeatherURL forcastWeatherURL;
    public static HashMap<String, String> listOfCities;
    private ArrayList<AllEventsData>  allEventsDatas;
    AllEventsData allEventsData;
    EventListAdapter eventListAdapter;
    ArrayList<ForecastDataObject> forecastData;
    ArrayList<String> currentWeatherDataInfo;
    private static String currentTempInCelsius;
    private static String weatherDescription;
    ForecastDataObject forecastDataObject;
    ArrayList<ForecastDataObject> forecastDataObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event_list);
        listView = (ListView) findViewById(R.id.AllEventlistView);
        dataBaseOperations = new DataBaseOperations(this);
        eventData = new ArrayList<NewEventObject>();
        allEventsDatas = new ArrayList<AllEventsData>();
        listOfCities = new HashMap<String, String>();
        forecastData = new ArrayList<ForecastDataObject>();
        currentWeatherDataInfo = new ArrayList<String>();
        forecastDataObjects = new ArrayList<ForecastDataObject>();
        loadData();
    }

    public void addNewJourneyButtonActionInAllEventListView(View view) {
        Intent newJourneyIntent = new Intent(this,CreateNewEventActivity.class);
        startActivity(newJourneyIntent);
    }

    public void testingHomeFragment(View view) {

        Intent test = new Intent(this,RowDetailsActivity.class);
        startActivity(test);
    }

    public void getTestData(View view) {
        eventData = dataBaseOperations.getAllEventData();

        //String city = null;
        String correspondingValueOfKey = null;
        for (int i = 0; i < eventData.size(); i++){
            String city = eventData.get(i).getPlaceName();
            Toast.makeText(getApplicationContext(), city, Toast.LENGTH_LONG).show();
            if (listOfCities.containsKey(city)) {
                correspondingValueOfKey = listOfCities.get(city);
                currentWeatherURL = new CurrentWeatherURL(correspondingValueOfKey, "metric");
                forcastWeatherURL = new CurrentWeatherURL(correspondingValueOfKey);
                getCurrentWeatherInfo(currentWeatherURL.getUrl());
                forecastData = getForecast(forcastWeatherURL.getUrlForeCast());
                allEventsData = new AllEventsData(eventData.get(i).getPlaceName(), currentTempInCelsius,weatherDescription,
                        eventData.get(i).getFrom(), eventData.get(i).getTo(), eventData.get(i).getBudget(), null,null,forecastData.get(0).getDiscription(),forecastData.get(1).getDiscription(),forecastData.get(2).getDiscription(),forecastData.get(3).getDiscription(),
                        forecastData.get(4).getDiscription(),forecastData.get(5).getDiscription(),forecastData.get(6).getDiscription(),forecastData.get(0).getImage(),forecastData.get(1).getImage(),
                        forecastData.get(2).getImage(),forecastData.get(3).getImage(),forecastData.get(4).getImage(),forecastData.get(5).getImage(),forecastData.get(6).getImage());
                allEventsDatas.add(allEventsData);
                Toast.makeText(getApplicationContext(), correspondingValueOfKey, Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getApplicationContext(), "Key not matched with ID", Toast.LENGTH_LONG).show();
            }
        }
        eventListAdapter = new EventListAdapter(this, allEventsDatas);
        listView.setAdapter(eventListAdapter);

        //Toast.makeText(getApplicationContext(), String.valueOf(currentWeatherInfo.size()), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), String.valueOf(forecastData.size()), Toast.LENGTH_SHORT).show();



    }

    public ArrayList<String> getCurrentWeatherInfo(String url) {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject mainObject = response.getJSONObject("main");
                    String temp = mainObject.getString("temp");
                    double value = Double.parseDouble(temp);
                    value = Math.floor(value * 10) / 10;
                    currentTempInCelsius = String.valueOf(value);
                    JSONArray weatherArray = response.getJSONArray("weather");

                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    weatherDescription = weatherObject.getString("description");
                    String weatherIcon = weatherObject.getString("icon");
                    String selectImage = "image_"+weatherIcon;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {

                }

            }
        });
        AppController.getInstance().addToRequestQueue(request);

        return currentWeatherDataInfo;
    }

    private ArrayList<ForecastDataObject> getForecast(String url) {
        //String url = "http://api.openweathermap.org/data/2.5/forecast/daily?id=1185241&units=metric&appid=0a3d6f75ff8e0621c702d3bace78059c";


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                try{

                    JSONArray listArray = response.getJSONArray("list");
                    for (int i = 0; i < listArray.length(); i++){
                        JSONObject listObject = listArray.getJSONObject(i);
                        JSONArray weatherArray = listObject.getJSONArray("weather");
                        JSONObject weatherObject = weatherArray.getJSONObject(0);
                        String description = weatherObject.getString("description");
                        String icon = weatherObject.getString("icon");
                        String selectImage = "image_"+icon;
                        forecastDataObject = new ForecastDataObject(description, selectImage);
                        forecastDataObjects.add(forecastDataObject);
                    }
                }
                catch (JSONException e){

                }

            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {

                }
            }
        }
        );
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

        return forecastDataObjects;
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