package Codewar;
import Codewar.Node;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Node_reader {
    public static List<Integer> treeByLevels(Node node)
    {
        ArrayDeque<Node> que = new ArrayDeque<>();
        if (node != null)
            que.add(node);
        List<Integer> result = new ArrayList<>();

        while (!que.isEmpty()){
            Node buffer1 = que.poll();
            if (buffer1.left != null)
                que.add(buffer1.left);
            if (buffer1.right != null)
                que.add(buffer1.right);
            result.add(buffer1.value);
        }
        return result.size() == 0 ? null : result;
    }

    public static void main(String[] args) {
        Node f = new Node(new Node(null, new Node(null, null, 4), 2), new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1);
        System.out.println(treeByLevels(f));
    }
}
