package tinkoff.exercise.nodes.DB;

import java.util.ArrayList;

import tinkoff.exercise.nodes.Node;

public interface NodesStorage {
    int getValue(int id);
    int addValue(int value);
    int addRelation(int parentId, int childId);
    void deleteRelation(int relationId);
    ArrayList<Node> getArrayNodes();
}
