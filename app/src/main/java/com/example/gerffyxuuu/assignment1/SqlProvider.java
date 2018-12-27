package com.example.gerffyxuuu.assignment1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

public class SqlProvider extends ContentProvider {
    public static final String AUTOHORITY = "cn.scu.sql";
    private Context mContext;
    DBHelper mDbHelper = null;
    SQLiteDatabase db = null;

    public static final int User_Code = 1;
    public static final int Class = 2;
    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        mMatcher.addURI(AUTOHORITY, "user", User_Code);
        // 若URI资源路径 = content://cn.scu.sqlprovider/user ，则返回注册码User_Code
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDbHelper = new DBHelper(mContext);
        db = mDbHelper.getWritableDatabase();
        db.execSQL("delete from user");
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String name = getTableName(uri);
        return db.query(name,projection,selection,selectionArgs,null,null,sortOrder,null);
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String table = getTableName(uri);
        db.insert(table,null,values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values, String selection,String[] selectionArgs) {
        return 0;
    }
    private String getTableName(Uri uri){
        String name = null;
        switch (mMatcher.match(uri)){
            case User_Code:{
                name = DBHelper.USER_TABLE_NAME;
                break;
            }
            case Class:{
                name = DBHelper.CLASS_TABLE_NAME;
            }
        }
        return name;
    }
}
