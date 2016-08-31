package hunter.black.tourmateblackhunter;

/**
 * Created by home on 7/20/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TourMateDataBase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String EVENT_ID = "eventId";
    public static final String PLACE_NAME = "placeName";
    public static final String BUDGET = "budget";
    public static final String START_DATE = "startdate";
    public static final String END_DATE = "enddate";
    public static final String TABLE_NEW_EVENT = "new_event";

    public static final String TABLE_BOOKLIST = "book_list";
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_NAME = "book_name";
    public static final String BOOK_AUTHOR = "book_author";
    public static final String BOOK_DISCRIPTION = "book_discripation";

    public static final String TABLE_BORROWBOOK = "borrow_book";
    public static final String BORROW_ID = "borrow_id";
    public static final String BORROW_DATE = "borrow_date";
    public static final String BORROW_RETURN_DATE = "return_date";
    public static final String BORROW_STATUS = "borrow_status";


    private Context context;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(CREATE_USERINFO_TABLE);
        sqLiteDatabase.execSQL("CREATE TABLE '"+TABLE_NEW_EVENT+"' ('"+EVENT_ID+"' integer PRIMARY KEY AUTOINCREMENT,'"+PLACE_NAME+"' text NOT NULL,'"+BUDGET+"' text NOT NULL,'"+START_DATE+"' text NOT NULL,'"+END_DATE+"' text NOT NULL);");
        Toast.makeText(context, "Table "+TABLE_NEW_EVENT+" Create",Toast.LENGTH_LONG).show();
        //sqLiteDatabase.execSQL("CREATE TABLE '"+TABLE_BOOKLIST+"' ('"+BOOK_ID+"' integer PRIMARY KEY AUTOINCREMENT,'"+BOOK_NAME+"' text NOT NULL,'"+BOOK_AUTHOR+"' text NOT NULL,'"+BOOK_DISCRIPTION+"' text NOT NULL);");
        //Toast.makeText(context, "Table "+TABLE_BOOKLIST+" Create",Toast.LENGTH_LONG).show();
       // sqLiteDatabase.execSQL("CREATE TABLE '"+TABLE_BORROWBOOK+"' ('"+BORROW_ID+"' integer PRIMARY KEY AUTOINCREMENT,'"+USER_ID+"' integer NOT NULL,'"+BOOK_ID+"' integer NOT NULL,'"+BORROW_DATE+"' text NOT NULL,'"+BORROW_RETURN_DATE+"' text NOT NULL,'"+BORROW_STATUS+"' text NOT NULL,FOREIGN KEY('"+USER_ID+"') REFERENCES '"+TABLE_USERINFO+"'('"+USER_ID+"'),FOREIGN KEY('"+BOOK_ID+"') REFERENCES '"+TABLE_BOOKLIST+"'('"+BOOK_ID+"'))");
        //Toast.makeText(context, "Table "+TABLE_BORROWBOOK+" Create",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

}