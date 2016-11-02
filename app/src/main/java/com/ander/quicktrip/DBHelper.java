package com.ander.quicktrip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ander on 8/30/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public String mAirportName;
    public int mAirportLength;
    private static final String TAG = "DBHelper";

    public static final Integer DATABASE_VERSION = 23;

    public static final String IATA_TABLE = "iata_table";
    public static final String AIRLINE_NAME = "airline_name";
    public static final String AIRLINE_IATA_CODE = "iata_code";

    public static final String AGENT_TABLE = "agent_table";
    public static final String AGENT_NAME = "agent_name";
    public static final String AGENT_CODE = "agent_code";

    public static final String AIRPORT_TABLE = "airport_table";
    public static final String AIRPORT_ID = "_id";
    public static final String AIRPORT_NAME = "airport_name";
    public static final String AIRPORT_CODE = "iata_code";
    public static final String AIRPORT_REGION = "airport_region";
    public static final String AIRPORT_COUNTRY = "airport_country";
    public static final String AIRPORT_CITY = "airport_city";

    public static final String REGION_TABLE = "region_table";
    public static final String REGION_ID = "region_id";
    public static final String REGION_IMAGE = "region_image";
    public static final String REGION_NAME = "region_name";

    public static final String FLIGHT_CARD_TABLE = "flight_card_table";
    public static final String FLIGHT_CARD_COUNTRY = "flight_card_country";
    public static final String FLIGHT_CARD_COST = "flight_card_cost";
    public static final String FLIGHT_CARD_INB = "flight_card_inbound";
    public static final String FLIGHT_CARD_OUT = "flight_card_outbound";


    private DBHelper(Context context) {
        super(context, "db", null, DATABASE_VERSION);
    }

    private static DBHelper INSTANCE;

    public static DBHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DBHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + IATA_TABLE + "(" +
                AIRLINE_NAME + " TEXT, " +
                AIRLINE_IATA_CODE + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + AGENT_TABLE + "("
                + AGENT_NAME + " TEXT, "
                + AGENT_CODE + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + AIRPORT_TABLE + "("
                + AIRPORT_ID + " TEXT PRIMARY KEY, "
                + AIRPORT_NAME + " TEXT, "
                + AIRPORT_CODE + " TEXT,"
                + AIRPORT_REGION + " TEXT, "
                + AIRPORT_COUNTRY + " TEXT, "
                + AIRPORT_CITY + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + REGION_TABLE + "("
                + REGION_ID + " INTEGER PRIMARY KEY, "
                + REGION_NAME + " TEXT, "
                + REGION_IMAGE + " TEXT )");

        sqLiteDatabase.execSQL("CREATE TABLE " + FLIGHT_CARD_TABLE + "("
                + FLIGHT_CARD_COUNTRY + " TEXT, "
                + FLIGHT_CARD_COST + " TEXT, "
                + FLIGHT_CARD_OUT + " TEXT, "
                + FLIGHT_CARD_INB + " TEXT )");

        insertAgentItems(sqLiteDatabase);
        insertAirlines(sqLiteDatabase);
        insertLocationData(sqLiteDatabase);
