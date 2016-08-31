package hunter.black.tourmateblackhunter;

/**
 * Created by home on 7/20/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseOperations {

    private static final String ADMIN_USER_NAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Context context;

    public DataBaseOperations(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);

    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean newEvent(NewEventObject newEventObject){
        this.open();

        ContentValues  contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.PLACE_NAME, newEventObject.getPlaceName() );
        contentValues.put(DataBaseHelper.BUDGET, newEventObject.getBudget() );
        contentValues.put(DataBaseHelper.START_DATE, newEventObject.getFrom() );
        contentValues.put(DataBaseHelper.END_DATE, newEventObject.getTo() );

        long inserted = database.insert(DataBaseHelper.TABLE_NEW_EVENT, null, contentValues);

        this.close();
        database.close();
        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<NewEventObject> getAllEventData(){
        ArrayList<NewEventObject> eventData = new ArrayList<NewEventObject>();
        this.open();
        Cursor cursor = database.query(DataBaseHelper.TABLE_NEW_EVENT, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String Place = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PLACE_NAME));
                String budget = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BUDGET));
                String start = cursor.getString(cursor.getColumnIndex(DataBaseHelper.START_DATE));
                String end = cursor.getString(cursor.getColumnIndex(DataBaseHelper.END_DATE));

                NewEventObject contact = new NewEventObject(Place, budget, start,end);
                eventData.add(contact);
                cursor.moveToNext();
            }
            this.close();

        }
        return eventData;

    }

   /* public boolean addContact(Contact contact) {

        this.open();

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.USER_NAME, contact.getName());
        cv.put(DataBaseHelper.PASSWORD, contact.getPhoneNo());
        cv.put(DataBaseHelper.NAME, contact.getName());
        cv.put(DataBaseHelper.PHONE_NO, contact.getPhoneNo());
        cv.put(DataBaseHelper.STD_ID, contact.getName());

        long inserted = database.insert(DataBaseHelper.TABLE_USERINFO, null, cv);
        this.close();

        database.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<Contact> getAllContacts() {

        this.open();

        ArrayList<Contact> contactList = new ArrayList<>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_USERINFO, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.USER_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_NAME));
                String passWord = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PASSWORD));

                Contact contact = new Contact(id, userName, passWord);
                contactList.add(contact);
                cursor.moveToNext();
            }
            this.close();

        }
        return contactList;
    }*/

    /*
    public String login(LogIn logIn){

        String userName = logIn.getLoginUsername().toString();
        String password = logIn.getLoginPassword().toString();

        if (userName.equals(ADMIN_USER_NAME) && password.equals(ADMIN_PASSWORD)){
            return "Admin";
        }
        else{
            this.open();
            String message = "Invalid";
            //Cursor cursor = database.query(DataBaseHelper.TABLE_USERINFO, new String[]{DataBaseHelper.USER_NAME,DataBaseHelper.PASSWORD},"'"+DataBaseHelper.USER_NAME+"'=? AND '"+DataBaseHelper.PASSWORD+"'=?",new String[]{logIn.getLoginUsername(),logIn.getLoginPassword()},null,null,null);

            Cursor cursor = database.rawQuery("select "+DataBaseHelper.USER_NAME+","+DataBaseHelper.PASSWORD+" from '"+DataBaseHelper.TABLE_USERINFO+"' where username='"+logIn.getLoginUsername()+"' and password='"+logIn.getLoginPassword()+"'",null);
            Toast.makeText(context,"count "+cursor.getCount(), Toast.LENGTH_LONG).show();
            if(cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                String username = cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_NAME));
                String pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PASSWORD));
                if(username.equals(logIn.getLoginUsername()) && pass.equals(logIn.getLoginPassword())){
                    message = "User";
                }
            }
            cursor.close();
            this.close();
            return message;
        }

    }

    public boolean registerNewUser(StudentInfo studentInfo){

        this.open();

        String userName = studentInfo.getUsername();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = database.query(DataBaseHelper.TABLE_USERINFO,null,DataBaseHelper.USER_NAME+"=?",new String[]{userName},null,null,null,null);
        if(cursor != null & cursor.getCount()>0)
            Toast.makeText(context, "User name already taken, Choose diffenert", Toast.LENGTH_LONG).show();
        else {

            contentValues.put(DataBaseHelper.FULL_NAME,studentInfo.getFullName());
            contentValues.put(DataBaseHelper.PHONE_NO,studentInfo.getPhoneNumber());
            contentValues.put(DataBaseHelper.USER_NAME,studentInfo.getUsername());
            contentValues.put(DataBaseHelper.PASSWORD,studentInfo.getPassword());
        }
        long insert = database.insert(DataBaseHelper.TABLE_USERINFO, null, contentValues);
        this.close();
        cursor.close();
        if(insert < 0)
            return false;
        else
            return true;
    }
    public boolean addNewBook(BookInfo bookInfo){
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.BOOK_NAME,bookInfo.getBookName());
        contentValues.put(DataBaseHelper.BOOK_AUTHOR,bookInfo.getAuthorName());
        contentValues.put(DataBaseHelper.BOOK_DISCRIPTION,bookInfo.getBookDiscripation());

        long insert = database.insert(DataBaseHelper.TABLE_BOOKLIST, null, contentValues);
        this.close();
        if(insert < 0)
            return false;
        else
            return true;
    }

    public ArrayList<BookInfo> getAllBooks(){

        ArrayList<BookInfo> bookArrayList = new ArrayList<BookInfo>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_BOOKLIST, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
                String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
                String author = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_AUTHOR));
                String discripation = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_DISCRIPTION));

                BookInfo bookInfo = new BookInfo(id, bookName, author,discripation);
                bookArrayList.add(bookInfo);
                cursor.moveToNext();
            }
            this.close();

        }

        return  bookArrayList;
    }

    public ArrayList<Integer> getAllBookId(){

        ArrayList<Integer> allBookId = new ArrayList<Integer>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_BOOKLIST,new String[]{DataBaseHelper.BOOK_ID}, null,null,null,null,null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for (int i = 0 ; i < cursor.getCount(); i++){
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
                allBookId.add(id);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.close();

        return allBookId;
    }

    public ArrayList<String> singleBookData(int id){
        this.open();

        ArrayList<String> singleData = new ArrayList<>();

        Cursor cursor = database.rawQuery("Select "+DataBaseHelper.BOOK_NAME+", " +
                ""+DataBaseHelper.BOOK_AUTHOR+" from '"+DataBaseHelper.TABLE_BOOKLIST+"'" +
                " where "+DataBaseHelper.BOOK_ID+"='"+id+"'",null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
            String author = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_AUTHOR));
            singleData.add(bookName);
            singleData.add(author);
        }
        cursor.close();
        this.close();
        return singleData;
    }

    public boolean newBorrow(BorrowInfo borrowInfo){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.USER_ID,borrowInfo.getStudentID());
        contentValues.put(DataBaseHelper.BOOK_ID,borrowInfo.getBookID());
        contentValues.put(DataBaseHelper.BORROW_DATE,borrowInfo.getBorrowDate());
        contentValues.put(DataBaseHelper.BORROW_RETURN_DATE,borrowInfo.getReturnedDate());
        contentValues.put(DataBaseHelper.BORROW_STATUS,borrowInfo.getStatus());

        long insert = database.insert(DataBaseHelper.TABLE_BORROWBOOK, null, contentValues);
        this.close();
        if(insert < 0)
            return false;
        else
            return true;
    }

    public ArrayList<BorrowAdapterData> borrowListData(){

        ArrayList<BorrowAdapterData> arrayList = new ArrayList<BorrowAdapterData>();

        this.open();

        Cursor cursor = database.rawQuery("Select "+DataBaseHelper.TABLE_USERINFO+"."+DataBaseHelper.FULL_NAME+","+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BOOK_ID+"," +
                ""+DataBaseHelper.TABLE_BOOKLIST+"."+DataBaseHelper.BOOK_NAME+","+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_DATE+","+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_RETURN_DATE+"," +
                ""+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_STATUS+" FROM "+DataBaseHelper.TABLE_USERINFO+","+DataBaseHelper.TABLE_BOOKLIST+"," +
                ""+DataBaseHelper.TABLE_BORROWBOOK+" Where " +
                ""+DataBaseHelper.TABLE_USERINFO+"."+DataBaseHelper.USER_ID+"="+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.USER_ID+" AND " +
                ""+DataBaseHelper.TABLE_BOOKLIST+"."+DataBaseHelper.BOOK_ID+"="+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BOOK_ID+"",null);

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++){
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FULL_NAME));
                int bookId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
                String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
                String  borrowDate= cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_DATE));
                String retrunDate = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_RETURN_DATE));
                String borrowStatus = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_STATUS));

                borrowAdapterData = new BorrowAdapterData(userName,bookId,bookName,borrowDate,retrunDate,borrowStatus);
                arrayList.add(borrowAdapterData);
                cursor.moveToNext();
            }
            this.close();
            cursor.close();
        }
        return arrayList;
    }

    public ArrayList<StudentInfo> getUserListData(){

        ArrayList<StudentInfo> dataArrayList = new ArrayList<StudentInfo>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_USERINFO,null,null,null,null,null,null);

        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++){
                String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_NO));
                String fullName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FULL_NAME));
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_NAME));

                studentInfo = new StudentInfo(fullName, phoneNo, userName);
                dataArrayList.add(studentInfo);
                cursor.moveToNext();
            }
            this.close();
            cursor.close();
        }
        return dataArrayList;
    }

    public ArrayList<BookInfo> getSingleBookAllData(int id){
        ArrayList<BookInfo> dataArrayList = new ArrayList<BookInfo>();
        this.open();
        Cursor cursor = database.query(DataBaseHelper.TABLE_BOOKLIST,null,DataBaseHelper.BOOK_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            int bookId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
            String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
            String bookAuthor = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_AUTHOR));
            String bookDescription = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_DISCRIPTION));
            bookInfo = new BookInfo(bookId,bookName,bookAuthor,bookDescription);
            dataArrayList.add(bookInfo);
        }
        cursor.close();
        this.close();
        return dataArrayList;
    }

    public ArrayList<BorrowAdapterData> getSigleBorrowAllData(int id) {

        ArrayList<BorrowAdapterData> dataArrayList = new ArrayList<BorrowAdapterData>();

        this.open();
        Cursor cursor = database.rawQuery("Select " + DataBaseHelper.TABLE_USERINFO + "." + DataBaseHelper.FULL_NAME + "," + DataBaseHelper.TABLE_BORROWBOOK + "." + DataBaseHelper.BOOK_ID + "," +
                "" + DataBaseHelper.TABLE_BOOKLIST + "." + DataBaseHelper.BOOK_NAME + "," + DataBaseHelper.TABLE_BORROWBOOK + "." + DataBaseHelper.BORROW_DATE + "," + DataBaseHelper.TABLE_BORROWBOOK + "." + DataBaseHelper.BORROW_RETURN_DATE + "," +
                "" + DataBaseHelper.TABLE_BORROWBOOK + "." + DataBaseHelper.BORROW_STATUS + " FROM " + DataBaseHelper.TABLE_USERINFO + "," + DataBaseHelper.TABLE_BOOKLIST + "," +
                "" + DataBaseHelper.TABLE_BORROWBOOK + " Where " + DataBaseHelper.TABLE_BOOKLIST + "." + DataBaseHelper.BOOK_ID + "=" + String.valueOf(id) + "", null);
        Toast.makeText(context, "Count " + cursor.getCount(), Toast.LENGTH_LONG).show();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FULL_NAME));
                int bookId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
                String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
                String borrowDate = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_DATE));
                String retrunDate = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_RETURN_DATE));
                String borrowStatus = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_STATUS));

                borrowAdapterData = new BorrowAdapterData(userName, bookId, bookName, borrowDate, retrunDate, borrowStatus);
                dataArrayList.add(borrowAdapterData);
                cursor.moveToNext();
            }
            this.close();
            cursor.close();
        }
        return dataArrayList;
    }

    public boolean deleteBook(String id){
        this.open();
        //Cursor cursor = database.rawQuery("Delete from "+DataBaseHelper.TABLE_BOOKLIST+" where "+DataBaseHelper.BOOK_ID+"="+id+"",null);
        int result = database.delete(DataBaseHelper.TABLE_BOOKLIST,DataBaseHelper.BOOK_ID+"=?",new String[]{id});
        this.close();
        if(result > 0)
            return true;
        else
        return false;
    }

    public boolean deleteBorrowBook(String id){
        this.open();
        int result = database.delete(DataBaseHelper.TABLE_BORROWBOOK,DataBaseHelper.BOOK_ID+"=?",new String[]{id});
        this.close();
        if(result > 0)
            return true;
        else
            return false;
    }

    public boolean deleteUser(String phone){
        this.open();
        int result = database.delete(DataBaseHelper.TABLE_USERINFO,DataBaseHelper.PHONE_NO+"=?",new String[]{phone});
        this.close();
        if(result > 0)
            return true;
        else
            return false;
    }

    public ArrayList<StudentInfo> getAllUserData(String id){

        ArrayList<StudentInfo> dataArrayList = new ArrayList<StudentInfo>();
        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_USERINFO,null,DataBaseHelper.USER_NAME+"=?",new String[]{id},null,null,null);

        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
                int idUser = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.USER_ID));
                String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_NO));
                String fullName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FULL_NAME));
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.USER_NAME));
                String pass = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PASSWORD));

                studentInfo = new StudentInfo(idUser, fullName, phoneNo,userName,pass);
                dataArrayList.add(studentInfo);
        }
        this.close();
        cursor.close();
        return dataArrayList;
    }

    public ArrayList<BorrowAdapterData> clientBorrowListData(String name){

        ArrayList<BorrowAdapterData> arrayList = new ArrayList<BorrowAdapterData>();

        this.open();

        Cursor cursor = database.rawQuery("Select "+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BOOK_ID+"," +
                ""+DataBaseHelper.TABLE_BOOKLIST+"."+DataBaseHelper.BOOK_NAME+","+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_DATE+","+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_RETURN_DATE+"," +
                ""+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BORROW_STATUS+" FROM "+DataBaseHelper.TABLE_BOOKLIST+"," +
                ""+DataBaseHelper.TABLE_BORROWBOOK+" Where "+DataBaseHelper.TABLE_BOOKLIST+"."+DataBaseHelper.BOOK_ID+"="+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.BOOK_ID+" AND " +
                ""+DataBaseHelper.TABLE_BORROWBOOK+"."+DataBaseHelper.USER_ID+"=(select "+DataBaseHelper.TABLE_USERINFO+"."+DataBaseHelper.USER_ID+" from "+DataBaseHelper.TABLE_USERINFO+","+DataBaseHelper.TABLE_BORROWBOOK+" where " +
                ""+DataBaseHelper.TABLE_USERINFO+"."+DataBaseHelper.USER_NAME+"='"+name+"')",null);

        Toast.makeText(context,"Count "+cursor.getCount(),Toast.LENGTH_LONG).show();

        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++){
                int bookId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BOOK_ID));
                String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BOOK_NAME));
                String  borrowDate= cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_DATE));
                String retrunDate = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_RETURN_DATE));
                String borrowStatus = cursor.getString(cursor.getColumnIndex(DataBaseHelper.BORROW_STATUS));

                borrowAdapterData = new BorrowAdapterData(bookId,bookName,borrowDate,retrunDate,borrowStatus);
                arrayList.add(borrowAdapterData);
                cursor.moveToNext();
            }
            this.close();
            cursor.close();
        }
        return arrayList;
    }  */
}

