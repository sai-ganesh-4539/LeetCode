class TrieNode {
    TrieNode[] child = new TrieNode[26];
    boolean g;
}
class StreamChecker {
    TrieNode root = new TrieNode();
    StringBuilder s = new StringBuilder();
    public StreamChecker(String[] words) {
        for (String w : words) insert(w);
    }
    private void insert(String word) {
        TrieNode node = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int x = word.charAt(i) - 'a';
            if (node.child[x] == null) node.child[x] = new TrieNode();
            node = node.child[x];
        }
        node.g = true;
    }
    public boolean query(char letter) {
        s.append(letter);
        TrieNode node = root;
        for (int i = s.length() - 1; i >= 0; i--) {
            int x = s.charAt(i) - 'a';
            if (node.child[x] == null) return false;
            node = node.child[x];
            if (node.g) return true;
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */