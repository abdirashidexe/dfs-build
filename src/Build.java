import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    Set<Vertex<String>> myVisited = new HashSet<>();
    printShortWordsHelper(vertex, k, myVisited);
  }

  public static void printShortWordsHelper(Vertex<String> current, int i, Set<Vertex<String>> visited)
  {
    if (current == null || visited.contains(current)) return;

    if (current.data.length() < i)
    {
      System.out.println(current.data);
      visited.add(current);

      }
      for (Vertex<String> neighbor : current.neighbors)
      {
        printShortWordsHelper(neighbor, i, visited);
      }

    // if no reachable words meet criteria, never enters if statement
  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    Set<Vertex<String>> myVisited = new HashSet<>();
    return longestWordHelper(vertex, myVisited);
  }

  public static String longestWordHelper(Vertex<String> current, Set<Vertex<String>> visited)
  {
    if (current == null || visited.contains(current)) return ""; // if null
    
    visited.add(current);
    String longestWord = current.data;
    
    for (Vertex<String> neighbor : current.neighbors)
    {
      String longestNeighborWord = longestWordHelper(neighbor, visited);
      if (longestNeighborWord.length() > longestWord.length())
      {
        longestWord = longestNeighborWord;
      }
    }

    return longestWord;
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    Set<Vertex<T>> myVisited = new HashSet<>();
    printSelfLoopersHelper(vertex, myVisited);
  }

  public static <T> void printSelfLoopersHelper(Vertex<T> current, Set<Vertex<T>> visited)
  {
    if (current == null || visited.contains(current)) return;

    visited.add(current);

    for (Vertex<T> neighbor : current.neighbors)
    {
      if (neighbor == current)
      {
        System.out.println(neighbor.data);
      }
      printSelfLoopersHelper(neighbor, visited);
    }
  }

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    Set<Airport> myVisited = new HashSet<>();
    return canReachHelper(start, destination, myVisited);
  }

  public static boolean canReachHelper(Airport start, Airport destination, Set<Airport> visited)
  {
    if (start == destination)
    {
      return true;
    }
    
    visited.add(start);

    for (Airport nextFlight : start.getOutboundFlights())
    {
      if (!visited.contains(nextFlight))
      {
        return canReachHelper(nextFlight, destination, visited);
      }
    }

    return false;
  }

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    Set<T> unreachableSet = new HashSet<>();
    Set<T> myVisited = new HashSet<>();

    if (!graph.keySet().contains(starting)) return graph.keySet();

    myVisited = unreachableHelper(graph, starting, myVisited);

    for (T node : graph.keySet())
    {
      if (!myVisited.contains(node))
      {
        unreachableSet.add(node);
      }
    }

    return unreachableSet;
  }

  public static <T> Set<T> unreachableHelper(Map<T, List<T>> graph, T starting, Set<T> visited)
  {
    if (!visited.contains(starting))
    {
      visited.add(starting);

      for (T neighbor : graph.get(starting))
      {
        unreachableHelper(graph, neighbor, visited); // populate entire visited
      }
    }
    
    return visited;
  }
}
