package ADraft;


public class TriesDemo {

    class Trie {

        Trie[] children = new Trie[26];
        private boolean isEnd = false;

        public Trie() {
        }

        public void insert(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) p.children[c - 'a'] = new Trie();
                p = p.children[c - 'a'];
            }
            p.isEnd = true;
        }

        public boolean search(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if (p.children[c - 'a'] == null) return false;
                p = p.children[c - 'a'];
            }
            return p.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie p = this;
            for (int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if (p.children[c - 'a'] == null) return false;
                p = p.children[c - 'a'];
            }
            return true;
        }
    }
}
