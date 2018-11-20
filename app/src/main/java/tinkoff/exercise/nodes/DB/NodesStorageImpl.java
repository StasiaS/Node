package tinkoff.exercise.nodes.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
        ContentValues values = new ContentValues();
        values.put(NodeDBContract.RelationEntry.PARENT_ID, String.valueOf(parentId));
        values.put(NodeDBContract.RelationEntry.CHILD_ID, String.valueOf(childId));

        long localId = dbHelper.getWritableDatabase()
                .insert(NodeDBContract.RelationEntry.TABLE_NAME,
                        null,
                        values);

        return (int) localId;
    }

    @Override
    public void deleteRelation(int relationId) {
        String where = NodeDBContract.RelationEntry._ID + " = ?";
        String[] whereArgs = {String.valueOf(relationId)};

        int count = dbHelper.getWritableDatabase().delete(
                NodeDBContract.RelationEntry.TABLE_NAME,
                where,
                whereArgs
        );

        if (count < 1)
            Log.e(this.getClass().getSimpleName(), "DELETE relation FAILED");
        else
            Log.d(this.getClass().getSimpleName(), "DELETE relation SUCCESS -- ");
    }

    @Override
    public ArrayList<Long> getChilds(int nodeId) {
        String[] columns = {NodeDBContract.RelationEntry.CHILD_ID};
        String where = NodeDBContract.RelationEntry.PARENT_ID + " = ?";
        String[] whereArgs = {String.valueOf(nodeId)};

        Cursor cursor = dbHelper.getReadableDatabase().query(
                NodeDBContract.RelationEntry.TABLE_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                NodeDBContract.RelationEntry._ID
        );

        ArrayList<Long> childs = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String val = cursor.getString(cursor.getColumnIndex(NodeDBContract.RelationEntry.CHILD_ID));
                Long value;
                if (val != null && !val.isEmpty()) {
                    value = Long.valueOf(val);
                    childs.add(value);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return childs;
    }

    @Override
    public ArrayList<Long> getParents(int nodeId) {
        String[] columns = {NodeDBContract.RelationEntry.PARENT_ID};
        String where = NodeDBContract.RelationEntry.CHILD_ID + " = ?";
        String[] whereArgs = {String.valueOf(nodeId)};

        Cursor cursor = dbHelper.getReadableDatabase().query(
                NodeDBContract.RelationEntry.TABLE_NAME,
                columns,
                where,
                whereArgs,
                null,
                null,
                NodeDBContract.RelationEntry._ID
        );

        ArrayList<Long> childs = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String val = cursor.getString(cursor.getColumnIndex(NodeDBContract.RelationEntry.PARENT_ID));
                Long value;
                if (val != null && !val.isEmpty()) {
                    value = Long.valueOf(val);
                    childs.add(value);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        return childs;
    }


    // TODO: Понимаю как детей вернуть id'шниками, но не понимаю как вернуть нодами
    @Override
    public ArrayList<Node> getArrayNodes() {
        ArrayList<Node> nodes = new ArrayList<>();

        return nodes;
    }
}
