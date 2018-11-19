package tinkoff.exercise.nodes.DB;

import android.provider.BaseColumns;

final class NodeDBContract {

    public NodeDBContract() {
    }

    static final class NodeEntry implements BaseColumns {
        final static String TABLE_NAME = "node";
        final static String _ID = BaseColumns._ID;
        final static String VALUE = "value";
    }

    static final class RelationEntry implements BaseColumns {
        final static String TABLE_NAME = "relation";
        final static String _ID = BaseColumns._ID;
        final static String PARENT_ID = "parent_id";
        final static String CHILD_ID = "child_id";
    }
}