//        insertRegionItems(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IATA_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AGENT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AIRPORT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FLIGHT_CARD_TABLE);
        onCreate(sqLiteDatabase);
    }

    // *********************************************************************

    // Below: random airport code queries

    // *********************************************************************

    public String getRandomAirportCode() {
        SQLiteDatabase db = getReadableDatabase();
        int random = (int) (Math.random() * 100);
        String randToString = String.valueOf(random);
        Cursor cursor = db.rawQuery("SELECT * FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_ID + " = " + randToString, null);

        if (cursor.moveToFirst()) {
            mAirportName = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
            Log.i(TAG, "getRandomAirportCode original inside: " + cursor.getString(cursor.getColumnIndex(AIRPORT_CODE)));
            Log.i(TAG, "getRandomAirportCode mAirportName: " + mAirportName);
        }
        db.close();
        return mAirportName;
    }

    public String getRandEUAirportCode() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_REGION + " like '%urop%'", null);

        int random = (int) (Math.random() * cursor.getCount());
        int randToString = random;
        cursor.moveToPosition(randToString);
        mAirportName = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
        Log.i(TAG, "getRandomAirportCode: original inside" + cursor.getString(cursor.getColumnIndex(AIRPORT_CODE)));
        Log.i(TAG, "getRandomAirportCode: mAirportName: " + mAirportName);
        db.close();
        return mAirportName;
    }

    public String getRandSAmericaAirportCode() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_REGION + " like '%outhEastAsi%'", null);

        int random = (int) (Math.random() * cursor.getCount());
        int randToString = random;

        cursor.moveToPosition(randToString);
        mAirportName = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
        Log.i(TAG, "getRandomAirportCode: original inside" + cursor.getString(cursor.getColumnIndex(AIRPORT_CODE)));
        Log.i(TAG, "getRandomAirportCode: mAirportName: " + mAirportName);

        db.close();
        return mAirportName;
    }

    public String getRandSAsiaAirportCode() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_REGION + " like '%outhAmeric%'", null);

        int random = (int) (Math.random() * cursor.getCount());
        int randToString = random;
        cursor.moveToPosition(randToString);
        mAirportName = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
        Log.i(TAG, "getRandomAirportCode: original inside" + cursor.getString(cursor.getColumnIndex(AIRPORT_CODE)));
        Log.i(TAG, "getRandomAirportCode: mAirportName: " + mAirportName);
        db.close();
        return mAirportName;
    }

    public String getRandNAmericaAirportCode() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_REGION + " like '%orthAmeric%'", null);

        int random = (int) (Math.random() * cursor.getCount());
        int randToString = random;
        cursor.moveToPosition(randToString);
        mAirportName = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
        Log.i(TAG, "getRandomAirportCode: original inside" + cursor.getString(cursor.getColumnIndex(AIRPORT_CODE)));
        Log.i(TAG, "getRandomAirportCode: mAirportName: " + mAirportName);
        db.close();
        return mAirportName;
    }

    // *********************************************************************

    // Below: general airport info queries

    // *********************************************************************

    public ArrayList<String> getAllAirportCodes() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + AIRPORT_CODE + "," + AIRPORT_NAME + " FROM " + AIRPORT_TABLE + " ORDER BY " + AIRPORT_NAME + " ASC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String code = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
            String name = cursor.getString(cursor.getColumnIndex(AIRPORT_NAME));
            arrayList.add(name + " (" + code + ")");
            cursor.moveToNext();
        }
        db.close();
        return arrayList;
    }

    public ArrayList<String> getAllEuropeCodes() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + AIRPORT_CODE + "," + AIRPORT_NAME + " FROM " +
                AIRPORT_TABLE + " WHERE " + AIRPORT_REGION + " Like '%urop%'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String code = cursor.getString(cursor.getColumnIndex(AIRPORT_CODE));
            String name = cursor.getString(cursor.getColumnIndex(AIRPORT_NAME));
            arrayList.add(name + " (" + code + ")");
            cursor.moveToNext();
        }
        db.close();
        return arrayList;
    }

    public ArrayList<String> getAirportInfo(String code) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT "
                + AIRPORT_NAME + "," + AIRPORT_REGION + "," + AIRPORT_COUNTRY +
                " FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_CODE + " = " + code, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_REGION)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_COUNTRY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<String> getAirportRegion(String code) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT " + AIRPORT_REGION +
                " FROM " + AIRPORT_TABLE + " WHERE " + AIRPORT_CODE + " = " + code, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_NAME)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_REGION)));
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_COUNTRY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<String> getAllCardVItems() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " +
                FLIGHT_CARD_COST + "," +
                FLIGHT_CARD_COUNTRY + "," +
                FLIGHT_CARD_INB + "," +
                FLIGHT_CARD_OUT +
                " FROM " + FLIGHT_CARD_TABLE, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex(FLIGHT_CARD_COST)));
            arrayList.add(cursor.getString(cursor.getColumnIndex(FLIGHT_CARD_COUNTRY)));
            arrayList.add(cursor.getString(cursor.getColumnIndex(FLIGHT_CARD_INB)));
            arrayList.add(cursor.getString(cursor.getColumnIndex(FLIGHT_CARD_OUT)));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public void insertCardVItems(String cost, String out, String inb, String country) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FLIGHT_CARD_COST, cost);
        contentValues.put(FLIGHT_CARD_COUNTRY, country);
        contentValues.put(FLIGHT_CARD_INB, inb);
        contentValues.put(FLIGHT_CARD_OUT, out);
        db.insert(FLIGHT_CARD_TABLE, null, contentValues);
    }

    public void removeCardVItems() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + FLIGHT_CARD_TABLE);
        db.execSQL("CREATE TABLE " + FLIGHT_CARD_TABLE + "("
                + FLIGHT_CARD_COUNTRY + " TEXT, "
                + FLIGHT_CARD_COST + " TEXT, "
                + FLIGHT_CARD_OUT + " TEXT, "
                + FLIGHT_CARD_INB + " TEXT )");
    }

    public ArrayList<String> getCityAndCountry(String code) {
        String minusSkyCode;
//        Log.i(TAG, "getCityAndCountry: " + code.substring(code.length() - 3, code.length()));
//        Log.i(TAG, "getCityAndCountry: " + code.substring(0, code.length() - 4));
        minusSkyCode = code.substring(0, code.length() - 4);
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT *" +
                        " FROM " + AIRPORT_TABLE +
                        " WHERE " + AIRPORT_CODE + " like " + "'%" + minusSkyCode + "%'"
                , null);
        if (cursor.moveToFirst()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_NAME)));
            arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_COUNTRY)));
            if (cursor.getString(cursor.getColumnIndex(AIRPORT_CITY)) != null) {
                arrayList.add(cursor.getString(cursor.getColumnIndex(AIRPORT_CITY)));
            }
        } else {
        }
        db.close();
        return arrayList;
    }

//    public ArrayList<String> arrayList()

    // *********************************************************************

    // Below: filling the database on the tables' creation

    // *********************************************************************

