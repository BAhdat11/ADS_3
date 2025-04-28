import java.util.Iterator;
import java.util.Stack;
import java.util.ArrayList;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>> {
    private K key;
    private V value;
    private BST<K, V> left, right;
    private int size;

    @Override
    public Iterator<BST<K, V>> iterator() {
        ArrayList<BST<K, V>> nodeList = new ArrayList<>();
        Stack<BST<K, V>> stack = new Stack<>();
        BST<K, V> currentNode = this;

        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            nodeList.add(currentNode);
            currentNode = currentNode.right;
        }
        return nodeList.iterator();
    }

    public void insert(K key, V value) {
        if (this.key == null) {
            this.key = key;
            this.value = value;
            size++;
            return;
        }

        if (key.compareTo(this.key) < 0) {
            if (left == null) {
                left = new BST<>();
                left.key = key;
                left.value = value;
                size++;
            } else {
                left.insert(key, value);
            }
        } else if (key.compareTo(this.key) > 0) {
            if (right == null) {
                right = new BST<>();
                right.key = key;
                right.value = value;
                size++;
            } else {
                right.insert(key, value);
            }
        } else {
            this.value = value;
        }
    }

    public V find(K key) {
        if (this.key == null) {
            return null;
        }

        if (key.compareTo(this.key) == 0) {
            return this.value;
        } else if (key.compareTo(this.key) < 0) {
            return (left != null) ? left.find(key) : null;
        } else {
            return (right != null) ? right.find(key) : null;
        }
    }

    public int getSize() {
        return size;
    }

    public void remove(K key) {
        BST<K, V> parent = null;
        BST<K, V> current = this;

        while (current != null && current.key != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null || current.key == null) {
            return;
        }

        if (current.left == null || current.right == null) {
            BST<K, V> child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                this.key = child.key;
                this.value = child.value;
                this.left = child.left;
                this.right = child.right;
                size--;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            BST<K, V> successorParent = current;
            BST<K, V> successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != current) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            current.key = successor.key;
            current.value = successor.value;
            size--;
        }
    }
}
