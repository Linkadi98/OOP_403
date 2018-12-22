package com.gr8.object;
import com.gr8.object.Node;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {
    @Override

    public int compare(Node node1, Node node2) {
        if (node1.getPriority() > node2.getPriority()){
            return 1;
        }

        if (node1.getPriority() < node2.getPriority()){
            return -1;
        }

        return 0;
    }
}
