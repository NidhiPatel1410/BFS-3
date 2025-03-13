// In this problem, call dfs on this node. Check if the node in map? no then create it's copy and now call dfs on it's neighbors. Then
// add this neighbor's copy to the list of neighbors of node.

// Time Complexity : O(V+E)
// Space Complexity : O(V)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// DFS
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
// DFS
class Solution {
    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        // Base Case
        if (node == null) {
            return null;
        }
        // Initialize map
        map = new HashMap<>();
        // Call dfs on the given node
        dfs(node);
        // Return this node's copy
        return map.get(node);
    }

    private void dfs(Node node) {
        // Base case - if the map already contains this node, return
        if (map.containsKey(node)) {
            return;
        }
        // Else create a copy and put in map
        Node copiedNode = clone(node);
        // Now get it's neighbors and call dfs on them
        List<Node> neighbors = node.neighbors;
        for (Node neighbor : neighbors) {
            dfs(neighbor);
            // Then add the copy of neighbor to the neighbor's list of the node
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }

    private Node clone(Node node) {
        // Before creating copy, check if already present in map, just return that, dont
        // create double copies
        if (map.containsKey(node)) {
            return map.get(node);
        }
        // Else, create a copy
        Node newNode = new Node(node.val);
        // Add to map
        map.put(node, newNode);
        // Return copy
        return newNode;
    }
}

// In this problem, first create the copy of given node, add in map and add in
// queue. Start bfs, poll the current and get it's neighbors and check if
// neighbor is not present in map, create it's copy and add it in the queue.

// Time Complexity : O(V+E)
// Space Complexity : O(V)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// BFS
class Solution {
    HashMap<Node, Node> map;

    public Node cloneGraph(Node node) {
        // Base case
        if (node == null) {
            return null;
        }
        map = new HashMap<>();
        // Copy of first node
        Node cloned = clone(node);
        // Queue
        Queue<Node> q = new LinkedList<>();
        // Add node to the queue
        q.add(node);
        // Start bfs
        while (!q.isEmpty()) {
            // Poll the current
            Node curr = q.poll();
            // Get it's neighbors
            List<Node> neighbors = curr.neighbors;
            // Check for each of it's neighbors
            for (Node neighbor : neighbors) {
                // if not in map, create their copy and add in queue
                if (!map.containsKey(neighbor)) {
                    Node copyNeigh = clone(neighbor);
                    q.add(neighbor);
                }
                // Add the neighbor's copy to the list of neighbors of current node
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    private Node clone(Node node) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        return newNode;
    }
}