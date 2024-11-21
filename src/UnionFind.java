import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<Node, Node> parent = new HashMap<>();
    private Map<Node, Integer> rank = new HashMap<>();

    public void createNewSet(Node node) {
        parent.put(node, node);
        rank.put(node, 0);
    }

    public Node findRootNode(Node node) {
        if (parent.get(node) != node) {
            parent.put(node, findRootNode(parent.get(node)));
        }
        return parent.get(node);
    }

    public void mergeNodeSets(Node node1, Node node2) {
        Node root1 = findRootNode(node1);
        Node root2 = findRootNode(node2);

        if (root1 != root2) {
            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
}