package com.example.iamma.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATA_BASE_NAME ="restaurant";
	SQLiteDatabase db;
	
	private static final int DATA_BASE_VERSION = 18;

	//SQLiteDatabase db = this.getWritableDatabase();
	Cursor cursor;
	
	//table name
	public static final String CUSTOMER_TABLE = "cust_tab";
	public static final String ORDER_TABLE = "order_tab";
	public static final String PLACED_ORDER ="placed_order";
	
	//column name of cust_tab
	public static final String C_ID ="_id";
	public static final String C_NAME = "cust_name";
	public static final String C_PHONE = "cust_phone";
	public static final String C_EMAIL = "cust_email";
	public static final String C_ADDR = "cust_address";
	
	//column name of order_tab
	public static final String O_NAME = "order_name";
	public static final String O_NO = "order_no";
	
	//column name for the Palced_order
	public static final String P_ID ="_id";
	public static final String P_USER_ID = "user_id";
	public static final String P_ORDER_ID = "order_id";
	
	
	String CREATE_C_TABLE = "CREATE TABLE " + CUSTOMER_TABLE + "("
			+ C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ C_NAME + " TEXT NOT NULL,"
			+ C_PHONE + " TEXT," + C_EMAIL + " TEXT,"
			+ C_ADDR + " TEXT );";
	
	String CREATE_O_TABLE = "CREATE TABLE " + ORDER_TABLE + "("
			+ O_NO + " INTEGER PRIMARY KEY, "
			+ O_NAME + " TEXT NOT NULL );";
	
	String CREAT_P_TABLE ="CREATE TABLE " +  PLACED_ORDER + "("
			
			+ P_USER_ID + " TEXT, "+ P_ORDER_ID + " INTEGER, " 
			+ " FOREIGN KEY (" + P_USER_ID +") REFERENCES " +CUSTOMER_TABLE+" ("+C_PHONE+"), "
			 + " FOREIGN KEY (" + P_ORDER_ID +") REFERENCES " +ORDER_TABLE+" ("+O_NO+"));";
	 
	public DatabaseHelper(Context context) {
		super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
		// db = this.getWritableDatabase();
		
	}
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	    	//Log.e("in onOPen", "foreign key is enable");
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_C_TABLE);
		db.execSQL(CREATE_O_TABLE);
		db.execSQL(CREAT_P_TABLE);
		//Log.e("db on create call","called");
		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
				db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
				db.execSQL("DROP TABLE IF EXISTS " + PLACED_ORDER);
				// Create tables again
				onCreate(db);
		
	}
	//insert order
	public void addOrder(String s){
		ContentValues ocv = new ContentValues();
		ocv.put(O_NAME, s);
		db=this.getWritableDatabase();
		db.insert(ORDER_TABLE, null, ocv);
		db.close();
		
	}
	//insert value
		public void addCustomer(String name,String phone,String email,String address){
			
			ContentValues cv = new ContentValues();
			cv.put(C_NAME, name);
			cv.put(C_PHONE, phone);
			cv.put(C_EMAIL, email);
			cv.put(C_ADDR, address);

			//insert row
			db = this.getWritableDatabase();
			db.insert(CUSTOMER_TABLE, null, cv);
			db.close();
			
		}
		
		public boolean validateUser(String userName,String mphone){
			// dbread=getReadableDatabase();
			db=this.getWritableDatabase();
			cursor =db.rawQuery("SELECT * FROM " + CUSTOMER_TABLE + " WHERE "
	                + C_NAME + "='" + userName +"'AND "+C_PHONE+"='"+mphone+"'" ,  null);
			 if(cursor.getCount() > 0)
				 return true;
			 
			return false;
			
		}

	//Get Table Register Detail
	public ArrayList<HashMap<String,String>> GetCustomer(){
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<HashMap<String,String>> regList = new ArrayList<>();
		String query = "SELECT * FROM "+ CUSTOMER_TABLE;
		Cursor cursor = db.rawQuery(query,null);
		while (cursor.moveToNext())
		{
			HashMap<String,String> reg = new HashMap<>();
			reg.put("cust_name",cursor.getString(cursor.getColumnIndex(C_NAME)));
			reg.put("cust_email",cursor.getString(cursor.getColumnIndex(C_EMAIL)));
			reg.put("cust_address",cursor.getString(cursor.getColumnIndex(C_ADDR)));
			//reg.put(myClass.getName().toString(),cursor.getString(cursor.getColumnIndex(C_NAME)));
			regList.add(reg);
		}
		return  regList;
	}
}