import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ1302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String book = br.readLine();
            if (map.containsKey(book)) {
                int value = map.get(book) + 1;
                map.put(book, value);
            } else {
                map.put(book, 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            int valueCompare = o2.getValue().compareTo(o1.getValue());
            if (valueCompare != 0) {
                return valueCompare;
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        System.out.println(list.get(0).getKey());
    }

}
