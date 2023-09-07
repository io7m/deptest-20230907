import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public final class Dependencies {

  public static void main(String[] args) throws IOException {
    try (var stream = Files.lines(Paths.get(args[0]))) {
      var items = stream.toList();
      var qualified = new HashSet<String>();

      var libs = new TreeSet<String>();

      for (var item : items) {
        var itemSplit = item.split(":");
        var group = itemSplit[0];
        var artif = itemSplit[1];

        var combined = group + ":" + artif;
        if (qualified.contains(combined)) {
          continue;
        }
        qualified.add(combined);

        var versi = itemSplit[2];
        var name = artif.replace('-', '_');

        if (group.contains("androidx")) {
          name = "androidx_" + name;
        }

        libs.add("libs." + name.replace('_', '.'));

        System.out.println("""
%s = { module = "%s:%s", version = "%s" }""".formatted(name, group, artif, versi));
      }

      System.out.println();

      for (var name : libs) {
        System.out.printf("  implementation(%s)%n", name);
      }
    }
  }
}