//    private void insertRegionItems(SQLiteDatabase sqLiteDatabase) {
//        ArrayList<RegionObj> images = new ArrayList<>();
//        images.add(new RegionObj("South East Asia", "https://upload.wikimedia.org/wikipedia/commons/5/59/Sultan_Omar_Ali_Saifuddin_Mosque_02.jpg"));
//        images.add(new RegionObj("Europe", "http://media1.markusharju.se/2016/01/OluffaGrekland4.jpg"));
//        images.add(new RegionObj("South America","http://image.sciencenet.cn/album/201510/08/0714100xz3dq92a10yxyi4.jpg"));
//        images.add(new RegionObj("North America","https://www.lonelyplanet.com/travel-blog/tip-article/wordpress_uploads/2016/01/Bison-crossing-river-in-Yellowstone.-Image-courtesy-of-Wyoming-Office-of-Tourism.jpg"));
//
//        for (int i = 0; i < images.size(); i ++){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(REGION_NAME,images.get(i).getmName());
//            contentValues.put(REGION_IMAGE,images.get(i).getmURL());
//            sqLiteDatabase.insert(REGION_TABLE,null,contentValues);
//        }
//
//    }

    private void insertAgentItems(SQLiteDatabase sqLiteDatabase) {
        ArrayList<AgentNNObj> arrayInner = new ArrayList<AgentNNObj>();

        arrayInner.add(new AgentNNObj("Opodo", "3503883"));
        arrayInner.add(new AgentNNObj("GetAFlight", "2627398"));
        arrayInner.add(new AgentNNObj("lastminute.com", "3165195"));
        arrayInner.add(new AgentNNObj("Bravofly", "2043147"));
        arrayInner.add(new AgentNNObj("Fly-Sharp", "2499240"));
        arrayInner.add(new AgentNNObj("TravelTrolley", "3943896"));
        arrayInner.add(new AgentNNObj("omegaflightstore.com", "3496199"));
        arrayInner.add(new AgentNNObj("EmailFlights", "3247564"));
        arrayInner.add(new AgentNNObj("travelup", "4061456"));
        arrayInner.add(new AgentNNObj("GotoGate", "2628363"));
        arrayInner.add(new AgentNNObj("eDreams", "2370315"));
        arrayInner.add(new AgentNNObj("STATravel", "3920907"));
        arrayInner.add(new AgentNNObj("Lees-Travel", "3146004"));
        arrayInner.add(new AgentNNObj("Mytrip", "1963108"));
        arrayInner.add(new AgentNNObj("Tripsta", "4056843"));
        arrayInner.add(new AgentNNObj("BudgetAir", "3874827"));
        arrayInner.add(new AgentNNObj("QatarAirways", "3690449"));
        arrayInner.add(new AgentNNObj("CheapOair", "2158117"));
        arrayInner.add(new AgentNNObj("wanguk", "2172259"));
        arrayInner.add(new AgentNNObj("BritishAirways", "2032127"));
        arrayInner.add(new AgentNNObj("EmailFlights", "3247564"));
        arrayInner.add(new AgentNNObj("ebookers", "2365707"));
        arrayInner.add(new AgentNNObj("UnitedAirlines", "4132306"));
        arrayInner.add(new AgentNNObj("Expedia", "4499211"));
        arrayInner.add(new AgentNNObj("Despegar", "2261936"));
        arrayInner.add(new AgentNNObj("Carlton Leisure", "2142076"));

        for (int i = 0; i < arrayInner.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(AGENT_NAME, arrayInner.get(i).getAgentName());
            contentValues.put(AGENT_CODE, arrayInner.get(i).getAgentNum());
            sqLiteDatabase.insert(AGENT_TABLE, null, contentValues);
        }
    }

    private void insertAirlines(SQLiteDatabase sqLiteDatabase) {
        ArrayList<AirlineNameNumObj> arrayList = new ArrayList<>();

        arrayList.add(new AirlineNameNumObj("Aegean Airlines", "A3"));
        arrayList.add(new AirlineNameNumObj("Air Lingus", "EI"));
        arrayList.add(new AirlineNameNumObj("Aero Airlines", "EE"));
        arrayList.add(new AirlineNameNumObj("Alpi Eagles", "E8"));
        arrayList.add(new AirlineNameNumObj("Atlantic Airways", "RC"));
        arrayList.add(new AirlineNameNumObj("Air Finland", "FIF"));
        arrayList.add(new AirlineNameNumObj("Air Iceland", "NY"));
        arrayList.add(new AirlineNameNumObj("Air Philippines", "2P"));
        arrayList.add(new AirlineNameNumObj("Air Georgian", "ZX"));
        arrayList.add(new AirlineNameNumObj("Aegean Airlines", "A3"));

        for (int i = 0; i < arrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(AIRLINE_NAME, arrayList.get(i).getAirlineName());
            contentValues.put(AIRLINE_IATA_CODE, arrayList.get(i).getAirlineNum());
            sqLiteDatabase.insert(IATA_TABLE, null, contentValues);
        }

    }

    private void insertLocationData(SQLiteDatabase sqLiteDatabase) {

        ArrayList<AirportInformationObj> arrayList = new ArrayList<>();
        arrayList.add(new AirportInformationObj("1", "Jakarta", "JKT", "SouthEastAsia", "Indonesia", "Jakarta"));
        arrayList.add(new AirportInformationObj("2", "Bangkok", "BKK", "SouthEastAsia", "Thailand", "Bangkok"));
        arrayList.add(new AirportInformationObj("3", "Manila Aquino Intl", "MNL", "SouthEastAsia", "Philipines", "Manila"));
        arrayList.add(new AirportInformationObj("4", "Kuala Lumpur", "KUL", "SouthEastAsia", "Malaysia", "Kuala Lumpur"));
        arrayList.add(new AirportInformationObj("5", "Surabaya", "SUB", "SouthEastAsia", "Indonesia", "Surabaya"));
        arrayList.add(new AirportInformationObj("6", "Ho Chi Minh City", "SGN", "SouthEastAsia", "Vietnam", "Ho Chi Minh City"));
        arrayList.add(new AirportInformationObj("7", "Hong Kong Intl", "HKG", "SouthEastAsia", "China", "Hong Kong"));
        arrayList.add(new AirportInformationObj("8", "Bali(Denspasar)", "DPS", "SouthEastAsia", "Indonesia", "Denspasar"));
        arrayList.add(new AirportInformationObj("9", "Hanoi", "HAN", "SouthEastAsia", "Vietnam"));
        arrayList.add(new AirportInformationObj("10", "Singapore Changi", "SIN", "SouthEastAsia", "Singapore"));
        arrayList.add(new AirportInformationObj("11", "Taipei", "TPE", "SouthEastAsia", "Taipei"));
        arrayList.add(new AirportInformationObj("12", "Mactan Cebu Intl", "CEB", "SouthEastAsia", "Philippines", "Mactan"));
        arrayList.add(new AirportInformationObj("13", "Macau", "MFM", "SouthEastAsia", "China"));
        arrayList.add(new AirportInformationObj("14", "Mexico City Juarez", "MEX", "NorthAmerica", "Mexico", "Mexico City"));
        arrayList.add(new AirportInformationObj("15", "Toronto", "YTO", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("16", "Montreal", "YMQ", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("17", "Tijuana", "TIJ", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("18", "Kelowna", "YLW", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("19", "Merida", "MID", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("20", "Vancouver Intl", "YVR", "NorthAmerica", "Canada", "Vancouver"));
        arrayList.add(new AirportInformationObj("21", "Saskatoon", "YXE", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("22", "Villahermosa", "VSA", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("23", "Hermosillo", "HMO", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("24", "Regina", "CUU", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("25", "Vancouver Intl", "YVR", "NorthAmerica", "Canada", "Vancouver"));
        arrayList.add(new AirportInformationObj("26", "Veracruz", "VER", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("27", "London", "YXU", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("28", "Torreon", "TRC", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("29", "Aguascientes", "AGU", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("30", "Mazatlan", "MZT", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("31", "Queretaro", "QRO", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("32", "Nanaimo", "YCD", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("33", "Winnipeg", "YWG", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("34", "Halifax Intl", "YHZ", "NorthAmerica", "Canada", "Halifax"));
        arrayList.add(new AirportInformationObj("35", "Ottawa Intl", "YOW", "NorthAmerica", "Canada", "Ottawa"));
        arrayList.add(new AirportInformationObj("36", "Edmonton Intl", "YEG", "NorthAmerica", "Canada", "Edmonton"));
        arrayList.add(new AirportInformationObj("37", "Victoria", "YYJ", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("38", "Oaxaca", "OAX", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("39", "Deer Lake", "YDF", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("40", "Cancun", "CUN", "NorthAmerica", "Mexico"));
        arrayList.add(new AirportInformationObj("41", "Toronto, Pearson Intl", "YYZ", "NorthAmerica", "Canada", "Niagara Falls"));
        arrayList.add(new AirportInformationObj("42", "Francisco Bangoy Intl", "DVO", "SouthEastAsia", "Philippines", "Davao City"));
        arrayList.add(new AirportInformationObj("43", "London", "LON", "Europe", "England"));
        arrayList.add(new AirportInformationObj("44", "Moscow", "MOW", "Europe", "Russia"));
        arrayList.add(new AirportInformationObj("45", "Paris", "PAR", "Europe", "France"));
        arrayList.add(new AirportInformationObj("46", "Amsterdam", "AMS", "Europe", "Netherlands"));
        arrayList.add(new AirportInformationObj("47", "Frankfurt", "FRA", "Europe", "Germany"));
        arrayList.add(new AirportInformationObj("48", "Madrid", "MAD", "Europe", "Spain"));
        arrayList.add(new AirportInformationObj("49", "Olso", "OSL", "Europe", "Norway"));
        arrayList.add(new AirportInformationObj("50", "Rome", "ROM", "Europe", "Italy"));
        arrayList.add(new AirportInformationObj("51", "Dublin", "DUB", "Europe", "Republic of Ireland"));
        arrayList.add(new AirportInformationObj("52", "Milan", "MIL", "Europe", "Italy"));
        arrayList.add(new AirportInformationObj("53", "Munich", "MUC", "Europe", "Germany"));
        arrayList.add(new AirportInformationObj("54", "Berlin", "BER", "Europe", "Germany"));
        arrayList.add(new AirportInformationObj("55", "Stockholm", "STO", "Europe", "Sweden"));
        arrayList.add(new AirportInformationObj("56", "St Petersburg Pulkovo", "LED", "Europe", "Russia", "Saint Petersburg"));
        arrayList.add(new AirportInformationObj("57", "Athens Intl", "ATH", "Europe", "Greece", "Athens"));
        arrayList.add(new AirportInformationObj("58", "Zurich", "ZRH", "Europe", "Switzerland"));
        arrayList.add(new AirportInformationObj("59", "Vienna", "VIE", "Europe", "Italy"));
        arrayList.add(new AirportInformationObj("60", "Manchester", "MAN", "Europe", "England"));
        arrayList.add(new AirportInformationObj("61", "Birmingham", "BHX", "Europe", "England"));
        arrayList.add(new AirportInformationObj("62", "Malaga", "AGP", "Europe", "Spain"));
        arrayList.add(new AirportInformationObj("63", "Palma Majorca", "PMI", "Europe", "Spain", "Majorca"));
        arrayList.add(new AirportInformationObj("64", "Catania Fontanarossa", "CTA", "Europe", "Italy", "Catania"));
        arrayList.add(new AirportInformationObj("65", "Tenerife", "TCI", "Europe", "Spain"));
        arrayList.add(new AirportInformationObj("66", "Gran Canaria Las Palmas", "LPA", "Europe", "Spain", "Las Palmas"));
        arrayList.add(new AirportInformationObj("67", "Trondheim", "TRD", "Europe", "Norway"));
        arrayList.add(new AirportInformationObj("68", "Faro", "FAO", "Europe", "Portugal"));
        arrayList.add(new AirportInformationObj("69", "Ercan", "ECN", "Europe", "Cyprus"));
        arrayList.add(new AirportInformationObj("70", "Düsseldorf", "DUS", "Europe", "Germany"));
        arrayList.add(new AirportInformationObj("71", "Bergen", "BGO", "Europe", "Norway"));
        arrayList.add(new AirportInformationObj("72", "Edinburgh", "EDI", "Europe", "Scotland"));
        arrayList.add(new AirportInformationObj("73", "Toulous Ariport", "TLS", "Europe", "France", "Toulous"));
        arrayList.add(new AirportInformationObj("74", "Alicante Airport", "ALC", "Europe", "Spain", "Alicante"));
        arrayList.add(new AirportInformationObj("75", "Nice Ariport", "NCE", "Europe", "France", "Nice"));
        arrayList.add(new AirportInformationObj("76", "Belfast Ariport", "BFS", "Europe", "Northern-Ireland", "Belfast"));
        arrayList.add(new AirportInformationObj("77", "Helsinki Vantaa", "HEL", "Europe", "Finland", "Helsinki"));
        arrayList.add(new AirportInformationObj("78", "Fortaleza Ariport", "FOR", "SouthAmerica", "Brazil", "Fortaleza"));
        arrayList.add(new AirportInformationObj("79", "Caracas Ariport", "CCS", "SouthAmerica", "Colombia", "Caracas"));
        arrayList.add(new AirportInformationObj("80", "Manaus Ariport", "MAO", "SouthAmerica", "Brazil", "Manaus"));
        arrayList.add(new AirportInformationObj("81", "Cali Ariport", "CLO", "SouthAmerica", "Colombia", "Cali"));
        arrayList.add(new AirportInformationObj("82", "Rio De Janeiro Ariport", "RIO", "SouthAmerica", "Brazil", "Rio de Janeiro"));
        arrayList.add(new AirportInformationObj("83", "Lima Ariport", "LIM", "SouthAmerica", "Peru", "Lima"));
        arrayList.add(new AirportInformationObj("84", "Buenos Aires", "BUE", "SouthAmerica", "Argentina", "Buenos Aires"));
        arrayList.add(new AirportInformationObj("85", "Brasilia Airport", "BSB", "SouthAmerica", "Brazil", "Brasilia"));
        arrayList.add(new AirportInformationObj("86", "Quito Airport", "UIO", "SouthAmerica", "Ecuador", "Quito"));
        arrayList.add(new AirportInformationObj("87", "Manaus Airport", "MAO", "SouthAmerica", "Brazil", "Manaus"));
        arrayList.add(new AirportInformationObj("88", "Caracas Airport", "CCS", "SouthAmerica", "Venezuela", "Caracas"));
        arrayList.add(new AirportInformationObj("89", "Barranquilla Airport", "BAQ", "SouthAmerica", "Colombia", "Barranquilla"));
        arrayList.add(new AirportInformationObj("90", "Guayaquil Airport", "GYE", "SouthAmerica", "Ecuador", "Guayaquil"));
        arrayList.add(new AirportInformationObj("91", "Recife Airport", "REC", "SouthAmerica", "Brazil", "Recife"));
        arrayList.add(new AirportInformationObj("92", "Medellin Airport", "MDE", "SouthAmerica", "Colombia", "Medellin"));
        arrayList.add(new AirportInformationObj("93", "Cartagena Airport", "CTG", "SouthAmerica", "Colombia", "Cartagena"));
        arrayList.add(new AirportInformationObj("94", "Cuzco Airport", "CUZ", "SouthAmerica", "Peru", "Cuzco"));
        arrayList.add(new AirportInformationObj("95", "Salvador Airport", "SSA", "SouthAmerica", "Argentina", "Salvador"));
        arrayList.add(new AirportInformationObj("96", "Puerto Montt", "PMC", "SouthAmerica", "Chile", "Puerto Montt"));
        arrayList.add(new AirportInformationObj("97", "Goiania Airport", "GYN", "SouthAmerica", "Brazil", "Goiania"));
        arrayList.add(new AirportInformationObj("98", "Santa Marta", "SMR", "SouthAmerica", "Colombia", "Santa Marta"));
        arrayList.add(new AirportInformationObj("99", "Belem Airport", "BEL", "SouthAmerica", "Brazil", "Belem"));
        arrayList.add(new AirportInformationObj("100", "Luzon Clark Intl", "CRK", "SouthEastAsia", "Philippines", "Angeles Philippines"));
        arrayList.add(new AirportInformationObj("101", "Washington Airport", "WAS", "NorthAmerica", "USA", "Washington"));
        arrayList.add(new AirportInformationObj("102", "Chicago Airport", "CHI", "NorthAmerica", "USA", "Chicago"));
        arrayList.add(new AirportInformationObj("103", "Los Angeles Intl", "LAX", "NorthAmerica", "USA", "Los Angeles"));
        arrayList.add(new AirportInformationObj("104", "Atlanta Hartsfield-Jackson", "ATL", "NorthAmerica", "USA", "Atlanta"));
        arrayList.add(new AirportInformationObj("105", "Dallas", "DFW", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("106", "Boston Logan Intl", "BOS", "NorthAmerica", "USA", "Boston"));
        arrayList.add(new AirportInformationObj("107", "Denver", "DEN", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("108", "Seattle", "SEA", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("109", "Houston", "HOU", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("110", "Orlando", "ORL", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("111", "Phoenix", "PHX", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("112", "Philadelphia", "PHL", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("113", "Miami Intl", "MIA", "NorthAmerica", "USA", "Miami"));
        arrayList.add(new AirportInformationObj("114", "Minneapolis St Paul", "MSP", "NorthAmerica", "USA", "Minneapolis"));
        arrayList.add(new AirportInformationObj("115", "San Diego", "SAN", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("116", "Portland", "PDX", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("117", "Tampa Intl", "TPA", "NorthAmerica", "USA", "Tampa"));
        arrayList.add(new AirportInformationObj("118", "Salt Lake City", "SLC", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("119", "Honolulu", "HNL", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("120", "Raleigh/Durham", "RDU", "NorthAmerica", "USA", "Raleigh"));
        arrayList.add(new AirportInformationObj("121", "Nashville", "BNA", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("122", "Québec City Jean Lesage Intl", "YUL", "NorthAmerica", "Canada", "Quebec City"));
        arrayList.add(new AirportInformationObj("123", "London Intl", "YXU", "NorthAmerica", "Canada", "London"));
        arrayList.add(new AirportInformationObj("124", "Calgary Intl", "YYC", "NorthAmerica", "Canada", "Calgary"));
        arrayList.add(new AirportInformationObj("125", "Charlotte Douglas Intl", "CLT", "NorthAmerica", "USA", "Charlotte"));
        arrayList.add(new AirportInformationObj("126", "Vancouver Intl", "CXH", "NorthAmerica", "Canada", "Whistler"));
        arrayList.add(new AirportInformationObj("127", "Vancouver Intl", "YVR", "NorthAmerica", "Canada", "Whistler"));
        arrayList.add(new AirportInformationObj("128", "Montréal–Pierre Elliott Trudeau Intl", "YUL", "NorthAmerica", "Canada", "Mont-Tremblant"));
        arrayList.add(new AirportInformationObj("129", "Montréal–Pierre Elliott Trudeau Intl", "YTM", "NorthAmerica", "Canada", "Mont-Tremblant"));
        arrayList.add(new AirportInformationObj("130", "Canmore Intl", "YYC", "NorthAmerica", "Canada", "Canmore"));
        arrayList.add(new AirportInformationObj("131", "Vancouver Intl", "YVR", "NorthAmerica", "Canada", "Sea Island"));
        arrayList.add(new AirportInformationObj("132", "Vancouver Intl", "YVR", "NorthAmerica", "Canada", "The Blue Mountains"));
        arrayList.add(new AirportInformationObj("133", "John C. Munro Hamilton Intl", "YHM", "NorthAmerica", "Canada", "Hamilton"));
        arrayList.add(new AirportInformationObj("134", "John C. Munro Hamilton Intl", "YYZ", "NorthAmerica", "Canada", "Hamilton"));
        arrayList.add(new AirportInformationObj("135", "Ottawa Macdonald–Cartier Intl", "YOW", "NorthAmerica", "Canada", "Kingston"));
        arrayList.add(new AirportInformationObj("136", "Ottawa Macdonald–Cartier Intl", "YUL", "NorthAmerica", "Canada", "Kingston"));
        arrayList.add(new AirportInformationObj("137", "Churchill", "YYQ", "NorthAmerica", "Canada"));
        arrayList.add(new AirportInformationObj("138", "New Orleans Louis Armstrong", "MSY", "NorthAmerica", "USA", "New Orleans"));
        arrayList.add(new AirportInformationObj("139", "Oakland Metropolitan Oak", "Oak", "NorthAmerica", "USA", "Oakland"));
        arrayList.add(new AirportInformationObj("140", "Sacramento", "SAC", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("141", "Pittsburgh Intl", "BNA", "NorthAmerica", "USA", "Pittsburgh"));
        arrayList.add(new AirportInformationObj("142", "Cincinnati", "CVG", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("143", "Hartford", "HFD", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("144", "Milwaukee General Mitchell", "SAC", "NorthAmerica", "USA", "Milwaukee"));
        arrayList.add(new AirportInformationObj("145", "Maui Kahului", "OGG", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("146", "Memphis Intl", "MEM", "NorthAmerica", "USA", "Memphis"));
        arrayList.add(new AirportInformationObj("147", "Buffalo Niagara", "SAC", "NorthAmerica", "USA", "Buffalo"));
        arrayList.add(new AirportInformationObj("148", "Albuquerque", "ABQ", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("149", "Indianapolis", "IND", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("150", "Las Vagas Mccarran", "LAS", "NorthAmerica", "USA", "Las Vagas"));
        arrayList.add(new AirportInformationObj("151", "San Jose Intl", "SJC", "NorthAmerica", "USA", "San Jose"));
        arrayList.add(new AirportInformationObj("152", "Austin-Bergstrom", "AUS", "NorthAmerica", "USA", "Austin"));
        arrayList.add(new AirportInformationObj("153", "St Louis", "STL", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("154", "Phnom Penh Intl", "PNH", "SouthEastAsia", "Cambodia", "Phnom Penh"));
        arrayList.add(new AirportInformationObj("155", "Sihanoukville Intl", "KOS", "SouthEastAsia", "Cambodia", "Phú Quốc"));
        arrayList.add(new AirportInformationObj("156", "Don Mueang Intl", "SAC", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("157", "Vancouver Intl", "YVR", "NorthAmerica", "USA", "San Jose"));
        arrayList.add(new AirportInformationObj("158", "Austin-Bergstrom", "AUS", "NorthAmerica", "USA", "Austin"));
        arrayList.add(new AirportInformationObj("159", "St Louis", "STL", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("160", "Phnom Penh Intl", "PNH", "SouthEastAsia", "Cambodia", "Phnom Penh"));
        arrayList.add(new AirportInformationObj("161", "Sihanoukville Intl", "KOS", "SouthEastAsia", "Cambodia", "Phú Quốc"));
        arrayList.add(new AirportInformationObj("162", "Don Mueang Intl", "SAC", "NorthAmerica", "USA"));
        arrayList.add(new AirportInformationObj("163", "koh samui", "USM", "SouthEastAsia", "Vietnam", "Ko Samui"));
        arrayList.add(new AirportInformationObj("164", "Siem Reap", "REP", "SouthEastAsia", "Cambodia"));
        arrayList.add(new AirportInformationObj("165", "Phú Quốc", "PQC", "SouthEastAsia", "Indonesia"));
        arrayList.add(new AirportInformationObj("166", "Da Lat", "DLI", "SouthEastAsia", "Vietnam"));
        arrayList.add(new AirportInformationObj("167", "Pattaya", "UTP", "SouthEastAsia", "Vietnam"));
        arrayList.add(new AirportInformationObj("168", "Danang", "DAD", "SouthEastAsia", "Vietnam"));
        arrayList.add(new AirportInformationObj("169", "Cat Bi Intl", "HPH", "SouthEastAsia", "Vietnam", "Haiphong"));
        arrayList.add(new AirportInformationObj("170", "Cat Bi Intl", "HPH", "SouthEastAsia", "Vietnam", "Ha Long Bay"));
        arrayList.add(new AirportInformationObj("171", "Noi Bai Intl", "HAN", "SouthEastAsia", "Vietnam", "Haiphong"));
        arrayList.add(new AirportInformationObj("172", "Noi Bai Intl", "HAN", "SouthEastAsia", "Vietnam", "Ha Long Bay"));
        arrayList.add(new AirportInformationObj("173", "Yangon Intl", "RGN", "SouthEastAsia", "Myanmar", "Yagon"));
        arrayList.add(new AirportInformationObj("174", "Da Lat", "DLI", "SouthEastAsia", "Vietnam"));
        arrayList.add(new AirportInformationObj("175", "Hanoi", "HAN", "SouthEastAsia", "Vietnam", "Sa Pa"));
        arrayList.add(new AirportInformationObj("176", "Denpasar", "DPS", "SouthEastAsia", "Indonesia", "Ubud"));
        arrayList.add(new AirportInformationObj("177", "Da Lat", "DLI", "SouthEastAsia", "Vietnam", "Phan Thiet"));
        arrayList.add(new AirportInformationObj("178", "Tan Son Nhat Intl", "SGN", "SouthEastAsia", "Vietnam", "Phan Thiet"));
        arrayList.add(new AirportInformationObj("179", "Tan Son Nhat Intl", "SGN", "SouthEastAsia", "Vietnam", "Mũi Né"));
        arrayList.add(new AirportInformationObj("180", "Phuket Intl", "HKT", "SouthEastAsia", "Vietnam", "Phuket City"));
        arrayList.add(new AirportInformationObj("181", "Adisutjipto Intl", "JOG", "SouthEastAsia", "Indonesia", "Yogyakarta"));
        arrayList.add(new AirportInformationObj("182", "Labuan Bajo", "JOG", "SouthEastAsia", "Indonesia", "Komodo"));
        arrayList.add(new AirportInformationObj("183", "Babo", "BXB", "SouthEastAsia", "Indonesia", "Raja Ampat Islands"));
        arrayList.add(new AirportInformationObj("184", "Suvarnabhumi", "BKK", "SouthEastAsia", "Indonesia", "Ko Chang District"));
        arrayList.add(new AirportInformationObj("185", "Samui", "USM", "SouthEastAsia", "Thailand", "Ko Pa Ngan"));
        arrayList.add(new AirportInformationObj("186", "Mactan–Cebu Intl", "CEB", "SouthEastAsia", "Philippines", "Mactan"));
        arrayList.add(new AirportInformationObj("187", "Sibulan", "DGT", "SouthEastAsia", "Philippines", "Dumaguete"));
        arrayList.add(new AirportInformationObj("188", "Nha Trang", "CXR", "SouthEastAsia", "Vietnam", "Nha Trang"));
        arrayList.add(new AirportInformationObj("189", "Václav Havel ", "PRG", "Europe", "Czech Republic","Prague"));
        arrayList.add(new AirportInformationObj("190", "Barcelona–El Prat ", "BCN", "Europe", "Spain", "Barcelona"));
        arrayList.add(new AirportInformationObj("191", "Venice Marco Polo", "VCE", "Europe", "Italy", "Venice"));
        arrayList.add(new AirportInformationObj("192", "Tours Val de Loire ", "TUF", "Europe", "France", "Tours"));
        arrayList.add(new AirportInformationObj("193", "Lyon–Saint-Exupéry", "LYS", "Europe", "France", "Lyon"));
        arrayList.add(new AirportInformationObj("194", "Avignon – Provence ", "MVV", "Europe", "France", "Avignon"));
        arrayList.add(new AirportInformationObj("195", "Megève", "MVV", "Europe", "France"));
        arrayList.add(new AirportInformationObj("196", "Strasbourg", "SXB", "Europe", "France"));
        arrayList.add(new AirportInformationObj("197", "Carcassonne", "CCF", "Europe", "France"));
        arrayList.add(new AirportInformationObj("198", "Lille", "LIL", "Europe", "France"));
        arrayList.add(new AirportInformationObj("199", "Lisbon Portela", "LIS", "Europe", "Portugal", "Lisbon"));
        arrayList.add(new AirportInformationObj("200", "Porto Intl", "OPO", "Europe", "Portugal", "Porto"));
        arrayList.add(new AirportInformationObj("201", "Faro", "FAO", "Europe", "Portugal", "Lagos"));
        arrayList.add(new AirportInformationObj("202", "Braga", "BGZ", "Europe", "Portugal", "Guimarães"));
        arrayList.add(new AirportInformationObj("203", "Lisbon Portela", "LIS", "Europe", "Portugal", "Sintra"));
        arrayList.add(new AirportInformationObj("204", "Lisbon Portela", "LIS", "Europe", "Portugal", "Sesimbra"));
        arrayList.add(new AirportInformationObj("205", "Vienna Intl", "VIE", "Europe", "Austria", "Vienna"));
        arrayList.add(new AirportInformationObj("206", "Innsbruck", "INN", "Europe", "Austria"));
        arrayList.add(new AirportInformationObj("207", "Salzburg Mozart Intl", "SZG", "Europe", "Austria", "Salzburg"));
        arrayList.add(new AirportInformationObj("208", "Graz", "GRZ", "Europe", "Austria"));
        arrayList.add(new AirportInformationObj("209", "Linz", "LNZ", "Europe", "Austria"));
        arrayList.add(new AirportInformationObj("210", "Salzburg Mozart Intl", "SZG", "Europe", "Hallstatt"));
        arrayList.add(new AirportInformationObj("211", "Graz", "GRZ", "Europe", "Austria"));
        arrayList.add(new AirportInformationObj("212", "Graz", "GRZ", "Europe", "Austria"));
        arrayList.add(new AirportInformationObj("213", "Geneva", "GVA", "Europe", "France", "Chamonix"));
        arrayList.add(new AirportInformationObj("214", "EuroAirport Basel-Mulhouse-Freiburg", "BSL", "Europe", "France", "Alsace"));
        arrayList.add(new AirportInformationObj("215", "Rome", "ROM", "Europe", "Italy"));
        arrayList.add(new AirportInformationObj("216", "Tambolaka", "TMC", "SouthEastAsia", "Indonesia", "Flores"));
        arrayList.add(new AirportInformationObj("217", "Cork Airport", "Ork", "Europe", "Republic of Ireland", "Cork"));
        arrayList.add(new AirportInformationObj("218", "Shannon", "SNN", "Europe", "Ireland", "Limerick City"));
        arrayList.add(new AirportInformationObj("219", "Belfast Intl", "BFS", "Europe", "Ireland", "Belfast"));
        arrayList.add(new AirportInformationObj("220", "Knock Airport", "NOC", "Europe", "Ireland", "Charlestown"));
        arrayList.add(new AirportInformationObj("221", "Copenhagen Airport", "CPH", "Europe", "Denmark", "Copenhagen"));
        arrayList.add(new AirportInformationObj("222", "Oslo Gardermoen", "OSL", "Europe", "Norway", "Oslo"));
        arrayList.add(new AirportInformationObj("223", "Malmö Airport", "MMX", "Europe", "Sweden", "Malmö"));
        arrayList.add(new AirportInformationObj("224", "Visby Airport", "VBY", "Europe", "Sweden","Gotland"));
        arrayList.add(new AirportInformationObj("225", "Ivalo Airport", "IVL", "Europe", "Finland", "Saariselkä"));
        arrayList.add(new AirportInformationObj("226", "Joensuu Airport", "JOE", "Europe", "Finland", "Joensuu"));
        arrayList.add(new AirportInformationObj("227", "Stockholm Airport", "STO", "Europe", "Sweden", "Uppsala"));
        arrayList.add(new AirportInformationObj("228", "Kalmar Airport", "KLR", "Europe", "Sweden", "Öland"));
        arrayList.add(new AirportInformationObj("229", "Småland Airport", "VXO", "Europe", "Sweden", "Småland"));
        arrayList.add(new AirportInformationObj("230", "Milan–Malpensa Airport", "MXP", "Europe", "Italy", "Milan"));
        arrayList.add(new AirportInformationObj("231", "Budapest Airport", "STO", "Europe", "Sweden", "Uppsala"));
        arrayList.add(new AirportInformationObj("232", "Budapest Ferenc Liszt Intl", "BUD", "Europe", "Hungary", "Budapest"));
        arrayList.add(new AirportInformationObj("233", "Naples Municipal", "APF", "Europe", "Italy", "amalfi coast"));
        arrayList.add(new AirportInformationObj("234", "Naples Municipal", "APF", "Europe", "Italy", "Pompeii"));
        arrayList.add(new AirportInformationObj("235", "Bergen Flesland Intl", "BGO", "Europe", "Finland", "Bergen"));
        arrayList.add(new AirportInformationObj("236", "Treviso Intl", "TSF", "Europe", "Italy", "Cortina d'Ampezzo"));
        arrayList.add(new AirportInformationObj("237", "Naples Municipal", "APF", "Europe", "Italy", "Capri"));
        arrayList.add(new AirportInformationObj("238", "Verona villafranca", "VRN", "Europe", "Italy", "Verona"));
        arrayList.add(new AirportInformationObj("239", "Trapani Florio", "TPS", "Europe", "Italy", "Trapani"));
        arrayList.add(new AirportInformationObj("240", "Comiso Airport", "CIY", "Europe", "Italy", "Cosmio"));
        arrayList.add(new AirportInformationObj("241", "Catania–Fontanarossa", "PMO", "Europe", "Italy", "Catania"));
        arrayList.add(new AirportInformationObj("242", "Peretola", "FLR", "Europe", "Italy", "Florence"));
        arrayList.add(new AirportInformationObj("243", "Peretola", "FLR", "Europe", "Italy", "Greve"));
        arrayList.add(new AirportInformationObj("244", "Bordeaux–Mérignac", "BOD", "Europe", "France", "Bordeaux"));
        arrayList.add(new AirportInformationObj("245", "Dijon-Bourgogne", "DIJ", "Europe", "France", "Dijon"));
        arrayList.add(new AirportInformationObj("246", "Dijon-Bourgogne", "DIJ", "Europe", "France", "Burgundy"));
        arrayList.add(new AirportInformationObj("247", "Falcone–Borsellino", "PMO", "Europe", "Italy", "Palermo"));
        arrayList.add(new AirportInformationObj("248", "Bern Airport", "BRN", "Europe", "Switzerland", "Bern"));
        arrayList.add(new AirportInformationObj("249", "Brussels Airport", "BRN", "Europe", "Belgium", "Brussels"));
        arrayList.add(new AirportInformationObj("250", "Vienna Intl", "VIE", "Europe", "Austria", "Bern"));
        arrayList.add(new AirportInformationObj("251", "Flughafen Salzburg", "SZG", "Europe", "Austria", "Salzburg"));
        arrayList.add(new AirportInformationObj("252", "Marrakesh Menara", "RAK", "NorthAfrica", "Morocco", "Marrakesh"));
        arrayList.add(new AirportInformationObj("253", "Casablanca Mohammed V Intl", "CMN", "NorthAfrica", "Morocco", "Casablanca"));
        arrayList.add(new AirportInformationObj("254", "Fès–Saïss", "FEZ", "NorthAfrica", "Morocco", "Fès"));
        arrayList.add(new AirportInformationObj("255", "Ouarzazate Airport", "OZZ", "NorthAfrica", "Morocco", "Ouarzazate"));
        arrayList.add(new AirportInformationObj("256", "Kotoka Intl", "RAK", "WestAfrica", "Ghana", "Accra"));
        arrayList.add(new AirportInformationObj("257", "Kumasi Airport", "KMS", "WestAfrica", "Ghana", "Kumasi"));
        arrayList.add(new AirportInformationObj("258", "Bangkok (All)", "BKK", "SouthEastAsia", "Vietnam", "Pattaya"));

        mAirportLength = arrayList.size();
        Log.i(TAG, "insertLocationData: " + mAirportLength);

        for (int i = 0; i < arrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(AIRPORT_ID, arrayList.get(i).getmAirport_ID());
            contentValues.put(AIRPORT_NAME, arrayList.get(i).getmAirportName());
            contentValues.put(AIRPORT_CODE, arrayList.get(i).getmAirportIata());
            contentValues.put(AIRPORT_REGION, arrayList.get(i).getmAirportRegion());
            contentValues.put(AIRPORT_COUNTRY, arrayList.get(i).getmAirportCountry());
            if (arrayList.get(i).getmCity() != null) {
                contentValues.put(AIRPORT_CITY, arrayList.get(i).getmCity());
            }
            sqLiteDatabase.insert(AIRPORT_TABLE, null, contentValues);
        }

    }
}
