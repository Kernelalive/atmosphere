package eubrazil.atmosphere.service.transfer;

public interface GraphableEdge<T extends Graphable> extends Comparable<GraphableEdge<T>> {

  T getLeft();

  T getRight();

  String id();
}
