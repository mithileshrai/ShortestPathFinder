class Node
{
    int first, second;

    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object ob) {
        Node node = (Node) ob;
        return (first == node.first && second == node.second);
    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }
}