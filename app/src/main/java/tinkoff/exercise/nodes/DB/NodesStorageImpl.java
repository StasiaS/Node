package tinkoff.exercise.nodes.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import tinkoff.exercise.nodes.Node;

public class NodesStorageImpl implements NodesStorage {

    private DBHelper dbHelper;

    public NodesStorageImpl(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public int getValue(int id) {
        String[] columns = {NodeDBContract.NodeEntry.VALUE};
        String where = NodeDBContract.NodeEntry._ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = dbHelper.getReadableDatabase().query(
                NodeDBContract.NodeEntry.TABLE_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                null
        );

        int value = 0;
        if (cursor.moveToFirst()) {
            String val = cursor.getString(cursor.getColumnIndex(NodeDBContract.NodeEntry.VALUE));
            if (val != null && !val.isEmpty()) {
                value = Integer.valueOf(val);
            }
            cursor.close();
        }
        return value;
    }

    @Override
    public int addValue(int value) {
        ContentValues values = new ContentValues();
        values.put(NodeDBContract.NodeEntry.VALUE, String.valueOf(value));

        long localId = dbHelper.getWritableDatabase()
                .insert(NodeDBContract.NodeEntry.TABLE_NAME,
                        null,
                        values);

        return (int) localId;
    }

    @Override
    public int addRelation(int parentId, int childId) {
        return 0;
    }

    @Override
    public void deleteRelation(int relationId) {

    }

    @Override
    public ArrayList<Node> getArrayNodes() {
        return null;
    }
}
