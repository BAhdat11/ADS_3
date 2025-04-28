import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(10000);
            String name = "Test" + random.nextInt(10000);
            table.put(new MyTestingClass(id, name), i);
        }

        for (int i = 0; i < 11; i++) {
            int count = 0;
            var node = table.getChain(i);
            while (node != null) {
                count++;
                node = node.next;
            }
            System.out.println("Bucket " + i + " has " + count + " elements");
        }
    }
}
