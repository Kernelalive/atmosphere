package eubrazil.atmosphere.service.transfer;

import java.io.Serializable;

public class OrderedGraphableEdge<T extends Graphable> implements GraphableEdge<T>, Serializable {

  private static final long serialVersionUID = 1L;

  private final T left;
  private final T right;
  private final String id;

  public OrderedGraphableEdge(T left, T right) {

    if (left == null || right == null) {
      throw new IllegalArgumentException();
    }

    if (left.equals(right)) {
      throw new IllegalArgumentException();
    }

    this.left = left;
    this.right = right;

    id = this.left.getGraphKey().concat(this.right.getGraphKey());
  }

  @Override
  public T getLeft() {
    return this.left;
  }

  @Override
  public T getRight() {
    return this.right;
  }

  @Override
  public int compareTo(GraphableEdge<T> o) {
    int res = this.left.getGraphKey().compareTo(o.getLeft().getGraphKey());
    if (res != 0) {
      return res;
    }
    return this.right.getGraphKey().compareTo(o.getRight().getGraphKey());
  }

  @Override
  public String id() {
    return this.id;
  }
}
