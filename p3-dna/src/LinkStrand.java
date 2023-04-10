public class LinkStrand implements IDnaStrand {

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    private class Node {
        String info;
        Node next;

        public Node(String s, Node n) {
            info = s;
            next = n;
        }
    }
    public LinkStrand() {
        this("");
    }
    public LinkStrand(String s) {
        initialize(s);
    }
    @Override
    public long size() {
        return mySize;
    }
    @Override
    public void initialize(String source) {
        myFirst = new Node(source, null);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }
    private void addFirst(String s) {
        Node newFirst = new Node(s, myFirst);
        myFirst = newFirst;
        mySize += s.length();
    }
    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna, null);

        myLast.next = newNode;
        myLast = newNode;
        mySize += dna.length();
        myAppends++;

        return this;
    }
    @Override
    public IDnaStrand reverse() {
        LinkStrand rev = new LinkStrand();
        Node current = myFirst;
        while (current != null) {
            StringBuilder temp = new StringBuilder(current.info);
            rev.addFirst(temp.reverse().toString());
            current = current.next;
        }
        return rev;
    }
    @Override
    public int getAppendCount() {
        return myAppends;
    }
    @Override
    public char charAt(int index) {

        if(index > mySize - 1 || index < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Trying to access char %s while Strand has a size of %s", index, mySize)
            );
        }
        if(myIndex == 0 || index < (myIndex - myLocalIndex)) {
            return iterativeCharAt(index, myFirst, 0);
        }

        return iterativeCharAt(index, myCurrent, myIndex - myLocalIndex);

    }
    private char iterativeCharAt(int index, Node nodeStart, int precedingNodeCount) {
        int totalCount = precedingNodeCount;
        int localCount = 0;
        Node listinha = nodeStart;

        while (totalCount != index) {
            if (index > totalCount + listinha.info.length() - 1) {
                totalCount += listinha.info.length();
                listinha = listinha.next;
            } else {
                localCount = index - totalCount;
                totalCount = index;
            }
        }
        myIndex = index;
        myCurrent = listinha;
        myLocalIndex = localCount;

        return listinha.info.charAt(localCount);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Node current = myFirst;
        while (current != null) {
            ret.append(current.info);
            current = current.next;
        }

        return ret.toString();
    }
}