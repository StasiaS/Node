package tinkoff.exercise.nodes.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "NodeDataBase.db";
    private static final int DB_VERSION = 1;

    private Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NODE_TABLE = "CREATE TABLE " + NodeDBContract.NodeEntry.TABLE_NAME + " ("
                + NodeDBContract.NodeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NodeDBContract.NodeEntry.VALUE + " INTEGER);";

        db.execSQL(SQL_CREATE_NODE_TABLE);

        String SQL_CREATE_RELATION_TABLE = "CREATE TABLE " + NodeDBContract.RelationEntry.TABLE_NAME + " ("
                + NodeDBContract.RelationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NodeDBContract.RelationEntry.PARENT_ID + " INTEGER, "
                + NodeDBContract.RelationEntry.CHILD_ID + " INTEGER);";

        db.execSQL(SQL_CREATE_RELATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
